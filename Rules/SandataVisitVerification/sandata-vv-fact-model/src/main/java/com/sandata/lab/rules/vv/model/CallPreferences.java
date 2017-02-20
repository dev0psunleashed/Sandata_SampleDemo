package com.sandata.lab.rules.vv.model;

import java.io.Serializable;

public class CallPreferences implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final int STAFF_ID_MATCH_ALL_9 = 9;
	public static final int STAFF_ID_MATCH_6_OF_9 = 6;
	public static final int STAFF_ID_MATCH_7_OF_9 = 7;
	public static final int DEFAULT_MIN_VISIT_TIME = 5 * 60000;
	public static final int DEFAULT_DEVIATION_THRESHOLD = 120;
	public static final boolean DEFAULT_SCHEDULE_FLAG_N = false;
	public static final boolean DEFAULT_CLUSTER_SCHEDULE = false;
	public static final int DEFAULT_EXPIRATION_HOURS = 24;
	public static final int DEFAULT_EXPIRATION_MINUTES = 1;
	public static final int DEFAULT_VISIT_LATE_IN_MINUTES = 15;
	public static final int DEFAULT_VISIT_EARLY_OUT_MINUTES = 15;
	public static final int DEFAULT_NO_SHOW_EXCEPTION_MINUTES = 30;
	public static final boolean DEFAULT_NO_SHOW_RXCEPTION = true;
	public static final int DEFAULT_SHORT_VISIT_MINUTES = 15;
	public static final int DEFAULT_SECOND_CALL_STILL_VALID_THRESHOLD = 24 * 60;
	public static final String DEFAULT_BE_ID = "1";
	public static final String DEFAULT_VV_ID = "STAFF_ID";
	private String be_id;
	private String vv_id;
	private int minVisitDurationMS;
	private int staffIdMatchLength;
	private int deviationThresholdMinutes;
	private int secondCallStillValidThresholdMinutes;
	private boolean scheduleFlagN;
	private boolean clusterSchedule;
	private int scheduleWindow;

	private int visitLateInMinutes;
	private int visitEarlyOutMinutes;
	private int visitNoShowMinutes;
	private boolean visitNoShowAlert;
	private int visitShortMinutes;

	public CallPreferences() {
		setStaffIdMatchLength(STAFF_ID_MATCH_ALL_9);
		setMinVisitDurationMS(DEFAULT_MIN_VISIT_TIME);
		setDeviationThresholdMinutes(DEFAULT_DEVIATION_THRESHOLD);
		setScheduleFlagN(DEFAULT_SCHEDULE_FLAG_N);
		setClusterSchedule(DEFAULT_CLUSTER_SCHEDULE);
		setScheduleWindow(DEFAULT_EXPIRATION_HOURS * 60);
		setVisitEarlyOutMinutes(DEFAULT_VISIT_EARLY_OUT_MINUTES);
		setVisitLateInMinutes(DEFAULT_VISIT_LATE_IN_MINUTES);
		//setVisitNoShowAlert(DEFAULT_NO_SHOW_EXCEPTION);
		setVisitNoShowMinutes(DEFAULT_NO_SHOW_EXCEPTION_MINUTES);
		setVisitShortMinutes(DEFAULT_SHORT_VISIT_MINUTES);
		setSecondCallStillValidThresholdMinutes(DEFAULT_SECOND_CALL_STILL_VALID_THRESHOLD);
		setBe_id(DEFAULT_BE_ID);
		setVv_id(DEFAULT_VV_ID);
	}
	public CallPreferences(String eighthunderedNumber)
	{
		this();
	}
	public int getStaffIdMatchLength() {
		return staffIdMatchLength;
	}
	public void setStaffIdMatchLength(int staffIdMatchLength) {
		this.staffIdMatchLength = staffIdMatchLength;
	}
	public int getMinVisitDurationMS() {
		return minVisitDurationMS;
	}
	public void setMinVisitDurationMS(int minVisitDurationMs) {
		this.minVisitDurationMS = minVisitDurationMs;
	}
	public int getDeviationThresholdMinutes() {
		return deviationThresholdMinutes;
	}
	public void setDeviationThresholdMinutes(int deviationThresholdMinutes) {
		this.deviationThresholdMinutes = deviationThresholdMinutes;
	}
	public boolean isScheduleFlagN() {
		return scheduleFlagN;
	}
	public void setScheduleFlagN(boolean scheduleFlagN) {
		this.scheduleFlagN = scheduleFlagN;
	}
	public boolean isClusterSchedule() {
		return clusterSchedule;
	}
	public void setClusterSchedule(boolean clusterSchedule) {
		this.clusterSchedule = clusterSchedule;
	}
	public int getScheduleWindow() {
		return scheduleWindow;
	}
	public void setScheduleWindow(int scheduleWindow) {
		this.scheduleWindow = scheduleWindow;
	}

	public int getVisitLateInMinutes() {
		return visitLateInMinutes;
	}

	public void setVisitLateInMinutes(int visitLateInMinutes) {
		this.visitLateInMinutes = visitLateInMinutes;
	}

	public int getVisitEarlyOutMinutes() {
		return visitEarlyOutMinutes;
	}

	public void setVisitEarlyOutMinutes(int visitEarlyOutMinutes) {
		this.visitEarlyOutMinutes = visitEarlyOutMinutes;
	}

	public int getVisitNoShowMinutes() {
		return visitNoShowMinutes;
	}

	public void setVisitNoShowMinutes(int visitNoShowMinutes) {
		this.visitNoShowMinutes = visitNoShowMinutes;
	}

	public boolean isVisitNoShowAlert() {
		return visitNoShowAlert;
	}

	public void setVisitNoShowAlert(boolean visitNoShowAlert) {
		this.visitNoShowAlert = visitNoShowAlert;
	}

	public int getVisitShortMinutes() {
		return visitShortMinutes;
	}

	public void setVisitShortMinutes(int visitShortMinutes) {
		this.visitShortMinutes = visitShortMinutes;
	}

	public int getSecondCallStillValidThresholdMinutes() {
		return secondCallStillValidThresholdMinutes;
	}

	public void setSecondCallStillValidThresholdMinutes(int secondCallStillValidThresholdMinutes) {
		this.secondCallStillValidThresholdMinutes = secondCallStillValidThresholdMinutes;
	}

	public String getBe_id() {
		return be_id;
	}

	public void setBe_id(String be_id) {
		this.be_id = be_id;
	}
	@Override
	public String toString() {
		String print = String.format("StaffId Match Length:\t\t(%d), \n" +
				"Minimum Visit Duration:\t\t(%d), \n" +
				"Deviation Threshold in Minutes:\t\t(%d), \n" +
				"Schedule Flag N:\t\t(%b), \n" +
				"Cluster Schedule:\t\t(%b), \n" +
				"Schedule Window:\t\t(%d), \n" +
				"Visit Early Out Minutes:\t\t(%d), \n" +
				"Visit Late In Minutes:\t\t(%d), \n" +
				"Visit No Show Alert:\t\t(%b), \n" +
				"Visit No Show Minutes:\t\t(%d), \n" +
				"Visit Short Minutes:\t\t(%d), \n" +
				"Second Call Still Valid Threshold in Minutes:\t\t(%d), \n" +
				"Visit Verification ID:\t\t(%s), \n" +
				" Agency ID:\t\t(%s) \n",getStaffIdMatchLength(), getMinVisitDurationMS(), getDeviationThresholdMinutes(), isScheduleFlagN(), isClusterSchedule(),
				getScheduleWindow(),getVisitEarlyOutMinutes(), getVisitLateInMinutes(), isVisitNoShowAlert(), getVisitNoShowMinutes(),
				getVisitShortMinutes(), getSecondCallStillValidThresholdMinutes(), getVv_id(), getBe_id());
		return print;
	}


	public String getVv_id() {
		if(this.vv_id == null || this.vv_id.isEmpty()) {
			this.vv_id = DEFAULT_VV_ID;
		}
		return this.vv_id;
	}

	public void setVv_id(String vv_id) {
		this.vv_id = vv_id;
	}
}
