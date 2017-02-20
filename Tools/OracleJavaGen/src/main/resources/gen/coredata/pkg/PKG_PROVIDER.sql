CREATE OR REPLACE PACKAGE PKG_PROVIDER IS

TYPE REF_CURSOR IS REF CURSOR;
FUNCTION getProvider(BE_ID VARCHAR2) RETURN REF_CURSOR;
FUNCTION getProvider(PROVIDER_KEY NUMBER) RETURN REF_CURSOR;
FUNCTION getProvider(PROVIDER_ARRAY STRING_ARRAY) RETURN REF_CURSOR;
FUNCTION insertProvider(PROVIDER_VAR PROVIDER_T) RETURN NUMBER;
FUNCTION updateProvider(PROVIDER_VAR PROVIDER_T) RETURN NUMBER;
FUNCTION deleteProvider(PROVIDER_KEY NUMBER) RETURN NUMBER;

END PKG_PROVIDER;
/

CREATE OR REPLACE PACKAGE BODY PKG_PROVIDER IS

FUNCTION getProvider(BE_ID VARCHAR2) RETURN REF_CURSOR
AS LANGUAGE JAVA NAME 'Provider.getProvider(java.lang.String) return java.sql.ResultSet';
FUNCTION getProvider(PROVIDER_KEY NUMBER) RETURN REF_CURSOR
AS LANGUAGE JAVA NAME 'Provider.getProvider(long) return java.sql.ResultSet';
FUNCTION getProvider(PROVIDER_ARRAY STRING_ARRAY) RETURN REF_CURSOR
AS LANGUAGE JAVA NAME 'Provider.getProvider(oracle.sql.ARRAY) return java.sql.ResultSet';
FUNCTION insertProvider(PROVIDER_VAR PROVIDER_T) RETURN NUMBER
AS LANGUAGE JAVA NAME 'Provider.insertProvider(oracle.sql.STRUCT) return long';
FUNCTION updateProvider(PROVIDER_VAR PROVIDER_T) RETURN NUMBER
AS LANGUAGE JAVA NAME 'Provider.updateProvider(oracle.sql.STRUCT) return long';
FUNCTION deleteProvider(PROVIDER_KEY NUMBER) RETURN NUMBER
AS LANGUAGE JAVA NAME 'Provider.deleteProvider(long) return long';

END PKG_PROVIDER;
/

