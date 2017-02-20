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

package com.sandata.lab.rest.bsn_ent.utils.constants;


public class App {

    public enum ID {

        REST_ROUTE_ENDPOINT ("SANDATA-REST-BSN_ENT-DATA-SERVICE"),
        SANDATA_GET_BSN_ENT_CROSSWALK_FROM_COMPANY ("SANDATA-GET-BSN_ENT-CROSSWALK-FROM-COMPANY"),
        SANDATA_GET_BSN_ENT_IDS_FOR_PAYROLL("SANDATA-GET-BSN-ENT-IDS-FOR-PAYROLL"),
        SANDATA_GET_BSN_ENT_IDS_FOR_PAYROLL_BY_VENDOR_ID("SANDATA-GET-BSN-ENT-IDS-FOR-PAYROLL-BY-VENDOR-ID"),
        SANDATA_GET_BSN_ENT("SANDATA-GET-BSN-ENT"),
        SANDATA_GET_BSN_ENT_LOCATION_IDS("SANDATA-GET-BSN-ENT-LOCATION-IDS"),
        SANDATA_GET_BSN_ENT_XWALK_BY_ID("SANDATA_GET_BSN_ENT_XWALK_BY_ID");

        private final String id;

        private ID(final String id) {
            this.id = id;
        }

        @Override
        public String toString() {
            return this.id;
        }
    }
}
