package com.sandata.lab.data.model.dl.model.extended.contract;

import com.sandata.lab.data.model.dl.model.Contract;
import com.sandata.lab.data.model.dl.model.extended.payroll.PayrollRateMatrixExchange;
import org.springframework.beans.BeanUtils;

import java.util.List;

public class ContractExt extends Contract {

    private static final long serialVersionUID = 1L;

    public ContractExt(Contract contract) {
        BeanUtils.copyProperties(contract, this);

        // manually copy Boolean properties as BeanUtils.copyproperties does not work for Boolean
        // see https://stackoverflow.com/questions/11332820/beanutils-setproperty-not-working-for-boolean-values
        setContractElectronicDataInterchangeSubmittableIndicator(contract.isContractElectronicDataInterchangeSubmittableIndicator());
        setContractHoldBillingIndicator(contract.isContractHoldBillingIndicator());
        setContractSplitBillingAllowedIndicator(contract.isContractSplitBillingAllowedIndicator());

        setContractNoHolidayPayIndicator(contract.isContractNoHolidayPayIndicator());
        setContractEligibilityCheckRequiredIndicator(contract.isContractEligibilityCheckRequiredIndicator());
        setContractConsumerDirectedServiceIndicator(contract.isContractConsumerDirectedServiceIndicator());

        setContractBillingUnitRoundIndicator(contract.isContractBillingUnitRoundIndicator());
        setContractSignatureRequiredIndicator(contract.isContractSignatureRequiredIndicator());
        setContractPatientInsuranceIDRequiredIndicator(contract.isContractPatientInsuranceIDRequiredIndicator());

        setContractActiveIndicator(contract.isContractActiveIndicator());
        setContractNurseNoteRequiredIndicator(contract.isContractNurseNoteRequiredIndicator());
    }

    private List<PayrollRateMatrixExchange> payrollRateMatrixExchangeList;

    public List<PayrollRateMatrixExchange> getPayrollRateMatrixExchangeList() {
        return payrollRateMatrixExchangeList;
    }

    public void setPayrollRateMatrixExchangeList(List<PayrollRateMatrixExchange> payrollRateMatrixExchangeList) {
        this.payrollRateMatrixExchangeList = payrollRateMatrixExchangeList;
    }
}
