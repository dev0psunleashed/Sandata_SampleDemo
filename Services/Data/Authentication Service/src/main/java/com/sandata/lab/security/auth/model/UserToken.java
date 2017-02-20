package com.sandata.lab.security.auth.model;

import com.google.gson.annotations.SerializedName;
import com.sandata.lab.data.model.base.BaseObject;
import com.sandata.lab.data.model.dl.model.extended.ApplicationUserExt;
import com.sandata.lab.security.auth.model.forgeRock.AccessToken;

public class UserToken extends BaseObject {

    @SerializedName("AccessToken")
    private AccessToken accessToken;

    @SerializedName("ApplicationUser")
    private ApplicationUserExt applicationUserExt;

    public ApplicationUserExt getApplicationUserExt() {
        return applicationUserExt;
    }

    public void setApplicationUserExt(ApplicationUserExt applicationUserExt) {
        this.applicationUserExt = applicationUserExt;
    }

    public AccessToken getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(AccessToken accessToken) {
        this.accessToken = accessToken;
    }

}
