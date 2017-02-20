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

package com.sandata.lab.rest.lookup;


import com.sandata.lab.data.model.response.Response;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;


public class RestfulServices {

    //region Lookup

    @GET
    @Path("/accounts/receivable/transaction/category")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_getArTxnCtgyLkup_AccountsReceivableTransactionCategoryLookup(@MatrixParam("update_cache") boolean updateCache) {

        return null;
    }

    @GET
    @Path("/accounts/receivable/transaction")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_getArTxnLkup_AccountsReceivableTransactionLookup(@MatrixParam("update_cache") boolean updateCache) {

        return null;
    }

    @GET
    @Path("/address_priority")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_getAddrPrioLkup_AddressPriorityLookup(@MatrixParam("update_cache") boolean updateCache) {

        return null;
    }

    @GET
    @Path("/address_type")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_getAddrTypLkup_AddressTypeLookup(
        @MatrixParam("update_cache") boolean updateCache) {
        return null;
    }

    @GET
    @Path("/auth_limit_type")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_getAuthLmtTypLkup_AuthorizationLimitTypeLookup(@MatrixParam("update_cache") boolean updateCache) {

        return null;
    }

    @GET
    @Path("/auth_limit_unit")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_getAuthLmtUnitLkup_AuthorizationLimitUnitLookup(@MatrixParam("update_cache") boolean updateCache) {

        return null;
    }

    @GET
    @Path("/codes/hcpcs")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_getHcpcsLkup_HCPCSLookup(
        @MatrixParam("keyword") String keyword,
        @MatrixParam("code") String code,
        @DefaultValue("1") @MatrixParam("page") int page,
        @DefaultValue("10") @MatrixParam("page_size") int pageSize,
        @DefaultValue("code") @MatrixParam("sort_on") String sortOn,
        @DefaultValue("ASC") @MatrixParam("direction") String direction) {
        return null;
    }

    @GET
    @Path("/codes/icd_diagnosis")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_getIcdDiagnosisLkup_ICDDiagnosisLookup(
        @MatrixParam("keyword") String keyword,
        @MatrixParam("code") String code,
        @MatrixParam("revision") String revision,
        @MatrixParam("eff_date") String effectiveDate,
        @DefaultValue("9999-12-31") @MatrixParam("term_date") String termDate,
        @DefaultValue("1") @MatrixParam("page") int page,
        @DefaultValue("10") @MatrixParam("page_size") int pageSize,
        @DefaultValue("code") @MatrixParam("sort_on") String sortOn,
        @DefaultValue("ASC") @MatrixParam("direction") String direction) {
        return null;
    }

    @GET
    @Path("/codes/modifiers")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_getMdfrLkup_ModifierLookup(
        @MatrixParam("update_cache") boolean updateCache) {
        return null;
    }

    @GET
    @Path("/contact_method")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_getContMthdLkup_ContactMethodLookup(
            @MatrixParam("update_cache") boolean updateCache) {
        return null;
    }

    @GET
    @Path("/contract/change_reason/codes")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_getContrChangeReasonLkup_ContractChangeReasonLookup(
            @MatrixParam("app_module") String appModule,
            @MatrixParam("payer_id") String payerId,
            @MatrixParam("contract_id") String contractId,
            @MatrixParam("code") String code,
            @MatrixParam("name") String name,
            @DefaultValue("1") @MatrixParam("page") int page,
            @DefaultValue("100") @MatrixParam("page_size") int pageSize,
            @DefaultValue("desc") @MatrixParam("sort_on") String sortOn,
            @DefaultValue("ASC") @MatrixParam("direction") String direction) {
        return null;
    }

    @GET
    @Path("/payer/billing/codes")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_getPayerBillingCodeLkup_PayerBillingCodeLookup(
            @MatrixParam("bsn_ent_id") String bsnEntId,
            @MatrixParam("payer_id") String payerId,
            @MatrixParam("code") String code,
            @MatrixParam("description") String description,
            @DefaultValue("1") @MatrixParam("page") int page,
            @DefaultValue("100") @MatrixParam("page_size") int pageSize,
            @DefaultValue("desc") @MatrixParam("sort_on") String sortOn,
            @DefaultValue("ASC") @MatrixParam("direction") String direction) {
        return null;
    }

