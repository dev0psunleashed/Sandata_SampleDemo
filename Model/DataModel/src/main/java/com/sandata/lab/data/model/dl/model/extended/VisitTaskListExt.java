package com.sandata.lab.data.model.dl.model.extended;

import com.google.gson.annotations.SerializedName;
import com.sandata.lab.data.model.dl.model.VisitEventPatientConfirmationQualifier;
import com.sandata.lab.data.model.dl.model.VisitTaskList;

/**
 * Extends VisitTaskList with additional middleware logic.
 * <p/>
 *
 * @author David Rutgos
 */
public class VisitTaskListExt extends VisitTaskList {

    private static final long serialVersionUID = 1L;

    @SerializedName("VisitTaskAcceptedIndicator")
    private Boolean visitTaskAcceptedIndicator;

    @SerializedName("VisitTaskDescription")
    private String visitTaskDescription;

    public Boolean getVisitTaskAcceptedIndicator() {
        return visitTaskAcceptedIndicator;
    }

    public void setVisitTaskAcceptedIndicator(Boolean visitTaskAcceptedIndicator) {
        this.visitTaskAcceptedIndicator = visitTaskAcceptedIndicator;
    }

    public VisitTaskListExt(VisitTaskList visitTaskList) {

        // Default to false
        setVisitTaskAcceptedIndicator(false);

        setVisitTaskListSK(visitTaskList.getVisitTaskListSK());
        setBusinessEntityID(visitTaskList.getBusinessEntityID());
        setVisitTaskListID(visitTaskList.getVisitTaskListID());
        setBusinessEntityTaskID(visitTaskList.getBusinessEntityTaskID());
        setVisitSK(visitTaskList.getVisitSK());
        setRecordCreateTimestamp(visitTaskList.getRecordCreateTimestamp());
        setRecordUpdateTimestamp(visitTaskList.getRecordUpdateTimestamp());
        setChangeVersionID(visitTaskList.getChangeVersionID());
        setCriticalTaskIndicator(visitTaskList.isCriticalTaskIndicator());
        setVisitTaskScheduledIndicator(visitTaskList.isVisitTaskScheduledIndicator());
        setVisitTaskPerformedIndicator(visitTaskList.isVisitTaskPerformedIndicator());
        setVisitTaskConfirmationQualifier(visitTaskList.getVisitTaskConfirmationQualifier());
        setVisitTaskNotPerformedReason(visitTaskList.getVisitTaskNotPerformedReason());
        setVisitTaskComment(visitTaskList.getVisitTaskComment());
        setTaskResultsReadingType(visitTaskList.getTaskResultsReadingType());
        setTaskResultsReadingValue(visitTaskList.getTaskResultsReadingValue());

        //dmr GEOR-1034
        if ((visitTaskList.getVisitTaskConfirmationQualifier() != null
            && (visitTaskList.getVisitTaskConfirmationQualifier() == VisitEventPatientConfirmationQualifier.CONFIRM))) {
            setVisitTaskAcceptedIndicator(true);
        }
    }

    public String getVisitTaskDescription() {
        return visitTaskDescription;
    }

    public void setVisitTaskDescription(String visitTaskDescription) {
        this.visitTaskDescription = visitTaskDescription;
    }
}
