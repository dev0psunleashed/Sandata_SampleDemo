package com.sandata.lab.common.utils.data.model;

import com.sandata.lab.data.model.base.BaseObject;

public class JiraItem extends BaseObject {

    private static final long serialVersionUID = 1L;

    private String summary;
    private String issueKey;
    private String issueType;
    private String status;
    private String assignee;
    private String reporter;
    private String frontEndDevEstimate;
    private String frontEndDependencies;
    private String middlewareDevEstimate;
    private String middlewareDependencies;
    private String databaseEstimates;
    private String priority;

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getIssueKey() {
        return issueKey;
    }

    public void setIssueKey(String issueKey) {
        this.issueKey = issueKey;
    }

    public String getIssueType() {
        return issueType;
    }

    public void setIssueType(String issueType) {
        this.issueType = issueType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public String getReporter() {
        return reporter;
    }

    public void setReporter(String reporter) {
        this.reporter = reporter;
    }

    public String getFrontEndDevEstimate() {
        return frontEndDevEstimate;
    }

    public void setFrontEndDevEstimate(String frontEndDevEstimate) {
        this.frontEndDevEstimate = frontEndDevEstimate;
    }

    public String getFrontEndDependencies() {
        return frontEndDependencies;
    }

    public void setFrontEndDependencies(String frontEndDependencies) {
        this.frontEndDependencies = frontEndDependencies;
    }

    public String getMiddlewareDevEstimate() {
        return middlewareDevEstimate;
    }

    public void setMiddlewareDevEstimate(String middlewareDevEstimate) {
        this.middlewareDevEstimate = middlewareDevEstimate;
    }

    public String getMiddlewareDependencies() {
        return middlewareDependencies;
    }

    public void setMiddlewareDependencies(String middlewareDependencies) {
        this.middlewareDependencies = middlewareDependencies;
    }

    public String getDatabaseEstimates() {
        return databaseEstimates;
    }

    public void setDatabaseEstimates(String databaseEstimates) {
        this.databaseEstimates = databaseEstimates;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }
}
