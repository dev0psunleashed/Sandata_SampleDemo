package com.sandata.lab.eligibility.routes.system;

import org.apache.camel.LoggingLevel;
import org.apache.camel.PropertyInject;

import com.sandata.lab.common.utils.camel.AbstractRouteBuilder;
import com.sandata.lab.eligibility.utils.log.LoggingUtils;

/**
 * Route to delete obsolete archived files
 */
public class CleanupArchiveFolderRoute extends AbstractRouteBuilder {

    @PropertyInject("{{cleanup.archive.folder.from.endpoint}}")
    private String cleanupArchiverFolderFromEndpoint;

    @Override
    protected void buildRoute() {
        onException(Exception.class)
            .log(LoggingLevel.ERROR, LoggingUtils.getErrorMessageInfo(this, "Unexpected exception happens in ${routeId}. Exception message: ${exception.message}.\nStack trace: ${exception.stacktrace}"));

        from(cleanupArchiverFolderFromEndpoint)
            .routeId(this.getClass().getSimpleName())
            .log(LoggingUtils.getLogMessageInfo(this, "Deleting ${header.CamelFileName}"));
    }

}
