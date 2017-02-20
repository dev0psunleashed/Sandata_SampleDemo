CREATE OR REPLACE PACKAGE PKG_EXCEPTIONS IS

  rec_ver_mismatch_code CONSTANT NUMBER := -20001;
  rec_already_deleted_code CONSTANT NUMBER := -20002;
  be_lob_exists_code CONSTANT NUMBER := -20003;
  no_agency_root_rec_code CONSTANT NUMBER := -20004; -- Agency does not exist in the BE_REL table as the root

  PROCEDURE VERSION_MISMATCH_ERR(l_msg VARCHAR2);
  PROCEDURE REC_ALREADY_DEL_ERR(l_msg VARCHAR2);
  PROCEDURE BE_LOB_EXISTS_ERR(l_msg VARCHAR2);
  PROCEDURE NO_AGENCY_ROOT_REC_ERR(l_msg VARCHAR2);

END PKG_EXCEPTIONS;
/

CREATE OR REPLACE PACKAGE BODY PKG_EXCEPTIONS IS

  PROCEDURE VERSION_MISMATCH_ERR(l_msg VARCHAR2)
  IS

    l_error_msg CLOB;

    rec_ver_mismatch_exc EXCEPTION;
    PRAGMA EXCEPTION_INIT( rec_ver_mismatch_exc, -20001 );

    BEGIN

      l_error_msg := 'VERSION_MISMATCH_ERR: EXCEPTION: ' || l_msg;
      dbms_output.put_line(l_error_msg);
      raise_application_error( rec_ver_mismatch_code, l_error_msg );

    END VERSION_MISMATCH_ERR;

  PROCEDURE REC_ALREADY_DEL_ERR(l_msg VARCHAR2)
  IS

    l_error_msg CLOB;

    rec_already_deleted_exc EXCEPTION;
    PRAGMA EXCEPTION_INIT( rec_already_deleted_exc, -20002 );

    BEGIN

      l_error_msg := 'REC_ALREADY_DEL_ERR: EXCEPTION: ' || l_msg;
      dbms_output.put_line(l_error_msg);
      raise_application_error( rec_already_deleted_code, l_error_msg );

    END REC_ALREADY_DEL_ERR;

  PROCEDURE BE_LOB_EXISTS_ERR(l_msg VARCHAR2)
  IS

    l_error_msg CLOB;

    be_lob_exists_exc EXCEPTION;
    PRAGMA EXCEPTION_INIT( be_lob_exists_exc, -20003 );

    BEGIN

      l_error_msg := 'BE_LOB_EXISTS_ERR: EXCEPTION: ' || l_msg;
      dbms_output.put_line(l_error_msg);
      raise_application_error( be_lob_exists_code, l_error_msg );

    END BE_LOB_EXISTS_ERR;

  PROCEDURE NO_AGENCY_ROOT_REC_ERR(l_msg VARCHAR2)
  IS

    l_error_msg CLOB;

    no_agency_root_rec_exc EXCEPTION;
    PRAGMA EXCEPTION_INIT( no_agency_root_rec_exc, -20004 );

    BEGIN

      l_error_msg := 'NO_AGENCY_ROOT_REC_ERR: EXCEPTION: ' || l_msg;
      dbms_output.put_line(l_error_msg);
      raise_application_error( no_agency_root_rec_code, l_error_msg );

    END NO_AGENCY_ROOT_REC_ERR;

END PKG_EXCEPTIONS;
/
