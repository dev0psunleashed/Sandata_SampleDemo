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

package com.sandata.lab.rest.am;


import com.sandata.lab.data.model.dl.model.*;
import com.sandata.lab.data.model.dl.model.extended.exception.BusinessEntityExceptionListExt;
import com.sandata.lab.data.model.dl.model.extended.exception.ContractExceptionListExt;
import com.sandata.lab.data.model.response.Response;
import com.sandata.lab.rest.am.model.VisitVerificationRoundingRuleSetting;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.math.BigInteger;
import java.util.List;

public class RestfulServices extends AdminRestfulServices {

    //region BusinessEntity

    //region BusinessEntityAllergyLookup

    @POST
    @Path("/lookup/allergy")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_insertBeAllergyLkup_BusinessEntityAllergyLookup(BusinessEntityAllergyLookup data) {

        return null;
    }

    @PUT
    @Path("/lookup/allergy")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_updateBeAllergyLkup_BusinessEntityAllergyLookup(BusinessEntityAllergyLookup data) {

        return null;
    }

    @DELETE
    @Path("/lookup/allergy/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_deleteBeAllergyLkup_BusinessEntityAllergyLookup(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/lookup/allergy/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_getBeAllergyLkup_BusinessEntityAllergyLookup(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/lookup/allergy")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_getBeAllergyLkup_BusinessEntityAllergyLookup(
        @MatrixParam("bsn_ent_id") String bsnEntId) {

        return null;
    }
    //endregion

    //region BusinessEntityCalendarLookup

    @POST
    @Path("/lookup/calendar")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_insertBeCalendarLkup_BusinessEntityCalendarLookup(BusinessEntityCalendarLookup data) {

        return null;
    }

    @PUT
    @Path("/lookup/calendar")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_updateBeCalendarLkup_BusinessEntityCalendarLookup(BusinessEntityCalendarLookup data) {

        return null;
    }

    @DELETE
    @Path("/lookup/calendar/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_deleteBeCalendarLkup_BusinessEntityCalendarLookup(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/lookup/calendar/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_getBeCalendarLkup_BusinessEntityCalendarLookup(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/lookup/calendar")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_getBeCalendarLkup_BusinessEntityCalendarLookup(
        @MatrixParam("bsn_ent_id") String bsnEntId) {

        return null;
    }
    //endregion

    //region BusinessEntityChangeReasonLookup

    @POST
    @Path("/lookup/change_reason")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_insertBeChangeReasonLkup_BusinessEntityChangeReasonLookup(BusinessEntityChangeReasonLookup data) {

        return null;
    }

    @PUT
    @Path("/lookup/change_reason")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_updateBeChangeReasonLkup_BusinessEntityChangeReasonLookup(BusinessEntityChangeReasonLookup data) {

        return null;
    }

    @DELETE
    @Path("/lookup/change_reason/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_deleteBeChangeReasonLkup_BusinessEntityChangeReasonLookup(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/lookup/change_reason/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_getBeChangeReasonLkup_BusinessEntityChangeReasonLookup(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/lookup/change_reason")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_getBeChangeReasonLkupByModuleAndStatus_BusinessEntityChangeReasonLookup(
        @MatrixParam("bsn_ent_id") String bsnEntId,
        @MatrixParam("module") String module,
        @MatrixParam("status") String status) {

        return null;
    }
    
    @GET
    @Path("/lookup/app_module")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_getApplicationModuleLookup_ApplicationModuleLookup() {

        return null;
    }
    
    @GET
    @Path("/lookup/change_reason/status")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_getBeChangeReasonLkupStatusByModule_BusinessEntityChangeReasonLookup(
        @MatrixParam("bsn_ent_id") String bsnEntId,
        @MatrixParam("module") String module) {

        return null;
    }
    
    //endregion

    //region BusinessEntityComplianceLookup

    @POST
    @Path("/lookup/compliance")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_insertBeCompLkup_BusinessEntityComplianceLookup(BusinessEntityComplianceLookup data) {

        return null;
    }

    @PUT
    @Path("/lookup/compliance")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_updateBeCompLkup_BusinessEntityComplianceLookup(BusinessEntityComplianceLookup data) {

        return null;
    }

    @DELETE
    @Path("/lookup/compliance/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_deleteBeCompLkup_BusinessEntityComplianceLookup(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/lookup/compliance/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_getBeCompLkup_BusinessEntityComplianceLookup(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/lookup/compliance")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_getBeCompLkupForName_BusinessEntityComplianceLookup(
        @MatrixParam("bsn_ent_id") String bsnEntId,
        @MatrixParam("comp_name") String compName,
        @DefaultValue("1") @MatrixParam("page") int page,
        @DefaultValue("50") @MatrixParam("page_size") int pageSize,
        @DefaultValue("REC_CREATE_TMSTP") @MatrixParam("sort_on") String sortOn,
        @DefaultValue("ASC") @MatrixParam("direction") String direction) {

        return null;
    }
    
    @GET
    @Path("/lookup/compliance/check_unique_name")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_checkComplianceUniqueName_BusinessEntityComplianceLookup(
        @MatrixParam("comp_name") String compCode,
        @MatrixParam("bsn_ent_id") String bsnEntId) {

        return null;
    }
    
    @GET
    @Path("/lookup/compliance/check_used_requisite")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_checkComplianceIsUsedAsRequisiteForOthers_BusinessEntityComplianceLookup(
        @MatrixParam("comp_code") String compCode,
        @MatrixParam("bsn_ent_id") String bsnEntId) {

        return null;
    }

    @GET
    @Path("/lookup/compliance/category/item")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_getBeCompLkupByCtgy_BusinessEntityComplianceLookup(
            @MatrixParam("bsn_ent_id") String bsnEntId,
            @MatrixParam("category") String category,
            @DefaultValue("1") @MatrixParam("page") int page,
            @DefaultValue("50") @MatrixParam("page_size") int pageSize,
            @DefaultValue("name") @MatrixParam("sort_on") String sortOn,
            @DefaultValue("ASC") @MatrixParam("direction") String direction) {

        return null;
    }
    //endregion

    // region BusinessEntityComplianceCategoryLookup

    @POST
    @Path("/lookup/compliance/category")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_insertBeCompCtgyLkup_BusinessEntityComplianceCategoryLookup(BusinessEntityComplianceCategoryLookup data) {

        return null;
    }

    @PUT
    @Path("/lookup/compliance/category")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_updateBeCompCtgyLkup_BusinessEntityComplianceCategoryLookup(BusinessEntityComplianceCategoryLookup data) {

        return null;
    }

    @DELETE
    @Path("/lookup/compliance/category/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_deleteBeCompCtgyLkup_BusinessEntityComplianceCategoryLookup(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/lookup/compliance/category/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_getBeCompCtgyLkup_BusinessEntityComplianceCategoryLookup(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/lookup/compliance/category")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_getBeCompCtgyLkup_BusinessEntityComplianceCategoryLookup(
            @MatrixParam("bsn_ent_id") String bsnEntId,
            @DefaultValue("name") @MatrixParam("sort_on") String sortOn,
            @DefaultValue("ASC") @MatrixParam("direction") String direction) {

        return null;
    }

    @GET
    @Path("/lookup/compliance/service")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_BSN_getBECompByService(
        @MatrixParam("svc_name") String serviceName,
        @MatrixParam("bsn_ent_id") String bsnEntId,
        @DefaultValue("1") @MatrixParam("page") int page,
        @DefaultValue("50") @MatrixParam("page_size") int pageSize,
        @DefaultValue("name") @MatrixParam("sort_on") String sortOn,
        @DefaultValue("ASC") @MatrixParam("direction") String direction) {

        return null;
    }

    // endregion

    //region BusinessEntityDayOfWeekLookup

    @POST
    @Path("/lookup/day_of_week")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_insertBeDayOfWeekLkup_BusinessEntityDayOfWeekLookup(BusinessEntityDayOfWeekLookup data) {

        return null;
    }

    @PUT
    @Path("/lookup/day_of_week")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_updateBeDayOfWeekLkup_BusinessEntityDayOfWeekLookup(BusinessEntityDayOfWeekLookup data) {

        return null;
    }

    @DELETE
    @Path("/lookup/day_of_week/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_deleteBeDayOfWeekLkup_BusinessEntityDayOfWeekLookup(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/lookup/day_of_week/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_getBeDayOfWeekLkup_BusinessEntityDayOfWeekLookup(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/lookup/day_of_week")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_getBeDayOfWeekLkup_BusinessEntityDayOfWeekLookup(
        @MatrixParam("bsn_ent_id") String bsnEntId) {

        return null;
    }
    //endregion

    //region BusinessEntityDurableMedicalEquipmentAndSupplyLookup

    @POST
    @Path("/lookup/dme_and_supply")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_insertBeDmeAndSupplyLkup_BusinessEntityDurableMedicalEquipmentAndSupplyLookup(BusinessEntityDurableMedicalEquipmentAndSupplyLookup data) {

        return null;
    }

    @PUT
    @Path("/lookup/dme_and_supply")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_updateBeDmeAndSupplyLkup_BusinessEntityDurableMedicalEquipmentAndSupplyLookup(BusinessEntityDurableMedicalEquipmentAndSupplyLookup data) {

        return null;
    }

    @DELETE
    @Path("/lookup/dme_and_supply/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_deleteBeDmeAndSupplyLkup_BusinessEntityDurableMedicalEquipmentAndSupplyLookup(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/lookup/dme_and_supply/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_getBeDmeAndSupplyLkup_BusinessEntityDurableMedicalEquipmentAndSupplyLookup(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/lookup/dme_and_supply")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_getBeDmeAndSupplyLkup_BusinessEntityDurableMedicalEquipmentAndSupplyLookup(
        @MatrixParam("bsn_ent_id") String bsnEntId) {

        return null;
    }
    //endregion

    //region BusinessEntityEmploymentClassLookup

