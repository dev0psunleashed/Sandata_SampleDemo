CREATE OR REPLACE TYPE PSTL_CODE_DET_LKUP_T AS OBJECT (
PSTL_CODE_DET_LKUP_SK	NUMBER(38),
REC_CREATE_TMSTP DATE,
REC_UPDATE_TMSTP DATE,
REC_EFF_TMSTP DATE,
REC_TERM_TMSTP DATE,
REC_CREATED_BY	VARCHAR2(50 BYTE),
REC_UPDATED_BY	VARCHAR2(50 BYTE),
CHANGE_REASON_MEMO	VARCHAR2(255 BYTE),
CURR_REC_IND	NUMBER(1),
CHANGE_VERSION_ID	NUMBER(38),
PSTL_CODE	VARCHAR2(5 BYTE),
PSTL_CODE_SRC_QLFR	VARCHAR2(50 BYTE),
PSTL_CODE_ZIP4	VARCHAR2(4 BYTE),
PSTL_CODE_ZIP4_RANGE	VARCHAR2(10 BYTE),
PSTL_CODE_CITY_NAME	VARCHAR2(100 BYTE),
PSTL_CODE_REC_TYP_CODE	CHAR(1 BYTE),
PSTL_CODE_CARRIER_ROUTE_ID	VARCHAR2(10 BYTE),
PSTL_CODE_STREET_PREDIR_CODE	VARCHAR2(2 BYTE),
PSTL_CODE_STREET_NAME	VARCHAR2(100 BYTE),
PSTL_CODE_STREET_SUFFIX	VARCHAR2(10 BYTE),
PSTL_CODE_STREET_POSTDIR_CODE	VARCHAR2(2 BYTE),
PSTL_CODE_PRMY_LOW_NUM	VARCHAR2(10 BYTE),
PSTL_CODE_PRMY_HIGH_NUM	VARCHAR2(10 BYTE),
PSTL_CODE_PRMY_ODD_EVEN_CODE	CHAR(1 BYTE),
PSTL_CODE_ENT_NAME	VARCHAR2(100 BYTE),
PSTL_CODE_SCNDRY_ABBRV	VARCHAR2(10 BYTE),
PSTL_CODE_SCNDRY_LOW_NUM	VARCHAR2(10 BYTE),
PSTL_CODE_SCNDRY_HIGH_NUM	VARCHAR2(10 BYTE),
PSTL_CODE_SCNDRY_ODD_EVEN_CODE	CHAR(1 BYTE),
PSTL_CODE_ADD_ON_LOW_SECTOR	VARCHAR2(10 BYTE),
PSTL_CODE_ADD_ON_LOW_SEGMENT	VARCHAR2(10 BYTE),
PSTL_CODE_ADD_ON_HIGH_SECTOR	VARCHAR2(10 BYTE),
PSTL_CODE_ADD_ON_HIGH_SEGMENT	VARCHAR2(10 BYTE),
PSTL_CODE_BASE_ALTERNATE_CODE	CHAR(1 BYTE),
LACS_STATUS_IND	VARCHAR2(1 BYTE),
GOV_BLDG_CODE	CHAR(1 BYTE),
PSTL_CODE_FIN_NUM	VARCHAR2(10 BYTE),
PSTL_CODE_STATE_CODE	CHAR(2 BYTE),
COUNTY_FIPS_CODE	VARCHAR2(10 BYTE),
CONGRNL_DISTR_NUM	VARCHAR2(10 BYTE),
MUNI_CITY_STATE_KEY	VARCHAR2(10 BYTE),
URBANIZATION_CITY_STATE_KEY	VARCHAR2(10 BYTE),
PREFD_LAST_LINE_CITY_STATE_KEY	VARCHAR2(10 BYTE)
);
/
