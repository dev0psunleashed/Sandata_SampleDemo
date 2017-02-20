package com.sandata.one.aggregator.documents.impl;

import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.common.utils.log.SandataLogger;
import com.sandata.lab.common.utils.string.StringUtil;
import com.sandata.lab.data.document.dl.model.Document;
import com.sandata.lab.data.model.jpub.model.*;
import com.sandata.lab.data.model.response.Response;
import com.sandata.one.aggregator.documents.impl.data.DocumentDataTransformer;
import com.sandata.one.aggregator.documents.impl.data.requests.OracleRequest;
import com.sandata.one.aggregator.documents.services.oracle.MainOracleRepositoryService;
import com.sandata.one.aggregator.documents.utils.log.DocumentDataLogger;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CrosswalkService {
    private final String PACKAGE_NAME = "PKG_DOCXWALK";
    private final String PATIENTX_TABLE_NAME = "PT_DOC_XWALK";
    private final String STAFF_TABLE_NAME = "STAFF_DOC_XWALK";
    private final String BSNENTX_TABLE_NAME = "BE_DOC_XWALK";
    private final String VISIT_TABLE_NAME = "VISIT_DOC_XWALK";

    private DocumentDataTransformer documentDataTransformer;

    public void setDocumentDataTransformer(DocumentDataTransformer documentDataTransformer) {
        this.documentDataTransformer = documentDataTransformer;
    }

    private MainOracleRepositoryService mainOracleRepositoryService;

    public void setMainOracleRepositoryService(MainOracleRepositoryService mainOracleRepositoryService) {
        this.mainOracleRepositoryService = mainOracleRepositoryService;
    }


    public void insertDocumentXwalk(Document document) throws SandataRuntimeException {

        SandataLogger logger = DocumentDataLogger.CreateLogger();

        try {
            PtDocXwalkT patientDocXwalkTyp = documentDataTransformer.transformDocToPtDocXwalkTyp(document);

            List<OracleRequest> oracleRequestList = new ArrayList<>();


            if (!StringUtil.IsNullOrEmpty(document.getPatientID())) {
                OracleRequest docPatientOracleRequest = new OracleRequest();
                docPatientOracleRequest.setData(patientDocXwalkTyp);
                docPatientOracleRequest.setMethodName("insertPtDocXwalk");
                docPatientOracleRequest.setPackageName(PACKAGE_NAME);
                oracleRequestList.add(docPatientOracleRequest);

                logger.logger().info(String.format("Inserting into patient doc xwalk.."));
            } else if (!StringUtil.IsNullOrEmpty(document.getStaffID())) {

                StaffDocXwalkT staffDocXwalkTyp = documentDataTransformer.transformDocToStaffDocXwalkTyp(document);

                OracleRequest docStaffOracleRequest = new OracleRequest();
                docStaffOracleRequest.setData(staffDocXwalkTyp);
                docStaffOracleRequest.setMethodName("insertStaffDocXwalk");
                docStaffOracleRequest.setPackageName(PACKAGE_NAME);
                oracleRequestList.add(docStaffOracleRequest);

                logger.logger().info(String.format("Inserting into staff doc xwalk.."));
            } else if (document.getVisitSK() != null && !document.getVisitSK().equals(BigInteger.ZERO)) {

                VisitDocXwalkT visitDocXwalkTyp = documentDataTransformer.transformDocToVisitDocXwalkTyp(document);

                OracleRequest docVisitOracleRequest = new OracleRequest();
                docVisitOracleRequest.setData(visitDocXwalkTyp);
                docVisitOracleRequest.setMethodName("insertVisitDocXwalk");
                docVisitOracleRequest.setPackageName(PACKAGE_NAME);
                oracleRequestList.add(docVisitOracleRequest);

                logger.logger().info(String.format("Inserting into visit doc xwalk.."));
            } else if (!StringUtil.IsNullOrEmpty(document.getBusinessEntityID())) {

                BeDocXwalkT bsnEntDocXwalkTyp = documentDataTransformer.transformDocToBeDocXwalkTyp(document);

                OracleRequest docPatientOracleRequest = new OracleRequest();
                docPatientOracleRequest.setData(bsnEntDocXwalkTyp);
                docPatientOracleRequest.setMethodName("insertBeDocXwalk");
                docPatientOracleRequest.setPackageName(PACKAGE_NAME);
                oracleRequestList.add(docPatientOracleRequest);

                logger.logger().info(String.format("Inserting into business entity doc xwalk.."));
            }

            if (oracleRequestList.size() > 0) {
                mainOracleRepositoryService.executeRequests(oracleRequestList);
            }

            logger.stop();
        } catch (Exception e) {

            e.printStackTrace();
            String errMsg = String.format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(errMsg, e);
        }
    }

    public int deleteDocumentXwalk(String documentID) throws SandataRuntimeException {

        SandataLogger logger = DocumentDataLogger.CreateLogger();

        try {
            List<OracleRequest> oracleRequestList = new ArrayList<>();

            OracleRequest docStaffOracleRequest = new OracleRequest();
            docStaffOracleRequest.setData(documentID);
            docStaffOracleRequest.setTableName("STAFF_DOC_XWALK");
            docStaffOracleRequest.setColumnName("DOC_ID");
            oracleRequestList.add(docStaffOracleRequest);

            OracleRequest docPatientOracleRequest = new OracleRequest();
            docPatientOracleRequest.setData(documentID);
            docPatientOracleRequest.setTableName("PT_DOC_XWALK");
            docPatientOracleRequest.setColumnName("DOC_ID");
            oracleRequestList.add(docPatientOracleRequest);

            OracleRequest docBsnOracleRequest = new OracleRequest();
            docBsnOracleRequest.setData(documentID);
            docBsnOracleRequest.setTableName("BE_DOC_XWALK");
            docBsnOracleRequest.setColumnName("DOC_ID");
            oracleRequestList.add(docPatientOracleRequest);

            OracleRequest docVisitOracleRequest = new OracleRequest();
            docVisitOracleRequest.setData(documentID);
            docVisitOracleRequest.setTableName("VISIT_DOC_XWALK");
            docVisitOracleRequest.setColumnName("DOC_ID");
            oracleRequestList.add(docPatientOracleRequest);

            logger.stop();

            return mainOracleRepositoryService.executeDeletes(oracleRequestList);
        } catch (Exception e) {

            e.printStackTrace();
            String errMsg = String.format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(errMsg, e);
        }
    }

    public Response getPatientXwalkDocs(String bsnEntID, String patientID, List<Object> properties, String className) {
        int page = (int) properties.get(0);
        int pageSize = (int) properties.get(1);
        String direction = (String) properties.get(3);
        String orderBy = (String) properties.get(2);
        String orderByColumn = "REC_UPDATE_TMSTP"; // Default

        if (orderBy.equalsIgnoreCase("crtmstp")) {
            orderByColumn = "REC_CREATE_TMSTP";
        }

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("PT_ID", patientID);
        parameters.put("BE_ID", bsnEntID);

        if (!StringUtil.IsNullOrEmpty(className)) {

            parameters.put("DOC_CLAS_NAME", className);
        }

        return mainOracleRepositoryService.getDocXwalkByKey(PATIENTX_TABLE_NAME, parameters, page, pageSize, orderByColumn, direction);
    }

    public Response getStaffXwalkDocs(String bsnEntID, String staffID, List<Object> properties, String className) {
        int page = (int) properties.get(0);
        int pageSize = (int) properties.get(1);
        String direction = (String) properties.get(3);
        String orderBy = (String) properties.get(2);
        String orderByColumn = "REC_UPDATE_TMSTP"; // Default

        if (orderBy.equalsIgnoreCase("crtmstp")) {
            orderByColumn = "REC_CREATE_TMSTP";
        }

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("STAFF_ID", staffID);
        parameters.put("BE_ID", bsnEntID);

        if (!StringUtil.IsNullOrEmpty(className)) {

            parameters.put("DOC_CLAS_NAME", className);
        }

        return mainOracleRepositoryService.getDocXwalkByKey(STAFF_TABLE_NAME, parameters, page, pageSize, orderByColumn, direction);
    }

    public Response getVisitXwalkDocs(String bsnEntID, String visitSK, List<Object> properties, String className) {
        int page = (int) properties.get(0);
        int pageSize = (int) properties.get(1);
        String direction = (String) properties.get(3);
        String orderBy = (String) properties.get(2);
        String orderByColumn = "REC_UPDATE_TMSTP"; // Default

        if (orderBy.equalsIgnoreCase("crtmstp")) {
            orderByColumn = "REC_CREATE_TMSTP";
        }

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("VISIT_SK", visitSK);
        parameters.put("BE_ID", bsnEntID);

        if (!StringUtil.IsNullOrEmpty(className)) {

            parameters.put("DOC_CLAS_NAME", className);
        }

        return mainOracleRepositoryService.getDocXwalkByKey(VISIT_TABLE_NAME, parameters, page, pageSize, orderByColumn, direction);
    }

    public Response getBeXwalkDocs(String bsnEntID, List<Object> properties, String className) {
        int page = (int) properties.get(0);
        int pageSize = (int) properties.get(1);
        String direction = (String) properties.get(3);
        String orderBy = (String) properties.get(2);
        String orderByColumn = "REC_UPDATE_TMSTP"; // Default

        if (orderBy.equalsIgnoreCase("crtmstp")) {
            orderByColumn = "REC_CREATE_TMSTP";
        }

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("BE_ID", bsnEntID);

        if (!StringUtil.IsNullOrEmpty(className)) {

            parameters.put("DOC_CLAS_NAME", className);
        }

        return mainOracleRepositoryService.getDocXwalkByKey(BSNENTX_TABLE_NAME, parameters, page, pageSize, orderByColumn, direction);
    }

    public BeDocXwalkT getBeXwalkDoc(String docID) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("DOC_ID", docID);

        return (BeDocXwalkT) mainOracleRepositoryService.getDocXwalk("BE_DOC_XWALK", parameters);
    }

    public PtDocXwalkT getPatientXwalkDoc(String docID) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("DOC_ID", docID);

        return (PtDocXwalkT) mainOracleRepositoryService.getDocXwalk("PT_DOC_XWALK", parameters);
    }

    public StaffDocXwalkT getStaffXwalkDoc(String docID) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("DOC_ID", docID);

        return (StaffDocXwalkT) mainOracleRepositoryService.getDocXwalk("STAFF_DOC_XWALK", parameters);
    }

    public VisitDocXwalkT getVisitXwalkDoc(String docID) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("DOC_ID", docID);

        return (VisitDocXwalkT) mainOracleRepositoryService.getDocXwalk("VISIT_DOC_XWALK", parameters);
    }
}
