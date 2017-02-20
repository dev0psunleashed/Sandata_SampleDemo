package com.sandata.lab.data.model.dl.model.find;

import com.google.gson.annotations.SerializedName;
import com.sandata.lab.data.model.Adapter1;
import com.sandata.lab.data.model.Adapter2;
import com.sandata.lab.data.model.dl.annotation.Mapping;
import com.sandata.lab.data.model.dl.model.AccountsReceivable;
import com.sandata.lab.data.model.dl.model.AccountsReceivableTransactionBatchDetail;
import com.sandata.lab.data.model.dl.model.PaymentTypeQualifier;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AccountsReceivableTransactionBatchDetailExt extends AccountsReceivableTransactionBatchDetail {

    private static final long serialVersionUID = 1L;

    @SerializedName("PaymentBalance")
    private BigDecimal paymentBalance;

    public BigDecimal getPaymentBalance() {
        return paymentBalance;
    }

    public void setPaymentBalance(BigDecimal paymentBalance) {
        this.paymentBalance = paymentBalance;
    }

}
