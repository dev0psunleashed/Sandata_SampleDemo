package com.sandata.lab.rest.sched.model;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.data.model.dl.model.Authorization;
import com.sandata.lab.data.model.dl.model.Contract;
import com.sandata.lab.data.model.dl.model.Order;

import org.springframework.beans.BeanUtils;

/**
 * Created by khangle on 10/19/2016.
 */
public class AuthorizationAdapter extends Authorization {

    private static final long serialVersionUID = 1L;
    
    private boolean isOrder;
    private List<Contract> contract;
    
    /**
     * this is the Equivalence in Minutes for SVC_UNIT_NAME = UNIT
     */
    private BigInteger equivalence;
    
    /**
     * Constructor that accepts an instance of <code>Authorization</code> or <code>Order</code>
     *
     * @param object
     */
    public AuthorizationAdapter(final Object object) {
        if (object instanceof Authorization) {
            isOrder = false;
            BeanUtils.copyProperties(object, this);

        } else if (object instanceof Order) {
            isOrder = true;
            fromOrder((Order)object);

        } else {
            throw new SandataRuntimeException("Unsupported object type");
        }
    }

    public boolean isOrder() {
        return isOrder;
    }

    /**
     * Copy properties from Order instance
     *
     * @param order
     */
    private void fromOrder(Order order) {
        setAuthorizationSK(order.getOrderSK());
        setAuthorizationParentSK(order.getOrderParentSK());
        setAuthorizationID(null);   // An Order doesn't have AuthId
        setRecordCreateTimestamp(order.getRecordCreateTimestamp());
        setRecordUpdateTimestamp(order.getRecordUpdateTimestamp());
        setRecordEffectiveTimestamp(order.getRecordEffectiveTimestamp());
        setRecordTerminationTimestamp(order.getRecordTerminationTimestamp());
        setRecordCreatedBy(order.getRecordCreatedBy());
        setRecordUpdatedBy(order.getRecordUpdatedBy());
        setChangeReasonMemo(order.getChangeReasonMemo());
        setCurrentRecordIndicator(order.isCurrentRecordIndicator());
        setChangeVersionID(order.getChangeVersionID());
        setBusinessEntityID(order.getBusinessEntityID());
        setPatientID(order.getPatientID());
        setPayerID(order.getPayerID());
        setOrderSK(order.getOrderSK());
        setAuthorizationIssuedDate(order.getOrderIssuedDate());
        setAuthorizationStartTimestamp(order.getOrderStartTimestamp());
        setAuthorizationEndTimestamp(order.getOrderEndTimestamp());
        setAuthorizationComment(order.getOrderComment());
        setAuthorizationLimitTypeName(order.getOrderLimitTypeName());
        setAuthorizationServiceUnitName(order.getOrderServiceUnitName());
        setAuthorizationLimit(order.getOrderLimit());
        setAuthorizationLimitTotal(order.getOrderLimitTotal());
        setAuthorizationLimitDay1(order.getOrderLimitDay1());
        setAuthorizationLimitStartTimeDay1(order.getOrderLimitStartTimeDay1());
        setAuthorizationLimitEndTimeDay1(order.getOrderLimitEndTimeDay1());
        setAuthorizationLimitDay2(order.getOrderLimitDay2());
        setAuthorizationLimitStartTimeDay2(order.getOrderLimitStartTimeDay2());
        setAuthorizationLimitEndTimeDay2(order.getOrderLimitEndTimeDay2());
        setAuthorizationLimitDay3(order.getOrderLimitDay3());
        setAuthorizationLimitStartTimeDay3(order.getOrderLimitStartTimeDay3());
        setAuthorizationLimitEndTimeDay3(order.getOrderLimitEndTimeDay3());
        setAuthorizationLimitDay4(order.getOrderLimitDay4());
        setAuthorizationLimitStartTimeDay4(order.getOrderLimitStartTimeDay4());
        setAuthorizationLimitEndTimeDay4(order.getOrderLimitEndTimeDay4());
        setAuthorizationLimitDay5(order.getOrderLimitDay5());
        setAuthorizationLimitStartTimeDay5(order.getOrderLimitStartTimeDay5());
        setAuthorizationLimitEndTimeDay5(order.getOrderLimitEndTimeDay5());
        setAuthorizationLimitDay6(order.getOrderLimitDay6());
        setAuthorizationLimitStartTimeDay6(order.getOrderLimitStartTimeDay6());
        setAuthorizationLimitEndTimeDay6(order.getOrderLimitEndTimeDay6());
        setAuthorizationLimitDay7(order.getOrderLimitDay7());
        setAuthorizationLimitStartTimeDay7(order.getOrderLimitStartTimeDay7());
        setAuthorizationLimitEndTimeDay7(order.getOrderLimitEndTimeDay7());
    }
    
    public List<Contract> getContract() {
        if (this.contract == null) {
            return new ArrayList<>();
        }
        
        return this.contract;
    }

    public BigInteger getEquivalence() {
        return equivalence;
    }

    public void setEquivalence(BigInteger equivalence) {
        this.equivalence = equivalence;
    }
}