    @POST
    @Path("/lookup/staff/employment_class")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_insertBeEmpltClsLkup_BusinessEntityEmploymentClassLookup(BusinessEntityEmploymentClassLookup data) {

        return null;
    }

    @PUT
    @Path("/lookup/staff/employment_class")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_updateBeEmpltClsLkup_BusinessEntityEmploymentClassLookup(BusinessEntityEmploymentClassLookup data) {

        return null;
    }

    @DELETE
    @Path("/lookup/staff/employment_class/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_deleteBeEmpltClsLkup_BusinessEntityEmploymentClassLookup(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/lookup/staff/employment_class/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_getBeEmpltClsLkup_BusinessEntityEmploymentClassLookup(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/lookup/staff/employment_class")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_getBeEmpltClsLkup_BusinessEntityEmploymentClassLookup(
        @MatrixParam("bsn_ent_id") String bsnEntId,
        @DefaultValue("name") @MatrixParam("sort_on") String sortOn,
        @DefaultValue("ASC") @MatrixParam("direction") String direction) {

        return null;
    }
    //endregion

    //region BusinessEntityExceptionList

    @POST
    @Path("/lookup/exceptions")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_insertBeExcpLst_BusinessEntityExceptionListExt(BusinessEntityExceptionListExt data) {

        return null;
    }

    @PUT
    @Path("/lookup/exceptions")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_updateBeExcpLst_BusinessEntityExceptionListExt(BusinessEntityExceptionListExt data) {

        return null;
    }

    @DELETE
    @Path("/lookup/exceptions/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_deleteBeExcpLst_BusinessEntityExceptionListExt(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/lookup/exceptions/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_getBeExcpLst_BusinessEntityExceptionListExt(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/lookup/exceptions")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_getBeExcpLstPk_BusinessEntityExceptionListExt(
            @MatrixParam("bsn_ent_id") String bsnEntId,
            @MatrixParam("exception_id") Long exceptionId) {

        return null;
    }
    //endregion

    //region BusinessEntityHolidayLookup

    @POST
    @Path("/lookup/holiday")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_insertBeHolidayLkup_BusinessEntityHolidayLookup(BusinessEntityHolidayLookup data) {

        return null;
    }

    @PUT
    @Path("/lookup/holiday")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_updateBeHolidayLkup_BusinessEntityHolidayLookup(BusinessEntityHolidayLookup data) {

        return null;
    }

    @DELETE
    @Path("/lookup/holiday/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_deleteBeHolidayLkup_BusinessEntityHolidayLookup(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/lookup/holiday/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_getBeHolidayLkup_BusinessEntityHolidayLookup(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/lookup/holiday")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_getBeHolidayLkup_BusinessEntityHolidayLookup(
        @MatrixParam("bsn_ent_id") String bsnEntId) {

        return null;
    }
    //endregion

    //region BusinessEntityEvacuationLevelLookup

    @POST
    @Path("/lookup/evacuation_level")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_insertBeEvacLvlLkup_BusinessEntityEvacuationLevelLookup(BusinessEntityEvacuationLevelLookup data) {

        return null;
    }

    @PUT
    @Path("/lookup/evacuation_level")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_updateBeEvacLvlLkup_BusinessEntityEvacuationLevelLookup(BusinessEntityEvacuationLevelLookup data) {

        return null;
    }

    @DELETE
    @Path("/lookup/evacuation_level/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_deleteBeEvacLvlLkup_BusinessEntityEvacuationLevelLookup(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/lookup/evacuation_level/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_getBeEvacLvlLkup_BusinessEntityEvacuationLevelLookup(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/lookup/evacuation_level")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_getBeEvacLvlLkup_BusinessEntityEvacuationLevelLookup(
        @MatrixParam("bsn_ent_id") String bsnEntId,
        @DefaultValue("EVAC_LVL_CODE") @MatrixParam("sort_on") String sortOn,
        @DefaultValue("ASC") @MatrixParam("direction") String direction) {

        return null;
    }
    //endregion

    //region BusinessEntityLanguageList

    @POST
    @Path("/lookup/language")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_insertBeLangLst_BusinessEntityLanguageList(BusinessEntityLanguageList data) {

        return null;
    }

    @PUT
    @Path("/lookup/language")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_updateBeLangLst_BusinessEntityLanguageList(BusinessEntityLanguageList data) {

        return null;
    }

    @DELETE
    @Path("/lookup/language/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_deleteBeLangLst_BusinessEntityLanguageList(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/lookup/language/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_getBeLangLst_BusinessEntityLanguageList(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/lookup/language")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_getBeLangLst_BusinessEntityLanguageList(
        @MatrixParam("bsn_ent_id") String bsnEntId,
        @DefaultValue("name") @MatrixParam("sort_on") String sortOn,
        @DefaultValue("ASC") @MatrixParam("direction") String direction) {

        return null;
    }
    //endregion

    //region BusinessEntityNutritionalRequirementLookup

    @POST
    @Path("/lookup/nutritional_requirements")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_insertBeNutrRqmtLkup_BusinessEntityNutritionalRequirementLookup(BusinessEntityNutritionalRequirementLookup data) {

        return null;
    }

    @PUT
    @Path("/lookup/nutritional_requirements")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_updateBeNutrRqmtLkup_BusinessEntityNutritionalRequirementLookup(BusinessEntityNutritionalRequirementLookup data) {

        return null;
    }

    @DELETE
    @Path("/lookup/nutritional_requirements/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_deleteBeNutrRqmtLkup_BusinessEntityNutritionalRequirementLookup(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/lookup/nutritional_requirements/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_getBeNutrRqmtLkup_BusinessEntityNutritionalRequirementLookup(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/lookup/nutritional_requirements")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_getBeNutrRqmtLkup_BusinessEntityNutritionalRequirementLookup(
        @MatrixParam("bsn_ent_id") String bsnEntId,
        @DefaultValue("name") @MatrixParam("sort_on") String sortOn,
        @DefaultValue("ASC") @MatrixParam("direction") String direction) {

        return null;
    }
    //endregion

    //region BusinessEntityRateTypeLookup

    @POST
    @Path("/lookup/rate_type")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_insertBeRateTypLkup_BusinessEntityRateTypeLookup(BusinessEntityRateTypeLookup data) {

        return null;
    }

    @PUT
    @Path("/lookup/rate_type")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_updateBeRateTypLkup_BusinessEntityRateTypeLookup(BusinessEntityRateTypeLookup data) {

        return null;
    }

    @DELETE
    @Path("/lookup/rate_type/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_deleteBeRateTypLkup_BusinessEntityRateTypeLookup(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/lookup/rate_type/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_getBeRateTypLkup_BusinessEntityRateTypeLookup(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/lookup/rate_type")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_getBeRateTypLkup_BusinessEntityRateTypeLookup(
        @MatrixParam("bsn_ent_id") String bsnEntId,
        @DefaultValue("name") @MatrixParam("sort_on") String sortOn,
        @DefaultValue("ASC") @MatrixParam("direction") String direction) {

        return null;
    }
    //endregion

    //region BusinessEntityRaceEthnicityList

    @POST
    @Path("/lookup/ethnicity")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_insertBeRaceEthnicityLst_BusinessEntityRaceEthnicityList(BusinessEntityRaceEthnicityList data) {

        return null;
    }

    @PUT
    @Path("/lookup/ethnicity")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_updateBeRaceEthnicityLst_BusinessEntityRaceEthnicityList(BusinessEntityRaceEthnicityList data) {

        return null;
    }

    @DELETE
    @Path("/lookup/ethnicity/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_deleteBeRaceEthnicityLst_BusinessEntityRaceEthnicityList(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/lookup/ethnicity/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_getBeRaceEthnicityLst_BusinessEntityRaceEthnicityList(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/lookup/ethnicity")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_getBeRaceEthnicityLst_BusinessEntityRaceEthnicityList(
        @MatrixParam("bsn_ent_id") String bsnEntId,
        @DefaultValue("name") @MatrixParam("sort_on") String sortOn,
        @DefaultValue("ASC") @MatrixParam("direction") String direction) {

        return null;
    }
    //endregion

    //region BusinessEntityReferenceFormatLookup

    @POST
    @Path("/lookup/reference/format")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_insertBeRefFmtLkup_BusinessEntityReferenceFormatLookup(BusinessEntityReferenceFormatLookup data) {

        return null;
    }

    @PUT
    @Path("/lookup/reference/format")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_updateBeRefFmtLkup_BusinessEntityReferenceFormatLookup(BusinessEntityReferenceFormatLookup data) {

        return null;
    }

    @DELETE
    @Path("/lookup/reference/format/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_deleteBeRefFmtLkup_BusinessEntityReferenceFormatLookup(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/lookup/reference/format/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_getBeRefFmtLkup_BusinessEntityReferenceFormatLookup(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/lookup/reference/format")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_getBeRefFmtLkup_BusinessEntityReferenceFormatLookup(
        @MatrixParam("bsn_ent_id") String bsnEntId) {

        return null;
    }
    //endregion

    //region BusinessEntityReferenceLookup

    @POST
    @Path("/lookup/reference")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_insertBeRefLkup_BusinessEntityReferenceLookup(BusinessEntityReferenceLookup data) {

        return null;
    }

    @PUT
    @Path("/lookup/reference")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_updateBeRefLkup_BusinessEntityReferenceLookup(BusinessEntityReferenceLookup data) {

        return null;
    }

    @DELETE
    @Path("/lookup/reference/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_deleteBeRefLkup_BusinessEntityReferenceLookup(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/lookup/reference/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_getBeRefLkup_BusinessEntityReferenceLookup(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/lookup/reference")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_getBeRefLkup_BusinessEntityReferenceLookup(
        @MatrixParam("bsn_ent_id") String bsnEntId) {

        return null;
    }
    //endregion

    //region BusinessEntityReligionList

