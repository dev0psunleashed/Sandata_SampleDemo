package com.sandata.one.aggregator.documents.impl;

import com.sandata.lab.common.utils.data.provider.GSONProvider;
import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.common.utils.log.SandataLogger;
import com.sandata.lab.data.document.dl.model.*;
import com.sandata.lab.data.model.jpub.model.*;
import com.sandata.lab.data.model.response.Response;
import com.sandata.one.aggregator.documents.api.DocumentStatusUpdate;
import com.sandata.one.aggregator.documents.impl.data.DocumentDataTransformer;
import com.sandata.one.aggregator.documents.impl.data.model.jpub.DocDetProptyLkupT;
import com.sandata.one.aggregator.documents.impl.data.model.jpub.DocDetT;
import com.sandata.one.aggregator.documents.impl.data.model.jpub.DocT;
import com.sandata.one.aggregator.documents.impl.data.model.jpub.DocTypLkupT;
import com.sandata.one.aggregator.documents.impl.data.requests.OracleRequest;
import com.sandata.one.aggregator.documents.services.oracle.DocumentOracleRepositoryService;
import com.sandata.one.aggregator.documents.settings.DocumentServiceSettings;
import com.sandata.one.aggregator.documents.utils.FileUtil;
import com.sandata.one.aggregator.documents.utils.log.DocumentDataLogger;
import org.apache.camel.Exchange;
import org.apache.commons.lang3.time.StopWatch;
import org.apache.cxf.message.MessageContentsList;

