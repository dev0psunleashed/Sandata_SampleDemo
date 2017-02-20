--------------------------------------------------------
--  DDL for Table Doc
--------------------------------------------------------
DROP TYPE DOC_TAB FORCE;
/

CREATE or REPLACE
TYPE DOC_T AS OBJECT (

  "DOC_SK" NUMBER(38,0),
	"REC_CREATE_TMSTP" DATE,
	"REC_UPDATE_TMSTP" DATE,
	"DOC_ID" VARCHAR2(50 BYTE),
	"DOC_TYP_NAME" VARCHAR2(100 BYTE),
	"DOC" BLOB,
	"DOC_OTHER" BLOB
)
/

CREATE TYPE DOC_TAB AS TABLE OF DOC_T;
/