    @POST
    @Path("/lookup/religion")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_insertBeReligionLst_BusinessEntityReligionList(BusinessEntityReligionList data) {

        return null;
    }

    @PUT
    @Path("/lookup/religion")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_updateBeReligionLst_BusinessEntityReligionList(BusinessEntityReligionList data) {

        return null;
    }

    @DELETE
    @Path("/lookup/religion/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_deleteBeReligionLst_BusinessEntityReligionList(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/lookup/religion/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_getBeReligionLst_BusinessEntityReligionList(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/lookup/religion")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_getBeReligionLst_BusinessEntityReligionList(
        @MatrixParam("bsn_ent_id") String bsnEntId,
        @DefaultValue("name") @MatrixParam("sort_on") String sortOn,
        @DefaultValue("ASC") @MatrixParam("direction") String direction) {

        return null;
    }
    //endregion

    //region BusinessEntityHealthcareProfessionalLookup

    @POST
    @Path("/lookup/healthcare/professional")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_insertBeHcpLkup_BusinessEntityHealthcareProfessionalLookup(BusinessEntityHealthcareProfessionalLookup data) {

        return null;
    }

    @PUT
    @Path("/lookup/healthcare/professional")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_updateBeHcpLkup_BusinessEntityHealthcareProfessionalLookup(BusinessEntityHealthcareProfessionalLookup data) {

        return null;
    }

    @DELETE
    @Path("/lookup/healthcare/professional/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_deleteBeHcpLkup_BusinessEntityHealthcareProfessionalLookup(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/lookup/healthcare/professional/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_getBeHcpLkup_BusinessEntityHealthcareProfessionalLookup(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/lookup/healthcare/professional")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_getBeHcpLkup_BusinessEntityHealthcareProfessionalLookup(
        @MatrixParam("bsn_ent_id") String bsnEntId) {

        return null;
    }

    @GET
    @Path("/lookup/healthcare/professional/find")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_findBeHcpLkup_BusinessEntityHealthcareProfessionalLookup(
            @MatrixParam("keyword") String keyword,
            @MatrixParam("bsn_ent_id") String bsnEntId,
            @DefaultValue("1") @MatrixParam("page") int page,
            @DefaultValue("10") @MatrixParam("page_size") int pageSize,
            @DefaultValue("last_name") @MatrixParam("sort_on") String sortOn,
            @DefaultValue("ASC") @MatrixParam("direction") String direction) {

        return null;
    }
    //endregion

    //region BusinessEntityReferralTypeLookup

    @POST
    @Path("/lookup/referral/type")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_insertBeRfrlTypLkup_BusinessEntityReferralTypeLookup(BusinessEntityReferralTypeLookup data) {

        return null;
    }

    @PUT
    @Path("/lookup/referral/type")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_updateBeRfrlTypLkup_BusinessEntityReferralTypeLookup(BusinessEntityReferralTypeLookup data) {

        return null;
    }

    @DELETE
    @Path("/lookup/referral/type/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_deleteBeRfrlTypLkup_BusinessEntityReferralTypeLookup(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/lookup/referral/type/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_getBeRfrlTypLkup_BusinessEntityReferralTypeLookup(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/lookup/referral/type")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_getBeRfrlTypLkup_BusinessEntityReferralTypeLookup(
        @MatrixParam("bsn_ent_id") String bsnEntId,
        @DefaultValue("name") @MatrixParam("sort_on") String sortOn,
        @DefaultValue("ASC") @MatrixParam("direction") String direction) {

        return null;
    }
    //endregion

    //region BusinessEntitySafetyMeasureLookup

    @POST
    @Path("/lookup/safety_measures")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_insertBeSftyMeasureLkup_BusinessEntitySafetyMeasureLookup(BusinessEntitySafetyMeasureLookup data) {

        return null;
    }

    @PUT
    @Path("/lookup/safety_measures")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_updateBeSftyMeasureLkup_BusinessEntitySafetyMeasureLookup(BusinessEntitySafetyMeasureLookup data) {

        return null;
    }

    @DELETE
    @Path("/lookup/safety_measures/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_deleteBeSftyMeasureLkup_BusinessEntitySafetyMeasureLookup(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/lookup/safety_measures/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_getBeSftyMeasureLkup_BusinessEntitySafetyMeasureLookup(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/lookup/safety_measures")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_getBeSftyMeasureLkup_BusinessEntitySafetyMeasureLookup(
        @MatrixParam("bsn_ent_id") String bsnEntId,
        @DefaultValue("name") @MatrixParam("sort_on") String sortOn,
        @DefaultValue("ASC") @MatrixParam("direction") String direction) {

        return null;
    }
    //endregion

    //region BusinessEntitySkillLookup

    @POST
    @Path("/lookup/staff/skill_set")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_insertBeSkillLkup_BusinessEntitySkillLookup(BusinessEntitySkillLookup data) {

        return null;
    }

    @PUT
    @Path("/lookup/staff/skill_set")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_updateBeSkillLkup_BusinessEntitySkillLookup(BusinessEntitySkillLookup data) {

        return null;
    }

    @DELETE
    @Path("/lookup/staff/skill_set/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_deleteBeSkillLkup_BusinessEntitySkillLookup(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/lookup/staff/skill_set/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_getBeSkillLkup_BusinessEntitySkillLookup(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/lookup/staff/skill_set")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_getBeSkillLkup_BusinessEntitySkillLookup(
        @MatrixParam("bsn_ent_id") String bsnEntId,
        @DefaultValue("name") @MatrixParam("sort_on") String sortOn,
        @DefaultValue("ASC") @MatrixParam("direction") String direction) {

        return null;
    }
    //endregion

    //region BusinessEntityTaxLookup

    @POST
    @Path("/lookup/tax")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_insertBeTaxLkup_BusinessEntityTaxLookup(BusinessEntityTaxLookup data) {

        return null;
    }

    @PUT
    @Path("/lookup/tax")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_updateBeTaxLkup_BusinessEntityTaxLookup(BusinessEntityTaxLookup data) {

        return null;
    }

    @DELETE
    @Path("/lookup/tax/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_deleteBeTaxLkup_BusinessEntityTaxLookup(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/lookup/tax/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_getBeTaxLkup_BusinessEntityTaxLookup(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/lookup/tax")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_getBeTaxLkup_BusinessEntityTaxLookup(
        @MatrixParam("bsn_ent_id") String bsnEntId) {

        return null;
    }
    //endregion

    //region BusinessEntityTypeOfCareLookup

    @POST
    @Path("/lookup/type_of_care")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_insertBeTypOfCareLkup_BusinessEntityTypeOfCareLookup(BusinessEntityTypeOfCareLookup data) {

        return null;
    }

    @PUT
    @Path("/lookup/type_of_care")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_updateBeTypOfCareLkup_BusinessEntityTypeOfCareLookup(BusinessEntityTypeOfCareLookup data) {

        return null;
    }

    @DELETE
    @Path("/lookup/type_of_care/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_deleteBeTypOfCareLkup_BusinessEntityTypeOfCareLookup(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/lookup/type_of_care/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_getBeTypOfCareLkup_BusinessEntityTypeOfCareLookup(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/lookup/type_of_care")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_getBeTypOfCareLkup_BusinessEntityTypeOfCareLookup(
        @MatrixParam("bsn_ent_id") String bsnEntId) {

        return null;
    }
    //endregion

    //region BusinessEntityVisitNoteTypeLookup

    @POST
    @Path("/lookup/visit/note/type")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_insertBeVisitNoteTypLkup_BusinessEntityVisitNoteTypeLookup(BusinessEntityVisitNoteTypeLookup data) {

        return null;
    }

    @PUT
    @Path("/lookup/visit/note/type")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_updateBeVisitNoteTypLkup_BusinessEntityVisitNoteTypeLookup(BusinessEntityVisitNoteTypeLookup data) {

        return null;
    }

    @DELETE
    @Path("/lookup/visit/note/type/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_deleteBeVisitNoteTypLkup_BusinessEntityVisitNoteTypeLookup(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/lookup/visit/note/type/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_getBeVisitNoteTypLkup_BusinessEntityVisitNoteTypeLookup(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/lookup/visit/note/type")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_getBeVisitNoteTypLkup_BusinessEntityVisitNoteTypeLookup(
        @MatrixParam("bsn_ent_id") String bsnEntId) {

        return null;
    }
    //endregion

    //region BusinessEntityLineOfBusinessLookup

    @POST
    @Path("/lookup/line_of_business")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_insertBeLobLkup_BusinessEntityLineOfBusinessLookup(
                @MatrixParam("bsn_ent_id") String bsnEntId,
                BusinessEntityLineOfBusinessLookup data) {

        return null;
    }

    @PUT
    @Path("/lookup/line_of_business")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_updateBeLobLkup_BusinessEntityLineOfBusinessLookup(
            @MatrixParam("bsn_ent_id") String bsnEntId,
            BusinessEntityLineOfBusinessLookup data) {

        return null;
    }

    @DELETE
    @Path("/lookup/line_of_business/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_deleteBeLobLkup_BusinessEntityLineOfBusinessLookup(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/lookup/line_of_business/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_getBeLobLkup_BusinessEntityLineOfBusinessLookup(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/lookup/line_of_business")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_getBeLobLkupPK_BusinessEntityLineOfBusinessLookup(
            @MatrixParam("bsn_ent_id") String bsnEntId,
            @DefaultValue("BE_LOB") @MatrixParam("sort_on") String sortOn,
            @DefaultValue("ASC") @MatrixParam("direction") String direction) {

        return null;
    }

