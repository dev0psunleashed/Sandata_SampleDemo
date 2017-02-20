SET SERVEROUTPUT ON

DECLARE

ethnicity_typ     COREDATA.BE_ETHNICITY_LST_TYP;
insert_result     NUMBER;

BEGIN

  ethnicity_typ := COREDATA.BE_ETHNICITY_LST_TYP(

    null,
    TO_DATE('2016-05-25', 'YYYY-MM-DD'),
    TO_DATE('2016-05-25', 'YYYY-MM-DD'),
    TO_DATE('2016-05-25', 'YYYY-MM-DD'),
    TO_DATE('9999-12-31', 'YYYY-MM-DD'),
    'PL-SQL Test',
    null,
    'INSERT Test',
    1,
    0,
    '1',
    '1732-7',
    'YUROK'
  );

  insert_result := COREDATA.PKG_AM.insertBeEthnicityLst(ethnicity_typ);

END;
/
