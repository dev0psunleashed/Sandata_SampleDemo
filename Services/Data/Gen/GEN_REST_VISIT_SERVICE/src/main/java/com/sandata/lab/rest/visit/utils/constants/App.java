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

package com.sandata.lab.rest.visit.utils.constants;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

public class App {
	
 	public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
 	public static final TimeZone UTC_TZ = TimeZone.getTimeZone("UTC");
 	
 	public static final SimpleDateFormat DB_DATE_FORMAT = new SimpleDateFormat("MM-dd-yyyy");
	
	public static final String BAR = "|";
	public static final String COMMA = ",";
	
	public enum ID {

        REST_ROUTE_ENDPOINT ("SANDATA-REST-VISIT-DATA-SERVICE");

        private final String id;

        private ID(final String id) {
            this.id = id;
        }

        @Override
        public String toString() {
            return this.id;
        }
	}
    
    public enum DATA_TYPE {

        LONG ("LONG"),
        NUMBER ("NUMBER"),
        DATE ("DATE"),
        VARCHAR("VARCHAR");

        private final String type;

        private DATA_TYPE(final String type) {
            this.type = type;
        }

        @Override
        public String toString() {
            return this.type;
        }
    }    
    
    public enum PATIENT_FIELD {

    	PTSK (3),
    	PTID (4),
    	PTFirstName (5),
    	PTLastName (6),
    	PTMedicareID (7),
    	PTMedicaidID (8),
    	BEID (9),
    	ServiceName (10)  ;

        private final int field;

        private PATIENT_FIELD(final int field) {
            this.field = field;
        }

        public int getValue() {
        	return field;
        }
        @Override
        public String toString() {
            return this.field + "";
        }
    }      
    
    public enum STAFF_FIELD {

    	StaffSK (3),
    	StaffID (4),
    	StaffFirstName (5),
    	StaffLastName (6),
    	BEID (7),
    	StaffPositionName (8) ;

        private final int field;

        private STAFF_FIELD(final int field) {
            this.field = field;
        }

        public int getValue() {
        	return field;
        }
        @Override
        public String toString() {
            return this.field + "";
        }
    }          
    
}
