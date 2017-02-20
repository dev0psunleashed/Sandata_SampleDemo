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

package com.sandata.lab.dl.doc.routes;

import com.sandata.lab.common.utils.camel.AbstractRouteBuilder;
import com.sandata.lab.common.utils.messaging.Messaging;
import com.sandata.lab.dl.doc.utils.constants.App;

/**
 * Handles the insert of document files.
 * <p/>
 *
 * @author David Rutgos
 */
public class InsertDocumentFileRoute extends AbstractRouteBuilder {

    @Override
    protected void buildRoute() {

        from("direct:" + App.ID.INSERT_DOCUMENT_FILE.toString())
                .routeId(App.ID.INSERT_DOCUMENT_FILE.toString())
                .beanRef("documentService", "generateStatusId")
                .wireTap(Messaging.Names.COMPONENT_TYPE_QUEUE + App.ID.INSERT_DOCUMENT_FILE.toString() + "_PROCESS").onPrepare(new Cloner())
                .beanRef("documentService", "returnStatusId")
                .removeHeaders("Content-Length")
                .beanRef("formatTransformer", "toResponse");

        from(Messaging.Names.COMPONENT_TYPE_QUEUE + App.ID.INSERT_DOCUMENT_FILE.toString() + "_PROCESS")
                .routeId(App.ID.INSERT_DOCUMENT_FILE.toString() + "_PROCESS")
                .beanRef("documentService", "insertDocumentFile")
                .log("InsertDocumentFileRoute: [DOCUMENT_STATUS_ID=${in.header.doc_status_id}]: COMPLETE!");
    }
}
