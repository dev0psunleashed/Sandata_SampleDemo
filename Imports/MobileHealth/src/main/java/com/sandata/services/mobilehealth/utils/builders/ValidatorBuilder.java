package com.sandata.services.mobilehealth.utils.builders;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sandata.up.commons.exception.SandataRuntimeException;
import com.sandata.services.mobilehealth.data.models.LoginInfo;
import com.sandata.services.mobilehealth.utils.Messaging;
import com.sandata.services.mobilehealth.validators.FTPValidator;
import com.sandata.services.mobilehealth.validators.FileValidator;
import com.sandata.services.mobilehealth.validators.SFTPValidator;
import com.sandata.services.mobilehealth.validators.Validator;

/**
 * Builder class to create Validator instance depending on the endpoint URL (i.e. either FTP/SFTP/File)
 * 
 * @author khangle
 */
public class ValidatorBuilder {
    private static final Logger LOGGER = LoggerFactory.getLogger(ValidatorBuilder.class);

    private static final String FTP_URL_PATTERN_STRING = "([ftp|sftp]://)([A-z0-9_\\-\\.]*)(:)([0-9]*)(/)(.*)(\\?)(.*)(username=)(.*)(&)(password=)(.*)";
    private static final Pattern ftpUrlPattern = Pattern.compile(FTP_URL_PATTERN_STRING);

    private String url;

    private ValidatorBuilder(String url) {
        this.url = url;
    }

    public static ValidatorBuilder createBuilder(String url) {
        return new ValidatorBuilder(url);
    }

    public Validator build(int connectionAttempt, long connectionAttemptDelay) {
        
        // Create validators for FTP or SFTP
        LoginInfo loginInfo = buildLoginInfo(ftpUrlPattern.matcher(url));
        if (loginInfo != null) {
            LOGGER.debug("ValidatorBuilder.build: loginInfo is not null");
            if (url.startsWith(Messaging.Names.COMPONENT_TYPE_FTP.toString())) {
                LOGGER.debug("ValidatorBuilder.build: url is started with {}", Messaging.Names.COMPONENT_TYPE_FTP.toString());
                return new FTPValidator(loginInfo, connectionAttempt, connectionAttemptDelay);
            } else if (url.startsWith(Messaging.Names.COMPONENT_TYPE_SFTP.toString())) {
                LOGGER.debug("ValidatorBuilder.build: url is started with {}", Messaging.Names.COMPONENT_TYPE_SFTP.toString());
                return new SFTPValidator(loginInfo, connectionAttempt, connectionAttemptDelay);
            }
        }

        // Create validator for File
        if (url.startsWith(Messaging.Names.COMPONENT_TYPE_FILE.toString())) {
            LOGGER.debug("ValidatorBuilder.build: url is started with {}", Messaging.Names.COMPONENT_TYPE_FILE.toString());
            return new FileValidator();
        }

        LOGGER.debug("ValidatorBuilder.build: return null");
        throw new SandataRuntimeException("Cannot create validator. Unsupported component for url " + url);
    }

    private LoginInfo buildLoginInfo(Matcher matcher) {
        if (matcher.find()) {
            return new LoginInfo(
                matcher.group(2),
                Integer.parseInt(matcher.group(4)),
                matcher.group(10),
                matcher.group(13).split("&")[0]
            );
        }
        return null;
    }
}
