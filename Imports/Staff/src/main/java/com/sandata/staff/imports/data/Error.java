package com.sandata.staff.imports.data;

public class Error
{
    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public String getUniqueID() {
        return UniqueID;
    }

    public void setUniqueID(String uniqueID) {
        UniqueID = uniqueID;
    }

    String Message;

    String UniqueID;
}
