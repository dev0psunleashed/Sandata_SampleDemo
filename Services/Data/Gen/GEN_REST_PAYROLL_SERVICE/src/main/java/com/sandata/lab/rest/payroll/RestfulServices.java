/*
 * Copyright (c) 2015. Sandata Technologies, LLC
 * 26 Harbor Park Drive, Port Washington, NY 11050, 800-544-7263
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of Sandata Technologies, LLC
 * ("Confidential Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered into with
 * Sandata.
 */

package com.sandata.lab.rest.payroll;

import com.sandata.lab.data.model.dl.model.*;
import com.sandata.lab.data.model.dl.model.extended.payroll.PayrollRateMatrixExchange;
import com.sandata.lab.data.model.response.Response;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

public class RestfulServices {

    //region PayrollInput

    @POST
    @Path("/input")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PAYROLL_insertPrInput_PayrollInput(PayrollInput data) {

        return null;
    }

    @PUT
    @Path("/input")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PAYROLL_updatePrInput_PayrollInput(PayrollInput data) {

        return null;
    }

    @DELETE
    @Path("/input/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PAYROLL_deletePrInput_PayrollInput(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/input/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PAYROLL_getPrInput_PayrollInput(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/input")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PAYROLL_getPrInput_PayrollInput(
            @MatrixParam("staff_id") String staffId,
            @MatrixParam("bsn_ent_id") String bsnEntId) {

        return null;
    }
    //endregion

    //region PayrollOutput

    @POST
    @Path("/output")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PAYROLL_insertPrOutput_PayrollOutput(PayrollOutput data) {

        return null;
    }

    @PUT
    @Path("/output")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PAYROLL_updatePrOutput_PayrollOutput(PayrollOutput data) {

        return null;
    }

    @DELETE
    @Path("/output/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PAYROLL_deletePrOutput_PayrollOutput(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/output/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PAYROLL_getPrOutput_PayrollOutput(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/output")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PAYROLL_getPrOutput_PayrollOutput(
            @MatrixParam("timesheet_summary_sk") String timesheetSummarySk,
            @MatrixParam("staff_id") String staffID,
            @MatrixParam("bsn_ent_id") String bsnEntID) {

        return null;
    }
    //endregion

    //region PayrollInputTaxDetail

    @POST
    @Path("/tax")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PAYROLL_insertPrInputTaxDet_PayrollInputTaxDetail(PayrollInputTaxDetail data) {

        return null;
    }

    @PUT
    @Path("/tax")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PAYROLL_updatePrInputTaxDet_PayrollInputTaxDetail(PayrollInputTaxDetail data) {

        return null;
    }

    @DELETE
    @Path("/tax/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PAYROLL_deletePrInputTaxDet_PayrollInputTaxDetail(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/tax/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PAYROLL_getPrInputTaxDet_PayrollInputTaxDetail(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/tax")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PAYROLL_getPrInputTaxDet_PayrollInputTaxDetail(
            @MatrixParam("payroll_input_sk") String payrollInputSk,
            @MatrixParam("bsn_ent_id") String bsnEntID) {

        return null;
    }
    //endregion

    //region PayrollInputEarningDetail

    @POST
    @Path("/earnings")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PAYROLL_insertPrInputEarnDet_PayrollInputEarningDetail(PayrollInputEarningDetail data) {

        return null;
    }

    @PUT
    @Path("/earnings")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PAYROLL_updatePrInputEarnDet_PayrollInputEarningDetail(PayrollInputEarningDetail data) {

        return null;
    }

    @DELETE
    @Path("/earnings/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PAYROLL_deletePrInputEarnDet_PayrollInputEarningDetail(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/earnings/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PAYROLL_getPrInputEarnDet_PayrollInputEarningDetail(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/earnings")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PAYROLL_getPrInputEarnDet_PayrollInputEarningDetail(
            @MatrixParam("payroll_input_sk") String payrollInputSk,
            @MatrixParam("bsn_ent_id") String bsnEntID) {

        return null;
    }
    //endregion

    //region PayrollInputDeductionDetail

