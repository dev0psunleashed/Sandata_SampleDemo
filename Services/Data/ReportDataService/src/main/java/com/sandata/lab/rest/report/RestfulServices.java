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

package com.sandata.lab.rest.report;


import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.sandata.lab.data.model.response.Response;

public class RestfulServices {

	@GET
	@Path("/payrollhours/location")
	@Produces(value = {MediaType.APPLICATION_JSON})
	public final Response PKG_report_getTotalPayrollHours_LocationResponse(
			@MatrixParam("bsn_ent_id") String bsnEntId,
			@DefaultValue("1") @MatrixParam("bsn_ent_lvl") int bsnEntLvl,
			@MatrixParam("from_date") String fromDate,
			@MatrixParam("to_date") String toDate,
			@DefaultValue("pending") @MatrixParam("status") String pending) {
		
		return null;
	}

	@GET
	@Path("/payrollhours/detail")
	@Produces(value = {MediaType.APPLICATION_JSON})
	public final Response PKG_report_getTotalPayrollHoursDetail_LocationResponse(
			@MatrixParam("bsn_ent_id") String bsnEntId,
			@DefaultValue("1") @MatrixParam("bsn_ent_lvl") int bsnEntLvl,
			@MatrixParam("from_date") String fromDate,
			@MatrixParam("to_date") String toDate,
			@DefaultValue("pending") @MatrixParam("status") String pending) {

		return null;
	}

	@GET
    @Path("/payrolldollars/location")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_report_getTotalPayrollDollars_LocationResponse(
            @MatrixParam("bsn_ent_id") String bsnEntId,
			@DefaultValue("1") @MatrixParam("bsn_ent_lvl") int bsnEntLvl,
            @MatrixParam("from_date") String fromDate,
            @MatrixParam("to_date") String toDate,
            @DefaultValue("pending") @MatrixParam("status") String pending) {

        return null;
    }
	@GET
	@Path("/payrolldollars/detail")
	@Produces(value = {MediaType.APPLICATION_JSON})
	public final Response PKG_report_getTotalPayrollDollarsDetail_LocationResponse(
			@MatrixParam("bsn_ent_id") String bsnEntId,
			@DefaultValue("1") @MatrixParam("bsn_ent_lvl") int bsnEntLvl,
			@MatrixParam("from_date") String fromDate,
			@MatrixParam("to_date") String toDate,
			@DefaultValue("pending") @MatrixParam("status") String pending) {

		return null;
	}

	@GET
	@Path("/overtimehours/location")
	@Produces(value = {MediaType.APPLICATION_JSON})
	public final Response PKG_report_getTotalOverTimeHours_LocationResponse(
			@MatrixParam("bsn_ent_id") String bsnEntId,
			@DefaultValue("1") @MatrixParam("bsn_ent_lvl") int bsnEntLvl,
			@MatrixParam("from_date") String fromDate,
			@MatrixParam("to_date") String toDate) {

		return null;
	}

	@GET
	@Path("/overtimehours/detail")
	@Produces(value = {MediaType.APPLICATION_JSON})
	public final Response PKG_report_getTotalOverTimeHoursDetail_LocationResponse(
			@MatrixParam("bsn_ent_id") String bsnEntId,
			@DefaultValue("1") @MatrixParam("bsn_ent_lvl") int bsnEntLvl,
			@MatrixParam("from_date") String fromDate,
			@MatrixParam("to_date") String toDate) {

		return null;
	}
	
	/**
	 * 
	 * @param bsnEntId
	 * @param bsnEntLvl
	 * @param fromDate
	 * @param toDate
	 * @return
	 */
	@GET
	@Path("/scheduledhours/location")
	@Produces(value = {MediaType.APPLICATION_JSON})
	public final Response PKG_report_getScheduledHours(
			@MatrixParam("bsn_ent_id") String bsnEntId,
			@DefaultValue("1") @MatrixParam("bsn_ent_lvl") int bsnEntLvl,
			@MatrixParam("from_date") String fromDate,
			@MatrixParam("to_date") String toDate) {

		return null;
	}

	/**
	 *
	 * @param bsnEntId
	 * @param bsnEntLvl
	 * @param fromDate
	 * @param toDate
	 * @return
	 */
	@GET
	@Path("/scheduledhours/detail")
	@Produces(value = {MediaType.APPLICATION_JSON})
	public final Response PKG_report_getScheduledHoursDetails(
			@MatrixParam("bsn_ent_id") String bsnEntId,
			@DefaultValue("1") @MatrixParam("bsn_ent_lvl") int bsnEntLvl,
			@MatrixParam("from_date") String fromDate,
			@MatrixParam("to_date") String toDate) {

		return null;
	}
	
	   /**
     * Total Count of Expired Authorizations for a Business Entity Per Location
     * 
     * @param bsnEntId
     * @param fromDate
     * @param toDate
     * @return
     *
     */
    @GET
    @Path("/expiredauth/location")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_report_getExpiredAuth(
            @MatrixParam("bsn_ent_id") String bsnEntId,
            @DefaultValue("1") @MatrixParam("bsn_ent_lvl") int bsnEntLvl,
            @MatrixParam("from_date") String fromDate,
            @MatrixParam("to_date") String toDate) {

        return null;
    }
    
    
    /**
     * Get details of Expired Authorizations for a Business Entity Per Location
     * 
     * @param bsnEntId
     * @param fromDate
     * @param toDate
     * @return
     *
     */
    @GET
    @Path("/expiredauth/detail")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_report_getExpiredAuthDetails(
            @MatrixParam("bsn_ent_id") String bsnEntId,
            @DefaultValue("1") @MatrixParam("bsn_ent_lvl") int bsnEntLvl,
            @MatrixParam("from_date") String fromDate,
            @MatrixParam("to_date") String toDate) {

        return null;
    }


