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

package com.sandata.lab.data.model.response;

import com.sandata.lab.data.model.base.BaseObject;
import com.sandata.lab.data.model.service.ServiceStatus;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.Date;

/**
 * Date: 8/19/13
 * Time: 3:59 PM
 */

@XmlType(propOrder = {"status", "token", "messageSummary","messageDetail", "errorMessage", "data"})
@XmlRootElement(name="response")
public class Response extends BaseObject {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * Response code.
     */
    private ServiceStatus status;

    /**
     * Session token.
     */
    private String token;

    /**
     * summary of return message.
     */
    private String messageSummary;

    /**
     * details about of return message.
     */
    private String messageDetail;

    /**
     * Error Message.
     */
    private String errorMessage;

    // how many records had failed
    private int failedCount;

    // how many records were successful
    private int succeededCount;

    private boolean cached;

    private Date cachedDate;

    private int totalRows = 0;
    private int page;
    private int pageSize;
    private String orderByColumn;
    private String orderByDirection;

    /**
     * Response Data Object.
     */
    private Object data;

    /**
     * Return response code.
     *
     * @return status
     */
    public final ServiceStatus getStatus() {
        return status;
    }

    /**
     * Set the response code.
     *
     * @param status
     */
    public void setStatus(final ServiceStatus status) {
        this.status = status;
    }

    /**
     * Return the session login token.
     *
     * @return token
     */
    public final String getToken() {
        return token;
    }

    public void setToken(final String token) {
        this.token = token;
    }

    public final String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(final String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public final Object getData() {
        return data;
    }

    public final void setData(final Object data) {
        this.data = data;
    }

    public String getMessageDetail() {
        return messageDetail;
    }

    public void setMessageDetail(String messageDetail) {
        this.messageDetail = messageDetail;
    }

    public String getMessageSummary() {
        return messageSummary;
    }

    public void setMessageSummary(String messageSummary) {
        this.messageSummary = messageSummary;
    }

    public int getFailedCount() {
        return failedCount;
    }

    public void setFailedCount(int failedCount) {
        this.failedCount = failedCount;
    }

    public int getSucceededCount() {
        return succeededCount;
    }

    public void setSucceededCount(int succeededCount) {
        this.succeededCount = succeededCount;
    }

    public boolean isCached() {
        return cached;
    }

    public void setCached(boolean cached) {
        this.cached = cached;
    }

    public Date getCachedDate() {
        return cachedDate;
    }

    public void setCachedDate(Date cachedDate) {
        this.cachedDate = cachedDate;
    }

    public int getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(int totalRows) {
        this.totalRows = totalRows;
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

    public String getOrderByColumn() {
        return orderByColumn;
    }

    public void setOrderByColumn(String orderByColumn) {
        this.orderByColumn = orderByColumn;
    }

    public String getOrderByDirection() {
        return orderByDirection;
    }

    public void setOrderByDirection(String orderByDirection) {
        this.orderByDirection = orderByDirection;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
