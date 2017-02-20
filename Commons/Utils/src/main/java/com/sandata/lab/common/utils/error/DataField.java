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

package com.sandata.lab.common.utils.error;

import com.sandata.lab.data.model.base.BaseObject;

import java.util.ArrayList;

public class DataField extends BaseObject {

    private static final long serialVersionUID = 1L;

    /// TODO - Add mesage type error, warning, information

    // and based on the above change the class file names remove the word failure.

	String fieldName;
    ArrayList<DataFieldValidationFailure> dataFieldValidationFailures;

	public DataField(String fieldName) {
		super();
		this.fieldName = fieldName;
        dataFieldValidationFailures = new ArrayList<DataFieldValidationFailure>();
    }

	public void addValidationError(DataFieldValidationFailure dataFieldValidationFailure){
		dataFieldValidationFailures.add(dataFieldValidationFailure);
	}
}