import java.io.*;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class DocumentService implements DocumentStatusUpdate {

    private final String PACKAGE_NAME = "PKG_DOC";

    private static final Object lock = new Object();

    private Map<String, DocumentSaveStatus> documentSaveStatusMap = new HashMap<>();

    private DocumentDataTransformer documentDataTransformer;

    public void setDocumentDataTransformer(DocumentDataTransformer documentDataTransformer) {
        this.documentDataTransformer = documentDataTransformer;
    }

    private DocumentOracleRepositoryService documentOracleRepositoryService;

    public void setDocumentOracleRepositoryService(DocumentOracleRepositoryService documentOracleRepositoryService) {
        this.documentOracleRepositoryService = documentOracleRepositoryService;
    }

    private CrosswalkService crosswalkService;

    public void setCrosswalkService(CrosswalkService crosswalkService) {
        this.crosswalkService = crosswalkService;
    }

    public void generateStatusId(Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = DocumentDataLogger.CreateLogger(exchange);

        logger.start();

        String docId = (String) exchange.getIn().getHeader("document_id");
        if (docId == null) {
            throw new SandataRuntimeException("generateStatusId: DocumentID (doc_id) must be provided!");
        }

        String docStatusId = UUID.randomUUID().toString();
        exchange.getIn().setHeader("doc_status_id", docStatusId);


        logger.info(String.format("generateStatusId: [DOC_ID=%s]: Generated [DOC_STATUS_ID=%s]", docId, docStatusId));

        logger.stop();
    }

    public void returnStatusId(Exchange exchange) {

        String docStatusId = (String) exchange.getIn().getHeader("doc_status_id");
        if (docStatusId == null) {
            throw new SandataRuntimeException("returnStatusId: DocumentStatusID (doc_status_id) must be provided!");
        }

        exchange.getIn().setBody(docStatusId);
    }

    public void getDocumentFileStream(Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = DocumentDataLogger.CreateLogger(exchange);

        logger.start();

        String docDataField = (String) exchange.getIn().getHeader("data_field");

        String docId = (String) exchange.getIn().getHeader("document_id");
        if (docId == null) {
            throw new SandataRuntimeException("getDocumentFileStream: DocumentID (document_id) must be provided!");
        }

        try {

            String documentCachePath = DocumentServiceSettings.getInstance().getDocumentCachePath();
            FileUtil.createDirectory(documentCachePath);

            String dataColumn = getDOCColumnName(docDataField);

            File cachedFile = documentOracleRepositoryService.getDocumentFile(docId, documentCachePath, logger, dataColumn);
            if (cachedFile == null) {
                throw new SandataRuntimeException("cachedFile == null");
            }


            exchange.getIn().setBody(cachedFile);
        } catch (Exception e) {

            e.printStackTrace();
            String errMsg = String.format("getDocumentFileStream: %s: %s: [DOC_ID=%s]: [ERROR_MSG=%s]",
                    getClass().getName(),
                    e.getClass().getName(),
                    docId,
                    e.getMessage());

            throw new SandataRuntimeException(errMsg, e);
        } finally {

            logger.stop();
        }
    }

    public void getPatientSignatureStream(Exchange exchange) throws SandataRuntimeException {
        processFileRequestForVisit(exchange);
    }

    public void getPatientAudioStream(Exchange exchange) throws SandataRuntimeException {
        processFileRequestForVisit(exchange);
    }

    private void processFileRequestForVisit(Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = DocumentDataLogger.CreateLogger(exchange);

        logger.start();

        long visitSk = (long) exchange.getIn().getHeader("visit_sk");
        if (visitSk <= 0) {
            throw new SandataRuntimeException("getPatientAudioStream: VisitSK (visit_sk) must be provided!");
        }

        String docId = null;
        try {

            String documentCachePath = DocumentServiceSettings.getInstance().getDocumentCachePath();
            FileUtil.createDirectory(documentCachePath);

            docId = documentOracleRepositoryService.getDocIdForVisit(visitSk);
            if (docId == null) {
                exchange.getIn().setBody(null);
                return;
            }

            File cachedFile = documentOracleRepositoryService.getDocumentFile(docId, documentCachePath, logger, "DOC");
            if (cachedFile == null) {
                throw new SandataRuntimeException("cachedFile == null");
            }

            exchange.getIn().setBody(cachedFile);

        } catch (Exception e) {

            e.printStackTrace();
            String errMsg = String.format("getDocumentFileStream: %s: %s: [DOC_ID=%s]: [ERROR_MSG=%s]",
                    getClass().getName(),
                    e.getClass().getName(),
                    docId,
                    e.getMessage());

            throw new SandataRuntimeException(errMsg, e);
        } finally {

            logger.stop();
        }
    }

    public void insertDocumentFile(Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = DocumentDataLogger.CreateLogger(exchange);

        logger.start();

        String docDataField = (String) exchange.getIn().getHeader("data_field");

        String docId = (String) exchange.getIn().getHeader("document_id");
        if (docId == null) {
            throw new SandataRuntimeException("insertDocumentFile: DocumentID (document_id) must be provided!");
        }

        String docStatusId = (String) exchange.getIn().getHeader("doc_status_id");
        if (docStatusId == null) {
            throw new SandataRuntimeException("insertDocumentFile: DocumentStatusID (doc_status_id) must be provided!");
        }

        try {

            StopWatch stopWatch = new StopWatch();
            DocumentSaveStatus documentSaveStatus = new DocumentSaveStatus();
            documentSaveStatus.setStarted(new Date());
            stopWatch.start();

            documentSaveStatus.setDocumentId(docId);
            documentSaveStatus.setStatusId(docStatusId);

            documentSaveStatusMap.put(docStatusId, documentSaveStatus);

            String documentBackupPath = DocumentServiceSettings.getInstance().getDocumentBackupPath();
            String documentStatusPath = DocumentServiceSettings.getInstance().getDocumentStatusPath();
            String documentCachePath = DocumentServiceSettings.getInstance().getDocumentCachePath();

            // Make sure file path exists (if not create it)
            FileUtil.createDirectory(documentBackupPath);
            FileUtil.createDirectory(documentStatusPath);
            FileUtil.createDirectory(documentCachePath);

            InputStream inputStream = exchange.getIn().getBody(InputStream.class);

            if (inputStream == null) {
                String errMsg = String.format("insertDocumentFile: [DOC_ID=%s]: inputStream == null", docId);
                errorMessage(errMsg, "Error", docStatusId, logger);
                throw new SandataRuntimeException(errMsg);
            }

            String dataColumn = getDOCColumnName(docDataField);

            // Backup the file
            documentSaveStatus.setStatus("BackupFile");
            String fileName = FileUtil.timeStampFileName(docId + dataColumn, "backup");
            File backupFile = new File(String.format("%s/%s", documentBackupPath, fileName));
            OutputStream outputStream = new FileOutputStream(backupFile, false);
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                logger.info(String.format("insertDocumentFile: [DOC_ID=%s]: [BYTES_READ=%d]", docId, bytesRead));
                outputStream.write(buffer, 0, bytesRead);
            }

            // Clean up
            try {
                outputStream.flush();
                outputStream.close();
            } catch (IOException ioe) {
                logger.warn(String.format("IOException: %s: [outputStream.close()]", ioe.getMessage()));
            }

            try {
                inputStream.close();
            } catch (IOException ioe) {
                logger.warn(String.format("IOException: %s: [inputStream.close()]", ioe.getMessage()));
            }
            //

            documentSaveStatus.setStatus("TempFile");

            File tmpFile = backupFile;

            documentSaveStatus.setFileSize(tmpFile.length());


            // Save the file to BLOB
            if (!documentOracleRepositoryService.saveFileToBlob(tmpFile, docId, docStatusId, this, dataColumn)) {

                throw new SandataRuntimeException(String.format("insertDocumentFile: %s: [DOC_ID=%s]: " +
                        "[DOC_STATUS_ID=%s]: saveFile was not successful!", tmpFile.getPath(), docId, docStatusId));
            }

            if (tmpFile.delete()) {
                logger.info(String.format("insertDocumentFile: %s: [DOC_ID=%s]: tmpFile deleted!", tmpFile.getPath(), docId));
            }

            documentSaveStatus.setCompleted(new Date());
            stopWatch.stop();
            documentSaveStatus.setElapsedTime(stopWatch.toString());

            writeDocumentStatus(documentSaveStatus, docStatusId, logger);

            // Remove status from Map
            DocumentSaveStatus removedValue = null;
            synchronized (lock) {
                removedValue = documentSaveStatusMap.remove(docStatusId);
            }

            if (removedValue != null) {
                logger.info(String.format("insertDocumentFile: [DOC_ID=%s]: Successfully removed from cache!", removedValue.getDocumentId()));
            } else {
                logger.warn(String.format("insertDocumentFile: [DOC_STATUS_ID=%s]: Expected value not in cache!", docStatusId));
            }
        } catch (Exception e) {

            e.printStackTrace();
            String errMsg = String.format("insertDocumentFile: %s: %s: [DOC_ID=%s]: [DOC_STATUS_ID=%s]: [ERROR_MSG=%s]",
                    getClass().getName(),
                    e.getClass().getName(),
                    docId,
                    docStatusId,
                    e.getMessage());

            throw new SandataRuntimeException(errMsg, e);
        } finally {

            logger.stop();
        }
    }

    private String getDOCColumnName(String docDataField) {
        if (docDataField.equalsIgnoreCase("document")) {
            return "DOC";
        } else if (docDataField.equalsIgnoreCase("other")) {
            return "DOC_OTHER";
        }

        return "DOC";

    }

    public void insertDocument(Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = DocumentDataLogger.CreateLogger(exchange);

        try {
            logger.start();

            Document document = exchange.getIn().getBody(Document.class);

            document.setDocumentID(UUID.randomUUID().toString());

            DocT docTyp = documentDataTransformer.transformDocumentToDocTyp(document);

            //PtDocXwalkTyp patientDocXwalkTyp = documentDataTransformer.transformDocToPtDocXwalkTyp(document);

            logger.logger().info(String.format("Inserting Document: [DOC_ID=%s]", document.getDocumentID()));

            OracleRequest docOracleRequest = new OracleRequest();
            docOracleRequest.setData(docTyp);
            docOracleRequest.setMethodName("insertDoc");
            docOracleRequest.setPackageName(PACKAGE_NAME);

            int docSK = documentOracleRepositoryService.execute(docOracleRequest);

            document.setDocumentSK(BigInteger.valueOf(docSK));

            insertDocumentDetails(document, logger);

            crosswalkService.insertDocumentXwalk(document);

            logger.logger().info(String.format("Document inserted: [DOC_SK=%d]", docSK));

            exchange.getIn().setBody(document.getDocumentID());
        } catch (Exception e) {

            e.printStackTrace();
            String errMsg = String.format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(errMsg, e);
        } finally {

            logger.stop();
        }
    }

    private void insertDocumentDetails(Document document, SandataLogger logger) throws SandataRuntimeException {

        List<DocumentDetail> documentDetailList = document.getDocumentDetails();

        if (documentDetailList == null) {

            logger.warn(String.format("[%s]: documentDetailList = null", document.getDocumentID()));
            return;
        }

        List<OracleRequest> oracleRequests = new ArrayList<>();

        for (DocumentDetail documentDetail : documentDetailList) {

            documentDetail.setDocumentSK(document.getDocumentSK());

            DocDetT docDetTyp = documentDataTransformer.transformDocumentDetailToDocDetT(documentDetail);

            OracleRequest oracleRequest = new OracleRequest();
            oracleRequest.setData(docDetTyp);
            oracleRequest.setPackageName(PACKAGE_NAME);
            oracleRequest.setMethodName("insertDocDet");

            oracleRequests.add(oracleRequest);
        }

        documentOracleRepositoryService.executeRequests(oracleRequests);
    }

    public void insertDocumentDetails(Exchange exchange) throws SandataRuntimeException {
        List<DocumentDetail> documentDetailList = (ArrayList<DocumentDetail>) exchange.getIn().getBody(ArrayList.class);

        List<OracleRequest> oracleRequests = new ArrayList<>();

        for (DocumentDetail documentDetail : documentDetailList) {

            DocDetT docDetTyp = documentDataTransformer.transformDocumentDetailToDocDetT(documentDetail);

            OracleRequest oracleRequest = new OracleRequest();
            oracleRequest.setData(docDetTyp);
            oracleRequest.setPackageName(PACKAGE_NAME);
            oracleRequest.setMethodName("insertDocDet");

            oracleRequests.add(oracleRequest);
        }

        int result = documentOracleRepositoryService.executeRequests(oracleRequests);

        exchange.getIn().setBody(result);
    }

    public void updateDocumentDetails(Exchange exchange) throws SandataRuntimeException {
        List<DocumentDetail> documentDetailList = (ArrayList<DocumentDetail>) exchange.getIn().getBody(ArrayList.class);

        List<DocDetT> docDetTypList = new ArrayList<>();

        for (DocumentDetail documentDetail : documentDetailList) {

            DocDetT docDetTyp = documentDataTransformer.transformDocumentDetailToDocDetT(documentDetail);

            if (docDetTyp != null) {
                docDetTypList.add(docDetTyp);
            }
        }

        int result = documentOracleRepositoryService.updateDocDetails(docDetTypList);

        exchange.getIn().setBody(result);
    }

    public void getDocument(Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = DocumentDataLogger.CreateLogger(exchange);

        try {
            logger.start();

            String docID = (String) exchange.getIn().getHeader("document_id");

            Document document = documentOracleRepositoryService.getDocumentForID(docID);

            PtDocXwalkT patientDocXwalkTyp = crosswalkService.getPatientXwalkDoc(document.getDocumentID());

            if (patientDocXwalkTyp != null) {
                documentDataTransformer.populateDocumentsfromPatientXwalkTyp(document, patientDocXwalkTyp);
            }

            StaffDocXwalkT staffDocXwalkTyp = crosswalkService.getStaffXwalkDoc(document.getDocumentID());

            if (staffDocXwalkTyp != null) {
                documentDataTransformer.populateDocumentsfromStaffXwalkTyp(document, staffDocXwalkTyp);
            }

            BeDocXwalkT bsnEntDocXwalkTyp = crosswalkService.getBeXwalkDoc(document.getDocumentID());

            if (bsnEntDocXwalkTyp != null) {
                documentDataTransformer.populateDocumentsfromBeXwalkTyp(document, bsnEntDocXwalkTyp);
            }

            exchange.getIn().setBody(document);

            logger.logger().info(String.format("Retrieved Document: [DOC_ID=%s]",
                    document.getDocumentID()));
        } catch (Exception e) {
            e.printStackTrace();

            String errMsg = String.format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(errMsg, e);
        } finally {
            logger.stop();
        }
    }

    public void getDocumentTypeLookup(Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = DocumentDataLogger.CreateLogger(exchange);

        try {
            logger.start();

            List<DocTypLkupT> docTypLkupTypList = documentOracleRepositoryService.getDocTypLkupTyp();

            if (!docTypLkupTypList.isEmpty()) {
                List<DocumentTypeLookUp> documentTypeLookUpList = new ArrayList<>();

                for (DocTypLkupT docTypLkupTyp : docTypLkupTypList) {

                    documentTypeLookUpList.add(documentDataTransformer.transformDocTypLkupToDocumentTypeLookup(docTypLkupTyp));
                }

                exchange.getIn().setBody(documentTypeLookUpList);
            }
        } catch (Exception e) {
            e.printStackTrace();

            String errMsg = String.format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(errMsg, e);
        } finally {
            logger.stop();
        }
    }

    public void getDocumentPropertiesLookup(Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = DocumentDataLogger.CreateLogger(exchange);

        try {
            logger.start();

            List<DocDetProptyLkupT> docDetProptyLkupTypList = documentOracleRepositoryService.getDocDetailLookup();

            if (!docDetProptyLkupTypList.isEmpty()) {
                List<DocumentDetailPropertyLookUp> documentDetailPropertyLookUps = new ArrayList<>();

                for (DocDetProptyLkupT docDetProptyLkupTyp : docDetProptyLkupTypList) {

                    documentDetailPropertyLookUps.add(documentDataTransformer.transformDocDetLkupProptyLkupTypToDocumentDetailLookup(docDetProptyLkupTyp));
                }

                exchange.getIn().setBody(documentDetailPropertyLookUps);
            }
        } catch (Exception e) {
            e.printStackTrace();

            String errMsg = String.format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(errMsg, e);
        } finally {
            logger.stop();
        }
    }

    public void getPatientDocuments(Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = DocumentDataLogger.CreateLogger(exchange);

        logger.start();

        try {
            MessageContentsList mcl = exchange.getIn().getBody(MessageContentsList.class);

            String patientID = (String) mcl.get(0);
            String bsnEntID = (String) mcl.get(1);
            String docType = (String) mcl.get(2);
            String className = (String) exchange.getIn().getHeader("class");

            List<Object> props = new ArrayList<>();

            props.add(mcl.get(3));
            props.add(mcl.get(4));
            props.add(mcl.get(5));
            props.add(mcl.get(6));

            Response response = crosswalkService.getPatientXwalkDocs(bsnEntID, patientID, props, className);

            List<PtDocXwalkT> patientDocXwalkTypList = (ArrayList) response.getData();

            List<String> docIds = getDocIDListFromPatientXwalk(patientDocXwalkTypList);

            if (docIds != null && !docIds.isEmpty()) {

                List<Document> documents = documentOracleRepositoryService.getDocuments(docIds, docType);

                documentDataTransformer.populateDocumentsfromPatientXwalkTyps(documents, patientDocXwalkTypList);

                exchange.getIn().setBody(documents);
            } else {
                exchange.getIn().setBody(null);
            }

            exchange.getIn().setHeader("TOTAL_ROWS", response.getTotalRows());
            exchange.getIn().setHeader("PAGE", response.getPage());
            exchange.getIn().setHeader("PAGE_SIZE", response.getPageSize());
            exchange.getIn().setHeader("ORDER_BY_COLUMN", response.getOrderByColumn());
            exchange.getIn().setHeader("ORDER_BY_DIRECTION", response.getOrderByDirection());

            logger.logger().info(String.format("Retrieved [DOC_XWALK_COUNT=%d] Patient Document IDs: [PT_ID=%s]",
                    patientDocXwalkTypList.size(), patientID));
        } catch (Exception e) {
            e.printStackTrace();
            throw new SandataRuntimeException(String.format("getPatientDocuments: %s: %s: [ERROR_MSG=%s]",
                    getClass().getName(), e.getClass().getName(), e.getMessage()), e);
        } finally {
            logger.stop();
        }
    }

    public void getStaffDocuments(Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = DocumentDataLogger.CreateLogger(exchange);
        logger.start();

        try {
            MessageContentsList mcl = exchange.getIn().getBody(MessageContentsList.class);

            String staffId = (String) mcl.get(0);
            String bsnEntId = (String) mcl.get(1);
            String docType = (String) mcl.get(2);
            String className = (String) exchange.getIn().getHeader("class");

            List<Object> props = new ArrayList<>();

            props.add(mcl.get(3));
            props.add(mcl.get(4));
            props.add(mcl.get(5));
            props.add(mcl.get(6));

            Response response = crosswalkService.getStaffXwalkDocs(bsnEntId, staffId, props, className);

            List<StaffDocXwalkT> staffDocXwalkTypList = (ArrayList) response.getData();

            List<String> docIds = getDocIDListFromStaffXwalk(staffDocXwalkTypList);

            if (docIds != null && !docIds.isEmpty()) {

                List<Document> documents = documentOracleRepositoryService.getDocuments(docIds, docType);

                documentDataTransformer.populateDocumentsfromStaffXwalkTyps(documents, staffDocXwalkTypList);

                exchange.getIn().setBody(documents);
            } else {
                exchange.getIn().setBody(null);
            }

            exchange.getIn().setHeader("TOTAL_ROWS", response.getTotalRows());
            exchange.getIn().setHeader("PAGE", response.getPage());
            exchange.getIn().setHeader("PAGE_SIZE", response.getPageSize());
            exchange.getIn().setHeader("ORDER_BY_COLUMN", response.getOrderByColumn());
            exchange.getIn().setHeader("ORDER_BY_DIRECTION", response.getOrderByDirection());

            logger.logger().info(String.format("Retrieved [DOC_XWALK_COUNT=%d] Staff Document IDs: [STAFF_ID=%s]",
                    staffDocXwalkTypList.size(), staffId));
        } catch (Exception e) {
            e.printStackTrace();
            throw new SandataRuntimeException(String.format("getPatientDocuments: %s: %s: [ERROR_MSG=%s]",
                    getClass().getName(), e.getClass().getName(), e.getMessage()), e);
        } finally {
            logger.stop();
        }
    }

    public void getVisitDocuments(Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = DocumentDataLogger.CreateLogger(exchange);
        logger.start();

        try {
            MessageContentsList mcl = exchange.getIn().getBody(MessageContentsList.class);

            String visitSK = (String) mcl.get(0);
            String bsnEntId = (String) mcl.get(1);
            String docType = (String) mcl.get(2);
            String className = (String) exchange.getIn().getHeader("class");

            List<Object> props = new ArrayList<>();

            props.add(mcl.get(3));
            props.add(mcl.get(4));
            props.add(mcl.get(5));
            props.add(mcl.get(6));

            Response response = crosswalkService.getVisitXwalkDocs(bsnEntId, visitSK, props, className);

            List<VisitDocXwalkT> visitDocXwalkTypList = (ArrayList) response.getData();

            List<String> docIds = getDocIDListFromVisitXwalk(visitDocXwalkTypList);

            if (docIds != null && !docIds.isEmpty()) {

                List<Document> documents = documentOracleRepositoryService.getDocuments(docIds, docType);

                documentDataTransformer.populateDocumentsfromVisitXwalkTyps(documents, visitDocXwalkTypList);

                exchange.getIn().setBody(documents);
            } else {
                exchange.getIn().setBody(null);
            }

            exchange.getIn().setHeader("TOTAL_ROWS", response.getTotalRows());
            exchange.getIn().setHeader("PAGE", response.getPage());
            exchange.getIn().setHeader("PAGE_SIZE", response.getPageSize());
            exchange.getIn().setHeader("ORDER_BY_COLUMN", response.getOrderByColumn());
            exchange.getIn().setHeader("ORDER_BY_DIRECTION", response.getOrderByDirection());

            logger.logger().info(String.format("Retrieved [DOC_XWALK_COUNT=%d] Visit Document IDs: [STAFF_ID=%s]",
                    visitDocXwalkTypList.size(), visitSK));
        } catch (Exception e) {
            e.printStackTrace();
            throw new SandataRuntimeException(String.format("getPatientDocuments: %s: %s: [ERROR_MSG=%s]",
                    getClass().getName(), e.getClass().getName(), e.getMessage()), e);
        } finally {
            logger.stop();
        }
    }

    public void getBeDocuments(Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = DocumentDataLogger.CreateLogger(exchange);
        logger.start();

        try {
            MessageContentsList mcl = exchange.getIn().getBody(MessageContentsList.class);

            String bsnEntId = (String) mcl.get(0);
            String docType = (String) mcl.get(1);
            String className = (String) exchange.getIn().getHeader("class");

            List<Object> props = new ArrayList<>();

            props.add(mcl.get(2));
            props.add(mcl.get(3));
            props.add(mcl.get(4));
            props.add(mcl.get(5));

            Response response = crosswalkService.getBeXwalkDocs(bsnEntId, props, className);

            List<BeDocXwalkT> bsnEntDocXwalkTypList = (ArrayList) response.getData();

            List<String> docIds = getDocIDListFromBeXwalk(bsnEntDocXwalkTypList);

            if (docIds != null && !docIds.isEmpty()) {

                List<Document> documents = documentOracleRepositoryService.getDocuments(docIds, docType);

                documentDataTransformer.populateDocumentsfromBeXwalkTyps(documents, bsnEntDocXwalkTypList);

                exchange.getIn().setBody(documents);
            } else {
                exchange.getIn().setBody(null);
            }

            exchange.getIn().setHeader("TOTAL_ROWS", response.getTotalRows());
            exchange.getIn().setHeader("PAGE", response.getPage());
            exchange.getIn().setHeader("PAGE_SIZE", response.getPageSize());
            exchange.getIn().setHeader("ORDER_BY_COLUMN", response.getOrderByColumn());
            exchange.getIn().setHeader("ORDER_BY_DIRECTION", response.getOrderByDirection());

            logger.logger().info(String.format("Retrieved [DOC_XWALK_COUNT=%d] Be Document IDs: [STAFF_ID=%s]",
                    bsnEntDocXwalkTypList.size(), bsnEntId));
        } catch (Exception e) {
            e.printStackTrace();
            throw new SandataRuntimeException(String.format("getPatientDocuments: %s: %s: [ERROR_MSG=%s]",
                    getClass().getName(), e.getClass().getName(), e.getMessage()), e);
        } finally {
            logger.stop();
        }
    }

    public void updateDocument(Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = DocumentDataLogger.CreateLogger(exchange);

        try {
            logger.start();

            Document document = exchange.getIn().getBody(Document.class);


            int result = crosswalkService.deleteDocumentXwalk(document.getDocumentID());

            if (result > 0) {
                insertDocument(exchange);
                logger.logger().info(String.format("Deleted Document: [DOC_ID=%s]", document.getDocumentID()));
            }

/*
            int result = crosswalkService.deleteDocumentXwalk(documentID);

            logger.logger().info(String.format("Document deleted: [DOC_ID=%s]", documentID));*/
        } catch (Exception e) {

            e.printStackTrace();
            String errMsg = String.format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(errMsg, e);
        } finally {

            logger.stop();
        }
    }

    public void deleteDocument(Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = DocumentDataLogger.CreateLogger(exchange);

        try {
            logger.start();

            String documentID = exchange.getIn().getBody(String.class);

            logger.logger().info(String.format("Deleting Document: [DOC_ID=%s]", documentID));

            int result = crosswalkService.deleteDocumentXwalk(documentID);

            logger.logger().info(String.format("Document deleted: [DOC_ID=%s]", documentID));

            exchange.getIn().setBody(result);
        } catch (Exception e) {

            e.printStackTrace();
            String errMsg = String.format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(errMsg, e);
        } finally {

            logger.stop();
        }
    }

    public void getDocumentUploadStatus(Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = DocumentDataLogger.CreateLogger(exchange);

        logger.start();

        String statusId = (String) exchange.getIn().getHeader("status_id");
        if (statusId == null) {
            throw new SandataRuntimeException("getDocumentUploadStatus: StatusID (status_id) must be provided!");
        }

        try {
            DocumentSaveStatus documentSaveStatus = documentSaveStatusMap.get(statusId);
            if (documentSaveStatus != null) {
                exchange.getIn().setBody(documentSaveStatus);
                return;
            }

            String documentStatusPath = DocumentServiceSettings.getInstance().getDocumentStatusPath();
            File jsonStatusFile = new File(String.format("%s/%s.json", documentStatusPath, statusId));
            if (jsonStatusFile.exists()) {

                byte[] jsonBytes = Files.readAllBytes(Paths.get(jsonStatusFile.getPath()));
                String jsonString = new String(jsonBytes, StandardCharsets.UTF_8);
                DocumentSaveStatus dss = (DocumentSaveStatus) GSONProvider.FromJson(jsonString, DocumentSaveStatus.class);
                exchange.getIn().setBody(dss);
            } else {
                String errMsg = String.format("WARNING: [DOC_STATUS_ID=%s]: UNKNOWN!", statusId);
                exchange.getIn().setBody(errMsg);
                logger.warn("getDocumentUploadStatus: " + errMsg);
            }
        } catch (Exception e) {

            e.printStackTrace();
            String errMsg = String.format("getDocumentUploadStatus: %s: %s: [DOC_STATUS_ID=%s]: [ERROR_MSG=%s]",
                    getClass().getName(),
                    e.getClass().getName(),
                    statusId,
                    e.getMessage());

            throw new SandataRuntimeException(errMsg, e);
        } finally {
            logger.stop();
        }
    }

    private List<String> getDocIDListFromPatientXwalk(List<PtDocXwalkT> list) throws SandataRuntimeException {

        try {

            List<String> ids = new ArrayList<>();

            for (PtDocXwalkT docX : list) {
                ids.add(docX.getDocId());
            }

            return ids;

        } catch (Exception e) {
            e.printStackTrace();
            throw new SandataRuntimeException(String.format("getDocIDListFromPatientXwalk: %s: %s: [ERROR_MSG=%s]",
                    getClass().getName(), e.getClass().getName(), e.getMessage()), e);
        }
    }

    private List<String> getDocIDListFromStaffXwalk(List<StaffDocXwalkT> list) throws SandataRuntimeException {

        try {

            List<String> ids = new ArrayList<>();

            for (StaffDocXwalkT docX : list) {
                ids.add(docX.getDocId());
            }

            return ids;

        } catch (Exception e) {
            e.printStackTrace();
            throw new SandataRuntimeException(String.format("getDocIDListFromStaffXwalk: %s: %s: [ERROR_MSG=%s]",
                    getClass().getName(), e.getClass().getName(), e.getMessage()), e);
        }
    }

    private List<String> getDocIDListFromVisitXwalk(List<VisitDocXwalkT> list) throws SandataRuntimeException {

        try {

            List<String> ids = new ArrayList<>();

            for (VisitDocXwalkT docX : list) {
                ids.add(docX.getDocId());
            }

            return ids;

        } catch (Exception e) {
            e.printStackTrace();
            throw new SandataRuntimeException(String.format("getDocIDListFromVisitXwalk: %s: %s: [ERROR_MSG=%s]",
                    getClass().getName(), e.getClass().getName(), e.getMessage()), e);
        }
    }

    private List<String> getDocIDListFromBeXwalk(List<BeDocXwalkT> list) throws SandataRuntimeException {

        try {

            List<String> ids = new ArrayList<>();

            for (BeDocXwalkT docX : list) {
                ids.add(docX.getDocId());
            }

            return ids;

        } catch (Exception e) {
            e.printStackTrace();
            throw new SandataRuntimeException(String.format("getDocIDListFromStaffXwalk: %s: %s: [ERROR_MSG=%s]",
                    getClass().getName(), e.getClass().getName(), e.getMessage()), e);
        }
    }

    public void getDocumentsByParameters(Exchange exchange) throws SandataRuntimeException {
        MessageContentsList mcl = exchange.getIn().getBody(MessageContentsList.class);

        String proptyId = (String) mcl.get(0);
        String proptyValue = (String) mcl.get(1);

        int page = (int) mcl.get(2);
        int pageSize = (int) mcl.get(3);
        String orderBy = (String) mcl.get(4);

        //Default value
        String orderByColumn = "REC_CREATE_TMSTP";

        String direction = (String) mcl.get(5);


        Response response = documentOracleRepositoryService.getDocsByDocDet(proptyId, proptyValue, page, pageSize, orderByColumn, direction);

        response.setData(response.getData());

        exchange.getIn().setHeader("TOTAL_ROWS", response.getTotalRows());
        exchange.getIn().setHeader("PAGE", response.getPage());
        exchange.getIn().setHeader("PAGE_SIZE", response.getPageSize());
        exchange.getIn().setHeader("ORDER_BY_COLUMN", response.getOrderByColumn());
        exchange.getIn().setHeader("ORDER_BY_DIRECTION", response.getOrderByDirection());

        exchange.getIn().setBody(response.getData());
    }


    @Override
    public void updateStatus(long totalBytesRead, long totalBytes, String status, String statusId, SandataLogger logger) {

        DocumentSaveStatus documentSaveStatus = documentSaveStatusMap.get(statusId);

        if (documentSaveStatus == null) {
            logger.error(String.format("updateStatus: [DOC_STATUS_ID=%s]: documentSaveStatus == null", statusId));
            return;
        }

        final int percentage = (int) (totalBytesRead * 100 / totalBytes);

        documentSaveStatus.setBytesTransferred(totalBytesRead);
        documentSaveStatus.setPercentage(percentage);
        documentSaveStatus.setStatus(status);

        logger.info(String.format("updateStatus: [DOC_STATUS_ID=%s]: [Percentage: %d%%][Status: %s][Bytes=%d/%d]",
                statusId, percentage, status, totalBytesRead, totalBytes));

        writeDocumentStatus(documentSaveStatus, statusId, logger);

    }

    @Override
    public void errorMessage(String errorMessage, String status, String statusId, SandataLogger logger) {

        DocumentSaveStatus documentSaveStatus = documentSaveStatusMap.get(statusId);

        if (documentSaveStatus == null) {
            logger.error(String.format("updateStatus: [DOC_STATUS_ID=%s]: documentSaveStatus == null", statusId));
            return;
        }

        documentSaveStatus.setStatus(status);
        documentSaveStatus.setErrorMessage(errorMessage);

        writeDocumentStatus(documentSaveStatus, statusId, logger);
    }

    private void writeDocumentStatus(DocumentSaveStatus documentSaveStatus, String statusId, SandataLogger logger) {

        FileOutputStream fop = null;

        try {
            String documentStatusPath = DocumentServiceSettings.getInstance().getDocumentStatusPath();

            File file = new File(String.format("%s/%s.json", documentStatusPath, statusId));
            fop = new FileOutputStream(file);
            if (file.createNewFile()) {
                logger.info(String.format("writeDocumentStatus: %s: [DOC_ID=%s]: [DOC_STATUS_ID=%s]: File created!",
                        file.getPath(), documentSaveStatus.getDocumentId(), statusId));
            }

            String json = GSONProvider.ToJson(documentSaveStatus);
            byte[] jsonBytes = json.getBytes();
            fop.write(jsonBytes);
            fop.flush();
        } catch (Exception e) {
            e.printStackTrace();
            String errMsg = String.format("writeDocumentStatus: %s: %s: [DOC_ID=%s]: [DOC_STATUS_ID=%s]: [ERROR_MSG=%s]",
                    getClass().getName(),
                    e.getClass().getName(),
                    documentSaveStatus.getDocumentId(),
                    statusId,
                    e.getMessage());
            logger.error(errMsg);
        } finally {
            try {
                if (fop != null) {
                    fop.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
