package com.sandata.lab.data.model.dl.model.extended.exception;

import org.springframework.beans.BeanUtils;

import com.google.gson.annotations.SerializedName;
import com.sandata.lab.data.model.dl.model.BusinessEntityExceptionList;

public class BusinessEntityExceptionListExt extends BusinessEntityExceptionList {
    private static final long serialVersionUID = -2436443230134262496L;

    @SerializedName("AdditionalSettings")
    protected String additionalSettings;

    @SerializedName("ExceptionName")
    private String exceptionName;

    /**
     * Default constructor
     */
    public BusinessEntityExceptionListExt() {
    }

    /**
     * Constructor
     * @param businessEntityExceptionList
     */
    public BusinessEntityExceptionListExt(BusinessEntityExceptionList businessEntityExceptionList) {
        BeanUtils.copyProperties(businessEntityExceptionList, this);

        // manually copy Boolean properties as BeanUtils.copyproperties does not work for Boolean
        // see https://stackoverflow.com/questions/11332820/beanutils-setproperty-not-working-for-boolean-values
        setExceptionAcknowledgableIndicator(businessEntityExceptionList.isExceptionAcknowledgableIndicator());
        setExceptionNonFixableIndicator(businessEntityExceptionList.isExceptionNonFixableIndicator());
    }

    /**
     * @return the additionalSettings
     */
    public String getAdditionalSettings() {
        return additionalSettings;
    }

    /**
     * @param additionalSettings the additionalSettings to set
     */
    public void setAdditionalSettings(String additionalSettings) {
        this.additionalSettings = additionalSettings;
    }

    /**
     * @return BusinessEntityExceptionList object
     */
    public BusinessEntityExceptionList getBusinessEntityExceptionList() {
        BusinessEntityExceptionList businessEntityExceptionList = new BusinessEntityExceptionList();
        BeanUtils.copyProperties(this, businessEntityExceptionList);

        // manually copy Boolean properties as BeanUtils.copyproperties does not work for Boolean
        // see https://stackoverflow.com/questions/11332820/beanutils-setproperty-not-working-for-boolean-values
        businessEntityExceptionList.setExceptionAcknowledgableIndicator(this.isExceptionAcknowledgableIndicator());
        businessEntityExceptionList.setExceptionNonFixableIndicator(this.isExceptionNonFixableIndicator());

        return businessEntityExceptionList;
    }

    public String getExceptionName() {
        return exceptionName;
    }

    public void setExceptionName(String exceptionName) {
        this.exceptionName = exceptionName;
    }
}
