SELECT * FROM EXCP_LKUP
  WHERE APP_MOD_NAME='VISIT VERIFICATION'
    AND (CURR_REC_IND=1 AND TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD')='9999-12-31');