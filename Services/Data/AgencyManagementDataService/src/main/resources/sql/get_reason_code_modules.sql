	SELECT *
	FROM COREDATA.BE_CHANGE_REASON_LKUP
	WHERE BE_ID                                = 1
	AND BE_CHANGE_REASON_LKUP_PAR_SK          IS NULL
	AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31'
	AND CURR_REC_IND                           = 1)
	ORDER BY BE_CHANGE_REASON_LKUP_SK ASC