    /*@POST
    @Path("/lookup/line_of_business/list")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_insertBeLobLkupList_BusinessEntityLineOfBusinessLookup(
            @MatrixParam("bsn_ent_id") String bsnEntId,
            List<String> lobList) {

        return null;
    }

    @PUT
    @Path("/lookup/line_of_business/list")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_updateBeLobLkupList_BusinessEntityLineOfBusinessLookup(
            @MatrixParam("bsn_ent_id") String bsnEntId,
            List<String> lobList) {

        return null;
    }

    @DELETE
    @Path("/lookup/line_of_business/list")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_deleteBeLobLkupList_BusinessEntityLineOfBusinessLookup(
            @MatrixParam("bsn_ent_id") String bsnEntId,
            List<String> lobList) {

        return null;
    }*/

    //endregion

    //region BusinessEntityAccountsReceivableNoteTypeLookup

    @POST
    @Path("/lookup/accounts/receivable/note/type")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_insertBeArNoteTypLkup_BusinessEntityAccountsReceivableNoteTypeLookup(BusinessEntityAccountsReceivableNoteTypeLookup data) {

        return null;
    }

    @PUT
    @Path("/lookup/accounts/receivable/note/type")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_updateBeArNoteTypLkup_BusinessEntityAccountsReceivableNoteTypeLookup(BusinessEntityAccountsReceivableNoteTypeLookup data) {

        return null;
    }

    @DELETE
    @Path("/lookup/accounts/receivable/note/type/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_deleteBeArNoteTypLkup_BusinessEntityAccountsReceivableNoteTypeLookup(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/lookup/accounts/receivable/note/type/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_getBeArNoteTypLkup_BusinessEntityAccountsReceivableNoteTypeLookup(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/lookup/accounts/receivable/note/type")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_getBeArNoteTypLkup_BusinessEntityAccountsReceivableNoteTypeLookup(
            @MatrixParam("bsn_ent_id") String bsnEntId) {

        return null;
    }

    //endregion

    //region BusinessEntityAdministrativeStaffRoleLookup

    @POST
    @Path("/lookup/administrative/staff/role")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_insertBeAdminStaffRoleLkup_BusinessEntityAdministrativeStaffRoleLookup(BusinessEntityAdministrativeStaffRoleLookup data) {

        return null;
    }

    @PUT
    @Path("/lookup/administrative/staff/role")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_updateBeAdminStaffRoleLkup_BusinessEntityAdministrativeStaffRoleLookup(BusinessEntityAdministrativeStaffRoleLookup data) {

        return null;
    }

    @DELETE
    @Path("/lookup/administrative/staff/role/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_deleteBeAdminStaffRoleLkup_BusinessEntityAdministrativeStaffRoleLookup(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/lookup/administrative/staff/role/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_getBeAdminStaffRoleLkup_BusinessEntityAdministrativeStaffRoleLookup(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/lookup/administrative/staff/role")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_getBeAdminStaffRoleLkup_BusinessEntityAdministrativeStaffRoleLookup(
            @MatrixParam("bsn_ent_id") String bsnEntId) {

        return null;
    }

    //endregion

    //endregion

    //region Medications

    //region BusinessEntityMedicationClassificationLookup

    @POST
    @Path("/lookup/medication/classification")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_insertBeMedClasLkup_BusinessEntityMedicationClassificationLookup(BusinessEntityMedicationClassificationLookup data) {

        return null;
    }

    @PUT
    @Path("/lookup/medication/classification")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_updateBeMedClasLkup_BusinessEntityMedicationClassificationLookup(BusinessEntityMedicationClassificationLookup data) {

        return null;
    }

    @DELETE
    @Path("/lookup/medication/classification/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_deleteBeMedClasLkup_BusinessEntityMedicationClassificationLookup(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/lookup/medication/classification/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_getBeMedClasLkup_BusinessEntityMedicationClassificationLookup(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/lookup/medication/classification")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_getBeMedClasLkup_BusinessEntityMedicationClassificationLookup(
        @MatrixParam("bsn_ent_id") String bsnEntId) {

        return null;
    }
    //endregion

    //region BusinessEntityMedicationDosageFrequencyLookup

    @POST
    @Path("/lookup/medication/dosage/frequency")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_insertBeMedDosageFreqLkup_BusinessEntityMedicationDosageFrequencyLookup(BusinessEntityMedicationDosageFrequencyLookup data) {

        return null;
    }

    @PUT
    @Path("/lookup/medication/dosage/frequency")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_updateBeMedDosageFreqLkup_BusinessEntityMedicationDosageFrequencyLookup(BusinessEntityMedicationDosageFrequencyLookup data) {

        return null;
    }

    @DELETE
    @Path("/lookup/medication/dosage/frequency/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_deleteBeMedDosageFreqLkup_BusinessEntityMedicationDosageFrequencyLookup(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/lookup/medication/dosage/frequency/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_getBeMedDosageFreqLkup_BusinessEntityMedicationDosageFrequencyLookup(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/lookup/medication/dosage/frequency")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_getBeMedDosageFreqLkup_BusinessEntityMedicationDosageFrequencyLookup(
        @MatrixParam("bsn_ent_id") String bsnEntId,
        @DefaultValue("name") @MatrixParam("sort_on") String sortOn,
        @DefaultValue("ASC") @MatrixParam("direction") String direction) {

        return null;
    }
    //endregion

    //region BusinessEntityMedicationDosageStrengthLookup

    @POST
    @Path("/lookup/medication/dosage/strength")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_insertBeMedDosageStrgLkup_BusinessEntityMedicationDosageStrengthLookup(BusinessEntityMedicationDosageStrengthLookup data) {

        return null;
    }

    @PUT
    @Path("/lookup/medication/dosage/strength")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_updateBeMedDosageStrgLkup_BusinessEntityMedicationDosageStrengthLookup(BusinessEntityMedicationDosageStrengthLookup data) {

        return null;
    }

    @DELETE
    @Path("/lookup/medication/dosage/strength/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_deleteBeMedDosageStrgLkup_BusinessEntityMedicationDosageStrengthLookup(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/lookup/medication/dosage/strength/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_getBeMedDosageStrgLkup_BusinessEntityMedicationDosageStrengthLookup(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/lookup/medication/dosage/strength")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_getBeMedDosageStrgLkup_BusinessEntityMedicationDosageStrengthLookup(
        @MatrixParam("bsn_ent_id") String bsnEntId) {

        return null;
    }
    //endregion

    //region BusinessEntityMedicationLookup

    @POST
    @Path("/lookup/medication")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_insertBeMedLkup_BusinessEntityMedicationLookup(BusinessEntityMedicationLookup data) {

        return null;
    }

    @PUT
    @Path("/lookup/medication")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_updateBeMedLkup_BusinessEntityMedicationLookup(BusinessEntityMedicationLookup data) {

        return null;
    }

    @DELETE
    @Path("/lookup/medication/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_deleteBeMedLkup_BusinessEntityMedicationLookup(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/lookup/medication/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_getBeMedLkup_BusinessEntityMedicationLookup(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/lookup/medication")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_getBeMedLkup_BusinessEntityMedicationLookup(
        @MatrixParam("bsn_ent_id") String bsnEntId) {

        return null;
    }
    //endregion

    //region MedicationRouteLookup

    @POST
    @Path("/lookup/medication/route")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_insertBeMedRouteLkup_BusinessEntityMedicationRouteLookup(BusinessEntityMedicationRouteLookup data) {

        return null;
    }

    @PUT
    @Path("/lookup/medication/route")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_updateBeMedRouteLkup_BusinessEntityMedicationRouteLookup(BusinessEntityMedicationRouteLookup data) {

        return null;
    }

    @DELETE
    @Path("/lookup/medication/route/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_deleteBeMedRouteLkup_BusinessEntityMedicationRouteLookup(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/lookup/medication/route/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_getBeMedRouteLkup_BusinessEntityMedicationRouteLookup(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/lookup/medication/route")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_getBeMedRouteLkup_BusinessEntityMedicationRouteLookup(
        @MatrixParam("bsn_ent_id") String bsnEntId) {

        return null;
    }
    //endregion
    //endregion

    //region Payer

    //region BusinessEntityPayerTypeLookup

    @POST
    @Path("/lookup/payer/type")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_insertBePayerTypLkup_BusinessEntityPayerTypeLookup(BusinessEntityPayerTypeLookup data) {

        return null;
    }

    @PUT
    @Path("/lookup/payer/type")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_updateBePayerTypLkup_BusinessEntityPayerTypeLookup(BusinessEntityPayerTypeLookup data) {

        return null;
    }

    @DELETE
    @Path("/lookup/payer/type/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_deleteBePayerTypLkup_BusinessEntityPayerTypeLookup(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/lookup/payer/type/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_getBePayerTypLkup_BusinessEntityPayerTypeLookup(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/lookup/payer/type")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_getBePayerTypLkup_BusinessEntityPayerTypeLookup(
        @MatrixParam("bsn_ent_id") String bsnEntId) {

        return null;
    }
    //endregion

    //region PayerBillingCodeList

    @POST
    @Path("/lookup/payer/billing/code")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_LOOKUP_insertPayerBillingCodeLkup_PayerBillingCodeLookup(PayerBillingCodeLookup data) {

        return null;
    }

    @PUT
    @Path("/lookup/payer/billing/code")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_LOOKUP_updatePayerBillingCodeLkup_PayerBillingCodeLookup(PayerBillingCodeLookup data) {

        return null;
    }

    @DELETE
    @Path("/lookup/payer/billing/code/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_LOOKUP_deletePayerBillingCodeLkup_PayerBillingCodeLookup(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/lookup/payer/billing/code/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_LOOKUP_getPayerBillingCodeLkup_PayerBillingCodeLookup(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/lookup/payer/billing/code")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_LOOKUP_getPayerBillingCodeLkupByPayerID_PayerBillingCodeLookup(
        @MatrixParam("bsn_ent_id") String bsnEntId,
        @MatrixParam("payer_id") String payerId) { // this is not supported in method getPayerBillingCodeLkup

        return null;
    }