    @GET
    @Path("/visit/verification/rounding/rules")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_getVvRndingRuleLkup_VisitVerificationRoundingRuleLookup(
            @MatrixParam("id") String id,
            @MatrixParam("name") String name,
            @MatrixParam("description") String description,
            @MatrixParam("qualifier") String qualifier,
            @DefaultValue("1") @MatrixParam("page") int page,
            @DefaultValue("100") @MatrixParam("page_size") int pageSize,
            @DefaultValue("desc") @MatrixParam("sort_on") String sortOn,
            @DefaultValue("ASC") @MatrixParam("direction") String direction) {
        return null;
    }

    @GET
    @Path("/dnr")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_getDnrLkup_DoNotResuscitateLookup(
        @MatrixParam("update_cache") boolean updateCache) {

        return null;
    }

    @GET
    @Path("/ethnicity")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_getRaceEthnicityLkup_RaceEthnicityLookup(
        @DefaultValue("1") @MatrixParam("page") int page,
        @DefaultValue("100") @MatrixParam("page_size") int pageSize,
        @DefaultValue("desc") @MatrixParam("sort_on") String sortOn,
        @DefaultValue("ASC") @MatrixParam("direction") String direction) {

        return null;
    }

    @GET
    @Path("/exception")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_getExcpLkup_ExceptionLookup(@MatrixParam("update_cache") boolean updateCache,
            @DefaultValue("EXCP_ID") @MatrixParam("sort_on") String sortOn,
            @DefaultValue("ASC") @MatrixParam("direction") String direction,
            @MatrixParam("exception_type_name") String exceptionTypeName) {

        return null;
    }

    @GET
    @Path("/frequency")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_getFreqTypLkup_FrequencyTypeLookup(
        @MatrixParam("update_cache") boolean updateCache) {
        return null;
    }

    @GET
    @Path("/gender")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_getGenderTypLkup_GenderTypeLookup(
        @MatrixParam("update_cache") boolean updateCache) {
        return null;
    }

    @GET
    @Path("/invoice/detail/status")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_getInvDetStatusLkup_InvoiceDetailStatusLookup(@MatrixParam("update_cache") boolean updateCache) {

        return null;
    }

    @GET
    @Path("/invoice/status")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_getInvStatusLkup_InvoiceStatusLookup(@MatrixParam("update_cache") boolean updateCache) {

        return null;
    }

    @GET
    @Path("/invoice/submission/type")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_getInvSubmTypLkup_InvoiceSubmissionTypeLookup(@MatrixParam("update_cache") boolean updateCache) {

        return null;
    }

    @GET
    @Path("/payer/sub/type")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_getPayerSubTypLkup_PayerSubTypeLookup(@MatrixParam("update_cache") boolean updateCache) {

        return null;
    }


    @GET
    @Path("/language")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_getLangLkup_LanguageLookup(
        @MatrixParam("update_cache") boolean updateCache) {
        return null;
    }

    @GET
    @Path("/marital_status")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_getMrtlStatusLkup_MaritalStatusLookup(
        @MatrixParam("update_cache") boolean updateCache) {
        return null;
    }

    @GET
    @Path("/medication")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_getMedLkup_MedicationLookup(
        @MatrixParam("keyword") String keyword,
        @MatrixParam("code") String code,
        @DefaultValue("1") @MatrixParam("page") int page,
        @DefaultValue("10") @MatrixParam("page_size") int pageSize,
        @DefaultValue("name") @MatrixParam("sort_on") String sortOn,
        @DefaultValue("ASC") @MatrixParam("direction") String direction) {
        return null;
    }

    @GET
    @Path("/patient/designee")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_getPdContDetTypLkup_PatientDesigneeContactDetailTypeLookup(
        @MatrixParam("update_cache") boolean updateCache) {

        return null;
    }

    @GET
    @Path("/patient/nutritional_requirements")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_getNutrRqmtLkup_NutritionalRequirementLookup(
        @MatrixParam("update_cache") boolean updateCache) {

        return null;
    }

    @GET
    @Path("/patient/priority_level")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_getPtPrioLvlLkup_PatientPriorityLevelLookup(
        @MatrixParam("update_cache") boolean updateCache) {
        return null;
    }

    @GET
    @Path("/payer/rank")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_getPayerRankLkup_PayerRankLookup(
        @MatrixParam("update_cache") boolean updateCache) {

        return null;
    }

