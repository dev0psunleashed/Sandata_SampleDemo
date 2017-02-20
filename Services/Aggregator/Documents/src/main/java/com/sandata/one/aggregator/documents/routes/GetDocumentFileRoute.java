package com.sandata.one.aggregator.documents.routes;

import com.sandata.lab.common.utils.camel.AbstractRouteBuilder;
import com.sandata.one.aggregator.documents.utils.constants.App;

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
                .bean("documentService", "getDocumentFileStream");

        from("direct:" + App.ID.GET_PATIENT_SIG_FILE.toString())
                .routeId(App.ID.GET_PATIENT_SIG_FILE.toString())
                .bean("documentService", "getPatientSignatureStream");

        from("direct:" + App.ID.GET_PATIENT_AUDIO_FILE.toString())
                .routeId(App.ID.GET_PATIENT_AUDIO_FILE.toString())
                .bean("documentService", "getPatientAudioStream");

    }
}
