package com.sandata.lab.data.model.icdcodes;

import com.sandata.lab.data.model.base.BaseObject;

/**
 * Date: 12/15/15
 * Time: 11:50 PM
 */

public class FindICDCodesFilter extends BaseObject {

    private static final long serialVersionUID = 1L;

    private String code;
    private String description;
    private String revision;
    private String effDate;
    private String termDate;
    private int page = 1;
    private int pageSize = 10;
    private String sortOn = "code";
    private String direction = "ASC";

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRevision() {
        return revision;
    }

    public void setRevision(String revision) {
        this.revision = revision;
    }

    public String getEffDate() {
        return effDate;
    }

    public void setEffDate(String effDate) {
        this.effDate = effDate;
    }

    public String getTermDate() {
        return termDate;
    }

    public void setTermDate(String termDate) {
        this.termDate = termDate;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getSortOn() {
        return sortOn;
    }

    public void setSortOn(String sortOn) {
        this.sortOn = sortOn;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }
}
