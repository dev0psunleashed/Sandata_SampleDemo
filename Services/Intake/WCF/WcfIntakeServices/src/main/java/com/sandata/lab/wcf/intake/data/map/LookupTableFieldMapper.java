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

package com.sandata.lab.wcf.intake.data.map;

import com.sandata.lab.common.utils.error.AbstractErrorHandler;
import com.sandata.lab.wcf.intake.utils.log.WcfIntakeServiceLogger;

/**
 * Map specified fields to the lookup table service.
 * <p/>
 *
 * @author David Rutgos
 */
public class LookupTableFieldMapper extends AbstractErrorHandler {

    public static LookupTableFieldMapper getInstance() {
        return new LookupTableFieldMapper();
    }

    public LookupTableFieldMapper() {
        super(WcfIntakeServiceLogger.CreateLogger());
    }

    public boolean isLookupField(final String fieldName) {

        if (fieldName == null) {
            return false;
        }

        switch (fieldName) {

            case "typeId": return true;
            case "preferredContactId": return true;
            case "admissionTypeId": return true;
            case "positionId": return true;
            case "maritalStatusId": return true;
            case "genderId": return true;
            case "languageId": return true;
            case "ethnicityId": return true;
            case "religionId": return true;
            case "stateId": return true;
            case "staffClassId": return true;
            case "militaryStatusId": return true;
            case "transportationTypeId": return true;
            case "staffStatusId": return true;
            case "federalMaritalStatusId": return true;
            case "stateMaritalStatusId": return true;
            case "stateTaxId": return true;
            case "liveInStateId": return true;
            case "cityTax1Id": return true;
            case "cityTax2Id": return true;
            case "payTypeId": return true;
            case "payFrequencyId": return true;
        }

        return false;
    }

    public Integer mapFieldToId(final String fieldName, final Object value) {

        if (fieldName == null
                || value == null) {
            return null;
        }

        switch (fieldName) {

            case "typeId": {
                return typeId(value);
            }

            case "preferredContactId": {
                return preferredContactId(value);
            }

            case "admissionTypeId": {
                return admissionTypeId(value);
            }

            case "positionId": {
                return positionId(value);
            }

            case "maritalStatusId":
            case "federalMaritalStatusId":
            case "stateMaritalStatusId": {
                return maritalStatusId(value);
            }

            case "genderId": {
                return genderId(value);
            }

            case "languageId": {
                return languageId(value);
            }

            case "ethnicityId": {
                return ethnicityId(value);
            }

            case "religionId": {
                return religionId(value);
            }

            case "stateId":
            case "liveInStateId": {
                return stateId(value);
            }

            case "staffClassId": {
                return staffClassId(value);
            }

            case "militaryStatusId": {
                return militaryStatusId(value);
            }

            case "transportationTypeId": {
                return transportationTypeId(value);
            }

            case "staffStatusId": {
                return staffStatusId(value);
            }

            case "stateTaxId": {
                return stateTaxId(value);
            }

            case "cityTax1Id":
            case "cityTax2Id": {
                return cityTaxId(value);
            }

            case "payTypeId": {
                return payTypeId(value);
            }

            case "payFrequencyId": {
                return payFrequencyId(value);
            }
        }

        return null;
    }

    public String stripId(final String fieldName) {

        if (fieldName == null) {
            return null;
        }

        if (! fieldName.endsWith("Id")) {
            return fieldName;
        }

        // Strip the "Id" from the fieldName
        return fieldName.substring(0, fieldName.length() - 2);
    }

    private int typeId(Object value) {
        return 1;
    }

    private int preferredContactId(Object value) {
        return 1;
    }

    private int admissionTypeId(Object value) {
        return 1;
    }

    private int positionId(Object value) {
        return 1;
    }

    private int maritalStatusId(Object value) {
        return 1;
    }

    private int genderId(Object value) {
        return 1;
    }

    private int languageId(Object value) {
        return 1;
    }

    private int ethnicityId(Object value) {
        return 1;
    }

    private int religionId(Object value) {
        return 1;
    }

    private int stateId(Object value) {
        return 1;
    }

    private int staffClassId(Object value) {
        return 1;
    }

    private int militaryStatusId(Object value) {
        return 1;
    }

    private int transportationTypeId(Object value) {
        return 1;
    }

    private int staffStatusId(Object value) {
        return 1;
    }

    private int stateTaxId(Object value) {
        return 1;
    }

    private int cityTaxId(Object value) {
        return 1;
    }

    private int payTypeId(Object value) {
        return 1;
    }

    private int payFrequencyId(Object value) {
        return 1;
    }
}
