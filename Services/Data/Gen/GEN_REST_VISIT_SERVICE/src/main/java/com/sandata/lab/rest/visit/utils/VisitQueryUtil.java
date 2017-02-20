package com.sandata.lab.rest.visit.utils;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.data.model.response.Response;
import com.sandata.lab.rest.visit.model.PatientForSearch;
import com.sandata.lab.rest.visit.model.StaffForSearch;
import com.sandata.lab.rest.visit.utils.constants.App;

public class VisitQueryUtil {

	private static Logger logger = LoggerFactory.getLogger("QueryUtil");

	/**
	 * 
	 * @param columns
	 * @param direction
	 * @return
	 */
	public String buildOrderByClause(List<String> columns, String direction) {
		if(columns == null || columns.size() == 0) {
			return "";
		}

		StringBuilder mSb = new StringBuilder("ORDER BY ");
		int index = 0;
		int size = columns.size();

		for(String column : columns) {
			if(index == size-1) {
				mSb.append(column + " ");
			} else {
				mSb.append(column + ", ");
			}
			index++;
		}
		mSb.append(direction);
		return mSb.toString();
	}

	/**
	 * 
	 * @param types
	 * @param columns
	 * @param values
	 * @return
	 */
	public String buildConditionClause(List<App.DATA_TYPE> types, List<String> columns, List<String> values) {
		if(types == null || columns == null || values == null) {
			return "";
		}

		if(types.size() != columns.size() || columns.size() != values.size() || types.size() != values.size()) {
			return "";
		}

		int i, size = types.size();

		App.DATA_TYPE type;
		String column;
		String value;

		StringBuilder mSb = new StringBuilder("");

		for(i = 0; i < size; i++) {
			type = types.get(i);
			column = columns.get(i);
			value = values.get(i);
			mSb.append(buildOneLikeCondition(type, column, value) + " ");
		}


		return mSb.toString();

	}

	/**
	 * 
	 * @param type
	 * @param column
	 * @param value
	 * @return
	 */
	public String buildOneLikeCondition(App.DATA_TYPE type, String column, String value ) {
		return buildOneCondition(type, column, value, true );
	}

	/**
	 * 
	 * @param type
	 * @param column
	 * @param value
	 * @return
	 */
	public String buildOneEqualCondition(App.DATA_TYPE type, String column, String value ) {
		return buildOneCondition(type, column, value, false );
	}	

	/**
	 * 
	 * @param type
	 * @param column
	 * @param value
	 * @param isLike
	 * @return
	 */
	public String buildOneCondition(App.DATA_TYPE type, String column, String value, boolean isLike ) {

		String retString = "";
		String[] tempStringArray = null;

		if(column.contains(App.BAR)) { //between two columns
			tempStringArray = column.split(App.BAR);
			retString = buildBetweenForTwoColumns(type, tempStringArray[0], tempStringArray[1], value);
		} else if(value.contains(App.BAR)){ //between two values
			tempStringArray = value.split(App.BAR);
			retString = buildBetweenForTwoValues(type, column, tempStringArray[0], tempStringArray[1]);
		} else {
			if(isLike) { //like '%token%' 
				retString = buildAndLike(type, column, value);  
			} else { //equal =
				retString = buildAndEqual(type, column, value);
			}
		}
		return retString;
	}


	/**
	 * 
	 * @param type
	 * @param column
	 * @param value If for Date type, the value is in "yyyy-MM-dd HH:mm" format.
	 * @return
	 */
	public String buildAndEqual(App.DATA_TYPE type, String column, String value ) {

		if(StringUtils.isEmpty(column) || StringUtils.isEmpty(value)) {
			return "";
		}

		StringBuilder mSb = new StringBuilder("");

		switch(type) {
		case NUMBER:
		case LONG:
			mSb.append(" AND " + column + "=" + value + " ");
			break;

		case VARCHAR:
			mSb.append(" AND UPPER(" + column + ")='" + value + "' ");
			break;

		case DATE:  
			mSb.append(" AND TO_CHAR(" + column + ", 'YYYY-MM-DD HH24:MI')='" + value + "' ");
			break;

		default:  logger.error("Invalid Oracle Database Data Type " + type);

		}

		return mSb.toString();

	}

