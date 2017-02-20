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

package com.sandata.lab.common.utils.data.transformer;

import com.sandata.lab.data.model.response.Response;
import com.sandata.lab.data.model.service.ServiceStatus;
import org.apache.camel.Exchange;

import java.util.List;

/**
 * Date: 7/25/13
 * Time: 2:46 AM
 */

public class FormatTransformer {

    public Response toResponse(Exchange exchange) {

        Response response = new Response();

        response.setStatus(ServiceStatus.SUCCESS);
        response.setId((String)exchange.getIn().getHeader("SANDATA_LOGGER_ID"));

        if (exchange.getIn().getHeader("TOTAL_ROWS") != null) {
            response.setTotalRows((Integer)exchange.getIn().getHeader("TOTAL_ROWS"));
        }

        if (exchange.getIn().getHeader("PAGE") != null) {
            response.setPage((Integer) exchange.getIn().getHeader("PAGE"));
        }

        if (exchange.getIn().getHeader("PAGE_SIZE") != null) {
            response.setPageSize((Integer) exchange.getIn().getHeader("PAGE_SIZE"));
        }

        if (exchange.getIn().getHeader("ORDER_BY_COLUMN") != null) {
            response.setOrderByColumn((String)exchange.getIn().getHeader("ORDER_BY_COLUMN"));
        }

        if (exchange.getIn().getHeader("ORDER_BY_DIRECTION") != null) {
            response.setOrderByDirection((String)exchange.getIn().getHeader("ORDER_BY_DIRECTION"));
        }

        if(exchange.getIn().getHeader("messageSummary")!=null)
            response.setMessageSummary((String) exchange.getIn().getHeader("messageSummary"));

        if(exchange.getIn().getHeader("messageDetail")!=null)
            response.setMessageDetail((String) exchange.getIn().getHeader("messageDetail"));

        response.setData(exchange.getIn().getBody());

        return response;
    }

    public Response toRulesResponse(Exchange exchange) {
        Response response = new Response();
        response.setStatus(ServiceStatus.SUCCESS);

        if(exchange.getIn().getHeader("messageSummary")!=null)
            response.setMessageSummary((String) exchange.getIn().getHeader("messageSummary"));

        if(exchange.getIn().getHeader("messageDetail")!=null)
            response.setMessageDetail((String) exchange.getIn().getHeader("messageDetail"));

        response.setData(exchange.getIn().getBody());

        if(response.getData() != null) {
            Object object = response.getData();
            if(object instanceof List) {
                List list = (List)object;
                if(list.size() == 0)
                    return response;
            }

            response.setStatus(ServiceStatus.RULE_FAILURE);
        }

        return response;
    }
}
