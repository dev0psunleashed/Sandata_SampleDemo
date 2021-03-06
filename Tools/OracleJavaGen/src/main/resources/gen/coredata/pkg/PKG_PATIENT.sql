CREATE OR REPLACE PACKAGE PKG_PATIENT IS

TYPE REF_CURSOR IS REF CURSOR;
FUNCTION getPt(BE_ID VARCHAR2) RETURN REF_CURSOR;
FUNCTION getPt(PT_KEY NUMBER) RETURN REF_CURSOR;
FUNCTION getPt(PT_ARRAY STRING_ARRAY) RETURN REF_CURSOR;
FUNCTION insertPt(PT_VAR PT_T) RETURN NUMBER;
FUNCTION updatePt(PT_VAR PT_T) RETURN NUMBER;
FUNCTION deletePt(PT_KEY NUMBER) RETURN NUMBER;
FUNCTION getPtAllergy(BE_ID VARCHAR2) RETURN REF_CURSOR;
FUNCTION getPtAllergy(PTALLERGY_KEY NUMBER) RETURN REF_CURSOR;
FUNCTION getPtAllergy(PTALLERGY_ARRAY STRING_ARRAY) RETURN REF_CURSOR;
FUNCTION insertPtAllergy(PT_ALLERGY_VAR PT_ALLERGY_T) RETURN NUMBER;
FUNCTION updatePtAllergy(PT_ALLERGY_VAR PT_ALLERGY_T) RETURN NUMBER;
FUNCTION deletePtAllergy(PT_ALLERGY_KEY NUMBER) RETURN NUMBER;
FUNCTION getPtClinicalAsmt(BE_ID VARCHAR2) RETURN REF_CURSOR;
FUNCTION getPtClinicalAsmt(PTCLINICALASMT_KEY NUMBER) RETURN REF_CURSOR;
FUNCTION getPtClinicalAsmt(PTCLINICALASMT_ARRAY STRING_ARRAY) RETURN REF_CURSOR;
FUNCTION insertPtClinicalAsmt(PT_CLINICAL_ASMT_VAR PT_CLINICAL_ASMT_T) RETURN NUMBER;
FUNCTION updatePtClinicalAsmt(PT_CLINICAL_ASMT_VAR PT_CLINICAL_ASMT_T) RETURN NUMBER;
FUNCTION deletePtClinicalAsmt(PT_CLINICAL_ASMT_KEY NUMBER) RETURN NUMBER;
FUNCTION getPtContAddr(BE_ID VARCHAR2) RETURN REF_CURSOR;
FUNCTION getPtContAddr(PTCONTADDR_KEY NUMBER) RETURN REF_CURSOR;
FUNCTION getPtContAddr(PTCONTADDR_ARRAY STRING_ARRAY) RETURN REF_CURSOR;
FUNCTION insertPtContAddr(PT_CONT_ADDR_VAR PT_CONT_ADDR_T) RETURN NUMBER;
FUNCTION updatePtContAddr(PT_CONT_ADDR_VAR PT_CONT_ADDR_T) RETURN NUMBER;
FUNCTION deletePtContAddr(PT_CONT_ADDR_KEY NUMBER) RETURN NUMBER;
FUNCTION getPtContEmail(BE_ID VARCHAR2) RETURN REF_CURSOR;
FUNCTION getPtContEmail(PTCONTEMAIL_KEY NUMBER) RETURN REF_CURSOR;
FUNCTION getPtContEmail(PTCONTEMAIL_ARRAY STRING_ARRAY) RETURN REF_CURSOR;
FUNCTION insertPtContEmail(PT_CONT_EMAIL_VAR PT_CONT_EMAIL_T) RETURN NUMBER;
FUNCTION updatePtContEmail(PT_CONT_EMAIL_VAR PT_CONT_EMAIL_T) RETURN NUMBER;
FUNCTION deletePtContEmail(PT_CONT_EMAIL_KEY NUMBER) RETURN NUMBER;
FUNCTION getPtContPhone(BE_ID VARCHAR2) RETURN REF_CURSOR;
FUNCTION getPtContPhone(PTCONTPHONE_KEY NUMBER) RETURN REF_CURSOR;
FUNCTION getPtContPhone(PTCONTPHONE_ARRAY STRING_ARRAY) RETURN REF_CURSOR;
FUNCTION insertPtContPhone(PT_CONT_PHONE_VAR PT_CONT_PHONE_T) RETURN NUMBER;
FUNCTION updatePtContPhone(PT_CONT_PHONE_VAR PT_CONT_PHONE_T) RETURN NUMBER;
FUNCTION deletePtContPhone(PT_CONT_PHONE_KEY NUMBER) RETURN NUMBER;
FUNCTION getPtDmeAndSupply(BE_ID VARCHAR2) RETURN REF_CURSOR;
FUNCTION getPtDmeAndSupply(PTDMEANDSUPPLY_KEY NUMBER) RETURN REF_CURSOR;
FUNCTION getPtDmeAndSupply(PTDMEANDSUPPLY_ARRAY STRING_ARRAY) RETURN REF_CURSOR;
FUNCTION insertPtDmeAndSupply(PT_DME_AND_SUPPLY_VAR PT_DME_AND_SUPPLY_T) RETURN NUMBER;
FUNCTION updatePtDmeAndSupply(PT_DME_AND_SUPPLY_VAR PT_DME_AND_SUPPLY_T) RETURN NUMBER;
FUNCTION deletePtDmeAndSupply(PT_DME_AND_SUPPLY_KEY NUMBER) RETURN NUMBER;
FUNCTION getPtDx(BE_ID VARCHAR2) RETURN REF_CURSOR;
FUNCTION getPtDx(PTDX_KEY NUMBER) RETURN REF_CURSOR;
FUNCTION getPtDx(PTDX_ARRAY STRING_ARRAY) RETURN REF_CURSOR;
FUNCTION insertPtDx(PT_DX_VAR PT_DX_T) RETURN NUMBER;
FUNCTION updatePtDx(PT_DX_VAR PT_DX_T) RETURN NUMBER;
FUNCTION deletePtDx(PT_DX_KEY NUMBER) RETURN NUMBER;
FUNCTION getPtEnv(BE_ID VARCHAR2) RETURN REF_CURSOR;
FUNCTION getPtEnv(PTENV_KEY NUMBER) RETURN REF_CURSOR;
FUNCTION getPtEnv(PTENV_ARRAY STRING_ARRAY) RETURN REF_CURSOR;
FUNCTION insertPtEnv(PT_ENV_VAR PT_ENV_T) RETURN NUMBER;
FUNCTION updatePtEnv(PT_ENV_VAR PT_ENV_T) RETURN NUMBER;
FUNCTION deletePtEnv(PT_ENV_KEY NUMBER) RETURN NUMBER;
FUNCTION getPtHcp(BE_ID VARCHAR2) RETURN REF_CURSOR;
FUNCTION getPtHcp(PTHCP_KEY NUMBER) RETURN REF_CURSOR;
FUNCTION getPtHcp(PTHCP_ARRAY STRING_ARRAY) RETURN REF_CURSOR;
FUNCTION insertPtHcp(PT_HCP_VAR PT_HCP_T) RETURN NUMBER;
FUNCTION updatePtHcp(PT_HCP_VAR PT_HCP_T) RETURN NUMBER;
FUNCTION deletePtHcp(PT_HCP_KEY NUMBER) RETURN NUMBER;
FUNCTION getPtIdXwalk(BE_ID VARCHAR2) RETURN REF_CURSOR;
FUNCTION getPtIdXwalk(PTIDXWALK_KEY NUMBER) RETURN REF_CURSOR;
FUNCTION getPtIdXwalk(PTIDXWALK_ARRAY STRING_ARRAY) RETURN REF_CURSOR;
FUNCTION insertPtIdXwalk(PT_ID_XWALK_VAR PT_ID_XWALK_T) RETURN NUMBER;
FUNCTION updatePtIdXwalk(PT_ID_XWALK_VAR PT_ID_XWALK_T) RETURN NUMBER;
FUNCTION deletePtIdXwalk(PT_ID_XWALK_KEY NUMBER) RETURN NUMBER;
FUNCTION getPtMed(BE_ID VARCHAR2) RETURN REF_CURSOR;
FUNCTION getPtMed(PTMED_KEY NUMBER) RETURN REF_CURSOR;
FUNCTION getPtMed(PTMED_ARRAY STRING_ARRAY) RETURN REF_CURSOR;
FUNCTION insertPtMed(PT_MED_VAR PT_MED_T) RETURN NUMBER;
FUNCTION updatePtMed(PT_MED_VAR PT_MED_T) RETURN NUMBER;
FUNCTION deletePtMed(PT_MED_KEY NUMBER) RETURN NUMBER;
FUNCTION getPtMedclHist(BE_ID VARCHAR2) RETURN REF_CURSOR;
FUNCTION getPtMedclHist(PTMEDCLHIST_KEY NUMBER) RETURN REF_CURSOR;
FUNCTION getPtMedclHist(PTMEDCLHIST_ARRAY STRING_ARRAY) RETURN REF_CURSOR;
FUNCTION insertPtMedclHist(PT_MEDCL_HIST_VAR PT_MEDCL_HIST_T) RETURN NUMBER;
FUNCTION updatePtMedclHist(PT_MEDCL_HIST_VAR PT_MEDCL_HIST_T) RETURN NUMBER;
FUNCTION deletePtMedclHist(PT_MEDCL_HIST_KEY NUMBER) RETURN NUMBER;
FUNCTION getPtNote(BE_ID VARCHAR2) RETURN REF_CURSOR;
FUNCTION getPtNote(PTNOTE_KEY NUMBER) RETURN REF_CURSOR;
FUNCTION getPtNote(PTNOTE_ARRAY STRING_ARRAY) RETURN REF_CURSOR;
FUNCTION insertPtNote(PT_NOTE_VAR PT_NOTE_T) RETURN NUMBER;
FUNCTION updatePtNote(PT_NOTE_VAR PT_NOTE_T) RETURN NUMBER;
FUNCTION deletePtNote(PT_NOTE_KEY NUMBER) RETURN NUMBER;
FUNCTION getPtNutrRqmt(BE_ID VARCHAR2) RETURN REF_CURSOR;
FUNCTION getPtNutrRqmt(PTNUTRRQMT_KEY NUMBER) RETURN REF_CURSOR;
FUNCTION getPtNutrRqmt(PTNUTRRQMT_ARRAY STRING_ARRAY) RETURN REF_CURSOR;
FUNCTION insertPtNutrRqmt(PT_NUTR_RQMT_VAR PT_NUTR_RQMT_T) RETURN NUMBER;
FUNCTION updatePtNutrRqmt(PT_NUTR_RQMT_VAR PT_NUTR_RQMT_T) RETURN NUMBER;
FUNCTION deletePtNutrRqmt(PT_NUTR_RQMT_KEY NUMBER) RETURN NUMBER;
FUNCTION getPtPayer(BE_ID VARCHAR2) RETURN REF_CURSOR;
FUNCTION getPtPayer(PTPAYER_KEY NUMBER) RETURN REF_CURSOR;
FUNCTION getPtPayer(PTPAYER_ARRAY STRING_ARRAY) RETURN REF_CURSOR;
FUNCTION insertPtPayer(PT_PAYER_VAR PT_PAYER_T) RETURN NUMBER;
FUNCTION updatePtPayer(PT_PAYER_VAR PT_PAYER_T) RETURN NUMBER;
FUNCTION deletePtPayer(PT_PAYER_KEY NUMBER) RETURN NUMBER;
FUNCTION getPtPayerIns(BE_ID VARCHAR2) RETURN REF_CURSOR;
FUNCTION getPtPayerIns(PTPAYERINS_KEY NUMBER) RETURN REF_CURSOR;
FUNCTION getPtPayerIns(PTPAYERINS_ARRAY STRING_ARRAY) RETURN REF_CURSOR;
FUNCTION insertPtPayerIns(PT_PAYER_INS_VAR PT_PAYER_INS_T) RETURN NUMBER;
FUNCTION updatePtPayerIns(PT_PAYER_INS_VAR PT_PAYER_INS_T) RETURN NUMBER;
FUNCTION deletePtPayerIns(PT_PAYER_INS_KEY NUMBER) RETURN NUMBER;
FUNCTION getPtPayerLmt(BE_ID VARCHAR2) RETURN REF_CURSOR;
FUNCTION getPtPayerLmt(PTPAYERLMT_KEY NUMBER) RETURN REF_CURSOR;
FUNCTION getPtPayerLmt(PTPAYERLMT_ARRAY STRING_ARRAY) RETURN REF_CURSOR;
FUNCTION insertPtPayerLmt(PT_PAYER_LMT_VAR PT_PAYER_LMT_T) RETURN NUMBER;
FUNCTION updatePtPayerLmt(PT_PAYER_LMT_VAR PT_PAYER_LMT_T) RETURN NUMBER;
FUNCTION deletePtPayerLmt(PT_PAYER_LMT_KEY NUMBER) RETURN NUMBER;
FUNCTION getPtPet RETURN REF_CURSOR;
FUNCTION getPtPet(PTPET_KEY NUMBER) RETURN REF_CURSOR;
FUNCTION getPtPet(PTPET_ARRAY STRING_ARRAY) RETURN REF_CURSOR;
FUNCTION insertPtPet(PT_PET_VAR PT_PET_T) RETURN NUMBER;
FUNCTION updatePtPet(PT_PET_VAR PT_PET_T) RETURN NUMBER;
FUNCTION deletePtPet(PT_PET_KEY NUMBER) RETURN NUMBER;
FUNCTION getPtRate(BE_ID VARCHAR2) RETURN REF_CURSOR;
FUNCTION getPtRate(PTRATE_KEY NUMBER) RETURN REF_CURSOR;
FUNCTION getPtRate(PTRATE_ARRAY STRING_ARRAY) RETURN REF_CURSOR;
FUNCTION insertPtRate(PT_RATE_VAR PT_RATE_T) RETURN NUMBER;
FUNCTION updatePtRate(PT_RATE_VAR PT_RATE_T) RETURN NUMBER;
FUNCTION deletePtRate(PT_RATE_KEY NUMBER) RETURN NUMBER;
FUNCTION getPtRqmt(BE_ID VARCHAR2) RETURN REF_CURSOR;
FUNCTION getPtRqmt(PTRQMT_KEY NUMBER) RETURN REF_CURSOR;
FUNCTION getPtRqmt(PTRQMT_ARRAY STRING_ARRAY) RETURN REF_CURSOR;
FUNCTION insertPtRqmt(PT_RQMT_VAR PT_RQMT_T) RETURN NUMBER;
FUNCTION updatePtRqmt(PT_RQMT_VAR PT_RQMT_T) RETURN NUMBER;
FUNCTION deletePtRqmt(PT_RQMT_KEY NUMBER) RETURN NUMBER;
FUNCTION getPtSftyMeasure(BE_ID VARCHAR2) RETURN REF_CURSOR;
FUNCTION getPtSftyMeasure(PTSFTYMEASURE_KEY NUMBER) RETURN REF_CURSOR;
FUNCTION getPtSftyMeasure(PTSFTYMEASURE_ARRAY STRING_ARRAY) RETURN REF_CURSOR;
FUNCTION insertPtSftyMeasure(PT_SFTY_MEASURE_VAR PT_SFTY_MEASURE_T) RETURN NUMBER;
FUNCTION updatePtSftyMeasure(PT_SFTY_MEASURE_VAR PT_SFTY_MEASURE_T) RETURN NUMBER;
FUNCTION deletePtSftyMeasure(PT_SFTY_MEASURE_KEY NUMBER) RETURN NUMBER;
FUNCTION getPtStructBrrDet RETURN REF_CURSOR;
FUNCTION getPtStructBrrDet(PTSTRUCTBRRDET_KEY NUMBER) RETURN REF_CURSOR;
FUNCTION getPtStructBrrDet(PTSTRUCTBRRDET_ARRAY STRING_ARRAY) RETURN REF_CURSOR;
FUNCTION insertPtStructBrrDet(PT_STRUCT_BRR_DET_VAR PT_STRUCT_BRR_DET_T) RETURN NUMBER;
FUNCTION updatePtStructBrrDet(PT_STRUCT_BRR_DET_VAR PT_STRUCT_BRR_DET_T) RETURN NUMBER;
FUNCTION deletePtStructBrrDet(PT_STRUCT_BRR_DET_KEY NUMBER) RETURN NUMBER;