    @GET
    @Path("/phone_type")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_getPhoneTypLkup_PhoneTypeLookup(
        @MatrixParam("update_cache") boolean updateCache) {
        return null;
    }

    @GET
    @Path("/religion")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_getReligionLkup_ReligionLookup(
        @MatrixParam("update_cache") boolean updateCache) {
        return null;
    }

    @GET
    @Path("/service_lookup")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_getSvcLkup_ServiceLookup(
        @MatrixParam("update_cache") boolean updateCache) {
        return null;
    }

    @GET
    @Path("/service/units")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_getSvcUnitLkup_ServiceUnitLookup(
        @MatrixParam("update_cache") boolean updateCache) {
        return null;
    }

    @GET
    @Path("/staff/employment_status")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_getEmpltStatusTypLkup_EmploymentStatusTypeLookup(
        @MatrixParam("update_cache") boolean updateCache) {
        return null;
    }

    @GET
    @Path("/state")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_getStateLkup_StateLookup(
            @MatrixParam("update_cache") boolean updateCache,
            @DefaultValue("code") @MatrixParam("sort_on") String orderBy) {

        return null;
    }

    @GET
    @Path("/schedule_participant_exclusion")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_getSchedPtcExclTypLkup_ScheduleParticipantExclusionTypeLookup(
        @MatrixParam("update_cache") boolean updateCache) {

        return null;
    }

    @GET
    @Path("/task")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_getTaskLkup_TaskLookup(
        @MatrixParam("update_cache") boolean updateCache) {

        return null;
    }

    @GET
    @Path("/transportation_mode")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_getTransportModeLkup_TransportationModeLookup(
        @MatrixParam("update_cache") boolean updateCache) {
        return null;
    }

    @GET
    @Path("/visit_event_type")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_getVisitEvntTypLkup_VisitEventTypeLookup(@MatrixParam("update_cache") boolean updateCache) {

        return null;
    }

    @GET
    @Path("/vendor")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_getVendorLkup_VendorLookup(@MatrixParam("update_cache") boolean updateCache) {

        return null;
    }

    @GET
    @Path("/interface")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_getIntfLkup_InterfaceLookup(@MatrixParam("update_cache") boolean updateCache) {

        return null;
    }

    @GET
    @Path("/interface/item")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_getIntfItemLkup_InterfaceItemLookup(@MatrixParam("update_cache") boolean updateCache) {

        return null;
    }

    @GET
    @Path("/postal_code")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_getPstlCodeLkup_PostalCodeLookup(
            @MatrixParam("postal_code") String postalCode,
            @MatrixParam("city_name") String cityName,
            @MatrixParam("county_name") String countyName,
            @MatrixParam("county_fips_code") String countyFipsCode,
            @MatrixParam("state_name") String stateName,
            @MatrixParam("state_code") String stateCode,
            @MatrixParam("state_fips_code") String stateFipsCode,
            @MatrixParam("county_msa_code") String countyMsaCode,
            @MatrixParam("area_code") String areaCode,
            @DefaultValue("1") @MatrixParam("page") int page,
            @DefaultValue("10") @MatrixParam("page_size") int pageSize,
            @DefaultValue("city_name") @MatrixParam("sort_on") String sortOn,
            @DefaultValue("ASC") @MatrixParam("direction") String direction) {

        return null;
    }

    @GET
    @Path("/county")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_getCountyLkup_CountyLookup(
            @MatrixParam("county_fips_code") String countyFipsCode,
            @MatrixParam("state_fips_code") String stateFipsCode,
            @MatrixParam("fips_cls_code") String fipsClsCode,
            @MatrixParam("county_name") String countyName,
            @MatrixParam("state_code") String stateCode,
            @DefaultValue("1") @MatrixParam("page") int page,
            @DefaultValue("10") @MatrixParam("page_size") int pageSize,
            @DefaultValue("county_name") @MatrixParam("sort_on") String sortOn,
            @DefaultValue("ASC") @MatrixParam("direction") String direction) {

        return null;
    }

    @GET
    @Path("/county/subdivision")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_getCountySubdivLkup_CountySubdivisionLookup(
            @MatrixParam("subdiv_fips_code") String countySubdivFipsCode,
            @MatrixParam("subdiv_qualifier") String countySubdivQlfr,
            @MatrixParam("subdiv_name") String countySubdivName,
            @MatrixParam("county_fips_code") String countyFipsCode,
            @MatrixParam("state_fips_code") String stateFipsCode,
            @MatrixParam("state_code") String stateCode,
            @DefaultValue("1") @MatrixParam("page") int page,
            @DefaultValue("10") @MatrixParam("page_size") int pageSize,
            @DefaultValue("state_code") @MatrixParam("sort_on") String sortOn,
            @DefaultValue("ASC") @MatrixParam("direction") String direction) {

        return null;
    }