    /**
     *
     * @param bsnEntId
     * @param bsnEntLvl
     * @param fromDate
     * @param toDate
     * @return
     */
    @GET
    @Path("/verifiedhours/location")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_report_getVerifiedHours(
			@MatrixParam("bsn_ent_id") String bsnEntId,
			@DefaultValue("1") @MatrixParam("bsn_ent_lvl") int bsnEntLvl,
			@MatrixParam("from_date") String fromDate,
			@MatrixParam("to_date") String toDate) {

		return null;
	}
    
    /**
     * Get details of Expired Authorizations for a Business Entity Per Location
     * 
     * @param bsnEntId
     * @param fromDate
     * @param toDate
     * @return
     *
     */
    @GET
    @Path("/visitexceptions/detail")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_report_getVisitExcpBreakdown(
            @MatrixParam("bsn_ent_id") String bsnEntId,
            @DefaultValue("1") @MatrixParam("bsn_ent_lvl") int bsnEntLvl,
            @MatrixParam("from_date") String fromDate,
            @MatrixParam("to_date") String toDate) {

        return null;
    }

    
    
    
    /**
     * Get number of visit
     * @param bsnEntId
     * @param bsnEntLvl
     * @param fromDate
     * @param toDate
     * @return*/
    @GET
    @Path("/verifiedhours/detail")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_report_getVerifiedHoursDetails(
			@MatrixParam("bsn_ent_id") String bsnEntId,
			@DefaultValue("1") @MatrixParam("bsn_ent_lvl") int bsnEntLvl,
			@MatrixParam("from_date") String fromDate,
			@MatrixParam("to_date") String toDate) {

		return null;
	}


	@GET
	@Path("/visitexceptions/location")
	@Produces(value = {MediaType.APPLICATION_JSON})
	public final Response PKG_report_getVisitExcecptions(
			@MatrixParam("bsn_ent_id") String bsnEntId,
			@DefaultValue("1") @MatrixParam("bsn_ent_lvl") int bsnEntLvl,
			@MatrixParam("from_date") String fromDate,
			@MatrixParam("to_date") String toDate) {

		return null;
	}


   /**
    * Staffs with Exceptions for Each Location
    *  
    * @param bsnEntId
    * @param fromDate
    * @param toDate
    * @return
    *
    */
   @GET
   @Path("/visitexceptions/detail/staff")
   @Produces(value = {MediaType.APPLICATION_JSON})
   public final Response PKG_report_getStaffsExceptions(
           @MatrixParam("bsn_ent_id") String bsnEntId,
           @DefaultValue("1") @MatrixParam("bsn_ent_lvl") int bsnEntLvl,
           @MatrixParam("from_date") String fromDate,
           @MatrixParam("to_date") String toDate,
	       @MatrixParam("staff_id") String staffID){
       return null;
   }
   
     
	
   /**
    * REST Endpoint > Total Open Orders for Location
    * @param bsnEntId
    * @param bsnEntLvl
    * @param fromDate
    * @param toDate
    * @return
    */
   @GET	
   @Path("/openorders/location")
   @Produces(value = {MediaType.APPLICATION_JSON})
   public final Response PKG_report_getOpenOrders(
		   @MatrixParam("bsn_ent_id") String bsnEntId,
		   @DefaultValue("1") @MatrixParam("bsn_ent_lvl") int bsnEntLvl,
		   @MatrixParam("from_date") String fromDate,
		   @MatrixParam("to_date") String toDate) {
	
	   return null;
   }
	
	//	REST Endpoint > List of Coordinators for the Location for Open Orders
	@GET
	@Path("/openorders/coordinators")
	@Produces(value = {MediaType.APPLICATION_JSON})
	public final Response PKG_report_getListCoordinators(
	        @MatrixParam("bsn_ent_id") String bsnEntId,
	        @DefaultValue("1") @MatrixParam("bsn_ent_lvl") int bsnEntLvl,
	        @MatrixParam("from_date") String fromDate,
	        @MatrixParam("to_date") String toDate) {
	
	    return null;
	}
	
	
	//	REST Endpoint > List of Coordinators for the Location for Open Orders
	@GET
	@Path("/openorders/patients")
	@Produces(value = {MediaType.APPLICATION_JSON})
	public final Response PKG_report_getListPatients(
			@MatrixParam("staff_id") String staffId,
	        @MatrixParam("bsn_ent_id") String bsnEntId,
	        @DefaultValue("1") @MatrixParam("bsn_ent_lvl") int bsnEntLvl,
	        @MatrixParam("from_date") String fromDate,
	        @MatrixParam("to_date") String toDate) {
	
	    return null;
	}

	@GET
	@Path("/outofcompliance/location")
	@Produces(value = {MediaType.APPLICATION_JSON})
	public final Response PKG_report_getOutOfCompliance_LocationResponse(
			@MatrixParam("bsn_ent_id") String bsnEntId,
			@DefaultValue("1") @MatrixParam("bsn_ent_lvl") int bsnEntLvl,
			@MatrixParam("from_date") String fromDate,
			@MatrixParam("to_date") String toDate) {

		return null;
	}

	@GET
	@Path("/outofcompliance/detail")
	@Produces(value = {MediaType.APPLICATION_JSON})
	public final Response PKG_report_getOutOfComplianceDetail_LocationResponse(
			@MatrixParam("bsn_ent_id") String bsnEntId,
			@DefaultValue("1") @MatrixParam("bsn_ent_lvl") int bsnEntLvl,
			@MatrixParam("from_date") String fromDate,
			@MatrixParam("to_date") String toDate) {

		return null;
	}
}