    @POST
    @Path("/lookup/payer/billing/code/list")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_insertPayerBillingCodeLstIdList_PayerBillingCodeList(
        @MatrixParam("bsn_ent_id") String bsnEntId,
        @MatrixParam("payer_id") String payerId,
        List<String> billingCodeList) {

        return null;
    }

    @PUT
    @Path("/lookup/payer/billing/code/list")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_updatePayerBillingCodeLstIdList_PayerBillingCodeList(
        @MatrixParam("bsn_ent_id") String bsnEntId,
        @MatrixParam("payer_id") String payerId,
        List<String> billingCodeList) {

        return null;
    }

    @DELETE
    @Path("/lookup/payer/billing/code/list")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_deletePayerBillingCodeLstIdList_PayerBillingCodeList(
        @MatrixParam("bsn_ent_id") String bsnEntId,
        @MatrixParam("payer_id") String payerId,
        List<String> billingCodeList) {

        return null;
    }
    //endregion

    //region PayerBillingCodeList

    @POST
    @Path("/lookup/payer/revenue/code")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_insertPayerRevCodeLst_PayerRevenueCodeList(PayerRevenueCodeList data) {

        return null;
    }

    @PUT
    @Path("/lookup/payer/revenue/code")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_updatePayerRevCodeLst_PayerRevenueCodeList(PayerRevenueCodeList data) {

        return null;
    }

    @DELETE
    @Path("/lookup/payer/revenue/code/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_deletePayerRevCodeLst_PayerRevenueCodeList(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/lookup/payer/revenue/code/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_getPayerRevCodeLst_PayerRevenueCodeList(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/lookup/payer/revenue/code")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_getPayerRevCodeLst_PayerRevenueCodeList(
            @MatrixParam("bsn_ent_id") String bsnEntId,
            @MatrixParam("payer_id") String payerId) {

        return null;
    }

    @POST
    @Path("/lookup/payer/revenue/code/list")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_insertPayerRevCodeLst_PayerRevenueCodeList(
            @MatrixParam("bsn_ent_id") String bsnEntId,
            @MatrixParam("payer_id") String payerId,
            List<String> revCodeList) {

        return null;
    }

    @PUT
    @Path("/lookup/payer/revenue/code/list")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_updatePayerRevCodeLst_PayerRevenueCodeList(
            @MatrixParam("bsn_ent_id") String bsnEntId,
            @MatrixParam("payer_id") String payerId,
            List<String> revCodeList) {

        return null;
    }

    @DELETE
    @Path("/lookup/payer/revenue/code/list")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_deletePayerRevCodeLst_PayerRevenueCodeList(
            @MatrixParam("bsn_ent_id") String bsnEntId,
            @MatrixParam("payer_id") String payerId,
            List<String> revCodeList) {

        return null;
    }
    //endregion

    //region ContractBillingCodeList

    @POST
    @Path("/lookup/payer/contract/billing/code")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_insertContrBillingCodeLst_ContractBillingCodeList(ContractBillingCodeList data) {

        return null;
    }

    @PUT
    @Path("/lookup/payer/contract/billing/code")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_updateContrBillingCodeLst_ContractBillingCodeList(ContractBillingCodeList data) {

        return null;
    }

    @DELETE
    @Path("/lookup/payer/contract/billing/code/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_deleteContrBillingCodeLst_ContractBillingCodeList(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/lookup/payer/contract/billing/code/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_getContrBillingCodeLst_ContractBillingCodeList(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/lookup/payer/contract/billing/code")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_getContrBillingCodeLst_ContractBillingCodeList(
        @MatrixParam("bsn_ent_id") String bsnEntId,
        @MatrixParam("payer_id") String payerId,
        @MatrixParam("contract_id") String contractId) {

        return null;
    }
    //endregion

    //region ContractModifierList

    @POST
    @Path("/lookup/payer/contract/modifier")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_insertContrMdfrLst_ContractModifierList(ContractModifierList data) {

        return null;
    }

    @PUT
    @Path("/lookup/payer/contract/modifier")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_updateContrMdfrLst_ContractModifierList(ContractModifierList data) {

        return null;
    }

    @DELETE
    @Path("/lookup/payer/contract/modifier/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_deleteContrMdfrLst_ContractModifierList(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/lookup/payer/contract/modifier/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_getContrMdfrLst_ContractModifierList(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/lookup/payer/contract/modifier")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_getContrMdfrLst_ContractModifierList(
        @MatrixParam("bsn_ent_id") String bsnEntId,
        @MatrixParam("payer_id") String payerId,
        @MatrixParam("contract_id") String contractId) {

        return null;
    }
    //endregion

    //region ContractRateTypeList

    @POST
    @Path("/lookup/payer/contract/rate_type")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_insertContrRateTypLst_ContractRateTypeList(ContractRateTypeList data) {

        return null;
    }

    @PUT
    @Path("/lookup/payer/contract/rate_type")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_updateContrRateTypLst_ContractRateTypeList(ContractRateTypeList data) {

        return null;
    }

    @DELETE
    @Path("/lookup/payer/contract/rate_type/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_deleteContrRateTypLst_ContractRateTypeList(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/lookup/payer/contract/rate_type/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_getContrRateTypLst_ContractRateTypeList(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/lookup/payer/contract/rate_type")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_getContrRateTypLst_ContractRateTypeList(
        @MatrixParam("bsn_ent_id") String bsnEntId,
        @MatrixParam("payer_id") String payerId,
        @MatrixParam("contract_id") String contractId) {

        return null;
    }
    //endregion

    //region ContractServiceList

    @POST
    @Path("/lookup/payer/contract/service")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_insertContrSvcLst_ContractServiceList(ContractServiceList data) {

        return null;
    }

    @PUT
    @Path("/lookup/payer/contract/service")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_updateContrSvcLst_ContractServiceList(ContractServiceList data) {

        return null;
    }

    @DELETE
    @Path("/lookup/payer/contract/service/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_deleteContrSvcLst_ContractServiceList(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/lookup/payer/contract/service/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_getContrSvcLst_ContractServiceList(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/lookup/payer/contract/service")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_getContrSvcLst_ContractServiceList(
        @MatrixParam("bsn_ent_id") String bsnEntId,
        @MatrixParam("payer_id") String payerId,
        @MatrixParam("contract_id") String contractId) {

        return null;
    }
    //endregion

    //region ContractTask

    @POST
    @Path("/contract/task")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_CONTRACT_insertContrTask_ContractTask(ContractTask data) {

        return null;
    }

    @PUT
    @Path("/contract/task")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_CONTRACT_updateContrTask_ContractTask(ContractTask data) {

        return null;
    }

    @DELETE
    @Path("/contract/task/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_CONTRACT_deleteContrTask_ContractTask(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/contract/task/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_CONTRACT_getContrTask_ContractTask(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/contract/tasks")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_CONTRACT_getContrTasks_ContractTask(
            @MatrixParam("svc_name") String service,
            @MatrixParam("contract_id") String contractId) {

        return null;
    }
    //endregion

    //region PayerLineOfBusinessList

    @POST
    @Path("/lookup/payer/line_of_business")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_insertPayerLobLst_PayerLineOfBusinessList(PayerLineOfBusinessList data) {

        return null;
    }

    @PUT
    @Path("/lookup/payer/line_of_business")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_updatePayerLobLst_PayerLineOfBusinessList(PayerLineOfBusinessList data) {

        return null;
    }

    @DELETE
    @Path("/lookup/payer/line_of_business/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_deletePayerLobLst_PayerLineOfBusinessList(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/lookup/payer/line_of_business/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_getPayerLobLst_PayerLineOfBusinessList(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/lookup/payer/line_of_business")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_getPayerLobLst_PayerLineOfBusinessList(
        @MatrixParam("bsn_ent_id") String bsnEntId,
        @MatrixParam("payer_id") String payerId) {

        return null;
    }

    @POST
    @Path("/lookup/payer/line_of_business/list")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_insertPayerLobLstList_PayerLineOfBusinessList(
        @MatrixParam("bsn_ent_id") String bsnEntId,
        @MatrixParam("payer_id") String payerId,
        List<String> lobList) {

        return null;
    }

    @PUT
    @Path("/lookup/payer/line_of_business/list")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_updatePayerLobLstList_PayerLineOfBusinessList(
        @MatrixParam("bsn_ent_id") String bsnEntId,
        @MatrixParam("payer_id") String payerId,
        List<String> lobList) {

        return null;
    }

    @DELETE
    @Path("/lookup/payer/line_of_business/list")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_deletePayerLobLstList_PayerLineOfBusinessList(
        @MatrixParam("bsn_ent_id") String bsnEntId,
        @MatrixParam("payer_id") String payerId,
        List<String> lobList) {

        return null;
    }
    //endregion

    //region PayerModifierLookup

    @POST
    @Path("/lookup/payer/modifier")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_LOOKUP_insertPayerMdfrLkup_PayerModifierLookup(PayerModifierLookup data) {

        return null;
    }

    @PUT
    @Path("/lookup/payer/modifier")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_LOOKUP_updatePayerMdfrLkup_PayerModifierLookup(PayerModifierLookup data) {

        return null;
    }

    @DELETE
    @Path("/lookup/payer/modifier/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_LOOKUP_deletePayerMdfrLkup_PayerModifierLookup(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/lookup/payer/modifier/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_LOOKUP_getPayerMdfrLkup_PayerModifierLookup(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/lookup/payer/modifier")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_LOOKUP_getPayerMdfrLkup_PayerModifierLookup(
        @MatrixParam("bsn_ent_id") String bsnEntId,
        @MatrixParam("payer_id") String payerId) {

        return null;
    }

    @POST
    @Path("/lookup/payer/modifier/list")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_LOOKUP_insertPayerMdfrLstList_PayerModifierLookup(
        @MatrixParam("bsn_ent_id") String bsnEntId,
        @MatrixParam("payer_id") String payerId,
        List<String> modifierCodeList) {

        return null;
    }