    @GET
    @Path("/prepaid/capitation/plan")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_getPcpLkup_PrepaidCapitationPlanLookup(@MatrixParam("update_cache") boolean updateCache) {

        return null;
    }

    @GET
    @Path("/application/module")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_getAppModLkup_ApplicationModuleLookup(@MatrixParam("update_cache") boolean updateCache) {

        return null;
    }

    //endregion

    //region Custom Methods

    @GET
    @Path("/visit/task")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_LOOKUP_getVisitTaskLst_VisitTaskList(
        @MatrixParam("visit_sk") Long visitSk,
        @MatrixParam("update_cache") boolean updateCache) {
        return null;
    }

    @GET
    @Path("/federal_tax_id/{bsn_ent_id}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_lookupFedTaxId_BusinessEntityFedTaxLookup(
        @PathParam("bsn_ent_id") String bsnEntId,
        @MatrixParam("update_cache") boolean updateCache) {

        return null;
    }

    @GET
    @Path("/biller/{bsn_ent_id}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_lookupBiller_BusinessEntityBillerLookup(
        @PathParam("bsn_ent_id") String bsnEntId,
        @MatrixParam("update_cache") boolean updateCache) {

        return null;
    }

    //endregion

    //region ENUM Endpoints

    //dmr--GEOR-4243: Agreed to return static enum list during dev call on 06/14/2016.
    @GET
    @Path("/enum/referral/received_by_method")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_getReferralReceivedByMethod_ENUM(@MatrixParam("update_cache") boolean updateCache) {

        return null;
    }

    @GET
    @Path("/enum/union/status")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_getUnionStatus_ENUM(@MatrixParam("update_cache") boolean updateCache) {

        return null;
    }

    @GET
    @Path("/admission_type")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_getAdmissionTypeLookup_ENUM(@MatrixParam("update_cache") boolean updateCache) {

        return null;
    }

    @GET
    @Path("/limit_by")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_getLimitByLookup_ENUM(@MatrixParam("update_cache") boolean updateCache) {

        return null;
    }

    @GET
    @Path("/military_status")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_getMilitaryStatusLookup_ENUM(@MatrixParam("update_cache") boolean updateCache) {

        return null;
    }

    @GET
    @Path("/notification_type")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_getNotificationTypeLookup_ENUM(@MatrixParam("update_cache") boolean updateCache) {

        return null;
    }

    @GET
    @Path("/staff_rel_type")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_getStaffRelationshipTypeLookup_ENUM(@MatrixParam("update_cache") boolean updateCache) {

        return null;
    }

    @GET
    @Path("/referral_receival_method")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_getReferralReceivalMethod_ENUM(@MatrixParam("update_cache") boolean updateCache) {

        return null;
    }

    @GET
    @Path("/additional_pay_type")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_getAdditionalPayType_ENUM(@MatrixParam("update_cache") boolean updateCache) {

        return null;
    }

    @GET
    @Path("/visit_status")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_getVisitStatus_ENUM(@MatrixParam("update_cache") boolean updateCache) {

        return null;
    }

    @GET
    @Path("/patient_status")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_getPatientStatus_ENUM(@MatrixParam("update_cache") boolean updateCache) {

        return null;
    }

    @GET
    @Path("/pay_hours")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_getPayHours_ENUM(@MatrixParam("update_cache") boolean updateCache) {

        return null;
    }

    @GET
    @Path("/tal")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_getTal_ENUM(@MatrixParam("update_cache") boolean updateCache) {

        return null;
    }

    @GET
    @Path("/ar/unapplied/balance_range")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_getUnappliedBalanceRangeLookup_ENUM(@MatrixParam("update_cache") boolean updateCache) {

        return null;
    }

    //endregion

    //region Dashboard Endpoints

    @GET
    @Path("/dashboard/payroll/overtime_hours")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_getPayRollOvertimeHours_Dashboard(@MatrixParam("update_cache") boolean updateCache) {

        return null;
    }

