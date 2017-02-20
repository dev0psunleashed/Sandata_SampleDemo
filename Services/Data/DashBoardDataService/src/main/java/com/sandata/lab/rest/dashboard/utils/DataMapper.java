package com.sandata.lab.rest.dashboard.utils;

import static com.sandata.lab.rest.dashboard.model.enums.Tables.MV_AUTH_EXPIRE;
import static com.sandata.lab.rest.dashboard.model.enums.Tables.MV_COMP;
import static com.sandata.lab.rest.dashboard.model.enums.Tables.MV_OPEN_ORDER;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sandata.lab.rest.dashboard.model.CoordinatorsResponse;
import com.sandata.lab.rest.dashboard.model.Location;
import com.sandata.lab.rest.dashboard.model.Patient;
import com.sandata.lab.rest.dashboard.model.PatientDetailsResponse;
import com.sandata.lab.rest.dashboard.model.StaffDetailsResponse;
import com.sandata.lab.rest.dashboard.model.enums.Columns;
import com.sandata.lab.rest.dashboard.model.enums.Tables;

public class DataMapper {
    public List<Location> map(ResultSet resultSet, String tableName) throws SQLException {
        List<Location> locationList = new ArrayList<>();


        while (resultSet.next()) {
            Location location = new Location();

            int lvl = resultSet.getInt("BE_LVL");

            String businessEntityIDColumn = "BE";

            if(lvl > 1)
            {
                businessEntityIDColumn = "BE_LVL_"+ lvl;
            }

            location.setBusinessEntityID(resultSet.getString(businessEntityIDColumn + "_ID"));
            location.setBusinessEntityName(resultSet.getString(businessEntityIDColumn + "_NAME"));
            location.setBusinessEntityLevel(lvl);
            
            location.setValue(resultSet.getInt("Total"));

            /*
            int locationCount = resultSet.getInt("Children_Count");
            if (locationCount > 0) {
                location.setHasChildren(true);
            } else {
                location.setHasChildren(false);
            }
            */

            if(location.getValue() > 0) {
                locationList.add(location);
            }
        }

        return locationList;
    }
    
    
    public List<Patient> map(ResultSet resultSet, String tableName,Class response) throws SQLException {
        

        if (Patient.class == response) {
        	List<Patient> patientList = new ArrayList<>();
        	Patient patient = null;
	        while (resultSet.next()) {
	        	
	        	patient = new Patient();
	        	patient.setPatientId(resultSet.getString("PT_ID"));
	        	patient.setFirstName(resultSet.getString("PT_FIRST_NAME"));
	        	patient.setLastName(resultSet.getString("PT_LAST_NAME"));
	            patientList.add(patient);

                if(tableName.equals(MV_OPEN_ORDER.getValue())){

                    patient.setDate(resultSet.getTimestamp("OPEN_ORD_DATE"));
                }
	
	        }
	
	        return patientList;
	        
        }
        
        return null;
    }

    public Object map(ResultSet resultSet, String tableName, String sumColumn, Class response, boolean isSum) throws SQLException {

        if (PatientDetailsResponse.class == response) {
            List<PatientDetailsResponse> patientDetailsResponseList = new ArrayList<>();

            while (resultSet.next()) {
                PatientDetailsResponse patientDetailsResponse = new PatientDetailsResponse();

                patientDetailsResponse.setPatientFirstName(resultSet.getString("PT_FIRST_NAME"));
                patientDetailsResponse.setPatientLastName(resultSet.getString("PT_LAST_NAME"));
                patientDetailsResponse.setPatientId(resultSet.getString("PT_ID"));

                if(isSum || (tableName.equals(Tables.MV_AUTH_EXPIRE.getValue()) && sumColumn.equals(Columns.PT_ID.getValue()))
                        ||  sumColumn.equals(Columns.PT_NON_ELIGIB_COUNT.getValue())) {
                    patientDetailsResponse.setValue(resultSet.getInt("Total"));
                }else if(!tableName.equals(Tables.MV_AUTH_EXPIRE.getValue()))
                {
                    patientDetailsResponse.setValue(resultSet.getInt(sumColumn));
                }

                /*
                if (tableName.equals(MV_SCHED.getValue()) || tableName.equals((MV_VISIT_VERIF.getValue()))) {
                    patientDetailsResponse.setId(resultSet.getString("VISIT_SK"));
                }
                */

                patientDetailsResponseList.add(patientDetailsResponse);
            }
            return patientDetailsResponseList;

        } else if (StaffDetailsResponse.class == response) {
            List<StaffDetailsResponse> staffDetailsResponseList = new ArrayList<>();

            while (resultSet.next()) {
                StaffDetailsResponse staffDetailsResponse = new StaffDetailsResponse();

                staffDetailsResponse.setStaffFirstName(resultSet.getString("STAFF_FIRST_NAME"));
                staffDetailsResponse.setStaffLastName(resultSet.getString("STAFF_LAST_NAME"));
                staffDetailsResponse.setStaffId(resultSet.getString("STAFF_ID"));


                if(isSum || tableName.equals(MV_AUTH_EXPIRE) || tableName.equals(MV_COMP.getValue())) {
                    staffDetailsResponse.setValue(resultSet.getInt("Total"));
                }else
                {
                    staffDetailsResponse.setValue(resultSet.getInt(sumColumn));
                }
                
                staffDetailsResponseList.add(staffDetailsResponse);

            }
            return staffDetailsResponseList;

        }else if (CoordinatorsResponse.class == response) {
            List<CoordinatorsResponse> coordinatorsResponseList = new ArrayList<>();

            while (resultSet.next()) {
            	CoordinatorsResponse coordinatorsResponse = new CoordinatorsResponse();

            	coordinatorsResponse.setCoordinatorId(resultSet.getString("COORDINATOR_ID"));
            	coordinatorsResponse.setCoordinatorFirstName(resultSet.getString("COORDINATOR_FIRST_NAME"));
            	coordinatorsResponse.setCoordinatorLastName(resultSet.getString("COORDINATOR_LAST_NAME"));
            	coordinatorsResponse.setValue(resultSet.getInt("TOTAL"));
                
            	coordinatorsResponseList.add(coordinatorsResponse);

            }
            return coordinatorsResponseList;

        }

        return null;
    }
    
}