    @PUT
    @Path("/lookup/payer/modifier/list")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_LOOKUP_updatePayerMdfrLstList_PayerModifierLookup(
        @MatrixParam("bsn_ent_id") String bsnEntId,
        @MatrixParam("payer_id") String payerId,
        List<String> modifierCodeList) {

        return null;
    }

    @DELETE
    @Path("/lookup/payer/modifier/list")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_LOOKUP_deletePayerMdfrLstList_PayerModifierLookup(
        @MatrixParam("bsn_ent_id") String bsnEntId,
        @MatrixParam("payer_id") String payerId,
        List<String> modifierCodeList) {

        return null;
    }
    //endregion

    //region PayerRateTypeList

    @POST
    @Path("/lookup/payer/rate_type")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_insertPayerRateTypLst_PayerRateTypeList(PayerRateTypeList data) {

        return null;
    }

    @PUT
    @Path("/lookup/payer/rate_type")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_updatePayerRateTypLst_PayerRateTypeList(PayerRateTypeList data) {

        return null;
    }

    @DELETE
    @Path("/lookup/payer/rate_type/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_deletePayerRateTypLst_PayerRateTypeList(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/lookup/payer/rate_type/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_getPayerRateTypLst_PayerRateTypeList(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/lookup/payer/rate_type")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_getPayerRateTypLst_PayerRateTypeList(
        @MatrixParam("bsn_ent_id") String bsnEntId,
        @MatrixParam("payer_id") String payerId) {

        return null;
    }

    @POST
    @Path("/lookup/payer/rate_type/list")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_insertPayerRateTypLstList_PayerRateTypeList(
        @MatrixParam("bsn_ent_id") String bsnEntId,
        @MatrixParam("payer_id") String payerId,
        List<String> rateTypeNameList) {

        return null;
    }

    @PUT
    @Path("/lookup/payer/rate_type/list")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_updatePayerRateTypLstList_PayerRateTypeList(
        @MatrixParam("bsn_ent_id") String bsnEntId,
        @MatrixParam("payer_id") String payerId,
        List<String> rateTypeNameList) {

        return null;
    }

    @DELETE
    @Path("/lookup/payer/rate_type/list")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_deletePayerRateTypLstList_PayerRateTypeList(
        @MatrixParam("bsn_ent_id") String bsnEntId,
        @MatrixParam("payer_id") String payerId,
        List<String> rateTypeNameList) {

        return null;
    }
    //endregion

    //region PayerServiceList

    @POST
    @Path("/lookup/payer/service")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_insertPayerSvcLst_PayerServiceList(PayerServiceList data) {
        return null;
    }

    @PUT
    @Path("/lookup/payer/service")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_updatePayerSvcLst_PayerServiceList(PayerServiceList data) {
        return null;
    }