    @POST
    @Path("/deductions")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PAYROLL_insertPrInputDedDet_PayrollInputDeductionDetail(PayrollInputDeductionDetail data) {

        return null;
    }

    @PUT
    @Path("/deductions")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PAYROLL_updatePrInputDedDet_PayrollInputDeductionDetail(PayrollInputDeductionDetail data) {

        return null;
    }

    @DELETE
    @Path("/deductions/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PAYROLL_deletePrInputDedDet_PayrollInputDeductionDetail(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/deductions/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PAYROLL_getPrInputDedDet_PayrollInputDeductionDetail(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/deductions")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PAYROLL_getPrInputDedDet_PayrollInputDeductionDetail(
            @MatrixParam("payroll_input_sk") String payrollInputSk,
            @MatrixParam("bsn_ent_id") String bsnEntID) {

        return null;
    }
    //endregion

    //region PayrollRateMatrixExchange

    @POST
    @Path("/rate/matrix")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PAYROLL_insertPrRateMatrixExchange_PayrollRateMatrixExchange(PayrollRateMatrixExchange data) {

        return null;
    }

    @PUT
    @Path("/rate/matrix")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PAYROLL_updatePrRateMatrixExchange_PayrollRateMatrixExchange(PayrollRateMatrixExchange data) {

        return null;
    }

    @DELETE
    @Path("/rate/matrix")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    public final javax.ws.rs.core.Response PKG_PAYROLL_deletePrRateMatrixExchange_PayrollRateMatrixExchange(
            @MatrixParam("bsn_ent_id") String bsnEntId,
            @MatrixParam("payer_id") String payerId,
            @MatrixParam("contract_id") String contractId,
            @MatrixParam("service_name") String serviceName,
            @MatrixParam("rate_type") String rateType) {

        return null;
    }

    @GET
    @Path("/rate/matrix")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final javax.ws.rs.core.Response PKG_PAYROLL_getPrRateMatrixExchange_PayrollRateMatrixExchange(
                    @MatrixParam("bsn_ent_id") String bsnEntId,
                    @MatrixParam("payer_id") String payerId,
                    @MatrixParam("contract_id") String contractId,
                    @MatrixParam("service_name") String serviceName,
                    @MatrixParam("rate_type") String rateType) {

        return null;
    }

    //endregion

    //region Custom

    @GET
    @Path("/detail/find")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PAYROLL_findPayrollDetail_Response(
            @MatrixParam("staff_first_name") String staffFirstName,
            @MatrixParam("staff_last_name") String staffLastName,
            @MatrixParam("staff_id") String staffId,
            @MatrixParam("pay_hours") String payHours,
            @MatrixParam("pay_hours_filter") String payHoursFilter,
            @MatrixParam("service") String service,
            @MatrixParam("from_date_time") String fromDate,
            @MatrixParam("to_date_time") String toDate,
            @MatrixParam("check") String checkNumber,
            @MatrixParam("check_amount") String checkAmount,
            @MatrixParam("check_date") String check_date,
            @MatrixParam("pay_rate") String payRate,
            @DefaultValue("ALL") @MatrixParam("status") String status,
            @DefaultValue("1") @MatrixParam("page") int page,
            @DefaultValue("10") @MatrixParam("page_size") int pageSize,
            @DefaultValue("ln") @MatrixParam("sort_on") String sortOn,
            @DefaultValue("ASC") @MatrixParam("direction") String direction,
            @MatrixParam("bsn_ent_id") String businessEntityId,
            @MatrixParam("patient_id") String patientId,
            @MatrixParam("patient_first_name") String patientFirstName,
            @MatrixParam("patient_last_name") String patientLastName,
            @MatrixParam("rate_type") String rateType) {
        return null;
    }

    @GET
    @Path("/detail")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PAYROLL_getPayrollDetail_Response(
            @MatrixParam("payroll_sk") long payrollSk,
            @MatrixParam("bsn_ent_id") String businessEntityId) {
        return null;
    }
    //endregion

    @POST
    @Path("/submit/{bsn_ent_id}")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PAYROLL_submitPayroll(@PathParam("bsn_ent_id") String data) {

        return null;
    }
}
