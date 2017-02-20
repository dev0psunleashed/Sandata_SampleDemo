package com.sandata.lab.data.model.dl.model.extended;

import com.google.gson.annotations.SerializedName;
import com.sandata.lab.data.model.dl.model.PayrollOutput;
import com.sandata.lab.data.model.dl.model.ServiceName;

import javax.xml.bind.annotation.XmlAttribute;
import java.math.BigDecimal;

public class PayrollOutputExt extends PayrollOutput {

    private static final long serialVersionUID = 1L;


    protected String staffTaxpayerIdentificationNumber;

    protected ServiceName staffPositionName;

    protected BigDecimal staffRateAmount;

    protected String staffFirstName;

    protected String staffLastName;

    protected String lineOfBusiness;

    public String getStaffLastName() {
        return staffLastName;
    }

    public void setStaffLastName(String staffLastName) {
        this.staffLastName = staffLastName;
    }

    public String getStaffFirstName() {
        return staffFirstName;
    }

    public void setStaffFirstName(String staffFirstName) {
        this.staffFirstName = staffFirstName;
    }

    public String getStaffTaxpayerIdentificationNumber() {
        return staffTaxpayerIdentificationNumber;
    }

    public void setStaffTaxpayerIdentificationNumber(String staffTaxpayerIdentificationNumber) {
        this.staffTaxpayerIdentificationNumber = staffTaxpayerIdentificationNumber;
    }

    public ServiceName getStaffPositionName() {
        return staffPositionName;
    }

    public void setStaffPositionName(ServiceName staffPositionName) {
        this.staffPositionName = staffPositionName;
    }

    public BigDecimal getStaffRateAmount() {
        return staffRateAmount;
    }

    public void setStaffRateAmount(BigDecimal staffRateAmount) {
        this.staffRateAmount = staffRateAmount;
    }

    public String getLineOfBusiness() {
        return lineOfBusiness;
    }

    public void setLineOfBusiness(String lineOfBusiness) {
        this.lineOfBusiness = lineOfBusiness;
    }
}
