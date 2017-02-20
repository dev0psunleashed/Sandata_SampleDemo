package com.sandata.lab.rest.billing.model;

import com.sandata.lab.data.model.dl.model.BillingDetail;
import org.springframework.beans.BeanUtils;

public class BillingDetailExt extends BillingDetail {

    private static final long serialVersionUID = 1L;

    public BillingDetailExt(BillingDetail billingDetail) {
            BeanUtils.copyProperties(billingDetail, this);
    }
}
