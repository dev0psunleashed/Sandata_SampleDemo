package com.sandata.lab.rest.ar.impl;

import com.sandata.lab.common.utils.data.money.MoneyUtil;
import com.sandata.lab.data.model.dl.model.custom.AccountsReceivableAutoPostSummary;
import com.sandata.lab.data.model.dl.model.find.AccountsReceivableAutoPostFindResult;

import java.math.RoundingMode;
import java.util.List;

import com.sandata.lab.data.model.dl.model.find.enums.ARPostStatus;
import com.sandata.lab.data.model.dl.model.custom.AccountsReceivableAutoPostDetail;

import com.sandata.lab.data.model.dl.model.InvoiceStatusName;
import com.sandata.lab.data.model.dl.model.find.*;
import com.sandata.lab.data.model.response.Response;


import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

public class MockDataHelper {


    public List<AccountsReceivableAutoPostFindResult> accountsReceivableFindResultList() {


        List<AccountsReceivableAutoPostFindResult> accountsReceivableFindResultList = new ArrayList<>();


        int checkNumber = 1238557665;
        int dateLong = 1467849600;
        BigDecimal checkAmount = new BigDecimal(5000.25);


        for (int i = 0; i < 1278; i++) {
            AccountsReceivableAutoPostFindResult accountsReceivableFindResult = new AccountsReceivableAutoPostFindResult();


            accountsReceivableFindResult.setCheckNumber(String.valueOf(checkNumber++));

            Date date = new Date();

            if (i % 25 == 0) {
                date.setTime(dateLong - 86400);
            }

            accountsReceivableFindResult.setArSk(new BigDecimal(i + 1));

            accountsReceivableFindResult.setCheckDate(date);

            accountsReceivableFindResult.setProviderName("Prime Care");

            accountsReceivableFindResult.setNpi("145975456");

            accountsReceivableFindResult.setPayer("Medicare");

            accountsReceivableFindResult.setCheckReceivedDate(date);

            accountsReceivableFindResult.setTaxId("123555321");

            checkAmount.add(new BigDecimal(i * 1.5 + 675));
            accountsReceivableFindResult.setCheckAmount(checkAmount);

            accountsReceivableFindResult.setPLBPresent(true);

            accountsReceivableFindResult.setEraReceivedDate(date);


            accountsReceivableFindResult.setArPostStatus(ARPostStatus.OPEN);

            accountsReceivableFindResultList.add(accountsReceivableFindResult);
        }

        return accountsReceivableFindResultList;
    }


    public List<AccountsReceivableAutoPostDetail> accountsReceivableAutoPostDetailList() {

        List<AccountsReceivableAutoPostDetail> accountsReceivableAutoPostDetails = new ArrayList<>();

        String invoiceNum = UUID.randomUUID().toString();

        int dateLong = 1467849600;

        for (int i = 0; i < 424; i++) {

            AccountsReceivableAutoPostDetail accountsReceivableAutoPostDetail = new AccountsReceivableAutoPostDetail();
            accountsReceivableAutoPostDetail.setPatientLastName("Smith");
            accountsReceivableAutoPostDetail.setPatientFirstName("Jane");

            accountsReceivableAutoPostDetail.setInvoiceNumber(UUID.randomUUID().toString());
            accountsReceivableAutoPostDetail.setBilledAmount(new BigDecimal(i * 300));
            accountsReceivableAutoPostDetail.setPaidAmount(new BigDecimal(i * 250));
            accountsReceivableAutoPostDetail.setAdjustedAmount(new BigDecimal(i * 110));
            accountsReceivableAutoPostDetail.setRemainingAmount(new BigDecimal(i * 500));

            Date date = new Date();

            if (i % 25 == 0) {
                date.setTime(dateLong - (86400 * 4));
            }

            accountsReceivableAutoPostDetail.setFirstDateOfService(date);
            accountsReceivableAutoPostDetail.setLastDateOfService(date);

            accountsReceivableAutoPostDetail.setSandataInvoice(true);

            accountsReceivableAutoPostDetails.add(accountsReceivableAutoPostDetail);
        }

        return accountsReceivableAutoPostDetails;
    }

