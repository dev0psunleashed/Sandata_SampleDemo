package com.sandata.one.aggregator.documents;

import com.sandata.lab.common.oracle.db.connection.SandataOracleConnection;
import com.sandata.one.aggregator.documents.impl.data.DocumentDataTransformer;
import com.sandata.one.aggregator.documents.services.oracle.DocumentOracleRepositoryService;
import oracle.ucp.UniversalConnectionPoolException;
import org.junit.After;
import org.junit.Before;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Date: 8/13/15
 * Time: 5:59 PM
 */

public class BaseDataIntegrationTest extends BaseTestSupport {

    private SandataOracleConnection sandataOracleConnection;
    private DocumentOracleRepositoryService documentOracleRepositoryService;
    private DocumentDataTransformer documentDataTransformer;

    @Before
    public void beforeTest() throws UniversalConnectionPoolException {

        sandataOracleConnection.startPool();
    }

    @After
    public void afterTest() throws UniversalConnectionPoolException {
        sandataOracleConnection.stopPool();
    }

    public BaseDataIntegrationTest() throws SQLException {

         this.sandataOracleConnection =
                new SandataOracleConnection()
                        .DatabaseName("sfdbdev2")
                        .ServerName("stxdevdb.sandata.com")
                        .PortNumber(1526)
                        .User("lobdata")
                        .Password("lAZa7QCLsc")
                        .Open();
    }

    private SandataOracleConnection getSandataOracleConnection() {
        return sandataOracleConnection;
    }

    @Override
    protected void onSetup() throws IOException {

        /*documentOracleRepositoryService = new DocumentOracleRepositoryService();
        documentOracleRepositoryService.setSandataOracleConnection(getSandataOracleConnection());
        documentDataTransformer = new DocumentDataTransformer();*/
    }

    /*@Test
    public void insertDoc() {
        try {
            Document document = new Document();
            document.setDocumentID("3");
            document.setDocumentSK(BigInteger.valueOf(1));
            document.setDocumentTypeLookupSK(BigInteger.valueOf(2));

            DocT docTyp = documentDataTransformer.transformDocumentToDocTyp(document,
                                    sandataOracleConnection.getConnection());

            OracleRequest oracleRequest = new OracleRequest();
            oracleRequest.setData(docTyp);
            oracleRequest.setMethodName("insertDoc");
            oracleRequest.setPackageName("PKG_DOC");

            documentOracleRepositoryService.execute(oracleRequest);

        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }

    }

    @Test
    public void insertDocTypLookup() {
        try {


            DocumentTypeLookUp documentTypeLookUp = new DocumentTypeLookUp();
            documentTypeLookUp.setDocumentTypeDesc("PDF");
            documentTypeLookUp.setDocumentTypeID("1");
            documentTypeLookUp.setDocumentTypeLookupSK(BigInteger.valueOf(1));

            DocTypLkupT docTypLkupTyp = documentDataTransformer.transformDocumentTypLkupToDocTypLkupTyp(documentTypeLookUp);

            OracleRequest oracleRequest = new OracleRequest();
            oracleRequest.setData(docTypLkupTyp);
            oracleRequest.setMethodName("insertDocTypLkUp");
            oracleRequest.setPackageName("PKG_DOC");

            documentOracleRepositoryService.execute(oracleRequest);

        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    */
}
