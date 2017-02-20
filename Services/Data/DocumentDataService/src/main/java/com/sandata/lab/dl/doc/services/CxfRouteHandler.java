/*
 * Copyright (c) 2016. Sandata Technologies, LLC
 * 26 Harbor Park Drive, Port Washington, NY 11050, 800-544-7263
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of Sandata Technologies, LLC
 * ("Confidential Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered into with
 * Sandata.
 */

package com.sandata.lab.dl.doc.services;

import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.dl.doc.app.AppContext;
import com.sandata.lab.dl.doc.impl.DocumentService;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;

/**
 * Handler to process REST endpoint requests.
 * <p/>
 *
 * @author David Rutgos
 */
public class CxfRouteHandler {

    public void handler(Exchange exchange) throws SandataRuntimeException {

        String httpMethod = (String) exchange.getIn().getHeader("CamelHttpMethod");
        String operationName = (String) exchange.getIn().getHeader("operationName");

        CamelContext context = AppContext.getContext();
        DocumentService documentService = (DocumentService) context.getRegistry().lookupByName("documentService");

        exchange.getIn().setHeader("CXF_ROUTE_HANDLED", Boolean.TRUE);

        if (httpMethod.equals("GET") && operationName.contains("_getDocumentUploadStatus")) {
            documentService.getDocumentUploadStatus(exchange);
        } else if (httpMethod.equals("GET") && operationName.contains("_getDocumentTypeLookup")) {
            documentService.getDocumentTypeLookup(exchange);
        } else if (httpMethod.equals("GET") && operationName.contains("_getDocumentPropertiesLookup")) {
            documentService.getDocumentPropertiesLookup(exchange);
        } else if (httpMethod.equals("GET") && operationName.contains("_getDocumentTemplate")) {
            documentService.getBeDocuments(exchange);
        } else if (httpMethod.equals("GET") && operationName.contains("_getDocumentsByDocDet")) {
            documentService.getDocumentsByParameters(exchange);
        } else if (httpMethod.equals("GET") && operationName.contains("_getDocument")) {
            documentService.getDocument(exchange);
        } else if (httpMethod.equals("GET") && operationName.contains("_getPatientDocuments")) {
            documentService.getPatientDocuments(exchange);
        } else if (httpMethod.equals("GET") && operationName.contains("_getStaffDocuments")) {
            documentService.getStaffDocuments(exchange);
        } else if (httpMethod.equals("GET") && operationName.contains("_getVisitDocuments")) {
            documentService.getVisitDocuments(exchange);
        } else if (httpMethod.equals("POST") && operationName.contains("_insertDocumentDetails")) {
            documentService.insertDocumentDetails(exchange);
        } else if (httpMethod.equals("POST") && operationName.contains("_insertDocument")) {
            documentService.insertDocument(exchange);
        } else if (httpMethod.equals("PUT") && operationName.contains("_updateDocumentDetails")) {
            documentService.updateDocumentDetails(exchange);
        } else if (httpMethod.equals("PUT") && operationName.contains("_updateDocument")) {
            documentService.updateDocument(exchange);
        } else if (httpMethod.equals("DELETE") && operationName.contains("_deleteDocument")) {
            documentService.deleteDocument(exchange);
        } else {
            exchange.getIn().setHeader("CXF_ROUTE_HANDLED", Boolean.FALSE);
        }
    }
}
