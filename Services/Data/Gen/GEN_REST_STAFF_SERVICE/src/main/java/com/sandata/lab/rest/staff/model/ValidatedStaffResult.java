/**
 * 
 */
package com.sandata.lab.rest.staff.model;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;

/**
 * @author huyvo
 *
 */
public class ValidatedStaffResult {

    @SerializedName("MessageLevel")
    private MessageLevel messageLevel;
    
    @SerializedName("ErrorMessage")
    private String errorMessage;
    
    @SerializedName("ExistedStaff")
    private List<StaffPromptInformation> existedStaffs;
    
    public MessageLevel getMessageLevel() {
        return messageLevel;
    }

    public void setMessageLevel(MessageLevel messageLevel) {
        this.messageLevel = messageLevel;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public List<StaffPromptInformation> getExistedStaffs() {
        if (this.existedStaffs == null) {
            this.existedStaffs = new ArrayList<>();
        }
        
        return this.existedStaffs;
    }



    public enum MessageLevel {
        /**
         * not allow to update/create
         */
        PREVENT,
        /**
         * warn but allow to update/create
         */
        WARN,
        /**
         * all validated fields are valid and allowed to be updated/created
         */
        ALLOW;
        
        public String value() {
            return name();
        }
        
        public static MessageLevel fromValue(String v) {
            return valueOf(v);
        }
    }
}
