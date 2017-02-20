package com.sandata.lab.rest.payer.model;

import com.google.gson.annotations.SerializedName;
import com.sandata.lab.data.model.base.BaseObject;
import org.apache.cxf.jaxrs.utils.ResourceUtils;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Tom Dornseif
 * Date: 5/16/2016
 * Time: 2:00 PM
 */
public class PayerContractHdr extends BaseObject{
    private static final long serialVersionUID = 1L;
    @SerializedName("BusinessEntityID")
    private String businessEntityID;
    @SerializedName("PayerSK")
    private BigInteger payerSK;
    @SerializedName("PayerID")
    private String payerId;
    @SerializedName("PayerName")
    private String payerName;
    @SerializedName("PayerActiveIndicator")
    private Boolean payerActiveIndicator;
    @SerializedName("ContractList")
    private List<ContractList> contractList;

    public PayerContractHdr(ResultSet resultSet) throws SQLException {
        this.businessEntityID = resultSet.getString("BE_ID");
        this.payerSK = BigInteger.valueOf(resultSet.getLong("PAYER_SK"));
        this.payerId = resultSet.getString("PAYER_ID");
        this.payerName = resultSet.getString("PAYER_NAME");
        this.payerActiveIndicator = resultSet.getBoolean("PAYER_ACTIVE_IND");
        addContract(resultSet);

    }
    public void addContract(ResultSet resultSet) throws SQLException {
        ContractList contractList = new ContractList();
        contractList.setPayerContractSK(BigInteger.valueOf(resultSet.getLong("PAYER_CONTR_SK")));
        contractList.setContractID(resultSet.getString("CONTR_ID"));
        contractList.setContractDescription(resultSet.getString("CONTR_DESC"));
        contractList.setContractActiveIndicator(resultSet.getBoolean("CONTR_ACTIVE_IND"));
        contractList.setPayerEffectiveDate(resultSet.getTimestamp("PAYER_EFF_DATE"));
        this.getContractList().add(contractList);
    }
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getBusinessEntityID() {
        return businessEntityID;
    }

    public void setBusinessEntityID(String businessEntityID) {
        this.businessEntityID = businessEntityID;
    }

    public BigInteger getPayerSK() {
        return payerSK;
    }

    public void setPayerSK(BigInteger payerSK) {
        this.payerSK = payerSK;
    }

    public String getPayerId() {
        return payerId;
    }

    public void setPayerId(String payerId) {
        this.payerId = payerId;
    }

    public String getPayerName() {
        return payerName;
    }

    public void setPayerName(String payerName) {
        this.payerName = payerName;
    }

    public Boolean getPayerActiveIndicator() {
        return payerActiveIndicator;
    }

    public void setPayerActiveIndicator(Boolean payerActiveIndicator) {
        this.payerActiveIndicator = payerActiveIndicator;
    }

    public List<ContractList> getContractList() {
        if(contractList == null) {
            contractList = new ArrayList<>();
        }
        return contractList;
    }


    private class ContractList extends BaseObject{
        private static final long serialVersionUID = 1L;

        @SerializedName("PayerContractSK")
        private BigInteger payerContractSK;
        @SerializedName("ContractID")
        private String contractID;
        @SerializedName("ContractDescription")
        private String contractDescription;
        @SerializedName("PayerEffectiveDate")
        private Date payerEffectiveDate;
        @SerializedName("ContractActiveindicator")
        private Boolean contractActiveIndicator;



        public BigInteger getPayerContractSK() {
            return payerContractSK;
        }

        public void setPayerContractSK(BigInteger payerContractSK) {
            this.payerContractSK = payerContractSK;
        }

        public String getContractID() {
            return contractID;
        }

        public void setContractID(String contractID) {
            this.contractID = contractID;
        }

        public String getContractDescription() {
            return contractDescription;
        }

        public void setContractDescription(String contractDescription) {
            this.contractDescription = contractDescription;
        }

        public Date getPayerEffectiveDate() {
            return payerEffectiveDate;
        }

        public void setPayerEffectiveDate(Date payerEffectiveDate) {
            this.payerEffectiveDate = payerEffectiveDate;
        }

        public Boolean getContractActiveIndicator() {
            return contractActiveIndicator;
        }

        public void setContractActiveIndicator(Boolean contractActiveIndicator) {
            this.contractActiveIndicator = contractActiveIndicator;
        }
    }
}
