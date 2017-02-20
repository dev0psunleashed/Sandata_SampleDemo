package com.sandata.one.drools.utils;

import java.math.BigInteger;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.drools.core.spi.KnowledgeHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sandata.lab.data.model.dl.model.extended.exception.BusinessEntityExceptionListExt;
import com.sandata.lab.data.model.dl.model.extended.exception.ContractExceptionListExt;
import com.sandata.lab.rules.vv.fact.ScheduleEventFact;
import com.sandata.lab.rules.vv.fact.VisitEventFact;
import com.sandata.lab.rules.vv.fact.VisitFact;
import com.sandata.lab.rules.vv.fact.PatientPhoneNumbersFact;
import com.sandata.lab.rules.vv.fact.VisitServiceValidationFact;


public class CommonUtils {

	private static Logger logger = LoggerFactory.getLogger(CommonUtils.class);
	private CommonUtils() {

	}

	/**
	 *
	 * @param values List of string need to be convert to string with sql format, used in IN clause
	 * @return comman seperated string
	 */
	public static String convertListToStringParams (List<String> values) {
		StringBuilder resultStr = new StringBuilder();
		for (int i = 0; i< values.size() ; i ++ ) {

			resultStr.append("'").append(values.get(i)).append("'");
			if(i < values.size() - 1) {
				resultStr.append(",");
			}

		}

		return resultStr.toString();
	}


	public static String buildStaffIdLikeConditions (List<String> staffIds) {
		StringBuilder staffIdLikeConditions = new StringBuilder();
		if (staffIds != null && !staffIds.isEmpty()) {
			staffIdLikeConditions.append("AND ( ");
			int counter = 0;
			for (String staffId : staffIds) {
				if (counter != 0) {
					staffIdLikeConditions.append(" OR ");
				}
				staffIdLikeConditions.append(" STAFF_ID = '");
				staffIdLikeConditions.append(staffId);
				staffIdLikeConditions.append("'");

				counter ++;
			}

			staffIdLikeConditions.append(" )");
		}
		return staffIdLikeConditions.toString();
	}


	/**
	 * Help method to log the triggered drools rule name.
	 * @param drools
	 * @param message
	 */
	public static void help(final KnowledgeHelper drools, final String message){
		logger.info(message);
		logger.info("\nrule triggered: " + drools.getRule().getName());
	}

	/**
	 * Help method to log the triggered drools rule name.
	 * @param drools
	 */
	public static void helper(final KnowledgeHelper drools){
		logger.info("\nrule triggered: " + drools.getRule().getName());
	}
	
	/**
	 * Check the input date is in a specific date range or not.
	 * @param inputDate
	 * @param fromDate
	 * @param toDate
	 * @return
	 */
	public static boolean isInDateRange(Date inputDate, Date fromDate, Date toDate) {
	    if (inputDate.getTime() >= fromDate.getTime() && inputDate.getTime() <= toDate.getTime()) {
	        return true;
	    } else {
	      return false;
	    }
	}

	/**
	 * Get the date to check whether it is after the exception effective date that an agency set. 
	 * The scheduled start time if it exists.
	 * If it does not exist, then it is the Call In Time. 
	 * If the schedule start time does not exist and the call in time is not available, use the adjusted start time received as part of the visit.  
	 * @param scheduleEventFact
	 * @param visitEventFact
	 * @param visitFact
	 * @return
	 */
	public static Date getVisitDate(ScheduleEventFact scheduleEventFact, VisitEventFact visitEventFact, VisitFact visitFact) {
	    if(scheduleEventFact != null && scheduleEventFact.getScheduleEventStartDatetime() != null) {
	    	return scheduleEventFact.getScheduleEventStartDatetime();
	    }

	    if(visitEventFact != null && visitEventFact.getCallInTime() != null && visitEventFact.isCallInIndicator()) {
	    	return visitEventFact.getCallInTime();
	    }
	    
	    if(visitFact != null && visitFact.getVisitAdjustedStartTimestamp() != null) {
	    	return visitFact.getVisitAdjustedStartTimestamp();
	    }    
	    return null;
	}

