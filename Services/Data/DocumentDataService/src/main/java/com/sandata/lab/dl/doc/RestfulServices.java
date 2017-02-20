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

package com.sandata.lab.dl.doc;


import com.sandata.lab.data.document.dl.model.Document;
import com.sandata.lab.data.document.dl.model.DocumentDetail;
import com.sandata.lab.data.model.response.Response;
import org.apache.camel.Consume;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

/**
 * Defines the cxf REST endpoints.
 * <p/>
 *
 * @author Ralph Sylvain
 */
public class RestfulServices {

    @GET
    @Path("/lookup/type")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PK_DOC_getDocumentTypeLookup_Response() {
        return null;
    }

    @GET
    @Path("/lookup/property")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PK_DOC_getDocumentPropertiesLookup_Response() {
        return null;
    }

    @GET
    @Path("/{document_id}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PK_DOC_getDocument_Response(@PathParam("document_id") String docId) {
        return null;
    }

    @GET
    @Path("/patient")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PK_DOC_getPatientDocuments_Response(@MatrixParam("patient_id") String patientId,
                                                              @MatrixParam("bsn_ent_id") String busEntID,
                                                              @MatrixParam("detailType") String type,
                                                              @DefaultValue("1") @MatrixParam("page") int page,
                                                              @DefaultValue("10") @MatrixParam("page_size") int pageSize,
                                                              @DefaultValue("ln") @MatrixParam("sort_on") String sortOn,
                                                              @DefaultValue("ASC") @MatrixParam("direction") String direction,
                                                              @MatrixParam("class") String className
                                                              ) {
        return null;
    }

    @GET
    @Path("/staff")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PK_DOC_getStaffDocuments_Response(@MatrixParam("staff_id") String staffId,
                                                            @MatrixParam("bsn_ent_id") String busEntID,
                                                            @MatrixParam("detailType") String type,
                                                            @DefaultValue("1") @MatrixParam("page") int page,
                                                            @DefaultValue("10") @MatrixParam("page_size") int pageSize,
                                                            @DefaultValue("ln") @MatrixParam("sort_on") String sortOn,
                                                            @DefaultValue("ASC") @MatrixParam("direction") String direction,
                                                            @MatrixParam("class") String className) {
        return null;
    }

    @GET
    @Path("/visit")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PK_DOC_getVisitDocuments_Response(@MatrixParam("visit_sk") String visitSk,
                                                            @MatrixParam("bsn_ent_id") String busEntID,
                                                            @MatrixParam("detailType") String type,
                                                            @DefaultValue("1") @MatrixParam("page") int page,
                                                            @DefaultValue("10") @MatrixParam("page_size") int pageSize,
                                                            @DefaultValue("ln") @MatrixParam("sort_on") String sortOn,
                                                            @DefaultValue("ASC") @MatrixParam("direction") String direction,
                                                            @MatrixParam("class") String className) {
        return null;
    }

    @GET
    @Path("/bsn")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PK_DOC_getDocumentTemplates_Response(
            @MatrixParam("bsn_ent_id") String busEntID,
            @MatrixParam("detailType") String type,
            @DefaultValue("1") @MatrixParam("page") int page,
            @DefaultValue("10") @MatrixParam("page_size") int pageSize, @DefaultValue("ln") @MatrixParam("sort_on") String sortOn,
            @DefaultValue("ASC") @MatrixParam("direction") String direction,
            @MatrixParam("class") String className) {
        return null;
    }

    @GET
    @Path("/docs")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PK_DOC_getDocumentsByDocDet_Response(
            @MatrixParam("detail_id") String detailID,
            @MatrixParam("detail_value") String detailValue,
            @DefaultValue("1") @MatrixParam("page") int page,
            @DefaultValue("10") @MatrixParam("page_size") int pageSize, @DefaultValue("createdDate") @MatrixParam("sort_on") String sortOn,
            @DefaultValue("ASC") @MatrixParam("direction") String direction) {
        return null;
    }

    @POST
    @Path("/")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PK_DOC_insertDocument_Response(Document data) {

        return null;
    }

    @PUT
    @Path("/update")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PK_DOC_updateDocument_Response(Document data) {

        return null;
    }

    @DELETE
    @Path("/delete/{document_id}")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PK_DOC_deleteDocument_Response(@PathParam("document_id") String docId) {

        return null;
    }

    @POST
    @Path("/file/{document_id}")
    @Consumes(value = {MediaType.APPLICATION_OCTET_STREAM})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response INSERT_DOCUMENT_FILE(InputStream inputStream, @PathParam("document_id") String docId,
                                               @DefaultValue("document") @MatrixParam("data_field") String docDataField) {

        return null;
    }

    @GET
    @Path("/file/upload/{status_id}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PK_DOC_getDocumentUploadStatus(@PathParam("status_id") String docId) {
        return null;
    }

    @GET
    @Path("/file/{document_id}")
    @Produces(value = {MediaType.APPLICATION_OCTET_STREAM})
    public final javax.ws.rs.core.Response GET_DOCUMENT_FILE(@PathParam("document_id") String docId,
                                                             @DefaultValue("document") @MatrixParam("data_field") String docDataField) {
        return null;
    }

    @POST
    @Path("/details")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PK_DOC_insertDocumentDetails(List<DocumentDetail> documentDetails) {
        return null;
    }

    @PUT
    @Path("/details")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PK_DOC_updateDocumentDetails(List<DocumentDetail> documentDetails) {
        return null;
    }
}
