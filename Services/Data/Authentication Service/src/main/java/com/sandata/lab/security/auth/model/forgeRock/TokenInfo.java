package com.sandata.lab.security.auth.model.forgeRock;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class TokenInfo implements Serializable {

    @SerializedName("access_token")
    private String accessToken;

    @SerializedName("scope")
    private List<String> scope;

    @SerializedName("expires_in")
    private long expiresIn;

    @SerializedName("SandataGUID")
    private String sandataGuid;

    @SerializedName("Persona")
    private String persona;

    @SerializedName("cn")
    private String cn;

    @SerializedName("token_type")
    private String tokenType;

    @SerializedName("o")
    private String organization;

    @SerializedName("ou")
    private String organizationalUnit;

    @SerializedName("lastLoginTime")
    private String lastLoginTime;

    @SerializedName("grant_type")
    private String grantType;

    @SerializedName("realm")
    private String realm;

    @SerializedName("isMemberOf")
    private String isMemberOf;

    @SerializedName("email")
    private String email;

    @SerializedName("error")
    private String error;

    @SerializedName("error_description")
    private String errorDescription;


    @SerializedName("BEID")
    String businessEntityID;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public List<String> getScope() {
        return scope;
    }

    public void setScope(List<String> scope) {
        this.scope = scope;
    }

    public long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(long expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getSandataGuid() {
        return sandataGuid;
    }

    public void setSandataGuid(String sandataGuid) {
        this.sandataGuid = sandataGuid;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }

    public String getPersona() {
        return persona;
    }

    public void setPersona(String persona) {
        this.persona = persona;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCn() {
        return cn;
    }

    public void setCn(String cn) {
        this.cn = cn;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getOrganizationalUnit() {
        return organizationalUnit;
    }

    public void setOrganizationalUnit(String organizationalUnit) {
        this.organizationalUnit = organizationalUnit;
    }

    public String getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(String lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getGrantType() {
        return grantType;
    }

    public void setGrantType(String grantType) {
        this.grantType = grantType;
    }

    public String getRealm() {
        return realm;
    }

    public void setRealm(String realm) {
        this.realm = realm;
    }

    public String getIsMemberOf() {
        return isMemberOf;
    }

    public void setIsMemberOf(String isMemberOf) {
        this.isMemberOf = isMemberOf;
    }

    public String getBusinessEntityID() {
        return businessEntityID;
    }

    public void setBusinessEntityID(String businessEntityID) {
        this.businessEntityID = businessEntityID;
    }
}
