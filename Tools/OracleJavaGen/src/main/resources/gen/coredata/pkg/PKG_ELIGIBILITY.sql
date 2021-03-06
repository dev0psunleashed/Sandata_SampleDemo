CREATE OR REPLACE PACKAGE PKG_ELIGIBILITY IS

TYPE REF_CURSOR IS REF CURSOR;
FUNCTION getElig(BE_ID VARCHAR2) RETURN REF_CURSOR;
FUNCTION getElig(ELIG_KEY NUMBER) RETURN REF_CURSOR;
FUNCTION getElig(ELIG_ARRAY STRING_ARRAY) RETURN REF_CURSOR;
FUNCTION insertElig(ELIG_VAR ELIG_T) RETURN NUMBER;
FUNCTION updateElig(ELIG_VAR ELIG_T) RETURN NUMBER;
FUNCTION deleteElig(ELIG_KEY NUMBER) RETURN NUMBER;

END PKG_ELIGIBILITY;
/

CREATE OR REPLACE PACKAGE BODY PKG_ELIGIBILITY IS

FUNCTION getElig(BE_ID VARCHAR2) RETURN REF_CURSOR
AS LANGUAGE JAVA NAME 'Elig.getElig(java.lang.String) return java.sql.ResultSet';
FUNCTION getElig(ELIG_KEY NUMBER) RETURN REF_CURSOR
AS LANGUAGE JAVA NAME 'Elig.getElig(long) return java.sql.ResultSet';
FUNCTION getElig(ELIG_ARRAY STRING_ARRAY) RETURN REF_CURSOR
AS LANGUAGE JAVA NAME 'Elig.getElig(oracle.sql.ARRAY) return java.sql.ResultSet';
FUNCTION insertElig(ELIG_VAR ELIG_T) RETURN NUMBER
AS LANGUAGE JAVA NAME 'Elig.insertElig(oracle.sql.STRUCT) return long';
FUNCTION updateElig(ELIG_VAR ELIG_T) RETURN NUMBER
AS LANGUAGE JAVA NAME 'Elig.updateElig(oracle.sql.STRUCT) return long';
FUNCTION deleteElig(ELIG_KEY NUMBER) RETURN NUMBER
AS LANGUAGE JAVA NAME 'Elig.deleteElig(long) return long';

END PKG_ELIGIBILITY;
/

