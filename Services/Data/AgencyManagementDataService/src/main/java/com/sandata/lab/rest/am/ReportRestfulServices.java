package com.sandata.lab.rest.am;

import com.sandata.lab.data.model.response.Response;

import javax.ws.rs.GET;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

public class ReportRestfulServices {

    @GET
    @Path("/report/types")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_REPORT_getReportTypes_List() {

        return null;
    }

    @GET
    @Path("/report/names")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_REPORT_getReportNames_List(@MatrixParam("report_type") String reportType) {

        return null;
    }

    @GET
    @Path("/report/params")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_REPORT_getReportParams_List(@MatrixParam("report_id") String reportId) {

        return null;
    }
}
