--------------------------------------------------------
--  DDL for Table Doc_Detl_Propty_Lkup
--------------------------------------------------------
DROP TYPE DOC_DET_PROPTY_LKUP_TAB FORCE;
/

CREATE or REPLACE
TYPE DOC_DET_PROPTY_LKUP_T AS OBJECT (

  "DOC_DET_PROPTY_LKUP_SK" NUMBER(38,0),
	"REC_CREATE_TMSTP" DATE,
	"REC_UPDATE_TMSTP" DATE,
	"DOC_DET_PROPTY_KEY_NAME" VARCHAR2(50 BYTE),
	"DOC_DET_PROPTY_KEY_DESC" VARCHAR2(250 BYTE)
)
/

CREATE TYPE DOC_DET_PROPTY_LKUP_TAB AS TABLE OF DOC_DET_PROPTY_LKUP_T;
/
