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

import java.util.HashMap;
import java.util.Map;

public class DataRecordValidationFailureMessage extends BaseObject {

    private static final long serialVersionUID = 1L;

	String recordProcessed;
	
	Map<String, DataField> fields = new HashMap<String, DataField>();

	public void addFieldFailureMessage(String fieldName, DataFieldValidationFailure dataFieldValidationFailure){
		DataField dataField = fields.get(fieldName);
		if(dataField == null){
			dataField = new DataField(fieldName);
			fields.put(fieldName, dataField);
		}
		else{
			fields.put(fieldName, dataField);
		}
		
		dataField.addValidationError(dataFieldValidationFailure);
	}

	public String getRecordProcessed() {
		return recordProcessed;
	}


	public void setRecordProcessed(String recordProcessed) {
		this.recordProcessed = recordProcessed;
	}
	
	public boolean isValid() {
		if(fields.isEmpty()){
			return true;
		}
		else{
			return false;
		}
	}
	
}
