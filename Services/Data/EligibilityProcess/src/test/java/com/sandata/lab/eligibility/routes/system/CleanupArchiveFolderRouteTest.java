package com.sandata.lab.eligibility.routes.system;

import java.io.File;
import java.io.IOException;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.AdviceWithRouteBuilder;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.impl.JndiRegistry;
import org.apache.camel.impl.PropertyPlaceholderDelegateRegistry;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import com.sandata.lab.eligibility.filters.ObsoleteArchiveFileFilter;

public class CleanupArchiveFolderRouteTest extends CamelTestSupport {

    private static final String SAMPLE_FILE = "/Inquiry_sample.json";
    private static final String SAMPLE_FOLDER = "src/test/resources/inquiry-json-files";
    private static final String TEMP_FOLDER = "target/temp-folder";
    private static final String ARCHIVE_FODLER = "archive";
    private static final String SAMPLE_FILE_IN_ARCHIVE_FODLER = new StringBuilder(TEMP_FOLDER).append("/").append(ARCHIVE_FODLER).append(SAMPLE_FILE).toString();
    private static final String SAMPLE_FILE_IN_TEMP_FOLDER = new StringBuilder(TEMP_FOLDER).append(SAMPLE_FILE).toString();
    private static final String MOCK_END_ENDPOINT = "mock:end";
    private static final String OBSOLETE_ARCHIVE_FILE_FILTER_BEAN_NAME = "obsoleteArchiveFileFilter";

    @Before
    public void setupTestFiles() throws IOException {
        File sampleFile = new File(SAMPLE_FOLDER + SAMPLE_FILE);
        File testFileInArchiveFolder = new File(SAMPLE_FILE_IN_ARCHIVE_FODLER);
        File testFileInTempFolder = new File(SAMPLE_FILE_IN_TEMP_FOLDER);
        
        FileUtils.copyFile(sampleFile, testFileInArchiveFolder);
        FileUtils.copyFile(sampleFile, testFileInTempFolder);

        // make the file too old (obsolete)
        testFileInArchiveFolder.setLastModified(1000);
        testFileInTempFolder.setLastModified(1000);
    }

    @After
    public void cleanUp() throws IOException {
        FileUtils.deleteDirectory(new File(TEMP_FOLDER));
    }

    @Override
    public boolean isUseAdviceWith() {
        return true;
    }

    @Override
    protected RouteBuilder createRouteBuilder() {
        return new CleanupArchiveFolderRoute();
    }

    @SuppressWarnings("rawtypes")
    @Override
    protected CamelContext createCamelContext() throws Exception {
        CamelContext context = super.createCamelContext();

        PropertyPlaceholderDelegateRegistry registry = (PropertyPlaceholderDelegateRegistry) context.getRegistry();
        JndiRegistry jndiRegistry = (JndiRegistry) registry.getRegistry();

        ObsoleteArchiveFileFilter obsoleteArchiveFileFilter = jndiRegistry.lookupByNameAndType(OBSOLETE_ARCHIVE_FILE_FILTER_BEAN_NAME, ObsoleteArchiveFileFilter.class);
        if (obsoleteArchiveFileFilter == null) {
            obsoleteArchiveFileFilter = new ObsoleteArchiveFileFilter();
            obsoleteArchiveFileFilter.setRetentionDuration(1);
            jndiRegistry.bind(OBSOLETE_ARCHIVE_FILE_FILTER_BEAN_NAME, obsoleteArchiveFileFilter);
        }

        return context;
    }

    private String getFromEndpoint() {
        return new StringBuilder("file:./")
                .append(TEMP_FOLDER)
                .append("?recursive=true&delete=true")
                .append("&antInclude=**/").append(ARCHIVE_FODLER).append("/*")
                .append("&delay=1s&filter=#").append(OBSOLETE_ARCHIVE_FILE_FILTER_BEAN_NAME)
                .toString();
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();

        context.getRouteDefinitions().get(0).adviceWith(context, new AdviceWithRouteBuilder() {

            @Override
            public void configure() throws Exception {
                // replace 'from' endpoint with direct:start
                replaceFromWith(getFromEndpoint());

                weaveAddLast().to(MOCK_END_ENDPOINT);
            }

        });
    }

    @Test
    public void testCleanupArchiveFolderRoute_should_delete_obsolete_json_files_in_archive_folder() throws Exception {
        // arrange
        MockEndpoint mockEndEndpoint = getMockEndpoint(MOCK_END_ENDPOINT);
        mockEndEndpoint.setExpectedCount(1);

        // act
        context.start();

        // assert
        mockEndEndpoint.assertIsSatisfied(2000);
        Thread.sleep(1000);
 
        // assert: should only delete obsolete file in archive folder
        assertFileExists(SAMPLE_FILE_IN_TEMP_FOLDER);
        assertFileNotExists(SAMPLE_FILE_IN_ARCHIVE_FODLER);
    }
}