    /**
     * Get all the applicable exception IDs 
     * @param scheduleEventFact
     * @param visitEventFact
     * @param visitFact
     * @return
     */
	public static List<BigInteger> getApplicableExceptions(ScheduleEventFact scheduleEventFact, VisitEventFact visitEventFact, VisitFact visitFact) {

		Date visitDate = getVisitDate(scheduleEventFact, visitEventFact, visitFact);
		
		List<BigInteger> validExceptionList = new ArrayList<BigInteger>();
	    List<BusinessEntityExceptionListExt> applicableBeExceptionList = visitFact.getApplicableBeExceptionList();
	    List<ContractExceptionListExt> applicableContractExceptionList = visitFact.getApplicableContractExceptionList();
	    
		if(visitDate == null) {
			return validExceptionList;
		}
		
		if(visitFact == null || 
			(applicableBeExceptionList == null && applicableContractExceptionList == null)) {
			return validExceptionList;	
		}
		
		if(applicableContractExceptionList != null) {
			for(ContractExceptionListExt contractException : applicableContractExceptionList) {
			    if(isInDateRange(visitDate,  contractException.getRecordEffectiveTimestamp(), contractException.getRecordTerminationTimestamp())){
			    	validExceptionList.add(contractException.getExceptionID());
			    }
			}
		}
		
		
		if(applicableBeExceptionList != null) {
			for(BusinessEntityExceptionListExt beException : applicableBeExceptionList) {
			    if(isInDateRange(visitDate,  beException.getRecordEffectiveTimestamp(), beException.getRecordTerminationTimestamp())){
			    	validExceptionList.add(beException.getExceptionID());
			    }
			}
		}
		return validExceptionList;
	}
	
	/**
	 * 
	 * @param visitFact
	 * @param visitEventFact
	 * @return
	 */
	public static boolean verifyAniWithPatientPhoneList(VisitEventFact visitEventFact, PatientPhoneNumbersFact patientPhoneNumbersFact, ScheduleEventFact scheduleEventFact, VisitFact visitFact) {
		
		logger.info("visitEventFact --------------> vv_data_content is: \n" + visitEventFact);
		logger.info("patientPhoneNumbersFact --------------> vv_data_content is: \n" + patientPhoneNumbersFact);
		logger.info("scheduleEventFact --------------> vv_data_content is: \n" + scheduleEventFact);
		logger.info("visitFact --------------> vv_data_content is: \n" + visitFact);
		
		List<BigInteger> applicableExeptions = getApplicableExceptions(scheduleEventFact, visitEventFact, visitFact);
		
		if(!applicableExeptions.contains(Constants.EXCEPTION_TYPE.UNMATCHED_PATIENT_ID_PHONE.getExceptionId())) {
			return true;
		}
				
		if(patientPhoneNumbersFact != null && patientPhoneNumbersFact.getPatientPhoneNumbersList() != null && visitEventFact != null) {
			return patientPhoneNumbersFact.getPatientPhoneNumbersList().contains(visitEventFact.getAni());
		}
		
		return false;
	}
	
	/**
	 * 
	 * @param visitFact
	 * @param visitEventFact
	 * @return
	 */
	public static boolean verifyVisitService(VisitEventFact visitEventFact, VisitServiceValidationFact visitServiceValidationFact, ScheduleEventFact scheduleEventFact, VisitFact visitFact) {
		List<BigInteger> applicableExeptions = getApplicableExceptions(scheduleEventFact, visitEventFact, visitFact);
		
		if(!applicableExeptions.contains(Constants.EXCEPTION_TYPE.MISSING_SERVICE.getExceptionId())) {
			return true;
		}
				
		if(visitServiceValidationFact != null && visitServiceValidationFact.getServicesList() != null && visitServiceValidationFact.getServicesList().size() > 0) {
			return true;
		}
		
		return false;
	}	
}
