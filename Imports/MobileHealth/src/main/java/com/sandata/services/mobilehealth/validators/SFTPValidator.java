package com.sandata.services.mobilehealth.validators;

import java.util.Vector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
import com.sandata.up.commons.exception.SandataRuntimeException;
import com.sandata.services.mobilehealth.data.models.LoginInfo;
import com.sandata.services.mobilehealth.exception.TargetValidationException;

/**
 * Validator class for using with sFTP connection
 * 
 * @author khangle
 */
public class SFTPValidator extends AbstractRemoteValidator {
    private static final Logger LOGGER = LoggerFactory.getLogger(SFTPValidator.class);

    private static final String SFTP_CHANNEL = "sftp";

    private static final JSch jsch = new JSch();
    private Session session;

    /**
     * Constructor
     * 
     * @param getLoginInfo()
     * @param connectionAttempt
     * @param connectionAttemptDelay
     */
    public SFTPValidator(final LoginInfo loginInfo, int connectionAttempt, long connectionAttemptDelay) {
        super(loginInfo, connectionAttempt, connectionAttemptDelay);
        
        try {
            initSession();
        } catch (JSchException jse) {
            cleanUp();
            throw new SandataRuntimeException("Can not initialize SFTP session", jse);
        }
    }

    /**
     * Method to initialize sFTP connection and will retry a number of <connectionAttempt> times 
     * to acquire connection to sFTP host
     * 
     * @throws JSchException
     */
    private void initSession() throws JSchException {
        session = jsch.getSession(
                getLoginInfo().getUserName(),
                getLoginInfo().getHost(),
                getLoginInfo().getPort());
        session.setConfig("StrictHostKeyChecking", "no");
        session.setPassword(getLoginInfo().getPassword());

        int retries = 0;
        LOGGER.info("[SFTPValidator] Preparing opening Session to " + getLoginInfo().getUserName() + "@" + getLoginInfo().getHost() 
                + " with connectionAttempt=" + getConnectionAttempt() + " and connectionAttemptDelay=" + getConnectionAttemptDelay());
        while (!session.isConnected() && retries < getConnectionAttempt()) {
            session.connect(10000);
            retries++;
            
            // If the session is not yet connected, wait before retrying
            if (!session.isConnected()) {
                LOGGER.warn("[SFTPValidator] Session cannot be opened to " + getLoginInfo().getUserName() + "@" + getLoginInfo().getHost() 
                        + " after " + retries + " attempt(s)");
                
                try {
                    Thread.sleep(getConnectionAttemptDelay());
                } catch (InterruptedException e) {
                    LOGGER.error("[SFTPValidator] Exception occured while waiting to retry opening connection to " 
                            + getLoginInfo().getUserName() + "@" + getLoginInfo().getHost(), e);
                }
            }
        }
        
        // If after retrying <connectionAttempt> times we still could not open connection to sFTP host
        if (!session.isConnected()) {
            session.disconnect();
            throw new SandataRuntimeException(
                    "Session created but was not able to connect to SFTP. Gave up after retrying " + getConnectionAttempt() + " times"
            );
        }
        
        LOGGER.info("[SFTPValidator] Session successfully opened to " + getLoginInfo().getUserName() + "@" + getLoginInfo().getHost());
    }

    /**
     * Method to open a channel to sFTP host using initialized session
     * 
     * @return
     * @throws JSchException
     */
    private ChannelSftp getSftpChannel() throws JSchException {
        // Make sure the session is connected, if not it needs to be re-connected
        if (session == null || !session.isConnected()) {
            LOGGER.warn("[SFTPValidator] Session to " + getLoginInfo().getUserName() + "@" + getLoginInfo().getHost() 
                    + " has been closed or not yet initialized. Will retry initializing Session...");
            initSession();
        }
        
        Channel channel = session.openChannel(SFTP_CHANNEL);
        int retries = 0;
        while (!channel.isConnected() && retries < getConnectionAttempt()) {
            channel.connect();
            retries++;
            
            // If the channel is not yet connected, wait before retrying
            if (!channel.isConnected()) {
                LOGGER.warn("[SFTPValidator] Channel cannot be opened to " + getLoginInfo().getUserName() + "@" + getLoginInfo().getHost() 
                        + " after " + retries + " attempt(s)");
                
                try {
                    Thread.sleep(getConnectionAttemptDelay());
                } catch (InterruptedException e) {
                    LOGGER.error("[SFTPValidator] Exception occured while waiting to retry opening channel to " 
                            + getLoginInfo().getUserName() + "@" + getLoginInfo().getHost(), e);
                }
            }
        }
        
        // If after retrying <connectionAttempt> times we still could not open channel to sFTP host
        if (!channel.isConnected()) {
            throw new SandataRuntimeException(
                    "Channel opened but was not able to connect to SFTP. Gave up after retrying " + getConnectionAttempt() + " times"
            );
        }
        
        return (ChannelSftp) channel;
    }

    @Override
    public boolean isExisted(final String filePath) throws TargetValidationException {
        ChannelSftp channel = null;
        try {
            channel = getSftpChannel();
            Vector files = channel.ls(filePath);
            return files != null && !files.isEmpty();
        } catch (JSchException je) {
            LOGGER.error(je.getMessage(), je);
            throw new TargetValidationException("Can not open channel to SFTP server", je);
        } catch (SftpException sfe) {
            if (sfe.id == ChannelSftp.SSH_FX_NO_SUCH_FILE) {
                return false;
            } else {
                LOGGER.error(sfe.getMessage(), sfe);
                throw new TargetValidationException(
                        String.format("Can not list out the file %s", filePath), sfe
                );
            }
        } finally {
            if (channel != null) {
                channel.disconnect();
            }
        }
    }

    @Override
    public void cleanUp() {
        if (this.session != null) {
            this.session.disconnect();
        }
    }
    
    @Override
    public boolean deleteFile(String filePath) throws TargetValidationException {
        ChannelSftp channel = null;
        try {
            channel = getSftpChannel();
            channel.rm(filePath);
            return true;
        } catch (JSchException je) {
            LOGGER.error(je.getMessage(), je);
            throw new TargetValidationException("Can not open channel to SFTP server", je);
        } catch (SftpException sfe) {
            if(sfe.id == ChannelSftp.SSH_FX_FAILURE) {
                return false;
            } else {
                LOGGER.error(sfe.getMessage(), sfe);
                throw new TargetValidationException(
                        String.format("Can not delete the file %s", filePath), sfe
                );
            }
        } finally {
            if (channel != null) {
                channel.disconnect();
            }
        }
    }

}
