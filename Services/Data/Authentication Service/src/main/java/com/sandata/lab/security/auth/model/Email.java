package com.sandata.lab.security.auth.model;

import com.google.gson.annotations.SerializedName;
import com.sandata.lab.data.model.base.BaseObject;

public class Email extends BaseObject {

    private static final long serialVersionUID = 1L;

    @SerializedName("Subject")
    private String subject;

    @SerializedName("Body")
    private String body;



    @SerializedName("Type")
    private String type;

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
