CREATE OR REPLACE PACKAGE PKG_DOC_UTIL IS

  FUNCTION UPDATE_DOC_DET_PROPTY(v_doc_det_lst IN DOC_DET_TAB) RETURN NUMBER;

  FUNCTION UPDATE_DOC_BLOB(v_column_name IN VARCHAR2, v_doc_id IN DOC.DOC_ID%TYPE) RETURN SYS_REFCURSOR;

END PKG_DOC_UTIL;
/

CREATE OR REPLACE PACKAGE BODY PKG_DOC_UTIL IS

  FUNCTION UPDATE_DOC_DET_PROPTY(v_doc_det_lst IN DOC_DET_TAB) RETURN NUMBER
  AS
  BEGIN
  
    FORALL idx IN v_doc_det_lst.FIRST .. v_doc_det_lst.LAST
      UPDATE DOC_DET
      SET
        DOC_DET_PROPTY_KEY_NAME = v_doc_det_lst(idx).DOC_DET_PROPTY_KEY_NAME,
        REC_UPDATE_TMSTP = v_doc_det_lst(idx).REC_UPDATE_TMSTP,
        DOC_DET_PROPTY_KEY_VAL = v_doc_det_lst(idx).DOC_DET_PROPTY_KEY_VAL
      WHERE DOC_DET_SK = v_doc_det_lst(idx).DOC_DET_SK;
  
    RETURN SQL%ROWCOUNT;
  
  END UPDATE_DOC_DET_PROPTY;

  ------------------------------------------------------------------
  FUNCTION UPDATE_DOC_BLOB(v_column_name IN VARCHAR2, v_doc_id IN DOC.DOC_ID%TYPE) RETURN SYS_REFCURSOR
  AS
    v_ref_cursor SYS_REFCURSOR;
  BEGIN
    IF v_column_name = 'DOC' THEN
      UPDATE DOC SET DOC = EMPTY_BLOB() WHERE DOC_ID = v_doc_id;
      OPEN v_ref_cursor FOR
      SELECT DOC FROM DOC WHERE DOC_ID = v_doc_id FOR UPDATE;
      
    ELSIF v_column_name = 'DOC_OTHER' THEN
      UPDATE DOC SET DOC_OTHER = EMPTY_BLOB() WHERE DOC_ID = v_doc_id;
      OPEN v_ref_cursor FOR
      SELECT DOC_OTHER FROM DOC WHERE DOC_ID = v_doc_id FOR UPDATE;
    END IF;

    RETURN v_ref_cursor;

  END UPDATE_DOC_BLOB;

END PKG_DOC_UTIL;
/
