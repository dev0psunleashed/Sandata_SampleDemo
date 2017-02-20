package com.sandata.lab.data.model.dl.model.extended;

import com.google.gson.annotations.SerializedName;
import com.sandata.lab.data.model.dl.model.VisitException;

/**
 * Extends VisitException with additional middleware logic.
 * </p>
 * 
 * @author khangle
 *
 */
public class VisitExceptionExt extends VisitException {

	private static final long serialVersionUID = 1L;
	
	@SerializedName("ExceptionLookupName")
	private String exceptionLookupName;
	
	@SerializedName("ExceptionLookupDescription")
	private String exceptionLookupDescription;

    @SerializedName("ExceptionNonFixableIndicator")
    protected Boolean exceptionNonFixableIndicator;

    @SerializedName("ExceptionAcknowledgableIndicator")
    protected Boolean exceptionAcknowledgableIndicator;

	/**
	 * Constructor
	 * 
	 * @param visitException
	 */
	public VisitExceptionExt(VisitException visitException, String exceptionLookupName, String exceptionLookupDescription) {
		setExceptionLookupName(exceptionLookupName);
		setExceptionLookupDescription(exceptionLookupDescription);
		setVisitExceptionSK(visitException.getVisitExceptionSK());
		setRecordCreateTimestamp(visitException.getRecordCreateTimestamp());
		setRecordUpdateTimestamp(visitException.getRecordUpdateTimestamp());
		setRecordEffectiveTimestamp(visitException.getRecordEffectiveTimestamp());
		setRecordTerminationTimestamp(visitException.getRecordTerminationTimestamp());
		setRecordCreatedBy(visitException.getRecordCreatedBy());
		setRecordUpdatedBy(visitException.getRecordUpdatedBy());
		setChangeReasonMemo(visitException.getChangeReasonMemo());
		setChangeVersionID(visitException.getChangeVersionID());
		setCurrentRecordIndicator(visitException.isCurrentRecordIndicator());
		setVisitSK(visitException.getVisitSK());
		setExceptionID(visitException.getExceptionID());
		setExceptionClearedManaullyIndicator(visitException.isExceptionClearedManaullyIndicator());
	}

    public VisitExceptionExt() {

    }

    public String getExceptionLookupName() {
		return exceptionLookupName;
	}

	public void setExceptionLookupName(String exceptionLookupName) {
		this.exceptionLookupName = exceptionLookupName;
	}

	public String getExceptionLookupDescription() {
		return exceptionLookupDescription;
	}

	public void setExceptionLookupDescription(String exceptionLookupDescription) {
		this.exceptionLookupDescription = exceptionLookupDescription;
	}

    public Boolean getExceptionNonFixableIndicator() {
        return exceptionNonFixableIndicator;
    }

    public void setExceptionNonFixableIndicator(Boolean exceptionNonFixableIndicator) {
        this.exceptionNonFixableIndicator = exceptionNonFixableIndicator;
    }

    public Boolean getExceptionAcknowledgableIndicator() {
        return exceptionAcknowledgableIndicator;
    }

    public void setExceptionAcknowledgableIndicator(Boolean exceptionAcknowledgableIndicator) {
        this.exceptionAcknowledgableIndicator = exceptionAcknowledgableIndicator;
    }
}