    @GET
    @Path("/dashboard/payroll/total_hours/paid")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_getPayRollTotalHoursPaid_Dashboard(@MatrixParam("update_cache") boolean updateCache) {

        return null;
    }

    @GET
    @Path("/dashboard/payroll/total_hours/pending")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_getPayRollTotalHoursPending_Dashboard(@MatrixParam("update_cache") boolean updateCache) {

        return null;
    }

    @GET
    @Path("/dashboard/payroll/total_dollars/paid")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_getPayRollTotalDollarsPaid_Dashboard(@MatrixParam("update_cache") boolean updateCache) {

        return null;
    }

    @GET
    @Path("/dashboard/payroll/total_dollars/pending")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_getPayRollTotalDollarsPending_Dashboard(@MatrixParam("update_cache") boolean updateCache) {

        return null;
    }

    @GET
    @Path("/dashboard/expired_auths")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_getExpiredAuths_Dashboard(@MatrixParam("update_cache") boolean updateCache) {

        return null;
    }

    @GET
    @Path("/dashboard/scheduled_hours")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_getScheduledHours_Dashboard(@MatrixParam("update_cache") boolean updateCache) {

        return null;
    }

    @GET
    @Path("/dashboard/visit_exceptions")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_getVisitExceptions_Dashboard(@MatrixParam("update_cache") boolean updateCache) {

        return null;
    }

    @GET
    @Path("/dashboard/visit_verified_hours")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_getVisitVerifiedHours_Dashboard(@MatrixParam("update_cache") boolean updateCache) {

        return null;
    }

    @GET
    @Path("/dashboard/open_orders")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_getOpenOrders_Dashboard(@MatrixParam("update_cache") boolean updateCache) {

        return null;
    }

    @GET
    @Path("/dashboard/out_of_compliance")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_getDateRange_Dashboard(@MatrixParam("update_cache") boolean updateCache) {

        return null;
    }

    @GET
    @Path("/dashboard/eligibility")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_getEligibility_Dashboard(@MatrixParam("update_cache") boolean updateCache) {

        return null;
    }

    //endregion

    //region Reference Values

    //dmr--ReferralTypeName is no longer a enum and can be provided by the Agency.
    /*@GET
    @Path("/reference/referral_type_name")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_getReferralTypeName_ReferralTypeName_REF(@MatrixParam("update_cache") boolean updateCache) {

        return null;
    }*/

    @GET
    @Path("/reference/fax_qualifier")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_getReferralTypeName_FaxQualifier_REF(@MatrixParam("update_cache") boolean updateCache) {

        return null;
    }

    @GET
    @Path("/reference/staff_position")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_getStaffServiceName_ServiceName_REF(@MatrixParam("update_cache") boolean updateCache) {

        return null;
    }

    @GET
    @Path("/reference/staff/associated/rate/type")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_getStaffAssociatedRateType_StaffAssociatedRateType_REF(@MatrixParam("update_cache") boolean updateCache) {

        return null;
    }

    @GET
    @Path("/reference/claim/type")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_getClaimTypeName_ClaimTypeName_REF(@MatrixParam("update_cache") boolean updateCache) {

        return null;
    }

    @GET
    @Path("/reference/rate/subtype/name")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_getRateSubTypeName_RateSubTypeName_REF(@MatrixParam("update_cache") boolean updateCache) {

        return null;
    }

    @GET
    @Path("/reference/rate/type/name")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_getRateTypeName_RateTypeName_REF(@MatrixParam("update_cache") boolean updateCache) {

        return null;
    }

    @GET
    @Path("/reference/payroll/weekend/start_days")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_getPayrollWeekendStartDays_PayrollWeekendStartDays_REF(@MatrixParam("update_cache") boolean updateCache) {

        return null;
    }

    @GET
    @Path("/reference/payroll/business/weekend/end_days")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_getPayrollWeekendEndDays_WeekEndDay_REF(@MatrixParam("update_cache") boolean updateCache) {

        return null;
    }

    @GET
    @Path("/reference/payroll/weekend/end_days")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_getPayrollWeekendEndDays_PayrollWeekendEndDays_REF(@MatrixParam("update_cache") boolean updateCache) {

        return null;
    }

    @GET
    @Path("/reference/compliance/result")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_getComplianceResult_ComplianceResult_REF() {
        return null;
    }

