package com.sandata.lab.dl.doc.routes;

import com.sandata.lab.common.utils.camel.AbstractRouteBuilder;
import com.sandata.lab.dl.doc.utils.constants.App;

/**
 * Handles the retrieval of document files.
 * <p/>
 *
 * @author David Rutgos
 */
public class GetDocumentFileRoute extends AbstractRouteBuilder {

    @Override
    protected void buildRoute() {

        from("direct:" + App.ID.GET_DOCUMENT_FILE.toString())
                .routeId(App.ID.GET_DOCUMENT_FILE.toString())
                .beanRef("documentService", "getDocumentFileStream");
    }
}
