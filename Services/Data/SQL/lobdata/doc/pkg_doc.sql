CREATE OR REPLACE
PACKAGE pkg_DOC AS

    TYPE ref_cursor IS REF CURSOR;

    FUNCTION insertDoc(DOC DOC_T) RETURN NUMBER;

    FUNCTION insertDocDet(DOC_DET DOC_DET_T) RETURN NUMBER;

    FUNCTION updateDocDet(DOC_DET DOC_DET_T) RETURN NUMBER;

    FUNCTION insertDocTypLkup(DOC_TYP_LKUP DOC_TYP_LKUP_T) RETURN NUMBER;

    FUNCTION insertDocDetProptyLkup(DOC_DET_PROPTY_LKUP DOC_DET_PROPTY_LKUP_T) RETURN NUMBER;

END pkg_DOC;
/

CREATE OR REPLACE
PACKAGE BODY pkg_DOC IS

    FUNCTION insertDoc(DOC DOC_T) RETURN NUMBER
      AS LANGUAGE JAVA NAME 'Doc.insertDoc(oracle.sql.STRUCT) return int';

    FUNCTION insertDocDet(DOC_DET DOC_DET_T) RETURN NUMBER
      AS LANGUAGE JAVA NAME 'DocDet.insertDocDet(oracle.sql.STRUCT) return int';

    FUNCTION updateDocDet(DOC_DET DOC_DET_T) RETURN NUMBER
      AS LANGUAGE JAVA NAME 'DocDet.updateDocDet(oracle.sql.STRUCT) return int';

    FUNCTION insertDocTypLkup(DOC_TYP_LKUP DOC_TYP_LKUP_T) RETURN NUMBER
      AS LANGUAGE JAVA NAME 'DocTypLkup.insertDocTypLkup(oracle.sql.STRUCT) return int';

    FUNCTION insertDocDetProptyLkup(DOC_DET_PROPTY_LKUP DOC_DET_PROPTY_LKUP_T) RETURN NUMBER
      AS LANGUAGE JAVA NAME 'DocDetProptyLkup.insertDocDetProptyLkup(oracle.sql.STRUCT) return int';

END pkg_DOC;
/
