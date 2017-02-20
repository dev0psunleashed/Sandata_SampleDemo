package com.sandata.one.aggregator.documents.impl.data;

import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.data.document.dl.model.Document;
import com.sandata.lab.data.document.dl.model.DocumentDetail;
import com.sandata.lab.data.document.dl.model.DocumentDetailPropertyLookUp;
import com.sandata.lab.data.document.dl.model.DocumentTypeLookUp;
import com.sandata.lab.data.model.jpub.model.*;
import com.sandata.one.aggregator.documents.impl.data.model.jpub.DocDetProptyLkupT;
import com.sandata.one.aggregator.documents.impl.data.model.jpub.DocDetT;
import com.sandata.one.aggregator.documents.impl.data.model.jpub.DocT;
import com.sandata.one.aggregator.documents.impl.data.model.jpub.DocTypLkupT;
import oracle.sql.BLOB;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Connection;
import java.util.Calendar;
import java.util.List;

public class DocumentDataTransformer {

    public DocT transformDocumentToDocTyp(Document document) throws SandataRuntimeException {

        try {
            DocT docTyp = new DocT();

            docTyp.setDocId(document.getDocumentID());

            BigInteger docSK = document.getDocumentSK();

            if (docSK != null) {
                docTyp.setDocSk(BigDecimal.valueOf(document.getDocumentSK().longValue()));
            }

            docTyp.setDoc(createBlob(null, null));

            java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());

            docTyp.setRecCreateTmstp(currentTimestamp);
            docTyp.setRecUpdateTmstp(currentTimestamp);


            docTyp.setDocTypName(document.getDocumentTypeName());


            docTyp.setDocOther(createBlob(null, null));

            return docTyp;
        } catch (Exception e) {
            e.printStackTrace();
            throw new SandataRuntimeException(String.format("transformDocumentToDocTyp: %s: %s: [ERROR_MSG=%s]",
                    getClass().getName(), e.getClass().getName(), e.getMessage()), e);
        } finally {
            //The caller will handle the connection closure IF ANY
        }
    }

    public Document transformDocTypToDocument(DocT docTyp) throws SandataRuntimeException {

        try {
            Document document = new Document();

            document.setDocumentID(docTyp.getDocId());
            document.setDocumentSK(BigInteger.valueOf(docTyp.getDocSk().longValue()));

            document.setRecordCreateTimestamp(docTyp.getRecCreateTmstp());
            document.setRecordUpdateTimestamp(docTyp.getRecUpdateTmstp());

            document.setDocumentTypeName(docTyp.getDocTypName());

            return document;
        } catch (Exception e) {
            e.printStackTrace();
            throw new SandataRuntimeException(String.format("transformDocTypToDocument: %s: %s: [ERROR_MSG=%s]",
                    getClass().getName(), e.getClass().getName(), e.getMessage()), e);
        }
    }


    private BLOB createBlob(final Connection connection, byte[] bytes) throws SandataRuntimeException {

        OutputStream outputStream = null;
        InputStream inputStream = null;

        try {

            if (bytes != null && bytes.length > 0 && connection != null) {
                BLOB blob = BLOB.createTemporary(connection, false, BLOB.DURATION_SESSION);

                outputStream = blob.setBinaryStream(0L);

                inputStream = new ByteArrayInputStream(bytes);

                byte[] buffer = new byte[blob.getBufferSize()];
                int byteread = 0;
                while ((byteread = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, byteread);
                }

                return blob;
            } else {
                return BLOB.getEmptyBLOB();
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new SandataRuntimeException(String.format("createEmptyBlob: %s: %s: [ERROR_MSG=%s]",
                    getClass().getName(), e.getClass().getName(), e.getMessage()), e);
        } finally {


            if (outputStream != null) {
                try {
                    outputStream.close();
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

    public PtDocXwalkT transformDocToPtDocXwalkTyp(Document document) throws SandataRuntimeException {

        try {
            PtDocXwalkT patientDocXwalkTyp = new PtDocXwalkT();

            patientDocXwalkTyp.setBeId(document.getBusinessEntityID());
            patientDocXwalkTyp.setPtId(document.getPatientID());
            patientDocXwalkTyp.setDocId(document.getDocumentID());

            java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());

            patientDocXwalkTyp.setRecEffTmstp(currentTimestamp);
            patientDocXwalkTyp.setRecUpdateTmstp(currentTimestamp);
            patientDocXwalkTyp.setRecCreateTmstp(currentTimestamp);
            patientDocXwalkTyp.setRecCreatedBy(document.getRecordCreatedBy());
            patientDocXwalkTyp.setChangeReasonMemo(document.getChangeReasonMemo());
            patientDocXwalkTyp.setDocClasName(document.getDocClassName());

            patientDocXwalkTyp.setChangeVersionId(BigDecimal.valueOf(0));

            return patientDocXwalkTyp;
        } catch (Exception e) {
            e.printStackTrace();
            throw new SandataRuntimeException(String.format("transformDocToPtDocXwalkTyp: %s: %s: [ERROR_MSG=%s]",
                    getClass().getName(), e.getClass().getName(), e.getMessage()), e);
        }
    }

    public StaffDocXwalkT transformDocToStaffDocXwalkTyp(Document document) throws SandataRuntimeException {

        try {
            StaffDocXwalkT staffDocXwalkTyp = new StaffDocXwalkT();

            staffDocXwalkTyp.setBeId(document.getBusinessEntityID());
            staffDocXwalkTyp.setStaffId(document.getStaffID());
            staffDocXwalkTyp.setDocId(document.getDocumentID());

            java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());

            staffDocXwalkTyp.setRecEffTmstp(currentTimestamp);
            staffDocXwalkTyp.setRecUpdateTmstp(currentTimestamp);
            staffDocXwalkTyp.setRecCreateTmstp(currentTimestamp);
            staffDocXwalkTyp.setRecCreatedBy(document.getRecordCreatedBy());
            staffDocXwalkTyp.setChangeReasonMemo(document.getChangeReasonMemo());
            staffDocXwalkTyp.setDocClasName(document.getDocClassName());

            staffDocXwalkTyp.setChangeVersionId(BigDecimal.valueOf(0));

            return staffDocXwalkTyp;
        } catch (Exception e) {
            e.printStackTrace();
            throw new SandataRuntimeException(String.format("transformDocToStaffDocXwalkTyp: %s: %s: [ERROR_MSG=%s]",
                    getClass().getName(), e.getClass().getName(), e.getMessage()), e);
        }
    }

    public VisitDocXwalkT transformDocToVisitDocXwalkTyp(Document document) throws SandataRuntimeException {

        try {
            VisitDocXwalkT visitDocXwalkTyp = new VisitDocXwalkT();

            visitDocXwalkTyp.setBeId(document.getBusinessEntityID());
            visitDocXwalkTyp.setVisitSk(BigDecimal.valueOf(document.getVisitSK().longValue()));
            visitDocXwalkTyp.setDocId(document.getDocumentID());

            java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());

            visitDocXwalkTyp.setRecEffTmstp(currentTimestamp);
            visitDocXwalkTyp.setRecUpdateTmstp(currentTimestamp);
            visitDocXwalkTyp.setRecCreateTmstp(currentTimestamp);
            visitDocXwalkTyp.setRecCreatedBy(document.getRecordCreatedBy());
            visitDocXwalkTyp.setChangeReasonMemo(document.getChangeReasonMemo());
            visitDocXwalkTyp.setDocClasName(document.getDocClassName());

            visitDocXwalkTyp.setChangeVersionId(BigDecimal.valueOf(0));

            return visitDocXwalkTyp;
        } catch (Exception e) {
            e.printStackTrace();
            throw new SandataRuntimeException(String.format("transformDocToVisitDocXwalkTyp: %s: %s: [ERROR_MSG=%s]",
                    getClass().getName(), e.getClass().getName(), e.getMessage()), e);
        }
    }

    public BeDocXwalkT transformDocToBeDocXwalkTyp(Document document) throws SandataRuntimeException {

        try {
            BeDocXwalkT bsnEntDocXwalkTyp = new BeDocXwalkT();

            bsnEntDocXwalkTyp.setBeId(document.getBusinessEntityID());
            bsnEntDocXwalkTyp.setDocId(document.getDocumentID());

            java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());

            bsnEntDocXwalkTyp.setRecEffTmstp(currentTimestamp);
            bsnEntDocXwalkTyp.setRecUpdateTmstp(currentTimestamp);
            bsnEntDocXwalkTyp.setRecCreateTmstp(currentTimestamp);
            bsnEntDocXwalkTyp.setRecCreatedBy(document.getRecordCreatedBy());
            bsnEntDocXwalkTyp.setChangeReasonMemo(document.getChangeReasonMemo());
            bsnEntDocXwalkTyp.setDocClasName(document.getDocClassName());

            bsnEntDocXwalkTyp.setChangeVersionId(BigDecimal.valueOf(0));

            return bsnEntDocXwalkTyp;
        } catch (Exception e) {
            e.printStackTrace();
            throw new SandataRuntimeException(String.format("transformDocToBeDocXwalkTyp: %s: %s: [ERROR_MSG=%s]",
                    getClass().getName(), e.getClass().getName(), e.getMessage()), e);
        }
    }

    public DocTypLkupT transformDocumentTypLkupToDocTypLkupTyp(DocumentTypeLookUp documentTypeLookUp) throws SandataRuntimeException {

        try {
            DocTypLkupT docTypLkupTyp = new DocTypLkupT();

            BigInteger docTypLkupSk = documentTypeLookUp.getDocumentTypeLookupSK();

            if (docTypLkupSk != null) {
                docTypLkupTyp.setDocTypLkupSk(BigDecimal.valueOf(documentTypeLookUp.getDocumentTypeLookupSK().longValue()));
            }

            java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());

            docTypLkupTyp.setRecCreateTmstp(currentTimestamp);
            docTypLkupTyp.setRecUpdateTmstp(currentTimestamp);

            docTypLkupTyp.setDocTypDesc(documentTypeLookUp.getDocumentTypeDesc());
            docTypLkupTyp.setDocTypName(documentTypeLookUp.getDocumentTypeName());

            return docTypLkupTyp;
        } catch (Exception e) {
            e.printStackTrace();
            throw new SandataRuntimeException(String.format("transformDocumentTypLkupToDocTypLkupTyp: %s: %s: [ERROR_MSG=%s]",
                    getClass().getName(), e.getClass().getName(), e.getMessage()), e);
        }
    }

    public DocumentTypeLookUp transformDocTypLkupToDocumentTypeLookup(DocTypLkupT docTypLkupTyp) throws SandataRuntimeException {

        try {
            DocumentTypeLookUp documentTypeLookUp = new DocumentTypeLookUp();

            documentTypeLookUp.setDocumentTypeName(docTypLkupTyp.getDocTypName());

            documentTypeLookUp.setDocumentTypeDesc(docTypLkupTyp.getDocTypDesc());

            documentTypeLookUp.setDocumentTypeLookupSK(BigInteger.valueOf(docTypLkupTyp.getDocTypLkupSk().longValue()));

            documentTypeLookUp.setRecordCreateTimestamp(docTypLkupTyp.getRecCreateTmstp());

            documentTypeLookUp.setRecordUpdateTimestamp(docTypLkupTyp.getRecUpdateTmstp());

            return documentTypeLookUp;
        } catch (Exception e) {
            e.printStackTrace();
            throw new SandataRuntimeException(String.format("transformDocTypLkupToDocumentTypeLookup: %s: %s: [ERROR_MSG=%s]",
                    getClass().getName(), e.getClass().getName(), e.getMessage()), e);
        }
    }

    public DocumentDetailPropertyLookUp transformDocDetLkupProptyLkupTypToDocumentDetailLookup(
            DocDetProptyLkupT docDetProptyLkupTyp) throws SandataRuntimeException {

        try {
            DocumentDetailPropertyLookUp documentDetailPropertyLookUp = new DocumentDetailPropertyLookUp();

            documentDetailPropertyLookUp.setDocumentDetailPropertyName(docDetProptyLkupTyp.getDocDetProptyKeyName());

            documentDetailPropertyLookUp.setDocumentDetailPropertyDesc(docDetProptyLkupTyp.getDocDetProptyKeyDesc());

            documentDetailPropertyLookUp.setDocumentDetailPropertyLookupSK(BigInteger.valueOf(docDetProptyLkupTyp.getDocDetProptyLkupSk().longValue()));

            documentDetailPropertyLookUp.setRecordCreateTimestamp(docDetProptyLkupTyp.getRecCreateTmstp());

            documentDetailPropertyLookUp.setRecordUpdateTimestamp(docDetProptyLkupTyp.getRecUpdateTmstp());

            return documentDetailPropertyLookUp;
        } catch (Exception e) {
            e.printStackTrace();
            throw new SandataRuntimeException(String.format("transformDocDetLkupProptyLkupTypToDocumentDetailLookup: %s: %s: [ERROR_MSG=%s]",
                    getClass().getName(), e.getClass().getName(), e.getMessage()), e);
        }
    }

    public DocDetProptyLkupT tranformDocumentDetailProptyLkupTypToDocDetProptyLkupT(
            DocumentDetailPropertyLookUp documentDetailPropertyLookUp) throws SandataRuntimeException {

        try {
            DocDetProptyLkupT docDetProptyLkupTyp = new DocDetProptyLkupT();

            docDetProptyLkupTyp.setDocDetProptyKeyDesc(documentDetailPropertyLookUp.getDocumentDetailPropertyDesc());
            docDetProptyLkupTyp.setDocDetProptyKeyName(documentDetailPropertyLookUp.getDocumentDetailPropertyName());

            BigInteger docDetProptyLkupSk = documentDetailPropertyLookUp.getDocumentDetailPropertyLookupSK();

            if (docDetProptyLkupSk != null) {
                docDetProptyLkupTyp.setDocDetProptyLkupSk(BigDecimal.valueOf(docDetProptyLkupSk.longValue()));
            }

            java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());

            docDetProptyLkupTyp.setRecCreateTmstp(currentTimestamp);
            docDetProptyLkupTyp.setRecUpdateTmstp(currentTimestamp);

            return docDetProptyLkupTyp;
        } catch (Exception e) {
            e.printStackTrace();
            throw new SandataRuntimeException(String.format("tranformDocumentDetailProptyLkupTypToDocDetProptyLkupT: %s: %s: [ERROR_MSG=%s]",
                    getClass().getName(), e.getClass().getName(), e.getMessage()), e);
        }
    }

    public DocDetT transformDocumentDetailToDocDetT(DocumentDetail documentDetail) throws SandataRuntimeException {

        try {
            DocDetT docDetTyp = new DocDetT();

            docDetTyp.setDocDetProptyKeyName(documentDetail.getDocumentDetailPropertyName());

            //SK value will be  ignored on object Insert in stored procedure

            if (documentDetail.getDocumentDetailSK() != null) {
                docDetTyp.setDocDetSk(BigDecimal.valueOf(documentDetail.getDocumentDetailSK().longValue()));
            }

            docDetTyp.setDocSk(BigDecimal.valueOf(documentDetail.getDocumentSK().longValue()));

            docDetTyp.setDocDetProptyKeyVal(documentDetail.getDocumentDetailPropertyVal());

            java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());

            docDetTyp.setRecCreateTmstp(currentTimestamp);
            docDetTyp.setRecUpdateTmstp(currentTimestamp);

            return docDetTyp;
        } catch (Exception e) {
            e.printStackTrace();
            throw new SandataRuntimeException(String.format("transformDocumentDetailToDocDetT: %s: %s: [ERROR_MSG=%s]",
                    getClass().getName(), e.getClass().getName(), e.getMessage()), e);
        }
    }

    public void populateDocumentsfromPatientXwalkTyps(List<Document> documents, List<PtDocXwalkT> patientDocXwalkTyps) {

        for (Document document : documents) {
            try {

                for (PtDocXwalkT patientDocXwalkTyp : patientDocXwalkTyps) {

                    if (patientDocXwalkTyp.getDocId().equalsIgnoreCase(document.getDocumentID())) {

                        populateDocumentsfromPatientXwalkTyp(document, patientDocXwalkTyp);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void populateDocumentsfromPatientXwalkTyp(Document document, PtDocXwalkT patientDocXwalkTyp) throws SandataRuntimeException {

        try {
            document.setPatientID(patientDocXwalkTyp.getPtId());
            document.setChangeReasonMemo(patientDocXwalkTyp.getChangeReasonMemo());
            document.setRecordCreatedBy(patientDocXwalkTyp.getRecCreatedBy());
            document.setBusinessEntityID(patientDocXwalkTyp.getBeId());
            document.setDocClassName(patientDocXwalkTyp.getDocClasName());
        } catch (Exception e) {
            e.printStackTrace();
            throw new SandataRuntimeException(String.format("populateDocumentsfromPatientXwalkTyp: %s: %s: [ERROR_MSG=%s]",
                    getClass().getName(), e.getClass().getName(), e.getMessage()), e);
        }
    }

    public void populateDocumentsfromVisitXwalkTyps(List<Document> documents, List<VisitDocXwalkT> visitDocXwalkTyps) {

        for (Document document : documents) {

            try {

                for (VisitDocXwalkT visitDocXwalkTyp : visitDocXwalkTyps) {

                    if (visitDocXwalkTyp.getDocId().equalsIgnoreCase(document.getDocumentID())) {

                        populateDocumentsfromVisitXwalkTyp(document, visitDocXwalkTyp);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void populateDocumentsfromVisitXwalkTyp(Document document, VisitDocXwalkT visitDocXwalkTyp) throws SandataRuntimeException {

        try {
            document.setVisitSK(BigInteger.valueOf(visitDocXwalkTyp.getVisitSk().longValue()));
            document.setChangeReasonMemo(visitDocXwalkTyp.getChangeReasonMemo());
            document.setRecordCreatedBy(visitDocXwalkTyp.getRecCreatedBy());
            document.setBusinessEntityID(visitDocXwalkTyp.getBeId());
            document.setDocClassName(visitDocXwalkTyp.getDocClasName());
        } catch (Exception e) {
            e.printStackTrace();
            throw new SandataRuntimeException(String.format("populateDocumentsfromVisitXwalkTyp: %s: %s: [ERROR_MSG=%s]",
                    getClass().getName(), e.getClass().getName(), e.getMessage()), e);
        }
    }

    public void populateDocumentsfromStaffXwalkTyps(List<Document> documents, List<StaffDocXwalkT> staffDocXwalkTyps) {

        for (Document document : documents) {

            try {

                for (StaffDocXwalkT staffDocXwalkTyp : staffDocXwalkTyps) {

                    if (staffDocXwalkTyp.getDocId().equalsIgnoreCase(document.getDocumentID())) {

                        populateDocumentsfromStaffXwalkTyp(document, staffDocXwalkTyp);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void populateDocumentsfromStaffXwalkTyp(Document document, StaffDocXwalkT staffDocXwalkTyp) throws SandataRuntimeException {

        try {
            document.setStaffID(staffDocXwalkTyp.getStaffId());
            document.setChangeReasonMemo(staffDocXwalkTyp.getChangeReasonMemo());
            document.setRecordCreatedBy(staffDocXwalkTyp.getRecCreatedBy());
            document.setBusinessEntityID(staffDocXwalkTyp.getBeId());
            document.setDocClassName(staffDocXwalkTyp.getDocClasName());
        } catch (Exception e) {
            e.printStackTrace();
            throw new SandataRuntimeException(String.format("populateDocumentsfromStaffXwalkTyp: %s: %s: [ERROR_MSG=%s]",
                    getClass().getName(), e.getClass().getName(), e.getMessage()), e);
        }
    }

    public void populateDocumentsfromBeXwalkTyp(Document document, BeDocXwalkT bsnEntDocXwalkTyp) throws SandataRuntimeException {

        try {
            document.setChangeReasonMemo(bsnEntDocXwalkTyp.getChangeReasonMemo());
            document.setRecordCreatedBy(bsnEntDocXwalkTyp.getRecCreatedBy());
            document.setBusinessEntityID(bsnEntDocXwalkTyp.getBeId());
            document.setDocClassName(bsnEntDocXwalkTyp.getDocClasName());
        } catch (Exception e) {
            e.printStackTrace();
            throw new SandataRuntimeException(String.format("populateDocumentsfromBeXwalkTyp: %s: %s: [ERROR_MSG=%s]",
                    getClass().getName(), e.getClass().getName(), e.getMessage()), e);
        }
    }

    public void populateDocumentsfromBeXwalkTyps(List<Document> documents, List<BeDocXwalkT> bsnEntDocXwalkTyps) {

        for (Document document : documents) {

            try {

                for (BeDocXwalkT bsnEntDocXwalkTyp : bsnEntDocXwalkTyps) {

                    if (bsnEntDocXwalkTyp.getDocId().equalsIgnoreCase(document.getDocumentID())) {

                        populateDocumentsfromBeXwalkTyp(document, bsnEntDocXwalkTyp);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
