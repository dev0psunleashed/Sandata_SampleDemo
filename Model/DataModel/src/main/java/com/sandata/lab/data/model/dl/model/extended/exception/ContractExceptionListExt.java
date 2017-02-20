package com.sandata.lab.data.model.dl.model.extended.exception;

import org.springframework.beans.BeanUtils;

import com.google.gson.annotations.SerializedName;
import com.sandata.lab.data.model.dl.model.ContractExceptionList;

public class ContractExceptionListExt extends ContractExceptionList {

    private static final long serialVersionUID = -7229855261950067412L;

    @SerializedName("AdditionalSettings")
    protected String additionalSettings;

    /**
     * Default constructor
     */
    public ContractExceptionListExt() {
        
    }
    
    public ContractExceptionListExt(ContractExceptionList contractExceptionList) {
        BeanUtils.copyProperties(contractExceptionList, this);

        // manually copy Boolean properties as BeanUtils.copyproperties does not work for Boolean
        // see https://stackoverflow.com/questions/11332820/beanutils-setproperty-not-working-for-boolean-values
        setExceptionNonFixableIndicator(contractExceptionList.isExceptionNonFixableIndicator());
        setExceptionAcknowledgableIndicator(contractExceptionList.isExceptionAcknowledgableIndicator());
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
     * @return ContractExceptionList object
     */
    public ContractExceptionList getContractExceptionList() {
        ContractExceptionList contractExceptionList = new ContractExceptionList();
        BeanUtils.copyProperties(this, contractExceptionList);

        // manually copy Boolean properties as BeanUtils.copyproperties does not work for Boolean
        // see https://stackoverflow.com/questions/11332820/beanutils-setproperty-not-working-for-boolean-values
        contractExceptionList.setExceptionNonFixableIndicator(this.isExceptionNonFixableIndicator());
        contractExceptionList.setExceptionAcknowledgableIndicator(this.isExceptionAcknowledgableIndicator());

        return contractExceptionList;
    }
}
