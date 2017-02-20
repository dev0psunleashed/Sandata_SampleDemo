package com.sandata.services.mobilehealth.validators;

import com.sandata.services.mobilehealth.data.models.LoginInfo;

/**
 * Base Validator that defines common properties used for remote connections (i.e. FTP, sFTP)
 * 
 * @author khangle
 */
public abstract class AbstractRemoteValidator implements Validator {

    /* Login info for opening remote connection */
    private LoginInfo loginInfo;
    
    /* Number of attempt to retry open connection */
    private int connectionAttempt = 3;
    
    /* Time in miliseconds  for waiting before retrying */
    private long connectionAttemptDelay = 1000;

    /**
     * Constructor
     * 
     * @param loginInfo
     * @param connectionAttempt
     * @param connectionAttemptDelay
     */
    public AbstractRemoteValidator(final LoginInfo loginInfo, int connectionAttempt, long connectionAttemptDelay) {
        this.loginInfo = loginInfo;
        this.connectionAttempt = connectionAttempt;
        this.connectionAttemptDelay = connectionAttemptDelay;
    }
    
    public int getConnectionAttempt() {
        return connectionAttempt;
    }

    public void setConnectionAttempt(int connectionAttempt) {
        this.connectionAttempt = connectionAttempt;
    }

    public long getConnectionAttemptDelay() {
        return connectionAttemptDelay;
    }

    public void setConnectionAttemptDelay(long connectionAttemptDelay) {
        this.connectionAttemptDelay = connectionAttemptDelay;
    }

    public LoginInfo getLoginInfo() {
        return loginInfo;
    }

    public void setLoginInfo(LoginInfo loginInfo) {
        this.loginInfo = loginInfo;
    }

}