	/**
	 * 
	 * @param type
	 * @param column
	 * @param value
	 * @return
	 */
	public String buildAndLike(App.DATA_TYPE type, String column, String value ) {

		if(StringUtils.isEmpty(column) || StringUtils.isEmpty(value)) {
			return "";
		}

		StringBuilder mSb = new StringBuilder("");

		switch(type) {
		case NUMBER:
		case LONG:
		case VARCHAR:
			mSb.append(" AND UPPER(" + column + ") like '" + value.trim() + "%' ");
			break;

		default:  logger.error("Invalid Oracle Database Data Type for Like Statement " + type + " column is " + column);

		}

		return mSb.toString();

	}	

	/**
	 * 
	 * @param type
	 * @param fromColumn
	 * @param toColumn
	 * @param value
	 * @return
	 */
	public String buildBetweenForTwoColumns(App.DATA_TYPE type, String fromColumn, String toColumn, String value ) {

		if(StringUtils.isEmpty(fromColumn) || StringUtils.isEmpty(toColumn) || StringUtils.isEmpty(value)) {
			return "";
		}		

		StringBuilder mSb = new StringBuilder("");

		switch(type) {
		case NUMBER:
		case LONG:
			mSb.append(" AND " + value + " BETWEEN " + fromColumn + " AND " + toColumn + " ");
			break;

		case VARCHAR:
			mSb.append(" AND '" + value + "' BETWEEN UPPER(" + fromColumn + ") AND UPPER(" + toColumn + ") ");
			break;

		case DATE:  
			mSb.append(" AND TO_DATE('" + value + "', 'YYYY-MM-DD HH24:MI') BETWEEN " + fromColumn + " AND " + toColumn + " ");
			break;

		default:  logger.error("Invalid Oracle Database Data Type " + type);

		}

		return mSb.toString();

	}	

	/**
	 * 
	 * @param type
	 * @param column
	 * @param fromValue
	 * @param toValue
	 * @return
	 */
	public String buildBetweenForTwoValues(App.DATA_TYPE type, String column, String fromValue, String toValue ) {

		if(StringUtils.isEmpty(column) || StringUtils.isEmpty(fromValue) || StringUtils.isEmpty(fromValue)) {
			return "";
		}		

		StringBuilder mSb = new StringBuilder("");

		switch(type) {
		case NUMBER:
		case LONG:
			mSb.append(" AND " + column + " BETWEEN " + fromValue + " AND " + toValue + " ");
			break;

		case VARCHAR:
			mSb.append(" AND UPPER(" + column + ") BETWEEN '" + fromValue + "' AND '" + toValue + "' ");
			break;

		case DATE:  
			mSb.append(" AND " + column + " BETWEEN TO_DATE('" + fromValue + "', 'YYYY-MM-DD HH24:MI') AND TO_DATE('" + toValue + "', 'YYYY-MM-DD HH24:MI') ");
			break;

		default:  logger.error("Invalid Oracle Database Data Type " + type);

		}

		return mSb.toString();

	}	

	public String buildListValueForColumn(String column, List<String> valueList) {
		if (valueList == null || valueList.size() == 0) {
			return "";
		}

		StringBuilder mSb = new StringBuilder("");

		int count = 0;

		mSb.append(" AND ");

		for(String value : valueList) {
			if(!StringUtils.isEmpty(value)) {
				if(count == 0) {
					mSb.append(" UPPER(" + column + ") in ('" + value.toUpperCase() + "' ");
				} else {
					mSb.append(" , '" + value.toUpperCase() + "' ");
				}
				count++;
			}
		}
		mSb.append(" ) ");

		return mSb.toString();
	}

	/**
	 * 
	 * @param valueList
	 * @return
	 */
	public String buildServiceNamesSelectClause(List<String> valueList, List<String> allServiceNameList) {
		
		if (valueList == null || valueList.size() == 0) {
			valueList = allServiceNameList;
		}

		StringBuilder mSb = new StringBuilder("");

		int count = 0;

		mSb.append(" , (");

		String tempSelectionString = " (SELECT DISTINCT PP2.SVC_NAME FROM PT_PAYER PP2 WHERE PP2.PT_ID=PP.PT_ID AND PP2.SVC_NAME = '%s' ) ";
		String dbConcatenationSynbol = " || ',' || ";

		for(String value : valueList) {
			if(!StringUtils.isEmpty(value)) {
				if(count == 0) {
					mSb.append(String.format(tempSelectionString, value));
				} else {
					mSb.append(dbConcatenationSynbol + String.format(tempSelectionString, value));
				}
				count++;
			}
		}
		mSb.append(" ) AS SVC_NAME_LST ");

		return mSb.toString();
	}	
	
