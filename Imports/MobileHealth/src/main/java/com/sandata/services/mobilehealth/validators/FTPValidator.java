package com.sandata.services.mobilehealth.validators;

import java.io.IOException;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sandata.up.commons.exception.SandataRuntimeException;
import com.sandata.services.mobilehealth.data.models.LoginInfo;
import com.sandata.services.mobilehealth.exception.TargetValidationException;

/**
 * Validator class for using with FTP connection
 * 
 * @author khangle
 */
public class FTPValidator extends AbstractRemoteValidator {
    private static final Logger LOGGER = LoggerFactory.getLogger(FTPValidator.class);

    /**
     * Constructor
     * 
     * @param loginInfo
     * @param connectionAttempt
     * @param connectionAttemptDelay
     */
    public FTPValidator(final LoginInfo loginInfo, int connectionAttempt, long connectionAttemptDelay) {
        super(loginInfo, connectionAttempt, connectionAttemptDelay);
    }

    /**
     * Method to open connection to FTP host then login with credentials
     * 
     * @return
     */
    private FTPClient initClient() {
        FTPClient client = new FTPClient();
        
        // Try opening connection to FTP host
        try {
            int retries = 0;
            LOGGER.info("[FTPValidator] Preparing opening connection to " + getLoginInfo().getUserName() + "@" + getLoginInfo().getHost() 
                    + " with connectionAttempt=" + getConnectionAttempt() + " and connectionAttemptDelay=" + getConnectionAttemptDelay());
            
            while (!client.isConnected() && retries < getConnectionAttempt()) {
                client.connect(getLoginInfo().getHost(), getLoginInfo().getPort());
                retries++;
                
                // If the connection is not yet connected, wait before retrying
                if (!client.isConnected()) {
                    LOGGER.warn("[FTPValidator] Connection cannot be opened to " + getLoginInfo().getUserName() + "@" + getLoginInfo().getHost() 
                            + " after " + retries + " attempt(s)");
                    
                    try {
                        Thread.sleep(getConnectionAttemptDelay());
                    } catch (InterruptedException e) {
                        LOGGER.error("[FTPValidator] Exception occured while waiting to retry opening connection to " 
                                + getLoginInfo().getUserName() + "@" + getLoginInfo().getHost(), e);
                    }
                }
            }
            
            
        } catch (SocketException se) {
            LOGGER.error("Can not connect to FTP due to socket error", se);
            throw new SandataRuntimeException("Can not connect to FTP", se);
        } catch (IOException ioe) {
            LOGGER.error("Can not connect to FTP due to IO error", ioe);
            throw new SandataRuntimeException("Can not connect to FTP", ioe);
        }
        
        // Try logging in to FTP host with credentials
        try {
            boolean isLogin = client.login(getLoginInfo().getUserName(), getLoginInfo().getPassword());
            if (!isLogin) {
                throw new SandataRuntimeException("Failed to login to FTP");
            }
        } catch (IOException ioe) {
            LOGGER.error("Can not login to FTP due to IO error", ioe);
            throw new SandataRuntimeException("Can not login to FTP", ioe);
        }
        
        return client;
    }

    @Override
    public boolean isExisted(final String filePath) throws TargetValidationException {
        FTPClient client = initClient();
        try {
            FTPFile[] files = client.listFiles(filePath);
            return files != null && files.length == 1;
        } catch (IOException ioe) {
            LOGGER.error("Can not list out file due to IO error", ioe);
            throw new SandataRuntimeException(
                    String.format("Can not list out the file %s", filePath), ioe
            );
        } finally {
            try {
                client.logout();
                client.disconnect();
            } catch (IOException e) {
                LOGGER.error("Error occured while closing FTPClient " + e);
            }
        }
    }

    @Override
    public void cleanUp() {
        // FTPClient resource is closed right after usage so nothing needs to be done here
    }

    @Override
    public boolean deleteFile(final String filePath) throws TargetValidationException {
        FTPClient client = initClient();
        
        try {
            return client.deleteFile(filePath);
        } catch (IOException ioe) {
            LOGGER.error("Can not delete file due to IO error", ioe);
            throw new SandataRuntimeException(
                    String.format("Can not delete the file %s", filePath), ioe);
        } finally {
            try {
                client.logout();
                client.disconnect();
            } catch (IOException e) {
                LOGGER.error("Error occured while closing FTPClient " + e);
            }
        }
    }
}
