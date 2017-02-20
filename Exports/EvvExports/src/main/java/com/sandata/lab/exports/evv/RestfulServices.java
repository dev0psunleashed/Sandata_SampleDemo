package com.sandata.lab.exports.evv;

import org.apache.cxf.common.util.ReflectionInvokationHandler;

import javax.ws.rs.GET;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: Tom Dornseif
 * Date: 10/8/2016
 * Time: 8:21 AM
 */
public class RestfulServices {
    @GET
    @Path("/uploadSchedules")
    @Produces(value = {MediaType.APPLICATION_XML})
    public final Response PKG_getUploadSchedules(
            @MatrixParam("from_date") String fromDate,
            @MatrixParam("toDate") String toDate,
            @MatrixParam("bsn_ent_id") String bsnEntId,
            @MatrixParam("santraxAgencyId") String agencyId) {
        return null;
    }

}