    public AccountsReceivableAutoPostSummary accountsReceivableAutoPostSummary() {

        AccountsReceivableAutoPostSummary accountsReceivableAutoPostSummary = new AccountsReceivableAutoPostSummary();

        accountsReceivableAutoPostSummary.setAdjustedAmount(new BigDecimal(1000.43).setScale(2, RoundingMode.CEILING));
        accountsReceivableAutoPostSummary.setDeniedAmount(new BigDecimal(54.45).setScale(2, RoundingMode.CEILING));
        accountsReceivableAutoPostSummary.setInvoicePaidPostAmount(new BigDecimal(3200.56).setScale(2, RoundingMode.CEILING));
        accountsReceivableAutoPostSummary.setPlbAmount(new BigDecimal(1232.11).setScale(2, RoundingMode.CEILING));
        accountsReceivableAutoPostSummary.setPlbCode("125446");
        accountsReceivableAutoPostSummary.setPlbDescription("Description");
        accountsReceivableAutoPostSummary.setSandataInvoicesPosted(54);
        accountsReceivableAutoPostSummary.setTotalBilled(new BigDecimal(14654.56).setScale(2, RoundingMode.CEILING));
        accountsReceivableAutoPostSummary.setUnappliedBalance(new BigDecimal(2006.09).setScale(2, RoundingMode.CEILING));
        accountsReceivableAutoPostSummary.setUnappliedBalancePostAmount(new BigDecimal(367.43).setScale(2, RoundingMode.CEILING));
        accountsReceivableAutoPostSummary.setTotalPaid(new BigDecimal(10964.23).setScale(2, RoundingMode.CEILING));

        return accountsReceivableAutoPostSummary;
    }

    public static Response findArTxnBatch(String bsnEntId,
                                          String batchNumber,
                                          String payerName,
                                          String checkNumber,
                                          String checkDepositDateString,
                                          BigDecimal totalAmount,
                                          BigDecimal paymentPostedUnapplied,
                                          BigDecimal totalDenied,
                                          BigDecimal totalPaid,
                                          BigDecimal totalOpen,
                                          Boolean batchPosted,
                                          int page,
                                          int pageSize,
                                          String sortOn,
                                          String direction) throws ParseException {


        List<ArTxnBatchManualPostFindResult> batchFindResultList = new ArrayList<>();

        for (int i = 1; i <= 9; i++) {
            ArTxnBatchManualPostFindResult batchFindResult = new ArTxnBatchManualPostFindResult();

            if (batchNumber != null
                && !batchNumber.isEmpty()) {
                batchFindResult.setBatchNumber(batchNumber);
            } else {
                batchFindResult.setBatchNumber(String.format("%s%s%s%s%s%s%s", i, i, i, i, i, i, i));
            }

            if (checkDepositDateString != null) {
                batchFindResult.setBatchDepositDate(new SimpleDateFormat("MM/dd/yyyy").parse(checkDepositDateString));
            } else {
                batchFindResult.setBatchDepositDate(new SimpleDateFormat("MM/dd/yyyy").parse(String.format("07/0%s/2016", i)));
            }

            if (payerName != null
                && !payerName.isEmpty()) {
                batchFindResult.setBatchPayerName(payerName);
            } else {
                batchFindResult.setBatchPayerName(String.format("Test Payer %s", i));
            }

            if (checkNumber != null
                && !checkNumber.isEmpty()) {
                batchFindResult.setBatchCheckNumber(checkNumber);
            } else {
                batchFindResult.setBatchCheckNumber(String.format("%s%s%s%s%s%s%s", i, i, i, i, i, i, i));
            }

            batchFindResult.setBatchTotalAmount(MoneyUtil.formatCurrency(totalAmount));

            batchFindResult.setBatchUnappliedPaymentPosted(MoneyUtil.formatCurrency(paymentPostedUnapplied));

            batchFindResult.setBatchTotalDenied(MoneyUtil.formatCurrency(totalDenied));

            batchFindResult.setBatchTotalPaid(MoneyUtil.formatCurrency(totalPaid));

            batchFindResult.setBatchTotalOpen(MoneyUtil.formatCurrency(totalOpen));

            batchFindResult.setBatchPosted(batchPosted != null || batchPosted);

            batchFindResultList.add(batchFindResult);
        }

        Response response = new Response();
        response.setData(batchFindResultList);
        response.setPage(page);
        response.setPageSize(pageSize);
        response.setOrderByColumn(sortOn);
        response.setOrderByDirection(direction);
        response.setTotalRows(batchFindResultList.size());

        return response;
    }
}
