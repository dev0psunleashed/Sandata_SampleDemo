package com.sandata.lab.rest.am.model;

import com.google.gson.annotations.SerializedName;
import com.sandata.lab.data.model.base.BaseObject;

public class ReportName extends BaseObject {

    private static final long serialVersionUID = 1L;

    @SerializedName("ReportID")
    private String reportId;
    @SerializedName("ReportName")
    private String reportName;
    @SerializedName("ReportDescription")
    private String reportDescription;

    public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
    }

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public String getReportDescription() {
        return reportDescription;
    }

    public void setReportDescription(String reportDescription) {
        this.reportDescription = reportDescription;
    }
}
