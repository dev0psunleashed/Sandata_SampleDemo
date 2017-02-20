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

package com.sandata.one.aggregator.audit;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

public class RestfulServices {

    //region Visit Patient Details

    @GET
    @Path("/forced_logout")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final javax.ws.rs.core.Response PKG_forcedLogout_Response() {
        return null;
    }

    //endregion
}
