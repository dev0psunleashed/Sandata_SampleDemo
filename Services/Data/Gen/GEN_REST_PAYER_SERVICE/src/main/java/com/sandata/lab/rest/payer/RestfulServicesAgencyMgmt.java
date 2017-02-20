package com.sandata.lab.rest.payer;

import com.sandata.lab.data.model.dl.model.*;
import com.sandata.lab.data.model.response.Response;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Created with IntelliJ IDEA.
 * User: Tom Dornseif
 * Date: 5/14/2016
 * Time: 2:08 PM
 */
public class RestfulServicesAgencyMgmt {

    @POST
    @Path("/am/payer/")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PAYER_insertPayer_Payer(Payer data) {

        return null;
    }

    @PUT
    @Path("/am/payer/")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PAYER_updatePayer_Payer(Payer data) {

        return null;
    }

    @DELETE
    @Path("/am/payer/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PAYER_deletePayer_Payer(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/am/payer/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PAYER_getPayer_Payer(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }
    
    

    @GET
    @Path("/am/payer/contracts/payer/{sequence_key}")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_getPayerContractHeadersByPayer_PayerContractHdr(@PathParam("sequence_key") long sequenceKey,
                                                               @DefaultValue("1") @MatrixParam("page") int page,
                                                               @DefaultValue("100") @MatrixParam("page_size") int pageSize) {

        return null;
    }

    @GET
    @Path("/am/payer/")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_getPayerByBsnEntAndPayer_Payer(
            @MatrixParam("payer_id") String payerId,
            @MatrixParam("bsn_ent_id") String bsnEntId ) {

        return null;
    }


    @POST
    @Path("/am/contract")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_CONTRACT_insertContr_Contract(Contract data) {

        return null;
    }

    @PUT
    @Path("/am/contract")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_CONTRACT_updateContr_Contract(Contract data) {

        return null;
    }

    @DELETE
    @Path("/am/contract/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_CONTRACT_deleteContr_Contract(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/am/contract/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_CONTRACT_getContr_Contract(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    //=======================================
    //Agency Management

    @POST
    @Path("/am/payer/lob/")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_insertPayerLineOfBusinessList_PayerLineOfBusinessList(PayerLineOfBusinessList data) {

        return null;
    }

    @PUT
    @Path("/am/payer/lob/")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_updatePayerLineOfBusinessList_PayerLineOfBusinessList(PayerLineOfBusinessList data) {

        return null;
    }

    @DELETE
    @Path("/am/payer/lob/{sequence_key}")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_deletePayerLobLst_PayerLineOfBusinessList(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/am/payer/lob/{sequence_key}")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_getPayerLobLst_PayerLineOfBusinessList(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/am/payer/lob/")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_getPayerLineOfBusinessListByBsnEnt_PayerLineOfBusinessList(
            @MatrixParam("payer_id") String payerId,
            @MatrixParam("bsn_ent_id") String bsnEntId ) {

        return null;
    }

    //Services Allowed
    @GET
    @Path("/am/payer/svc/{sequence_key}")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_getPayerSvcLst_PayerServiceList(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/am/payer/svc/")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_getPayerSvcLstBsnEntPayer_PayerServiceList(
            @MatrixParam("payer_id") String payerId,
            @MatrixParam("bsn_ent_id") String bsnEntId ) {
        return null;
    }


    @POST
    @Path("/am/payer/svc/")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_insertPayerSvcLst_PayerServiceList(PayerServiceList data) {

        return null;
    }

    @PUT
    @Path("/am/payer/svc/")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_updatePayerSvcLst_PayerServiceList(PayerServiceList data) {

        return null;
    }

    @DELETE
    @Path("/am/payer/svc/{sequence_key}")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_deletePayerSvcLst_PayerServiceList(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    //Rate Type Allowed
    @GET
    @Path("/am/payer/rate/type/{sequence_key}")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_getPayerRateTypLst_PayerRateTypeList(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/am/payer/rate/type/")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_getPayerRateTypLstBsnEntPayer_PayerRateTypeList(
            @MatrixParam("payer_id") String payerId,
            @MatrixParam("bsn_ent_id") String bsnEntId ) {

        return null;
    }

    @POST
    @Path("/am/payer/rate/type/")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_insertPayerRateTypLst_PayerRateTypeList(PayerRateTypeList data) {

        return null;
    }

    @PUT
    @Path("/am/payer/rate/type/")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_updatePayerRateTypLst_ayerRateTypeList(PayerRateTypeList data) {

        return null;
    }

    @DELETE
    @Path("/am/payer/rate/type/{sequence_key}")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_deletePayerRateTypLst_PayerRateTypeList(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    //Billing Code
    @GET
    @Path("/am/payer/billing/code/{sequence_key}")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_LOOKUP_getPayerBillingCodeLkup_PayerBillingCodeLookup(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/am/payer/billing/code/")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_LOOKUP_getPayerBillingCodeLstByBsnEntPayer_PayerBillingCodeLookup(
            @MatrixParam("payer_id") String payerId,
            @MatrixParam("bsn_ent_id") String bsnEntId ) {

        return null;
    }

    @POST
    @Path("/am/payer/billing/code/")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_LOOKUP_insertPayerBillingCodeLkup_PayerBillingCodeLookup(PayerBillingCodeLookup data) {

        return null;
    }

    @PUT
    @Path("/am/payer/billing/code/")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_LOOKUP_updatePayerBillingCodeLkup_PayerBillingCodeLookup(PayerBillingCodeLookup data) {

        return null;
    }

    @DELETE
    @Path("/am/payer/billing/code/{sequence_key}")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_LOOKUP_deletePayerBillingCodeLkup_PayerBillingCodeList(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    //Modifier allowed
    @GET
    @Path("/am/payer/mdfr/{sequence_key}")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_LOOKUP_getPayerMdfrLkup_PayerModifierLookup(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }
    @GET
    @Path("/am/payer/mdfr/")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_LOOKUP_getPayerMdfrLkupByBsnEntPayer_PayerModifierLookup(
    @MatrixParam("payer_id") String payerId,
    @MatrixParam("bsn_ent_id") String bsnEntId ) {

        return null;
    }

    @POST
    @Path("/am/payer/mdfr/")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_LOOKUP_insertPayerMdfrLkup_PayerModifierLookup(PayerModifierLookup data) {

        return null;
    }

    @PUT
    @Path("/am/payer/mdfr/")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_LOOKUP_updatePayerMdfrLkup_PayerModifierLookup(PayerModifierLookup data) {

        return null;
    }

    @DELETE
    @Path("/am/payer/mdfr/{sequence_key}")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_LOOKUP_deletePayerMdfrLkup_PayerModifierLookup(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    /*Start Payer Contract*/
    
    //Line Of Business: the payer contract is now just contract
    /*@GET
    @Path("/am/payer/contract/lob/{sequence_key}")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_getContrLobLst_ContractLineOfBusinessList(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/am/payer/contract/lob/")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_getContrLobLstBsnEntPayerContr_ContractLineOfBusinessList(
            @MatrixParam("bsn_ent_id") String bsnEntId,
            @MatrixParam("payer_id") String payerId,
            @MatrixParam("contr_id") String contrId,
            @DefaultValue("1") @MatrixParam("page") int page,
            @DefaultValue("100") @MatrixParam("page_size") int pageSize ) {
        return null;
    }

    @POST
    @Path("/am/payer/contract/lob/")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_insertContrLobLst_ContractLineOfBusinessList(ContractLineOfBusinessList data) {

        return null;
    }

    @PUT
    @Path("/am/payer/contract/lob/")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_updateContrLobLst_ContractLineOfBusinessList(ContractLineOfBusinessList data) {

        return null;
    }

    @DELETE
    @Path("/am/payer/contract/lob/{sequence_key}")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_deleteContrLobLst_ContractLineOfBusinessList(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }*/

    //Services Allowed
    @GET
    @Path("/am/payer/contract/svc/{sequence_key}")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_getContrSvcLst_ContractServiceList(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/am/payer/contract/svc/")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_getContrSvcLstBsnEntPayerContr_ContractServiceList(
            @MatrixParam("bsn_ent_id") String bsnEntId,
            @MatrixParam("payer_id") String payerId,
            @MatrixParam("contr_id") String contrId,
            @DefaultValue("1") @MatrixParam("page") int page,
            @DefaultValue("100") @MatrixParam("page_size") int pageSize ) {

        return null;
    }

    @POST
    @Path("/am/payer/contract/svc/")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_insertContrSvcLst_ContractServiceList(ContractServiceList data) {

        return null;
    }

    @PUT
    @Path("/am/payer/contract/svc/")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_updateContrSvcLst_ContractServiceList(ContractServiceList data) {

        return null;
    }

    @DELETE
    @Path("/am/payer/contract/svc/{sequence_key}")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_deleteContrSvcLst_ContractServiceList(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    //Rate Type Allowed
    @GET
    @Path("/am/payer/contract/rate/type/{sequence_key}")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_getContrRateTypLst_ContractRateTypeList(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }
    @GET
    @Path("/am/payer/contract/rate/type/")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_getContrRateTypLstBsnEntPayerContr_ContractRateTypeList(
            @MatrixParam("bsn_ent_id") String bsnEntId,
            @MatrixParam("payer_id") String payerId,
            @MatrixParam("contr_id") String contrId,
            @DefaultValue("1") @MatrixParam("page") int page,
            @DefaultValue("100") @MatrixParam("page_size") int pageSize ) {

        return null;
    }

    @POST
    @Path("/am/payer/contract/rate/type/")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_insertContrRateTypLst_ContractRateTypeList(ContractRateTypeList data) {

        return null;
    }

    @PUT
    @Path("/am/payer/contract/rate/type/")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_updateContrRateTypLst_ContractRateTypeList(ContractRateTypeList data) {

        return null;
    }

    @DELETE
    @Path("/am/payer/contract/rate/type/{sequence_key}")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_deleteContrRateTypLst_ContractRateTypeList(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    //Modifier allowed
    @GET
    @Path("/am/payer/contract/mdfr/{sequence_key}")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_getContrMdfrLst_ContractModifierList(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }
    
    @GET
    @Path("/am/payer/contract/mdfr/")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_getContrMdfrLstBsnEntPayerContr_ContractModifierList(
            @MatrixParam("bsn_ent_id") String bsnEntId,
            @MatrixParam("payer_id") String payerId,
            @MatrixParam("contr_id") String contrId,
            @DefaultValue("1") @MatrixParam("page") int page,
            @DefaultValue("100") @MatrixParam("page_size") int pageSize ) {

        return null;
    }
    
    @POST
    @Path("/am/payer/contract/mdfr/")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_insertContrMdfrLst_ContractModifierList(ContractModifierList data) {

        return null;
    }

    @PUT
    @Path("/am/payer/contract/mdfr/")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_updateContrMdfrLst_ContractModifierList(ContractModifierList data) {

        return null;
    }

    @DELETE
    @Path("/am/payer/contract/mdfr/{sequence_key}")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_deleteContrMdfrLst_ContractModifierList(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    //Billing Rates Matrixs
    @GET
    @Path("/billing/rate/matrix/{sequence_key}")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_BILLING_getBillingRateMatrix_BillingRateMatrix(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/billing/rate/matrix/")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_BILLING_getBillingRateMatrixBsnEntPayerContr_BillingRateMatrix(
            @MatrixParam("bsn_ent_id") String bsnEntId,
            @MatrixParam("payer_id") String payerId,
            @MatrixParam("contract_id") String contrId,
            @DefaultValue("1") @MatrixParam("page") int page,
            @DefaultValue("100") @MatrixParam("page_size") int pageSize) {

        return null;
    }

    @POST
    @Path("/billing/rate/matrix/")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_BILLING_insertBillingRateMatrix_BillingRateMatrix(BillingRateMatrix data) {

        return null;
    }

    @PUT
    @Path("/billing/rate/matrix/")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_BILLING_updateBillingRate_BillingRateMatrix(BillingRateMatrix data) {

        return null;
    }

    @DELETE
    @Path("/billing/rate/matrix/{sequence_key}")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_BILLING_deleteBillingRateMatrix_BillingRateMatrix(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    /*Business Entity*/

    //Line Of Business
    @GET
    @Path("/am/payer/be/lob/{bsn_ent_id}")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_getBeLobLkup_BusinessEntityLineOfBusinessLookup(
            @PathParam("bsn_ent_id") String bsnEntId ) {

        return null;
    }

    //Service
    @GET
    @Path("/am/payer/be/svc/")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_LOOKUP_getSvcLkup_ServiceLookup() {


        return null;
    }


    //Rate Type Lookup
    @GET
    @Path("/am/payer/be/rate/type/{bsn_ent_id}")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_getBeRateTypLkup_BusinessEntityRateTypeLookup(
            @PathParam("bsn_ent_id") String bsnEntId) {

        return null;
    }

    //Billing Code: table was deleted
//    @GET
//    @Path("/am/payer/be/bill/code/{bsn_ent_id}")
//    @Consumes(value = {MediaType.APPLICATION_JSON})
//    @Produces(value = {MediaType.APPLICATION_JSON})
//    public final Response PKG_AM_getBeBillingCodeLkup_BusinessEntityBillingCodeLookup(
//            @PathParam("bsn_ent_id") String bsnEntId  ) {
//
//        return null;
//    }

    //Modifier Lookup
    @GET
    @Path("/am/payer/be/mdfr/{bsn_ent_id}")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_getBeMdfrLkup_BusinessEntityModifierLookup(
            @PathParam("bsn_ent_id") String bsnEntId  ) {

        return null;
    }

}
