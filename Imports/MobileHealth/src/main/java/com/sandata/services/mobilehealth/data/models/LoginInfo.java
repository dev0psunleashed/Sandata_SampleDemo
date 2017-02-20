package com.sandata.services.mobilehealth.data.models;

/**
 * Copied from EvvBatch
 * 
 * @author khangle
 */
public class LoginInfo {

    private String host;
    private int port;
    private String userName;
    private String password;

    public LoginInfo(final String host, final int port, final String userName, final String password) {
        this.host = host;
        this.port = port;
        this.userName = userName;
        this.password = password;
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

}