    @GET
    @Path("/reference/inservice/training/result")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_getInServiceTrainingResult_InServiceTrainingResult_REF() {
        return null;
    }

    @GET
    @Path("/reference/compliant/indicator")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_getCompliantIndicator_CompliantIndicator_REF() {
        return null;
    }

    @GET
    @Path("/reference/compliance/allow/scheduling")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_getComplianceAllowScheduling_ComplianceAllowScheduling_REF() {
        return null;
    }

    @GET
    @Path("/reference/allow/scheduling")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_getAllowScheduling_AllowScheduling_REF() {
        return null;
    }

    @GET
    @Path("/reference/eligibility/status")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_getEligibilityStatusName_EligibilityStatusName_REF(@MatrixParam("update_cache") boolean updateCache) {

        return null;
    }

    @GET
    @Path("/reference/compliance/item/frequency")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_getComplianceItemFrequency_Frequency_REF(@MatrixParam("update_cache") boolean updateCache) {

        return null;
    }

    @GET
    @Path("/reference/compliance/notification/period")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_getComplianceNotificationPeriod_ComplianceNotificationPeriod_REF(@MatrixParam("update_cache") boolean updateCache) {

        return null;
    }

    @GET
    @Path("/reference/compliance/required_from")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_getComplianceRequiredFrom_ComplianceRequiredFrom_REF(@MatrixParam("update_cache") boolean updateCache) {

        return null;
    }

    @GET
    @Path("/reference/staff/item/required_from")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_getStaffItemRequiredFromQualifier_StaffItemRequiredFromQualifier_REF(@MatrixParam("update_cache") boolean updateCache) {

        return null;
    }

    @GET
    @Path("/reference/patient/relationship/type")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_getPatientPayerRelationshipTypeName_PatientPayerRelationshipTypeName_REF(@MatrixParam("update_cache") boolean updateCache) {

        return null;
    }

    @GET
    @Path("/reference/payment/responsibility/value")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_getPaymentResponsibilityValueQualifier_PaymentResponsibilityValueQualifier_REF(@MatrixParam("update_cache") boolean updateCache) {

        return null;
    }

    @GET
    @Path("/reference/compliance/additional/info")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_getComplianceAdditionalInformationQualifier_ComplianceAdditionalInformationQualifier_REF(@MatrixParam("update_cache") boolean updateCache) {

        return null;
    }

    @GET
    @Path("/reference/accounts/receivable/batch/status")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_getBatchStatus_BatchStatus_REF(@MatrixParam("update_cache") boolean updateCache) {

        return null;
    }

    @GET
    @Path("/reference/accounts/receivable/open/balance")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_getOpenBalance_OpenBalance_REF(@MatrixParam("update_cache") boolean updateCache) {

        return null;
    }

    @GET
    @Path("/reference/patient/assessment/frequency")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_getPatientAssessmentFrequency_PatientAssessmentFrequency_REF(@MatrixParam("update_cache") boolean updateCache) {

        return null;
    }

    @GET
    @Path("/reference/patient/supervisory/visit/frequency")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_getPatientSupervisoryVisitFrequency_PatientSupervisoryVisitFrequency_REF(@MatrixParam("update_cache") boolean updateCache) {

        return null;
    }

    @GET
    @Path("/reference/hcp/provider/type/name")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_getHealthcareProfessionalProviderTypeName_HealthcareProfessionalProviderTypeName_REF(@MatrixParam("update_cache") boolean updateCache) {

        return null;
    }

    @GET
    @Path("/reference/payer/type")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_getPayerTypeQualifier_PayerTypeQualifier_REF(@MatrixParam("update_cache") boolean updateCache) {

        return null;
    }

    @GET
    @Path("/reference/schedule/permission/qualifier")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_getSchedulePermissionQualifier_SchedulePermissionQualifier_REF(@MatrixParam("update_cache") boolean updateCache) {

        return null;
    }

    @GET
    @Path("/reference/compliance/mandatory/completion/threshold")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_getComplianceMandatoryCompletionThreshold_ComplianceMandatoryCompletionThreshold_REF(@MatrixParam("update_cache") boolean updateCache) {

        return null;
    }

    @GET
    @Path("/reference/visit/confirmation/qualifier")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_getVisitEventPatientConfirmationQualifier_VisitEventPatientConfirmationQualifier_REF(@MatrixParam("update_cache") boolean updateCache) {

        return null;
    }

    //endregion
}
