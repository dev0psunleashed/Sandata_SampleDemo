/**
 * 
 */
package com.sandata.lab.rules.vv.imports.services;

import com.sandata.lab.data.model.response.Response;
import com.sandata.lab.rules.vv.imports.model.EVVCall;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * @author thanhxle
 *
 * @Description :
 *
 * @date Aug 23, 2016
 * 
 * 
 */
public class RestfulService {
    
    @POST
    @Path("/import")
    @Produces(value = {MediaType.APPLICATION_JSON})
    @Consumes(value = { MediaType.APPLICATION_JSON})
    public final Response EVV_CALLS_IMPORT_REST_ROUTE(List<EVVCall> calls) {
        
            return null;
        }
    
}



