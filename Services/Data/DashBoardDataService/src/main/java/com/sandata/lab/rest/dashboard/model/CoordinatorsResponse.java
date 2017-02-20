package com.sandata.lab.rest.dashboard.model;

import com.google.gson.annotations.SerializedName;
import com.sandata.lab.data.model.base.BaseObject;

/**
 * CoordinatorsResponse object
 * 
 * @author thanh.le
 * @date july 7, 2016
 *
 */
public class CoordinatorsResponse extends BaseObject {

	private static final long serialVersionUID = 1L;

	@SerializedName("COORDINATOR_ID")
	private String coordinatorId;
	
	@SerializedName("COORDINATOR_FIRST_NAME")
	private String coordinatorFirstName;
	
	@SerializedName("COORDINATOR_LAST_NAM")
	private String coordinatorLastName;
	
	@SerializedName("Value")
	private int value;

	public String getCoordinatorId() {
		return coordinatorId;
	}

	public void setCoordinatorId(String coordinatorId) {
		this.coordinatorId = coordinatorId;
	}

	public String getCoordinatorFirstName() {
		return coordinatorFirstName;
	}

	public void setCoordinatorFirstName(String coordinatorFirstName) {
		this.coordinatorFirstName = coordinatorFirstName;
	}

	public String getCoordinatorLastName() {
		return coordinatorLastName;
	}

	public void setCoordinatorLastName(String coordinatorLastName) {
		this.coordinatorLastName = coordinatorLastName;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
	
	
}