END PKG_PATIENT;
/

CREATE OR REPLACE PACKAGE BODY PKG_PATIENT IS

FUNCTION getPt(BE_ID VARCHAR2) RETURN REF_CURSOR
AS LANGUAGE JAVA NAME 'Pt.getPt(java.lang.String) return java.sql.ResultSet';
FUNCTION getPt(PT_KEY NUMBER) RETURN REF_CURSOR
AS LANGUAGE JAVA NAME 'Pt.getPt(long) return java.sql.ResultSet';
FUNCTION getPt(PT_ARRAY STRING_ARRAY) RETURN REF_CURSOR
AS LANGUAGE JAVA NAME 'Pt.getPt(oracle.sql.ARRAY) return java.sql.ResultSet';
FUNCTION insertPt(PT_VAR PT_T) RETURN NUMBER
AS LANGUAGE JAVA NAME 'Pt.insertPt(oracle.sql.STRUCT) return long';
FUNCTION updatePt(PT_VAR PT_T) RETURN NUMBER
AS LANGUAGE JAVA NAME 'Pt.updatePt(oracle.sql.STRUCT) return long';
FUNCTION deletePt(PT_KEY NUMBER) RETURN NUMBER
AS LANGUAGE JAVA NAME 'Pt.deletePt(long) return long';
FUNCTION getPtAllergy(BE_ID VARCHAR2) RETURN REF_CURSOR
AS LANGUAGE JAVA NAME 'PtAllergy.getPtAllergy(java.lang.String) return java.sql.ResultSet';
FUNCTION getPtAllergy(PTALLERGY_KEY NUMBER) RETURN REF_CURSOR
AS LANGUAGE JAVA NAME 'PtAllergy.getPtAllergy(long) return java.sql.ResultSet';
FUNCTION getPtAllergy(PTALLERGY_ARRAY STRING_ARRAY) RETURN REF_CURSOR
AS LANGUAGE JAVA NAME 'PtAllergy.getPtAllergy(oracle.sql.ARRAY) return java.sql.ResultSet';
FUNCTION insertPtAllergy(PT_ALLERGY_VAR PT_ALLERGY_T) RETURN NUMBER
AS LANGUAGE JAVA NAME 'PtAllergy.insertPtAllergy(oracle.sql.STRUCT) return long';
FUNCTION updatePtAllergy(PT_ALLERGY_VAR PT_ALLERGY_T) RETURN NUMBER
AS LANGUAGE JAVA NAME 'PtAllergy.updatePtAllergy(oracle.sql.STRUCT) return long';
FUNCTION deletePtAllergy(PT_ALLERGY_KEY NUMBER) RETURN NUMBER
AS LANGUAGE JAVA NAME 'PtAllergy.deletePtAllergy(long) return long';
FUNCTION getPtClinicalAsmt(BE_ID VARCHAR2) RETURN REF_CURSOR
AS LANGUAGE JAVA NAME 'PtClinicalAsmt.getPtClinicalAsmt(java.lang.String) return java.sql.ResultSet';
FUNCTION getPtClinicalAsmt(PTCLINICALASMT_KEY NUMBER) RETURN REF_CURSOR
AS LANGUAGE JAVA NAME 'PtClinicalAsmt.getPtClinicalAsmt(long) return java.sql.ResultSet';
FUNCTION getPtClinicalAsmt(PTCLINICALASMT_ARRAY STRING_ARRAY) RETURN REF_CURSOR
AS LANGUAGE JAVA NAME 'PtClinicalAsmt.getPtClinicalAsmt(oracle.sql.ARRAY) return java.sql.ResultSet';
FUNCTION insertPtClinicalAsmt(PT_CLINICAL_ASMT_VAR PT_CLINICAL_ASMT_T) RETURN NUMBER
AS LANGUAGE JAVA NAME 'PtClinicalAsmt.insertPtClinicalAsmt(oracle.sql.STRUCT) return long';
FUNCTION updatePtClinicalAsmt(PT_CLINICAL_ASMT_VAR PT_CLINICAL_ASMT_T) RETURN NUMBER
AS LANGUAGE JAVA NAME 'PtClinicalAsmt.updatePtClinicalAsmt(oracle.sql.STRUCT) return long';
FUNCTION deletePtClinicalAsmt(PT_CLINICAL_ASMT_KEY NUMBER) RETURN NUMBER
AS LANGUAGE JAVA NAME 'PtClinicalAsmt.deletePtClinicalAsmt(long) return long';
FUNCTION getPtContAddr(BE_ID VARCHAR2) RETURN REF_CURSOR
AS LANGUAGE JAVA NAME 'PtContAddr.getPtContAddr(java.lang.String) return java.sql.ResultSet';
FUNCTION getPtContAddr(PTCONTADDR_KEY NUMBER) RETURN REF_CURSOR
AS LANGUAGE JAVA NAME 'PtContAddr.getPtContAddr(long) return java.sql.ResultSet';
FUNCTION getPtContAddr(PTCONTADDR_ARRAY STRING_ARRAY) RETURN REF_CURSOR
AS LANGUAGE JAVA NAME 'PtContAddr.getPtContAddr(oracle.sql.ARRAY) return java.sql.ResultSet';
FUNCTION insertPtContAddr(PT_CONT_ADDR_VAR PT_CONT_ADDR_T) RETURN NUMBER
AS LANGUAGE JAVA NAME 'PtContAddr.insertPtContAddr(oracle.sql.STRUCT) return long';
FUNCTION updatePtContAddr(PT_CONT_ADDR_VAR PT_CONT_ADDR_T) RETURN NUMBER
AS LANGUAGE JAVA NAME 'PtContAddr.updatePtContAddr(oracle.sql.STRUCT) return long';
FUNCTION deletePtContAddr(PT_CONT_ADDR_KEY NUMBER) RETURN NUMBER
AS LANGUAGE JAVA NAME 'PtContAddr.deletePtContAddr(long) return long';
FUNCTION getPtContEmail(BE_ID VARCHAR2) RETURN REF_CURSOR
AS LANGUAGE JAVA NAME 'PtContEmail.getPtContEmail(java.lang.String) return java.sql.ResultSet';
FUNCTION getPtContEmail(PTCONTEMAIL_KEY NUMBER) RETURN REF_CURSOR
AS LANGUAGE JAVA NAME 'PtContEmail.getPtContEmail(long) return java.sql.ResultSet';
FUNCTION getPtContEmail(PTCONTEMAIL_ARRAY STRING_ARRAY) RETURN REF_CURSOR
AS LANGUAGE JAVA NAME 'PtContEmail.getPtContEmail(oracle.sql.ARRAY) return java.sql.ResultSet';
FUNCTION insertPtContEmail(PT_CONT_EMAIL_VAR PT_CONT_EMAIL_T) RETURN NUMBER
AS LANGUAGE JAVA NAME 'PtContEmail.insertPtContEmail(oracle.sql.STRUCT) return long';
FUNCTION updatePtContEmail(PT_CONT_EMAIL_VAR PT_CONT_EMAIL_T) RETURN NUMBER
AS LANGUAGE JAVA NAME 'PtContEmail.updatePtContEmail(oracle.sql.STRUCT) return long';
FUNCTION deletePtContEmail(PT_CONT_EMAIL_KEY NUMBER) RETURN NUMBER
AS LANGUAGE JAVA NAME 'PtContEmail.deletePtContEmail(long) return long';
FUNCTION getPtContPhone(BE_ID VARCHAR2) RETURN REF_CURSOR
AS LANGUAGE JAVA NAME 'PtContPhone.getPtContPhone(java.lang.String) return java.sql.ResultSet';
FUNCTION getPtContPhone(PTCONTPHONE_KEY NUMBER) RETURN REF_CURSOR
AS LANGUAGE JAVA NAME 'PtContPhone.getPtContPhone(long) return java.sql.ResultSet';
FUNCTION getPtContPhone(PTCONTPHONE_ARRAY STRING_ARRAY) RETURN REF_CURSOR
AS LANGUAGE JAVA NAME 'PtContPhone.getPtContPhone(oracle.sql.ARRAY) return java.sql.ResultSet';
FUNCTION insertPtContPhone(PT_CONT_PHONE_VAR PT_CONT_PHONE_T) RETURN NUMBER
AS LANGUAGE JAVA NAME 'PtContPhone.insertPtContPhone(oracle.sql.STRUCT) return long';
FUNCTION updatePtContPhone(PT_CONT_PHONE_VAR PT_CONT_PHONE_T) RETURN NUMBER
AS LANGUAGE JAVA NAME 'PtContPhone.updatePtContPhone(oracle.sql.STRUCT) return long';
FUNCTION deletePtContPhone(PT_CONT_PHONE_KEY NUMBER) RETURN NUMBER
AS LANGUAGE JAVA NAME 'PtContPhone.deletePtContPhone(long) return long';
FUNCTION getPtDmeAndSupply(BE_ID VARCHAR2) RETURN REF_CURSOR
AS LANGUAGE JAVA NAME 'PtDmeAndSupply.getPtDmeAndSupply(java.lang.String) return java.sql.ResultSet';
FUNCTION getPtDmeAndSupply(PTDMEANDSUPPLY_KEY NUMBER) RETURN REF_CURSOR
AS LANGUAGE JAVA NAME 'PtDmeAndSupply.getPtDmeAndSupply(long) return java.sql.ResultSet';
FUNCTION getPtDmeAndSupply(PTDMEANDSUPPLY_ARRAY STRING_ARRAY) RETURN REF_CURSOR
AS LANGUAGE JAVA NAME 'PtDmeAndSupply.getPtDmeAndSupply(oracle.sql.ARRAY) return java.sql.ResultSet';
FUNCTION insertPtDmeAndSupply(PT_DME_AND_SUPPLY_VAR PT_DME_AND_SUPPLY_T) RETURN NUMBER
AS LANGUAGE JAVA NAME 'PtDmeAndSupply.insertPtDmeAndSupply(oracle.sql.STRUCT) return long';
FUNCTION updatePtDmeAndSupply(PT_DME_AND_SUPPLY_VAR PT_DME_AND_SUPPLY_T) RETURN NUMBER
AS LANGUAGE JAVA NAME 'PtDmeAndSupply.updatePtDmeAndSupply(oracle.sql.STRUCT) return long';
FUNCTION deletePtDmeAndSupply(PT_DME_AND_SUPPLY_KEY NUMBER) RETURN NUMBER
AS LANGUAGE JAVA NAME 'PtDmeAndSupply.deletePtDmeAndSupply(long) return long';
FUNCTION getPtDx(BE_ID VARCHAR2) RETURN REF_CURSOR
AS LANGUAGE JAVA NAME 'PtDx.getPtDx(java.lang.String) return java.sql.ResultSet';
FUNCTION getPtDx(PTDX_KEY NUMBER) RETURN REF_CURSOR
AS LANGUAGE JAVA NAME 'PtDx.getPtDx(long) return java.sql.ResultSet';
FUNCTION getPtDx(PTDX_ARRAY STRING_ARRAY) RETURN REF_CURSOR
AS LANGUAGE JAVA NAME 'PtDx.getPtDx(oracle.sql.ARRAY) return java.sql.ResultSet';
FUNCTION insertPtDx(PT_DX_VAR PT_DX_T) RETURN NUMBER
AS LANGUAGE JAVA NAME 'PtDx.insertPtDx(oracle.sql.STRUCT) return long';
FUNCTION updatePtDx(PT_DX_VAR PT_DX_T) RETURN NUMBER
AS LANGUAGE JAVA NAME 'PtDx.updatePtDx(oracle.sql.STRUCT) return long';
FUNCTION deletePtDx(PT_DX_KEY NUMBER) RETURN NUMBER
AS LANGUAGE JAVA NAME 'PtDx.deletePtDx(long) return long';
FUNCTION getPtEnv(BE_ID VARCHAR2) RETURN REF_CURSOR
AS LANGUAGE JAVA NAME 'PtEnv.getPtEnv(java.lang.String) return java.sql.ResultSet';
FUNCTION getPtEnv(PTENV_KEY NUMBER) RETURN REF_CURSOR
AS LANGUAGE JAVA NAME 'PtEnv.getPtEnv(long) return java.sql.ResultSet';
FUNCTION getPtEnv(PTENV_ARRAY STRING_ARRAY) RETURN REF_CURSOR
AS LANGUAGE JAVA NAME 'PtEnv.getPtEnv(oracle.sql.ARRAY) return java.sql.ResultSet';
FUNCTION insertPtEnv(PT_ENV_VAR PT_ENV_T) RETURN NUMBER
AS LANGUAGE JAVA NAME 'PtEnv.insertPtEnv(oracle.sql.STRUCT) return long';
FUNCTION updatePtEnv(PT_ENV_VAR PT_ENV_T) RETURN NUMBER
AS LANGUAGE JAVA NAME 'PtEnv.updatePtEnv(oracle.sql.STRUCT) return long';
FUNCTION deletePtEnv(PT_ENV_KEY NUMBER) RETURN NUMBER
AS LANGUAGE JAVA NAME 'PtEnv.deletePtEnv(long) return long';
FUNCTION getPtHcp(BE_ID VARCHAR2) RETURN REF_CURSOR
AS LANGUAGE JAVA NAME 'PtHcp.getPtHcp(java.lang.String) return java.sql.ResultSet';
FUNCTION getPtHcp(PTHCP_KEY NUMBER) RETURN REF_CURSOR
AS LANGUAGE JAVA NAME 'PtHcp.getPtHcp(long) return java.sql.ResultSet';
FUNCTION getPtHcp(PTHCP_ARRAY STRING_ARRAY) RETURN REF_CURSOR
AS LANGUAGE JAVA NAME 'PtHcp.getPtHcp(oracle.sql.ARRAY) return java.sql.ResultSet';
FUNCTION insertPtHcp(PT_HCP_VAR PT_HCP_T) RETURN NUMBER
AS LANGUAGE JAVA NAME 'PtHcp.insertPtHcp(oracle.sql.STRUCT) return long';
FUNCTION updatePtHcp(PT_HCP_VAR PT_HCP_T) RETURN NUMBER
AS LANGUAGE JAVA NAME 'PtHcp.updatePtHcp(oracle.sql.STRUCT) return long';
FUNCTION deletePtHcp(PT_HCP_KEY NUMBER) RETURN NUMBER
AS LANGUAGE JAVA NAME 'PtHcp.deletePtHcp(long) return long';
FUNCTION getPtIdXwalk(BE_ID VARCHAR2) RETURN REF_CURSOR
AS LANGUAGE JAVA NAME 'PtIdXwalk.getPtIdXwalk(java.lang.String) return java.sql.ResultSet';
FUNCTION getPtIdXwalk(PTIDXWALK_KEY NUMBER) RETURN REF_CURSOR
AS LANGUAGE JAVA NAME 'PtIdXwalk.getPtIdXwalk(long) return java.sql.ResultSet';
FUNCTION getPtIdXwalk(PTIDXWALK_ARRAY STRING_ARRAY) RETURN REF_CURSOR
AS LANGUAGE JAVA NAME 'PtIdXwalk.getPtIdXwalk(oracle.sql.ARRAY) return java.sql.ResultSet';
FUNCTION insertPtIdXwalk(PT_ID_XWALK_VAR PT_ID_XWALK_T) RETURN NUMBER
AS LANGUAGE JAVA NAME 'PtIdXwalk.insertPtIdXwalk(oracle.sql.STRUCT) return long';
FUNCTION updatePtIdXwalk(PT_ID_XWALK_VAR PT_ID_XWALK_T) RETURN NUMBER
AS LANGUAGE JAVA NAME 'PtIdXwalk.updatePtIdXwalk(oracle.sql.STRUCT) return long';
FUNCTION deletePtIdXwalk(PT_ID_XWALK_KEY NUMBER) RETURN NUMBER
AS LANGUAGE JAVA NAME 'PtIdXwalk.deletePtIdXwalk(long) return long';
FUNCTION getPtMed(BE_ID VARCHAR2) RETURN REF_CURSOR
AS LANGUAGE JAVA NAME 'PtMed.getPtMed(java.lang.String) return java.sql.ResultSet';
FUNCTION getPtMed(PTMED_KEY NUMBER) RETURN REF_CURSOR
AS LANGUAGE JAVA NAME 'PtMed.getPtMed(long) return java.sql.ResultSet';
FUNCTION getPtMed(PTMED_ARRAY STRING_ARRAY) RETURN REF_CURSOR
AS LANGUAGE JAVA NAME 'PtMed.getPtMed(oracle.sql.ARRAY) return java.sql.ResultSet';
FUNCTION insertPtMed(PT_MED_VAR PT_MED_T) RETURN NUMBER
AS LANGUAGE JAVA NAME 'PtMed.insertPtMed(oracle.sql.STRUCT) return long';
FUNCTION updatePtMed(PT_MED_VAR PT_MED_T) RETURN NUMBER
AS LANGUAGE JAVA NAME 'PtMed.updatePtMed(oracle.sql.STRUCT) return long';
FUNCTION deletePtMed(PT_MED_KEY NUMBER) RETURN NUMBER
AS LANGUAGE JAVA NAME 'PtMed.deletePtMed(long) return long';
FUNCTION getPtMedclHist(BE_ID VARCHAR2) RETURN REF_CURSOR
AS LANGUAGE JAVA NAME 'PtMedclHist.getPtMedclHist(java.lang.String) return java.sql.ResultSet';
FUNCTION getPtMedclHist(PTMEDCLHIST_KEY NUMBER) RETURN REF_CURSOR
AS LANGUAGE JAVA NAME 'PtMedclHist.getPtMedclHist(long) return java.sql.ResultSet';
FUNCTION getPtMedclHist(PTMEDCLHIST_ARRAY STRING_ARRAY) RETURN REF_CURSOR
AS LANGUAGE JAVA NAME 'PtMedclHist.getPtMedclHist(oracle.sql.ARRAY) return java.sql.ResultSet';
FUNCTION insertPtMedclHist(PT_MEDCL_HIST_VAR PT_MEDCL_HIST_T) RETURN NUMBER
AS LANGUAGE JAVA NAME 'PtMedclHist.insertPtMedclHist(oracle.sql.STRUCT) return long';
FUNCTION updatePtMedclHist(PT_MEDCL_HIST_VAR PT_MEDCL_HIST_T) RETURN NUMBER
AS LANGUAGE JAVA NAME 'PtMedclHist.updatePtMedclHist(oracle.sql.STRUCT) return long';
FUNCTION deletePtMedclHist(PT_MEDCL_HIST_KEY NUMBER) RETURN NUMBER
AS LANGUAGE JAVA NAME 'PtMedclHist.deletePtMedclHist(long) return long';
FUNCTION getPtNote(BE_ID VARCHAR2) RETURN REF_CURSOR
AS LANGUAGE JAVA NAME 'PtNote.getPtNote(java.lang.String) return java.sql.ResultSet';
FUNCTION getPtNote(PTNOTE_KEY NUMBER) RETURN REF_CURSOR
AS LANGUAGE JAVA NAME 'PtNote.getPtNote(long) return java.sql.ResultSet';
FUNCTION getPtNote(PTNOTE_ARRAY STRING_ARRAY) RETURN REF_CURSOR
AS LANGUAGE JAVA NAME 'PtNote.getPtNote(oracle.sql.ARRAY) return java.sql.ResultSet';
FUNCTION insertPtNote(PT_NOTE_VAR PT_NOTE_T) RETURN NUMBER
AS LANGUAGE JAVA NAME 'PtNote.insertPtNote(oracle.sql.STRUCT) return long';
FUNCTION updatePtNote(PT_NOTE_VAR PT_NOTE_T) RETURN NUMBER
AS LANGUAGE JAVA NAME 'PtNote.updatePtNote(oracle.sql.STRUCT) return long';
FUNCTION deletePtNote(PT_NOTE_KEY NUMBER) RETURN NUMBER
AS LANGUAGE JAVA NAME 'PtNote.deletePtNote(long) return long';
FUNCTION getPtNutrRqmt(BE_ID VARCHAR2) RETURN REF_CURSOR
AS LANGUAGE JAVA NAME 'PtNutrRqmt.getPtNutrRqmt(java.lang.String) return java.sql.ResultSet';
FUNCTION getPtNutrRqmt(PTNUTRRQMT_KEY NUMBER) RETURN REF_CURSOR
AS LANGUAGE JAVA NAME 'PtNutrRqmt.getPtNutrRqmt(long) return java.sql.ResultSet';
FUNCTION getPtNutrRqmt(PTNUTRRQMT_ARRAY STRING_ARRAY) RETURN REF_CURSOR
AS LANGUAGE JAVA NAME 'PtNutrRqmt.getPtNutrRqmt(oracle.sql.ARRAY) return java.sql.ResultSet';
FUNCTION insertPtNutrRqmt(PT_NUTR_RQMT_VAR PT_NUTR_RQMT_T) RETURN NUMBER
AS LANGUAGE JAVA NAME 'PtNutrRqmt.insertPtNutrRqmt(oracle.sql.STRUCT) return long';
FUNCTION updatePtNutrRqmt(PT_NUTR_RQMT_VAR PT_NUTR_RQMT_T) RETURN NUMBER
AS LANGUAGE JAVA NAME 'PtNutrRqmt.updatePtNutrRqmt(oracle.sql.STRUCT) return long';
FUNCTION deletePtNutrRqmt(PT_NUTR_RQMT_KEY NUMBER) RETURN NUMBER
AS LANGUAGE JAVA NAME 'PtNutrRqmt.deletePtNutrRqmt(long) return long';
FUNCTION getPtPayer(BE_ID VARCHAR2) RETURN REF_CURSOR
AS LANGUAGE JAVA NAME 'PtPayer.getPtPayer(java.lang.String) return java.sql.ResultSet';
FUNCTION getPtPayer(PTPAYER_KEY NUMBER) RETURN REF_CURSOR
AS LANGUAGE JAVA NAME 'PtPayer.getPtPayer(long) return java.sql.ResultSet';
FUNCTION getPtPayer(PTPAYER_ARRAY STRING_ARRAY) RETURN REF_CURSOR
AS LANGUAGE JAVA NAME 'PtPayer.getPtPayer(oracle.sql.ARRAY) return java.sql.ResultSet';
FUNCTION insertPtPayer(PT_PAYER_VAR PT_PAYER_T) RETURN NUMBER
AS LANGUAGE JAVA NAME 'PtPayer.insertPtPayer(oracle.sql.STRUCT) return long';
FUNCTION updatePtPayer(PT_PAYER_VAR PT_PAYER_T) RETURN NUMBER
AS LANGUAGE JAVA NAME 'PtPayer.updatePtPayer(oracle.sql.STRUCT) return long';
FUNCTION deletePtPayer(PT_PAYER_KEY NUMBER) RETURN NUMBER
AS LANGUAGE JAVA NAME 'PtPayer.deletePtPayer(long) return long';
FUNCTION getPtPayerIns(BE_ID VARCHAR2) RETURN REF_CURSOR
AS LANGUAGE JAVA NAME 'PtPayerIns.getPtPayerIns(java.lang.String) return java.sql.ResultSet';
FUNCTION getPtPayerIns(PTPAYERINS_KEY NUMBER) RETURN REF_CURSOR
AS LANGUAGE JAVA NAME 'PtPayerIns.getPtPayerIns(long) return java.sql.ResultSet';
FUNCTION getPtPayerIns(PTPAYERINS_ARRAY STRING_ARRAY) RETURN REF_CURSOR
AS LANGUAGE JAVA NAME 'PtPayerIns.getPtPayerIns(oracle.sql.ARRAY) return java.sql.ResultSet';
FUNCTION insertPtPayerIns(PT_PAYER_INS_VAR PT_PAYER_INS_T) RETURN NUMBER
AS LANGUAGE JAVA NAME 'PtPayerIns.insertPtPayerIns(oracle.sql.STRUCT) return long';
FUNCTION updatePtPayerIns(PT_PAYER_INS_VAR PT_PAYER_INS_T) RETURN NUMBER
AS LANGUAGE JAVA NAME 'PtPayerIns.updatePtPayerIns(oracle.sql.STRUCT) return long';
FUNCTION deletePtPayerIns(PT_PAYER_INS_KEY NUMBER) RETURN NUMBER
AS LANGUAGE JAVA NAME 'PtPayerIns.deletePtPayerIns(long) return long';
FUNCTION getPtPayerLmt(BE_ID VARCHAR2) RETURN REF_CURSOR
AS LANGUAGE JAVA NAME 'PtPayerLmt.getPtPayerLmt(java.lang.String) return java.sql.ResultSet';
FUNCTION getPtPayerLmt(PTPAYERLMT_KEY NUMBER) RETURN REF_CURSOR
AS LANGUAGE JAVA NAME 'PtPayerLmt.getPtPayerLmt(long) return java.sql.ResultSet';
FUNCTION getPtPayerLmt(PTPAYERLMT_ARRAY STRING_ARRAY) RETURN REF_CURSOR
AS LANGUAGE JAVA NAME 'PtPayerLmt.getPtPayerLmt(oracle.sql.ARRAY) return java.sql.ResultSet';
FUNCTION insertPtPayerLmt(PT_PAYER_LMT_VAR PT_PAYER_LMT_T) RETURN NUMBER
AS LANGUAGE JAVA NAME 'PtPayerLmt.insertPtPayerLmt(oracle.sql.STRUCT) return long';
FUNCTION updatePtPayerLmt(PT_PAYER_LMT_VAR PT_PAYER_LMT_T) RETURN NUMBER
AS LANGUAGE JAVA NAME 'PtPayerLmt.updatePtPayerLmt(oracle.sql.STRUCT) return long';
FUNCTION deletePtPayerLmt(PT_PAYER_LMT_KEY NUMBER) RETURN NUMBER
AS LANGUAGE JAVA NAME 'PtPayerLmt.deletePtPayerLmt(long) return long';
FUNCTION getPtPet RETURN REF_CURSOR
AS LANGUAGE JAVA NAME 'PtPet.getPtPet() return java.sql.ResultSet';
FUNCTION getPtPet(PTPET_KEY NUMBER) RETURN REF_CURSOR
AS LANGUAGE JAVA NAME 'PtPet.getPtPet(long) return java.sql.ResultSet';
FUNCTION getPtPet(PTPET_ARRAY STRING_ARRAY) RETURN REF_CURSOR
AS LANGUAGE JAVA NAME 'PtPet.getPtPet(oracle.sql.ARRAY) return java.sql.ResultSet';
FUNCTION insertPtPet(PT_PET_VAR PT_PET_T) RETURN NUMBER
AS LANGUAGE JAVA NAME 'PtPet.insertPtPet(oracle.sql.STRUCT) return long';
FUNCTION updatePtPet(PT_PET_VAR PT_PET_T) RETURN NUMBER
AS LANGUAGE JAVA NAME 'PtPet.updatePtPet(oracle.sql.STRUCT) return long';
FUNCTION deletePtPet(PT_PET_KEY NUMBER) RETURN NUMBER
AS LANGUAGE JAVA NAME 'PtPet.deletePtPet(long) return long';
FUNCTION getPtRate(BE_ID VARCHAR2) RETURN REF_CURSOR
AS LANGUAGE JAVA NAME 'PtRate.getPtRate(java.lang.String) return java.sql.ResultSet';
FUNCTION getPtRate(PTRATE_KEY NUMBER) RETURN REF_CURSOR
AS LANGUAGE JAVA NAME 'PtRate.getPtRate(long) return java.sql.ResultSet';
FUNCTION getPtRate(PTRATE_ARRAY STRING_ARRAY) RETURN REF_CURSOR
AS LANGUAGE JAVA NAME 'PtRate.getPtRate(oracle.sql.ARRAY) return java.sql.ResultSet';
FUNCTION insertPtRate(PT_RATE_VAR PT_RATE_T) RETURN NUMBER
AS LANGUAGE JAVA NAME 'PtRate.insertPtRate(oracle.sql.STRUCT) return long';
FUNCTION updatePtRate(PT_RATE_VAR PT_RATE_T) RETURN NUMBER
AS LANGUAGE JAVA NAME 'PtRate.updatePtRate(oracle.sql.STRUCT) return long';
FUNCTION deletePtRate(PT_RATE_KEY NUMBER) RETURN NUMBER
AS LANGUAGE JAVA NAME 'PtRate.deletePtRate(long) return long';
FUNCTION getPtRqmt(BE_ID VARCHAR2) RETURN REF_CURSOR
AS LANGUAGE JAVA NAME 'PtRqmt.getPtRqmt(java.lang.String) return java.sql.ResultSet';
FUNCTION getPtRqmt(PTRQMT_KEY NUMBER) RETURN REF_CURSOR
AS LANGUAGE JAVA NAME 'PtRqmt.getPtRqmt(long) return java.sql.ResultSet';
FUNCTION getPtRqmt(PTRQMT_ARRAY STRING_ARRAY) RETURN REF_CURSOR
AS LANGUAGE JAVA NAME 'PtRqmt.getPtRqmt(oracle.sql.ARRAY) return java.sql.ResultSet';
FUNCTION insertPtRqmt(PT_RQMT_VAR PT_RQMT_T) RETURN NUMBER
AS LANGUAGE JAVA NAME 'PtRqmt.insertPtRqmt(oracle.sql.STRUCT) return long';
FUNCTION updatePtRqmt(PT_RQMT_VAR PT_RQMT_T) RETURN NUMBER
AS LANGUAGE JAVA NAME 'PtRqmt.updatePtRqmt(oracle.sql.STRUCT) return long';
FUNCTION deletePtRqmt(PT_RQMT_KEY NUMBER) RETURN NUMBER
AS LANGUAGE JAVA NAME 'PtRqmt.deletePtRqmt(long) return long';
FUNCTION getPtSftyMeasure(BE_ID VARCHAR2) RETURN REF_CURSOR
AS LANGUAGE JAVA NAME 'PtSftyMeasure.getPtSftyMeasure(java.lang.String) return java.sql.ResultSet';
FUNCTION getPtSftyMeasure(PTSFTYMEASURE_KEY NUMBER) RETURN REF_CURSOR
AS LANGUAGE JAVA NAME 'PtSftyMeasure.getPtSftyMeasure(long) return java.sql.ResultSet';
FUNCTION getPtSftyMeasure(PTSFTYMEASURE_ARRAY STRING_ARRAY) RETURN REF_CURSOR
AS LANGUAGE JAVA NAME 'PtSftyMeasure.getPtSftyMeasure(oracle.sql.ARRAY) return java.sql.ResultSet';
FUNCTION insertPtSftyMeasure(PT_SFTY_MEASURE_VAR PT_SFTY_MEASURE_T) RETURN NUMBER
AS LANGUAGE JAVA NAME 'PtSftyMeasure.insertPtSftyMeasure(oracle.sql.STRUCT) return long';
FUNCTION updatePtSftyMeasure(PT_SFTY_MEASURE_VAR PT_SFTY_MEASURE_T) RETURN NUMBER
AS LANGUAGE JAVA NAME 'PtSftyMeasure.updatePtSftyMeasure(oracle.sql.STRUCT) return long';
FUNCTION deletePtSftyMeasure(PT_SFTY_MEASURE_KEY NUMBER) RETURN NUMBER
AS LANGUAGE JAVA NAME 'PtSftyMeasure.deletePtSftyMeasure(long) return long';
FUNCTION getPtStructBrrDet RETURN REF_CURSOR
AS LANGUAGE JAVA NAME 'PtStructBrrDet.getPtStructBrrDet() return java.sql.ResultSet';
FUNCTION getPtStructBrrDet(PTSTRUCTBRRDET_KEY NUMBER) RETURN REF_CURSOR
AS LANGUAGE JAVA NAME 'PtStructBrrDet.getPtStructBrrDet(long) return java.sql.ResultSet';
FUNCTION getPtStructBrrDet(PTSTRUCTBRRDET_ARRAY STRING_ARRAY) RETURN REF_CURSOR
AS LANGUAGE JAVA NAME 'PtStructBrrDet.getPtStructBrrDet(oracle.sql.ARRAY) return java.sql.ResultSet';
FUNCTION insertPtStructBrrDet(PT_STRUCT_BRR_DET_VAR PT_STRUCT_BRR_DET_T) RETURN NUMBER
AS LANGUAGE JAVA NAME 'PtStructBrrDet.insertPtStructBrrDet(oracle.sql.STRUCT) return long';
FUNCTION updatePtStructBrrDet(PT_STRUCT_BRR_DET_VAR PT_STRUCT_BRR_DET_T) RETURN NUMBER
AS LANGUAGE JAVA NAME 'PtStructBrrDet.updatePtStructBrrDet(oracle.sql.STRUCT) return long';
FUNCTION deletePtStructBrrDet(PT_STRUCT_BRR_DET_KEY NUMBER) RETURN NUMBER
AS LANGUAGE JAVA NAME 'PtStructBrrDet.deletePtStructBrrDet(long) return long';

END PKG_PATIENT;
/

