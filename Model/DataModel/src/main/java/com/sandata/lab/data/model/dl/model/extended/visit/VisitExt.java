package com.sandata.lab.data.model.dl.model.extended.visit;

import org.springframework.beans.BeanUtils;

import com.google.gson.annotations.SerializedName;
import com.sandata.lab.data.model.dl.model.Visit;

/**
 * @author huyvo
 *
 */
public class VisitExt extends Visit {
    private static final long serialVersionUID = 1L;
    
    @SerializedName("TimezoneName")
    protected String timezoneName;

    public VisitExt() {
        
    }
    
    public VisitExt(Visit visit) {
        BeanUtils.copyProperties(visit, this);
        this.getVisitEvent().addAll(visit.getVisitEvent());
        this.getVisitService().addAll(visit.getVisitService());
        this.getVisitVerification().addAll(visit.getVisitVerification());
        this.getVisitDocumentCrosswalk().addAll(visit.getVisitDocumentCrosswalk());
        this.getVisitAuthorization().addAll(visit.getVisitAuthorization());
        this.getVisitNote().addAll(visit.getVisitNote());
        this.getVisitActivity().addAll(visit.getVisitActivity());
        this.getVisitException().addAll(visit.getVisitException());
        this.getTimesheetDetail().addAll(visit.getTimesheetDetail());
        this.getVisitTaskList().addAll(visit.getVisitTaskList());
    }

    public String getTimezoneName() {
        return timezoneName;
    }

    public void setTimezoneName(String timezoneName) {
        this.timezoneName = timezoneName;
    }
}