    @DELETE
    @Path("/lookup/payer/service/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})

    public final Response PKG_AM_deletePayerSvcLst_PayerServiceList(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/lookup/payer/service/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_getPayerSvcLst_PayerServiceList(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @POST
    @Path("/lookup/payer/service/list")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_insertPayerServiceList_PayerServiceList(@MatrixParam("payer_id") String payerId,
                                                                         @MatrixParam("bsn_ent_id") String bsnEntId,
                                                                         List<String> serviceNameList) {
        return null;
    }

    @PUT
    @Path("/lookup/payer/service/list")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_updatePayerServiceList_PayerServiceList(@MatrixParam("payer_id") String payerId,
                                                                         @MatrixParam("bsn_ent_id") String bsnEntId,
                                                                         List<String> serviceNameList) {
        return null;
    }

    @DELETE
    @Path("/lookup/payer/service/list")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_deletePayerServiceList_PayerServiceList(@MatrixParam("payer_id") String payerId,
                                                                         @MatrixParam("bsn_ent_id") String bsnEntId,
                                                                         List<String> serviceNameList) {
        return null;
    }

    @GET
    @Path("/lookup/payer/service")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_getPayerSvcLst_PayerServiceList(
        @MatrixParam("bsn_ent_id") String bsnEntId,
        @MatrixParam("payer_id") String payerId) {

        return null;
    }

    //endregion

    //endregion

    //region Patient

    //region BusinessEntityPatientNoteTypeLookup

    @POST
    @Path("/lookup/patient/note/type")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_insertBePtNoteTypLkup_BusinessEntityPatientNoteTypeLookup(BusinessEntityPatientNoteTypeLookup data) {

        return null;
    }

    @PUT
    @Path("/lookup/patient/note/type")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_updateBePtNoteTypLkup_BusinessEntityPatientNoteTypeLookup(BusinessEntityPatientNoteTypeLookup data) {

        return null;
    }

    @DELETE
    @Path("/lookup/patient/note/type/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_deleteBePtNoteTypLkup_BusinessEntityPatientNoteTypeLookup(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/lookup/patient/note/type/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_getBePtNoteTypLkup_BusinessEntityPatientNoteTypeLookup(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/lookup/patient/note/type")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_getBePtNoteTypLkupByBEID_BusinessEntityPatientNoteTypeLookup(
        @MatrixParam("bsn_ent_id") String bsnEntId,
        @DefaultValue("name") @MatrixParam("sort_on") String sortOn,
        @DefaultValue("ASC") @MatrixParam("direction") String direction) {

        return null;
    }

    //endregion

    //region BusinessEntityPatientPriorityLevelLookup

    @POST
    @Path("/lookup/patient/priority_level")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_insertBePtPrioLvlLkup_BusinessEntityPatientPriorityLevelLookup(BusinessEntityPatientPriorityLevelLookup data) {

        return null;
    }

    @PUT
    @Path("/lookup/patient/priority_level")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_updateBePtPrioLvlLkup_BusinessEntityPatientPriorityLevelLookup(BusinessEntityPatientPriorityLevelLookup data) {

        return null;
    }

    @DELETE
    @Path("/lookup/patient/priority_level/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_deleteBePtPrioLvlLkup_BusinessEntityPatientPriorityLevelLookup(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/lookup/patient/priority_level/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_getBePtPrioLvlLkup_BusinessEntityPatientPriorityLevelLookup(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/lookup/patient/priority_level")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_getBePtPrioLvlLkup_BusinessEntityPatientPriorityLevelLookup(
        @MatrixParam("bsn_ent_id") String bsnEntId,
        @DefaultValue("name") @MatrixParam("sort_on") String sortOn,
        @DefaultValue("ASC") @MatrixParam("direction") String direction) {

        return null;
    }

    //endregion

    //region BusinessEntityPatientRequirementLookup

    @POST
    @Path("/lookup/patient/requirement")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_insertBePtRqmtLkup_BusinessEntityPatientRequirementLookup(BusinessEntityPatientRequirementLookup data) {

        return null;
    }

    @PUT
    @Path("/lookup/patient/requirement")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_updateBePtRqmtLkup_BusinessEntityPatientRequirementLookup(BusinessEntityPatientRequirementLookup data) {

        return null;
    }

    @DELETE
    @Path("/lookup/patient/requirement/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_deleteBePtRqmtLkup_BusinessEntityPatientRequirementLookup(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/lookup/patient/requirement/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_getBePtRqmtLkup_BusinessEntityPatientRequirementLookup(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/lookup/patient/requirement")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_getBePtRqmtLkup_BusinessEntityPatientRequirementLookup(
            @MatrixParam("bsn_ent_id") String bsnEntId,
            @DefaultValue("name") @MatrixParam("sort_on") String sortOn,
            @DefaultValue("ASC") @MatrixParam("direction") String direction) {

        return null;
    }

    //endregion

    //endregion

    //region Staff

    //region BusinessEntityStaffTrainingLookup

    @POST
    @Path("/lookup/staff/training")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_insertBeStaffTrngLkup_BusinessEntityStaffTrainingLookup(BusinessEntityStaffTrainingLookup data) {

        return null;
    }

    @PUT
    @Path("/lookup/staff/training")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_updateBeStaffTrngLkup_BusinessEntityStaffTrainingLookup(BusinessEntityStaffTrainingLookup data) {

        return null;
    }

    @DELETE
    @Path("/lookup/staff/training/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_deleteBeStaffTrngLkup_BusinessEntityStaffTrainingLookup(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/lookup/staff/training/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_getBeStaffTrngLkup_BusinessEntityStaffTrainingLookup(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/lookup/staff/training")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_getBeStaffTrngLkup_BusinessEntityStaffTrainingLookup(
        @MatrixParam("bsn_ent_id") String bsnEntId,
        @DefaultValue("") @MatrixParam("staff_trng_name") String staffTrngName,
        @DefaultValue("1") @MatrixParam("page") int page,
        @DefaultValue("50") @MatrixParam("page_size") int pageSize,
        @DefaultValue("REC_CREATE_TMSTP") @MatrixParam("sort_on") String sortOn,
        @DefaultValue("ASC") @MatrixParam("direction") String direction) {

        return null;
    }

    @GET
    @Path("/lookup/staff/training/classes/service")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_BSN_getBEStaffTrngClassesByService(
        @MatrixParam("svc_name") String serviceName,
        @MatrixParam("bsn_ent_id") String bsnEntId,
        @DefaultValue("1") @MatrixParam("page") int page,
        @DefaultValue("50") @MatrixParam("page_size") int pageSize,
        @DefaultValue("name") @MatrixParam("sort_on") String sortOn,
        @DefaultValue("ASC") @MatrixParam("direction") String direction) {

        return null;
    }

    @GET
    @Path("/lookup/staff/training/classes/category")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_BSN_getBEStaffTrngClassesByCategory(
    		@MatrixParam("category") String category,
            @MatrixParam("bsn_ent_id") String bsnEntId,
            @MatrixParam("staff_trng_name") String staffTrngName,
            @DefaultValue("1") @MatrixParam("page") int page,
            @DefaultValue("50") @MatrixParam("page_size") int pageSize,
            @DefaultValue("name") @MatrixParam("sort_on") String sortOn,
            @DefaultValue("ASC") @MatrixParam("direction") String direction) {
    	
    	return null;
    }

    @GET
    @Path("/lookup/be/training/locations")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_BSN_getBEStaffTrngLocations(
            @MatrixParam("bsn_ent_id") String bsnEntId,
            @DefaultValue("1") @MatrixParam("page") int page,
            @DefaultValue("50") @MatrixParam("page_size") int pageSize,
            @DefaultValue("name") @MatrixParam("sort_on") String sortOn,
            @DefaultValue("ASC") @MatrixParam("direction") String direction) {

        return null;
    }
    
    
    //endregion

    //region BusinessEntityStaffTrainingCategory
    @POST
    @Path("/lookup/staff/training/category")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_insertBeStaffTrngCtgyLkup_BusinessEntityStaffTrainingCategoryLookup(BusinessEntityStaffTrainingCategoryLookup data) {

        return null;
    }

    @PUT
    @Path("/lookup/staff/training/category")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_updateBeStaffTrngCtgyLkup_BusinessEntityStaffTrainingCategoryLookup(BusinessEntityStaffTrainingCategoryLookup data) {

        return null;
    }

    @DELETE
    @Path("/lookup/staff/training/category/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_deleteBeStaffTrngCtgyLkup_BusinessEntityStaffTrainingCategoryLookup(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/lookup/staff/training/category/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_getBeStaffTrngCtgyLkup_BusinessEntityStaffTrainingCategoryLookup(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/lookup/staff/training/category")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_getBeStaffTrngCtgyLkup_BusinessEntityStaffTrainingCategoryLookup(
        @MatrixParam("bsn_ent_id") String bsnEntId,
        @DefaultValue("name") @MatrixParam("sort_on") String sortOn,
        @DefaultValue("ASC") @MatrixParam("direction") String direction) {

        return null;
    }


    //endregion BusinessEntityStaffTrainingCategory

    // region BusinessEntityStaffTrainingRelationship

    @POST
    @Path("/lookup/staff/training/relationship")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_BSN_insertBeStaffTrngRel_BusinessEntityStaffTrainingRelationship(BusinessEntityStaffTrainingRelationship data) {
        return null;
    }

    @PUT
    @Path("/lookup/staff/training/relationship")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_BSN_updateBeStaffTrngRel_BusinessEntityStaffTrainingRelationship(BusinessEntityStaffTrainingRelationship data) {
        return null;
    }

    @DELETE
    @Path("/lookup/staff/training/relationship/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_BSN_deleteBeStaffTrngRel_BusinessEntityStaffTrainingRelationship(@PathParam("sequence_key") long sequenceKey) {
        return null;
    }

    @GET
    @Path("/lookup/staff/training/relationship/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_BSN_getBeStaffTrngRel_BusinessEntityStaffTrainingRelationship(@PathParam("sequence_key") long sequenceKey) {
        return null;
    }

    @GET
    @Path("/lookup/staff/training/relationship")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_BSN_getBeStaffTrngRel_BusinessEntityStaffTrainingRelationship(@MatrixParam("bsn_ent_id") String bsnEntId,
                                                                                            @MatrixParam("staff_trng_code") String staffTrngCode) {
        return null;
    }

    // endregion

    // region BusinessEntityStaffTrainingRelationshipDetail

    @POST
    @Path("/lookup/staff/training/relationship/detail")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_BSN_insertBeStaffTrngRelDet_BusinessEntityStaffTrainingRelationshipDetail(BusinessEntityStaffTrainingRelationshipDetail data) {
        return null;
    }

    @PUT
    @Path("/lookup/staff/training/relationship/detail")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_BSN_updateBeStaffTrngRelDet_BusinessEntityStaffTrainingRelationshipDetail(BusinessEntityStaffTrainingRelationshipDetail data) {
        return null;
    }

    @DELETE
    @Path("/lookup/staff/training/relationship/detail/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_BSN_deleteBeStaffTrngRelDet_BusinessEntityStaffTrainingRelationshipDetail(@PathParam("sequence_key") long sequenceKey) {
        return null;
    }

    @GET
    @Path("/lookup/staff/training/relationship/detail/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_BSN_getBeStaffTrngRelDet_BusinessEntityStaffTrainingRelationshipDetail(@PathParam("sequence_key") long sequenceKey) {
        return null;
    }

    // endregion

    //region BusinessEntityStaffTypeLookup

    @POST
    @Path("/lookup/staff/type")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_insertBeStaffTypLkup_BusinessEntityStaffTypeLookup(BusinessEntityStaffTypeLookup data) {

        return null;
    }

    @PUT
    @Path("/lookup/staff/type")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_updateBeStaffTypLkup_BusinessEntityStaffTypeLookup(BusinessEntityStaffTypeLookup data) {

        return null;
    }

    @DELETE
    @Path("/lookup/staff/type/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_deleteBeStaffTypLkup_BusinessEntityStaffTypeLookup(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/lookup/staff/type/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_getBeStaffTypLkup_BusinessEntityStaffTypeLookup(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/lookup/staff/type")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_getBeStaffTypLkup_BusinessEntityStaffTypeLookup(
        @MatrixParam("bsn_ent_id") String bsnEntId) {

        return null;
    }
    //endregion

    //region BusinessEntityStaffWorkingPreferenceLookup

    @POST
    @Path("/lookup/staff/working/preference")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_insertBeStaffWrkPrefLkup_BusinessEntityStaffWorkingPreferenceLookup(BusinessEntityStaffWorkingPreferenceLookup data) {

        return null;
    }

    @PUT
    @Path("/lookup/staff/working/preference")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_updateBeStaffWrkPrefLkup_BusinessEntityStaffWorkingPreferenceLookup(BusinessEntityStaffWorkingPreferenceLookup data) {

        return null;
    }

    @DELETE
    @Path("/lookup/staff/working/preference/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_deleteBeStaffWrkPrefLkup_BusinessEntityStaffWorkingPreferenceLookup(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/lookup/staff/working/preference/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_getBeStaffWrkPrefLkup_BusinessEntityStaffWorkingPreferenceLookup(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/lookup/staff/working/preference")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_getBeStaffWrkPrefLkup_BusinessEntityStaffWorkingPreferenceLookup(
        @MatrixParam("bsn_ent_id") String bsnEntId,
        @DefaultValue("name") @MatrixParam("sort_on") String sortOn,
        @DefaultValue("ASC") @MatrixParam("direction") String direction) {

        return null;
    }
    //endregion

    //region BusinessEntityStaffReferralTypeLookup

    @POST
    @Path("/lookup/staff/referral/type")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_insertBeStaffRfrlTypLkup_BusinessEntityStaffReferralTypeLookup(BusinessEntityStaffReferralTypeLookup data) {

        return null;
    }

    @PUT
    @Path("/lookup/staff/referral/type")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_updateBeStaffRfrlTypLkup_BusinessEntityStaffReferralTypeLookup(BusinessEntityStaffReferralTypeLookup data) {

        return null;
    }

    @DELETE
    @Path("/lookup/staff/referral/type/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_deleteBeStaffRfrlTypLkup_BusinessEntityStaffReferralTypeLookup(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/lookup/staff/referral/type/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_getBeStaffRfrlTypLkup_BusinessEntityStaffReferralTypeLookup(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/lookup/staff/referral/type")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_getBeStaffRfrlTypLkup_BusinessEntityStaffReferralTypeLookup(
        @MatrixParam("bsn_ent_id") String bsnEntId) {

        return null;
    }
    //endregion

    //region BusinessEntityStaffNoteTypeLookup

    @POST
    @Path("/lookup/staff/note/type")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_insertBeStaffNoteTypLkup_BusinessEntityStaffNoteTypeLookup(BusinessEntityStaffNoteTypeLookup data) {

        return null;
    }

    @PUT
    @Path("/lookup/staff/note/type")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_updateBeStaffNoteTypLkup_BusinessEntityStaffNoteTypeLookup(BusinessEntityStaffNoteTypeLookup data) {

        return null;
    }

    @DELETE
    @Path("/lookup/staff/note/type/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_deleteBeStaffNoteTypLkup_BusinessEntityStaffNoteTypeLookup(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/lookup/staff/note/type/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_getBeStaffNoteTypLkup_BusinessEntityStaffNoteTypeLookup(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/lookup/staff/note/type")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_getBeStaffNoteTypLkupByBEID_BusinessEntityStaffNoteTypeLookup(
        @MatrixParam("bsn_ent_id") String bsnEntId,
        @DefaultValue("name") @MatrixParam("sort_on") String sortOn,
        @DefaultValue("ASC") @MatrixParam("direction") String direction) {

        return null;
    }
    //endregion

    //region BusinessEntityStaffPayrollTypeLookup

    @POST
    @Path("/lookup/staff/payroll/type")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_insertBeStaffPrTypLkup_BusinessEntityStaffPayrollTypeLookup(BusinessEntityStaffPayrollTypeLookup data) {

        return null;
    }

    @PUT
    @Path("/lookup/staff/payroll/type")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_updateBeStaffPrTypLkup_BusinessEntityStaffPayrollTypeLookup(BusinessEntityStaffPayrollTypeLookup data) {

        return null;
    }

    @DELETE
    @Path("/lookup/staff/payroll/type/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_deleteBeStaffPrTypLkup_BusinessEntityStaffPayrollTypeLookup(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/lookup/staff/payroll/type/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_getBeStaffPrTypLkup_BusinessEntityStaffPayrollTypeLookup(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/lookup/staff/payroll/type")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_getBeStaffPrTypLkup_BusinessEntityStaffPayrollTypeLookup(
        @MatrixParam("bsn_ent_id") String bsnEntId) {

        return null;
    }

    @GET
    @Path("/lookup/staff/position")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_getStaffPosition_StaffPosition(
        @MatrixParam("bsn_ent_id") String bsnEntId) {

        return null;
    }
    //endregion

    //endregion

    //region Payroll

    //region BusinessEntityPayrollCodeLookup

    @POST
    @Path("/lookup/payroll/code")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_insertBePrCodeLkup_BusinessEntityPayrollCodeLookup(BusinessEntityPayrollCodeLookup data) {

        return null;
    }

    @PUT
    @Path("/lookup/payroll/code")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_updateBePrCodeLkup_BusinessEntityPayrollCodeLookup(BusinessEntityPayrollCodeLookup data) {

        return null;
    }

    @DELETE
    @Path("/lookup/payroll/code/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_deleteBePrCodeLkup_BusinessEntityPayrollCodeLookup(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/lookup/payroll/code/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_getBePrCodeLkup_BusinessEntityPayrollCodeLookup(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/lookup/payroll/code")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_getBePrCodeLkup_BusinessEntityPayrollCodeLookup(
        @MatrixParam("bsn_ent_id") String bsnEntId) {

        return null;
    }
    //endregion

    //endregion

    //region PKG_APP - Metadata

    //region ApplicationTenantKeyConfiguration

    @GET
    @Path("/generate/staff/id/{bsn_ent_id}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_APP_generateStaffId_ApplicationTenantKeyConfiguration(@PathParam("bsn_ent_id") String bsnEntId) {

        return null;
    }


    @GET
    @Path("/generate/patient/id/{bsn_ent_id}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_APP_generatePatientId_ApplicationTenantKeyConfiguration(@PathParam("bsn_ent_id") String bsnEntId) {

        return null;
    }

    @POST
    @Path("/settings/patient_staff")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_APP_insertAppTenantKeyConf_ApplicationTenantKeyConfiguration(ApplicationTenantKeyConfiguration data,
                                                                                           @MatrixParam("bsn_ent_id") String bsnEntId) {

        return null;
    }

    @PUT
    @Path("/settings/patient_staff")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_APP_updateAppTenantKeyConf_ApplicationTenantKeyConfiguration(ApplicationTenantKeyConfiguration data,
                                                                                           @MatrixParam("bsn_ent_id") String bsnEntId) {

        return null;
    }

    @DELETE
    @Path("/settings/patient_staff/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_APP_deleteAppTenantKeyConf_ApplicationTenantKeyConfiguration(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }


    @GET
    @Path("/settings/patient_staff/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_APP_getAppTenantKeyConf_ApplicationTenantKeyConfiguration(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/settings/patient_staff")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_APP_getAppTenantKeyConf_ApplicationTenantKeyConfiguration(@MatrixParam("bsn_ent_id") String bsnEntId,
                                                                                        @MatrixParam("key_name") String keyName) {

        return null;
    }
    //endregion

    //endregion
    
    //region Staff Training Category Service
    
    @POST
    @Path("/staff/training/category/service")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_STAFF_saveStaffTrngCtgySvcList(List<StaffTrainingCategoryService> staffTrngCateSvcList) {

        return null;
    }
    
    @PUT
    @Path("/staff/training/category/service")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_STAFF_updateStaffTrngCtgySvc_StaffTrainingCategoryService(StaffTrainingCategoryService staffTrngCateSvc) {

        return null;
    }
    
    @GET
    @Path("/staff/training/category/service/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_STAFF_getStaffTrngCtgySvc_StaffTrainingCategoryService(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }
    
    @GET
    @Path("/staff/training/category/service/list")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_STAFF_getStaffTrngCtgySvcList_StaffTrainingCategoryService(
    		@MatrixParam("bsn_ent_id") String bsnEntId,
    		@DefaultValue("1") @MatrixParam("page") int page,//8
            @DefaultValue("10") @MatrixParam("page_size") int pageSize) {

        return null;
    }
    
    @DELETE
    @Path("/staff/training/category/service/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_STAFF_deleteStaffTrngCtgySvc_StaffTrainingCategoryService(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }
    
    @DELETE
    @Path("/staff/training/category/service/list")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_STAFF_deleteStaffTrngCtgySvcList_StaffTrainingCategoryService(
        List<BigInteger> staffTrngCtgySvcList) {

        return null;
    }
    @GET
    @Path("/staff/training/category/name/can_add")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_STAFF_getStaffTrngCtgryNameCanAdd_StaffTrainingCategoryService(
            @MatrixParam("bsn_ent_id") String bsnEntId,
            @MatrixParam("name") String name ) {
        return null;
    }
  //endregion
    
    // region Total hours by date ranges

    @GET
    @Path("/lookup/staff/work_hours")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_getStaffWorkedHoursByRange_StaffWorkedHoursResult(
            @MatrixParam("bsn_ent_id") String bsnEntId,
            @MatrixParam("staff_id") String staffId,
            @MatrixParam("ranges") List<String> ranges) {

        return null;
    }

    @GET
    @Path("/lookup/patient/schedule_hours")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_getPatientScheduledHoursByRange_PatientScheduledHoursResult(
            @MatrixParam("bsn_ent_id") String bsnEntId,
            @MatrixParam("patient_id") String patientId,
            @MatrixParam("ranges") List<String> ranges) {

        return null;
    }
    
    // endregion

    //region ApplicationTenantKeyConfiguration

    @POST
    @Path("/settings")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_APP_insertAppTenantKeyConfForBEID_ApplicationTenantKeyConfiguration(
            ApplicationTenantKeyConfiguration applicationTenantKeyConfiguration,
            @MatrixParam("bsn_ent_id") String bsnEntId) {

        return null;
    }

    @PUT
    @Path("/settings")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_APP_updateAppTenantKeyConfForBEID_ApplicationTenantKeyConfiguration(
            ApplicationTenantKeyConfiguration applicationTenantKeyConfiguration,
            @MatrixParam("bsn_ent_id") String bsnEntId) {

        return null;
    }

    @DELETE
    @Path("/settings/{app_tenant_key_conf_sk}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_APP_deleteAppTenantKeyConfForBEID_ApplicationTenantKeyConfiguration(
            @PathParam("app_tenant_key_conf_sk") Long appTenantKeyConfSK) {

        return null;
    }

    @GET
    @Path("/settings")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_APP_getAppTenantKeyConfForName_ApplicationTenantKeyConfiguration(
            @MatrixParam("key_name") String keyName,
            @MatrixParam("bsn_ent_id") String bsnEntId) {

        return null;
    }

    @GET
    @Path("/settings/system")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_APP_getAppSysKeyConfForName_ApplicationTenantKeyConfiguration(
            @MatrixParam("key_name") String keyName) {

        return null;
    }

    //endregion

    //region Visit Verification Rounding Rules Additional Settings

    @POST
    @Path("/lookup/visit/verification/rounding/rules/configuration")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_LOOKUP_insertVvRndingRuleSettings_VisitVerificationRoundingRuleSetting(
            @MatrixParam("bsn_ent_id") String bsnEntId,
            @MatrixParam("payer_id") String payerId,
            @MatrixParam("contract_id") String contractId,
            VisitVerificationRoundingRuleSetting data) {

        return null;
    }

    @PUT
    @Path("/lookup/visit/verification/rounding/rules/configuration")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_LOOKUP_updateVvRndingRuleSettings_VisitVerificationRoundingRuleSetting(
            @MatrixParam("bsn_ent_id") String bsnEntId,
            @MatrixParam("payer_id") String payerId,
            @MatrixParam("contract_id") String contractId,
            VisitVerificationRoundingRuleSetting data) {

        return null;
    }

    @DELETE
    @Path("/lookup/visit/verification/rounding/rules/configuration")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_LOOKUP_deleteVvRndingRuleSettings_VisitVerificationRoundingRuleSetting(
            @MatrixParam("bsn_ent_id") String bsnEntId,
            @MatrixParam("payer_id") String payerId,
            @MatrixParam("contract_id") String contractId,
            @MatrixParam("rule_id") VisitVerificationRoundingRuleID ruleId,
            @MatrixParam("qualifier") VisitVerificationRoundingRuleQualifier qualifier) {

        return null;
    }

    @GET
    @Path("/lookup/visit/verification/rounding/rules/configuration")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_LOOKUP_getVvRndingRuleSettings_VisitVerificationRoundingRuleSetting(
            @MatrixParam("bsn_ent_id") String bsnEntId,
            @MatrixParam("payer_id") String payerId,
            @MatrixParam("contract_id") String contractId,
            @MatrixParam("rule_id") VisitVerificationRoundingRuleID ruleId,
            @MatrixParam("qualifier") VisitVerificationRoundingRuleQualifier qualifier) {

        return null;
    }

    //dmr--Get with filter and sorting options in the Lookup Service project

    //endregion

    //region ContractExceptionList

    @POST
    @Path("/lookup/payer/contract/exceptions")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_insertContrExcpLst_ContractExceptionListExt(
            @MatrixParam("bsn_ent_id") String bsnEntId,
            ContractExceptionListExt data) {

        return null;
    }

    @PUT
    @Path("/lookup/payer/contract/exceptions")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_updateContrExcpLst_ContractExceptionListExt(
            @MatrixParam("bsn_ent_id") String bsnEntId,
            ContractExceptionListExt data) {

        return null;
    }

    @DELETE
    @Path("/lookup/payer/contract/exceptions/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_deleteContrExcpLst_ContractExceptionListExt(
            @MatrixParam("bsn_ent_id") String bsnEntId,
            @PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/lookup/payer/contract/exceptions/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_getContrExcpLst_ContractExceptionListExt(
            @MatrixParam("bsn_ent_id") String bsnEntId,
            @PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/lookup/payer/contract/exceptions")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_getContrExcpLstForID_ContractExceptionListExt(
            @MatrixParam("bsn_ent_id") String bsnEntId,
            @MatrixParam("payer_id") String payerId,
            @MatrixParam("contract_id") String contractId) {

        return null;
    }
    //endregion ContractExceptionList

    //endregion
}
