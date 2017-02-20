package com.sandata.lab.exports.evv.impl;

/**
 * Created with IntelliJ IDEA.
 * User: Tom Dornseif
 * Date: 11/7/2016
 * Time: 9:24 AM
 */
public class CredentialHelper {
    private String username;
    private String password;
    private String account;
    private String description;
    private String bsnEntId;
    private boolean initialized;

    public CredentialHelper() {
           setInitialized(false);
    }

    public void init() {
        this.setInitialized(true);
    }

    public void destroy() {
        this.setInitialized(false);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBsnEntId() {
        return bsnEntId;
    }

    public void setBsnEntId(String bsnEntId) {
        this.bsnEntId = bsnEntId;
    }

    public boolean isInitialized() {
        return initialized;
    }

    public void setInitialized(boolean initialized) {
        this.initialized = initialized;
    }
}
