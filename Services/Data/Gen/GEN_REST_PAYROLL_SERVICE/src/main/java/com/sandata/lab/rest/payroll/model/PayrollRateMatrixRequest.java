package com.sandata.lab.rest.payroll.model;

import com.sandata.lab.data.model.base.BaseObject;
import com.sandata.lab.data.model.dl.model.ServiceName;

/**
 * Models the request to perform CRUD operations on PayrollMatrix.
 * <p/>
 *
 * @author David Rutgos
 */
public class PayrollRateMatrixRequest extends BaseObject {

    private static final long serialVersionUID = 1L;

    private String businessEntityId;
    private String payerId;
    private String contractId;
    private ServiceName serviceName;
    private String rateTypeName;

    public String getBusinessEntityId() {
        return businessEntityId;
    }

    public void setBusinessEntityId(String businessEntityId) {
        this.businessEntityId = businessEntityId;
    }

    public String getPayerId() {
        return payerId;
    }

    public void setPayerId(String payerId) {
        this.payerId = payerId;
    }

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public ServiceName getServiceName() {
        return serviceName;
    }

    public void setServiceName(ServiceName serviceName) {
        this.serviceName = serviceName;
    }

    public String getRateTypeName() {
        return rateTypeName;
    }

    public void setRateTypeName(String rateTypeName) {
        this.rateTypeName = rateTypeName;
    }
}
