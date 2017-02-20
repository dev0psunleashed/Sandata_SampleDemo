--------------------------------------------------------
--  DDL for Table Doc_Typ_Lkup
--------------------------------------------------------
DROP TYPE DOC_TYP_LKUP_TAB FORCE;
/

CREATE or REPLACE
TYPE DOC_TYP_LKUP_T AS OBJECT (

	"DOC_TYP_LKUP_SK" NUMBER(38,0),
	"REC_CREATE_TMSTP" DATE,
	"REC_UPDATE_TMSTP" DATE,
	"DOC_TYP_NAME" VARCHAR2(100 BYTE),
	"DOC_TYP_DESC" VARCHAR2(250 BYTE)
)
/

CREATE TYPE DOC_TYP_LKUP_TAB AS TABLE OF DOC_TYP_LKUP_T;
/