	/**
	 * 
	 * @param resultSet
	 * @param resultColumnTypes
	 * @param resultIndices
	 * @return
	 */
	public Response parseResultSetForPatient(ResultSet resultSet) {
		Response response = new Response();
		
		List<PatientForSearch> results = new ArrayList<PatientForSearch>();
		
		PatientForSearch patientForSearch = null;
		
		if(resultSet == null) {
			return null;
		}
		
		String serviceNames = "";
		
		try {
			while (resultSet.next()) {
                if (response.getTotalRows() == 0) {
                    response.setTotalRows(resultSet.getBigDecimal("TOTAL_ROWS").intValue());
                }
				patientForSearch = new PatientForSearch();
				patientForSearch.setPtSk(BigInteger.valueOf(resultSet.getBigDecimal(App.PATIENT_FIELD.PTSK.getValue()).longValue()));
				patientForSearch.setPtId(resultSet.getString(App.PATIENT_FIELD.PTID.getValue()));
				patientForSearch.setPtFirstName(resultSet.getString(App.PATIENT_FIELD.PTFirstName.getValue()));
				patientForSearch.setPtLastName(resultSet.getString(App.PATIENT_FIELD.PTLastName.getValue()));
				patientForSearch.setPtMedicareId(resultSet.getString(App.PATIENT_FIELD.PTMedicareID.getValue()));
				patientForSearch.setPtMedicaidId(resultSet.getString(App.PATIENT_FIELD.PTMedicaidID.getValue()));
				patientForSearch.setBeId(resultSet.getString(App.PATIENT_FIELD.BEID.getValue()));
				
				serviceNames = resultSet.getString(App.PATIENT_FIELD.ServiceName.getValue());
				
				patientForSearch.setServiceNameList(getServiceNameList(serviceNames));
				results.add(patientForSearch);
			}
		} catch (SQLException e ) {
			throw new SandataRuntimeException(String.format("%s: %s",
					e.getClass().getName(), e.getMessage()));

		} 

		response.setData(results);
		return response;
	}

	public Response parseResultSetForStaff(ResultSet resultSet) {
		Response response = new Response();
		
		List<StaffForSearch> results = new ArrayList<StaffForSearch>();
		
		StaffForSearch staffForSearch = null;
		
		if(resultSet == null) {
			return null;
		}
		
		try {
			while (resultSet.next()) {
                if (response.getTotalRows() == 0) {
                    response.setTotalRows(resultSet.getBigDecimal("TOTAL_ROWS").intValue());
                }
                staffForSearch = new StaffForSearch();
                staffForSearch.setStaffSk(BigInteger.valueOf(resultSet.getBigDecimal(App.STAFF_FIELD.StaffSK.getValue()).longValue()));
                staffForSearch.setStaffId(resultSet.getString(App.STAFF_FIELD.StaffID.getValue()));
                staffForSearch.setStaffFirstName(resultSet.getString(App.STAFF_FIELD.StaffFirstName.getValue()));
                staffForSearch.setStaffLastName(resultSet.getString(App.STAFF_FIELD.StaffLastName.getValue()));
                staffForSearch.setBeId(resultSet.getString(App.STAFF_FIELD.BEID.getValue()));
                staffForSearch.setStaffPositionName(resultSet.getString(App.STAFF_FIELD.StaffPositionName.getValue()));
				results.add(staffForSearch);
			}
		} catch (SQLException e ) {
			throw new SandataRuntimeException(String.format("%s: %s",
					e.getClass().getName(), e.getMessage()));

		} 

		response.setData(results);
		return response;
	}
	
	
	/**
	 * 
	 * @param serviceNames
	 * @return
	 */
	public List<String> getServiceNameList(String serviceNames){
		
		if(StringUtils.isEmpty(serviceNames)) {
			return null;
		}
		
		String[] serviceNameArray = serviceNames.split(App.COMMA);
		
		List<String> retList = new ArrayList<String>();
		
		for(String serviceName : serviceNameArray) {
			if(!StringUtils.isEmpty(serviceName)) {
				retList.add(serviceName);
			}
		}
		
		return retList;
	}

}
