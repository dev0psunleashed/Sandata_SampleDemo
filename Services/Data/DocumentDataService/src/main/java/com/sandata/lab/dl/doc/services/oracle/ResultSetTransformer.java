package com.sandata.lab.dl.doc.services.oracle;

import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.data.document.dl.model.Document;
import com.sandata.lab.data.document.dl.model.DocumentDetail;
import com.sandata.lab.data.model.jpub.model.BeDocXwalkT;
import com.sandata.lab.data.model.jpub.model.PtDocXwalkT;
import com.sandata.lab.data.model.jpub.model.StaffDocXwalkT;
import com.sandata.lab.data.model.jpub.model.VisitDocXwalkT;
import com.sandata.lab.dl.doc.impl.data.model.jpub.DocDetProptyLkupT;
import com.sandata.lab.dl.doc.impl.data.model.jpub.DocT;
import com.sandata.lab.dl.doc.impl.data.model.jpub.DocTypLkupT;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.sql.Blob;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ResultSetTransformer {

    public PtDocXwalkT transformResultSetToPtDocXwalkTyp(ResultSet resultSet) throws SandataRuntimeException {

        try {
            PtDocXwalkT patientDocXwalkTyp = new PtDocXwalkT();

            patientDocXwalkTyp.setPtDocXwalkSk(resultSet.getBigDecimal("PT_DOC_XWALK_SK"));
            patientDocXwalkTyp.setPtId(resultSet.getString("PT_ID"));
            patientDocXwalkTyp.setBeId(resultSet.getString("BE_ID"));
            patientDocXwalkTyp.setDocId(resultSet.getString("DOC_ID"));
            patientDocXwalkTyp.setDocClasName(resultSet.getString("DOC_CLAS_NAME"));
            patientDocXwalkTyp.setRecCreatedBy(resultSet.getString("REC_CREATED_BY"));
            patientDocXwalkTyp.setCurrRecInd(resultSet.getBigDecimal("CURR_REC_IND"));
            patientDocXwalkTyp.setRecCreateTmstp(resultSet.getTimestamp("REC_CREATE_TMSTP"));
            patientDocXwalkTyp.setRecUpdateTmstp(resultSet.getTimestamp("REC_UPDATE_TMSTP"));
            patientDocXwalkTyp.setRecEffTmstp(resultSet.getTimestamp("REC_EFF_TMSTP"));
            patientDocXwalkTyp.setRecTermTmstp(resultSet.getTimestamp("REC_TERM_TMSTP"));
            patientDocXwalkTyp.setChangeVersionId(resultSet.getBigDecimal("CHANGE_VERSION_ID"));
            patientDocXwalkTyp.setChangeReasonMemo(resultSet.getString("CHANGE_REASON_MEMO"));

            return patientDocXwalkTyp;
        } catch (Exception e) {

            e.printStackTrace();
            throw new SandataRuntimeException(String.format("transformResultSetToPtDocXwalkTyp: %s: %s: [ERROR_MSG=%s]",
                    getClass().getName(), e.getClass().getName(), e.getMessage()), e);
        }
    }

    public BeDocXwalkT transformResultSetToBeDocXwalkTyp(ResultSet resultSet) throws SandataRuntimeException {

        try {
            BeDocXwalkT bsnEntDocXwalkTyp = new BeDocXwalkT();

            bsnEntDocXwalkTyp.setBeDocXwalkSk(resultSet.getBigDecimal("BE_DOC_XWALK_SK"));
            bsnEntDocXwalkTyp.setBeId(resultSet.getString("BE_ID"));
            bsnEntDocXwalkTyp.setDocId(resultSet.getString("DOC_ID"));
            bsnEntDocXwalkTyp.setRecCreatedBy(resultSet.getString("REC_CREATED_BY"));
            bsnEntDocXwalkTyp.setCurrRecInd(resultSet.getBigDecimal("CURR_REC_IND"));
            bsnEntDocXwalkTyp.setRecCreateTmstp(resultSet.getTimestamp("REC_CREATE_TMSTP"));
            bsnEntDocXwalkTyp.setRecUpdateTmstp(resultSet.getTimestamp("REC_UPDATE_TMSTP"));
            bsnEntDocXwalkTyp.setRecEffTmstp(resultSet.getTimestamp("REC_EFF_TMSTP"));
            bsnEntDocXwalkTyp.setRecTermTmstp(resultSet.getTimestamp("REC_TERM_TMSTP"));
            bsnEntDocXwalkTyp.setChangeVersionId(resultSet.getBigDecimal("CHANGE_VERSION_ID"));
            bsnEntDocXwalkTyp.setChangeReasonMemo(resultSet.getString("CHANGE_REASON_MEMO"));

            return bsnEntDocXwalkTyp;
        } catch (Exception e) {

            e.printStackTrace();
            throw new SandataRuntimeException(String.format("transformResultSetToBeDocXwalkTyp: %s: %s: [ERROR_MSG=%s]",
                    getClass().getName(), e.getClass().getName(), e.getMessage()), e);
        }
    }

    public StaffDocXwalkT transformResultSetToStaffDocXwalkTyp(ResultSet resultSet) throws SandataRuntimeException {

        try {

            StaffDocXwalkT staffDocXwalkTyp = new StaffDocXwalkT();

            staffDocXwalkTyp.setStaffDocXwalkSk(resultSet.getBigDecimal("STAFF_DOC_XWALK_SK"));
            staffDocXwalkTyp.setStaffId(resultSet.getString("STAFF_ID"));
            staffDocXwalkTyp.setBeId(resultSet.getString("BE_ID"));
            staffDocXwalkTyp.setDocId(resultSet.getString("DOC_ID"));
            staffDocXwalkTyp.setDocClasName(resultSet.getString("DOC_CLAS_NAME"));
            staffDocXwalkTyp.setRecCreatedBy(resultSet.getString("REC_CREATED_BY"));
            staffDocXwalkTyp.setCurrRecInd(resultSet.getBigDecimal("CURR_REC_IND"));
            staffDocXwalkTyp.setRecCreateTmstp(resultSet.getTimestamp("REC_CREATE_TMSTP"));
            staffDocXwalkTyp.setRecUpdateTmstp(resultSet.getTimestamp("REC_UPDATE_TMSTP"));
            staffDocXwalkTyp.setRecEffTmstp(resultSet.getTimestamp("REC_EFF_TMSTP"));
            staffDocXwalkTyp.setRecTermTmstp(resultSet.getTimestamp("REC_TERM_TMSTP"));
            staffDocXwalkTyp.setChangeVersionId(resultSet.getBigDecimal("CHANGE_VERSION_ID"));
            staffDocXwalkTyp.setChangeReasonMemo(resultSet.getString("CHANGE_REASON_MEMO"));

            return staffDocXwalkTyp;
        } catch (Exception e) {

            e.printStackTrace();
            throw new SandataRuntimeException(String.format("transformResultSetToStaffDocXwalkTyp: %s: %s: [ERROR_MSG=%s]",
                    getClass().getName(), e.getClass().getName(), e.getMessage()), e);
        }
    }

    public VisitDocXwalkT transformResultSetToVisitDocXwalkTyp(ResultSet resultSet) throws SandataRuntimeException {

        try {

            VisitDocXwalkT visitDocXwalkTyp = new VisitDocXwalkT();

            visitDocXwalkTyp.setVisitDocXwalkSk(resultSet.getBigDecimal("VISIT_DOC_XWALK_SK"));
            visitDocXwalkTyp.setVisitSk(resultSet.getBigDecimal("VISIT_SK"));
            visitDocXwalkTyp.setBeId(resultSet.getString("BE_ID"));
            visitDocXwalkTyp.setDocId(resultSet.getString("DOC_ID"));
            visitDocXwalkTyp.setDocClasName(resultSet.getString("DOC_CLAS_NAME"));
            visitDocXwalkTyp.setRecCreatedBy(resultSet.getString("REC_CREATED_BY"));
            visitDocXwalkTyp.setCurrRecInd(resultSet.getBigDecimal("CURR_REC_IND"));
            visitDocXwalkTyp.setRecCreateTmstp(resultSet.getTimestamp("REC_CREATE_TMSTP"));
            visitDocXwalkTyp.setRecUpdateTmstp(resultSet.getTimestamp("REC_UPDATE_TMSTP"));
            visitDocXwalkTyp.setRecEffTmstp(resultSet.getTimestamp("REC_EFF_TMSTP"));
            visitDocXwalkTyp.setRecTermTmstp(resultSet.getTimestamp("REC_TERM_TMSTP"));
            visitDocXwalkTyp.setChangeVersionId(resultSet.getBigDecimal("CHANGE_VERSION_ID"));
            visitDocXwalkTyp.setChangeReasonMemo(resultSet.getString("CHANGE_REASON_MEMO"));

            return visitDocXwalkTyp;
        } catch (Exception e) {

            e.printStackTrace();
            throw new SandataRuntimeException(String.format("transformResultSetToVisitDocXwalkTyp: %s: %s: [ERROR_MSG=%s]",
                    getClass().getName(), e.getClass().getName(), e.getMessage()), e);
        }
    }

    public DocT transformResultSetToDocTyp(ResultSet resultSet) throws SandataRuntimeException {

        try {

            DocT docTyp = new DocT();

            docTyp.setDocSk(resultSet.getBigDecimal("DOC_SK"));
            docTyp.setDocId(resultSet.getString("DOC_ID"));
            docTyp.setDocTypName(resultSet.getString("DOC_TYP_NAME"));
            docTyp.setRecCreateTmstp(resultSet.getTimestamp("REC_CREATE_TMSTP"));
            docTyp.setRecUpdateTmstp(resultSet.getTimestamp("REC_UPDATE_TMSTP"));

            return docTyp;

        } catch (Exception e) {

            e.printStackTrace();
            throw new SandataRuntimeException(String.format("transformResultSetToDocument: %s: %s: [ERROR_MSG=%s]",
                    getClass().getName(), e.getClass().getName(), e.getMessage()), e);
        }
    }

    public void transformResultSetToDocument(ResultSet resultSet, Map<BigInteger, Document> documentMap) throws SandataRuntimeException {

        try {

            Document document = null;

            if (documentMap != null) {
                BigInteger docSK = BigInteger.valueOf(resultSet.getBigDecimal("DOC_SK").longValue());
                document = documentMap.get(docSK);
            }

            if (document == null) {

                document = new Document();

                document.setDocumentSK(BigInteger.valueOf(resultSet.getBigDecimal("DOC_SK").longValue()));
                document.setDocumentID(resultSet.getString("DOC_ID"));
                document.setDocumentTypeName(resultSet.getString("DOC_TYP_NAME"));

                document.setRecordCreateTimestamp(resultSet.getTimestamp("REC_CREATE_TMSTP"));
                document.setRecordUpdateTimestamp(resultSet.getTimestamp("REC_UPDATE_TMSTP"));

                // Data is being returned in GETFILE REST ENDPOINT
                //     Blob blob = resultSet.getBlob("DOC_OTHER");
                //     document.setDocOther(getByteArrayFromBlob(blob));
            }

            addDocumentDetail(document, resultSet);

            if (documentMap != null) {
                documentMap.put(document.getDocumentSK(), document);
            }

        } catch (Exception e) {

            e.printStackTrace();
            throw new SandataRuntimeException(String.format("transformResultSetToDocument: %s: %s: [ERROR_MSG=%s]",
                    getClass().getName(), e.getClass().getName(), e.getMessage()), e);
        }
    }

    private byte[] getByteArrayFromBlob(Blob blob) {

        ByteArrayOutputStream byteArrayOutputStream = null;
        InputStream inputStream = null;

        try {

            byteArrayOutputStream = new ByteArrayOutputStream();
            inputStream = blob.getBinaryStream();

            int readBytes = 0;
            byte[] bytes = new byte[1024];
            while ((readBytes = inputStream.read(bytes)) != -1) {
                byteArrayOutputStream.write(readBytes);
            }

            blob.free();

            return byteArrayOutputStream.toByteArray();

        } catch (Exception e) {
            e.printStackTrace();
            throw new SandataRuntimeException(String.format("createEmptyBlob: %s: %s: [ERROR_MSG=%s]",
                    getClass().getName(), e.getClass().getName(), e.getMessage()), e);
        } finally {


            if (byteArrayOutputStream != null) {
                try {
                    byteArrayOutputStream.close();
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }

            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
        }
    }

    private void addDocumentDetail(Document document, ResultSet resultSet) throws SandataRuntimeException {

        try {
            if (resultSet.getBigDecimal("DOC_DET_SK") == null) {
                return;
            }

            List<DocumentDetail> documentDetailList = document.getDocumentDetails();

            if (documentDetailList == null) {
                documentDetailList = new ArrayList<>();
            }

            DocumentDetail documentDetail = new DocumentDetail();

            documentDetail.setDocumentSK(BigInteger.valueOf(resultSet.getBigDecimal("DOC_SK").longValue()));
            documentDetail.setDocumentDetailPropertyName(resultSet.getString("DOC_DET_PROPTY_KEY_NAME"));
            documentDetail.setDocumentDetailPropertyVal(resultSet.getString("DOC_DET_PROPTY_KEY_VAL"));
            documentDetail.setDocumentDetailSK(BigInteger.valueOf(resultSet.getBigDecimal("DOC_DET_SK").longValue()));
            documentDetail.setRecordCreateTimestamp(resultSet.getTimestamp("REC_CREATE_TMSTP"));
            documentDetail.setRecordUpdateTimestamp(resultSet.getTimestamp("REC_UPDATE_TMSTP"));

            documentDetailList.add(documentDetail);
            document.setDocumentDetails(documentDetailList);
        } catch (Exception e) {

            e.printStackTrace();
            throw new SandataRuntimeException(String.format("addDocumentDetail: %s: %s: [ERROR_MSG=%s]",
                    getClass().getName(), e.getClass().getName(), e.getMessage()), e);
        }
    }

    public DocTypLkupT transformResultSetToDocTypLkupTyp(ResultSet resultSet) throws SandataRuntimeException {

        try {

            DocTypLkupT docTypLkupTyp = new DocTypLkupT();

            docTypLkupTyp.setDocTypLkupSk(resultSet.getBigDecimal("DOC_TYP_LKUP_SK"));
            docTypLkupTyp.setDocTypDesc(resultSet.getString("DOC_TYP_DESC"));
            docTypLkupTyp.setDocTypName(resultSet.getString("DOC_TYP_NAME"));

            docTypLkupTyp.setRecCreateTmstp(resultSet.getTimestamp("REC_CREATE_TMSTP"));
            docTypLkupTyp.setRecUpdateTmstp(resultSet.getTimestamp("REC_UPDATE_TMSTP"));

            return docTypLkupTyp;
        } catch (Exception e) {

            e.printStackTrace();
            throw new SandataRuntimeException(String.format("transformResultSetToDocTypLkupTyp: %s: %s: [ERROR_MSG=%s]",
                    getClass().getName(), e.getClass().getName(), e.getMessage()), e);
        }
    }

    public DocDetProptyLkupT transformResultSetToDocDetProptyLkupT(ResultSet resultSet) throws SandataRuntimeException {

        try {
            DocDetProptyLkupT docDetProptyLkup = new DocDetProptyLkupT();

            docDetProptyLkup.setDocDetProptyLkupSk(resultSet.getBigDecimal("DOC_DET_PROPTY_LKUP_SK"));
            docDetProptyLkup.setDocDetProptyKeyDesc(resultSet.getString("DOC_DET_PROPTY_KEY_DESC"));
            docDetProptyLkup.setDocDetProptyKeyName(resultSet.getString("DOC_DET_PROPTY_KEY_NAME"));

            docDetProptyLkup.setRecCreateTmstp(resultSet.getTimestamp("REC_CREATE_TMSTP"));
            docDetProptyLkup.setRecUpdateTmstp(resultSet.getTimestamp("REC_UPDATE_TMSTP"));

            return docDetProptyLkup;
        } catch (Exception e) {

            e.printStackTrace();
            throw new SandataRuntimeException(String.format("transformResultSetToDocDetProptyLkupT: %s: %s: [ERROR_MSG=%s]",
                    getClass().getName(), e.getClass().getName(), e.getMessage()), e);
        }
    }
}
