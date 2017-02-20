
 /* 
 INSERT INTO EXCP_LKUP 
  (EXCP_LKUP_SK ,
      REC_CREATE_TMSTP ,
      REC_UPDATE_TMSTP ,
      CHANGE_VERSION_ID ,
      EXCP_TYPE_LKUP_SK ,
      EXCP_NAME ,
      EXCP_DESC,
      EXCP_NON_FIXABLE_IND ,
      EXCP_ACK_IND     
    )
    VALUES
    (1002 ,
      TO_DATE('2016-02-25 08:00:00', 'YYYY-MM-DD HH24:MI:SS'),
      TO_DATE('2016-02-25 08:00:00', 'YYYY-MM-DD HH24:MI:SS'),
      1 ,
      2 ,
      'Unknown Patient' , 
      'The number that the Staff member is calling from does not match to a patient phone number within the system.' ,
      1 ,
      0);

INSERT INTO EXCP_LKUP 
  (EXCP_LKUP_SK ,
      REC_CREATE_TMSTP ,
      REC_UPDATE_TMSTP ,
      CHANGE_VERSION_ID ,
      EXCP_TYPE_LKUP_SK ,
      EXCP_NAME ,
      EXCP_DESC,
      EXCP_NON_FIXABLE_IND ,
      EXCP_ACK_IND     
    )
    VALUES
    (1003 ,
      TO_DATE('2016-02-25 08:00:00', 'YYYY-MM-DD HH24:MI:SS'),
      TO_DATE('2016-02-25 08:00:00', 'YYYY-MM-DD HH24:MI:SS'),
      1 ,
      2 ,
      'Unknown Staff' , 
      'When a staff member enters an ID that is not recognized by the system.' ,
      1 ,
      0);
      
INSERT INTO EXCP_LKUP 
  (EXCP_LKUP_SK ,
      REC_CREATE_TMSTP ,
      REC_UPDATE_TMSTP ,
      CHANGE_VERSION_ID ,
      EXCP_TYPE_LKUP_SK ,
      EXCP_NAME ,
      EXCP_DESC,
      EXCP_NON_FIXABLE_IND ,
      EXCP_ACK_IND     
    )
    VALUES
    (1004 ,
      TO_DATE('2016-02-25 08:00:00', 'YYYY-MM-DD HH24:MI:SS'),
      TO_DATE('2016-02-25 08:00:00', 'YYYY-MM-DD HH24:MI:SS'),
      1 ,
      2 ,
      'Visit (Scheduled visit) with no calls' , 
      'The system must identify when a scheduled visit did not have any calls made for it (No in or Out).' ,
      1 ,
      0);
      
      INSERT INTO EXCP_LKUP 
  (EXCP_LKUP_SK ,
      REC_CREATE_TMSTP ,
      REC_UPDATE_TMSTP ,
      CHANGE_VERSION_ID ,
      EXCP_TYPE_LKUP_SK ,
      EXCP_NAME ,
      EXCP_DESC,
      EXCP_NON_FIXABLE_IND ,
      EXCP_ACK_IND     
    )
    VALUES
    (1005 ,
      TO_DATE('2016-02-25 08:00:00', 'YYYY-MM-DD HH24:MI:SS'),
      TO_DATE('2016-02-25 08:00:00', 'YYYY-MM-DD HH24:MI:SS'),
      1 ,
      2 ,
      'Visit with no in-call' , 
      'This is an exception until a user matches a call to the schedule start time' ,
      1 ,
      0);

      INSERT INTO EXCP_LKUP 
  (EXCP_LKUP_SK ,
      REC_CREATE_TMSTP ,
      REC_UPDATE_TMSTP ,
      CHANGE_VERSION_ID ,
      EXCP_TYPE_LKUP_SK ,
      EXCP_NAME ,
      EXCP_DESC,
      EXCP_NON_FIXABLE_IND ,
      EXCP_ACK_IND     
    )
    VALUES
    (1006 ,
      TO_DATE('2016-02-25 08:00:00', 'YYYY-MM-DD HH24:MI:SS'),
      TO_DATE('2016-02-25 08:00:00', 'YYYY-MM-DD HH24:MI:SS'),
      1 ,
      2 ,
      'Visit with no out-call' , 
      'This is an exception until a user matches a call to the schedule end time.' ,
      1 ,
      0);

      INSERT INTO EXCP_LKUP 
  (EXCP_LKUP_SK ,
      REC_CREATE_TMSTP ,
      REC_UPDATE_TMSTP ,
      CHANGE_VERSION_ID ,      
      EXCP_TYPE_LKUP_SK ,
      EXCP_NAME ,
      EXCP_DESC,
      EXCP_NON_FIXABLE_IND ,
      EXCP_ACK_IND     
    )
    VALUES
    (1007 ,
      TO_DATE('2016-02-25 08:00:00', 'YYYY-MM-DD HH24:MI:SS'),
      TO_DATE('2016-02-25 08:00:00', 'YYYY-MM-DD HH24:MI:SS'),
      1 ,
      2 ,
      'Unscheduled Visits' , 
      'A staff member calls from a patients house during a time that is not scheduled.' ,
      1 ,
      0);

      INSERT INTO EXCP_LKUP 
  (EXCP_LKUP_SK ,
      REC_CREATE_TMSTP ,
      REC_UPDATE_TMSTP ,
      CHANGE_VERSION_ID ,
      EXCP_TYPE_LKUP_SK ,
      EXCP_NAME ,
      EXCP_DESC,
      EXCP_NON_FIXABLE_IND ,
      EXCP_ACK_IND     
    )
    VALUES
    (1008 ,
      TO_DATE('2016-02-25 08:00:00', 'YYYY-MM-DD HH24:MI:SS'),
      TO_DATE('2016-02-25 08:00:00', 'YYYY-MM-DD HH24:MI:SS'),
      1 ,
      2 ,
      'Actual Hours More Than Scheduled Hours' , 
      'The amount of hours totalled between the call-in time and the call-out time is greater than the amount of hous that were scheduled.' ,
      1 ,
      0);

      INSERT INTO EXCP_LKUP 
  (EXCP_LKUP_SK ,
      REC_CREATE_TMSTP ,
      REC_UPDATE_TMSTP ,
      CHANGE_VERSION_ID ,
      EXCP_TYPE_LKUP_SK ,
      EXCP_NAME ,
      EXCP_DESC,
      EXCP_NON_FIXABLE_IND ,
      EXCP_ACK_IND     
    )
    VALUES
    (1009 ,
      TO_DATE('2016-02-25 08:00:00', 'YYYY-MM-DD HH24:MI:SS'),
      TO_DATE('2016-02-25 08:00:00', 'YYYY-MM-DD HH24:MI:SS'),
      1 ,
      2 ,
      'Missing Tasks' , 
      'Configurable. If the staff member was required to put in tasks during the visit and did not. Any task ID not typed in (via keypad) during the call must trigger this exception.' ,
      1 ,
      0);

      INSERT INTO EXCP_LKUP 
  (EXCP_LKUP_SK ,
      REC_CREATE_TMSTP ,
      REC_UPDATE_TMSTP ,
      CHANGE_VERSION_ID ,
      EXCP_TYPE_LKUP_SK ,
      EXCP_NAME ,
      EXCP_DESC,
      EXCP_NON_FIXABLE_IND ,
      EXCP_ACK_IND     
    )
    VALUES
    (1010 ,
      TO_DATE('2016-02-25 08:00:00', 'YYYY-MM-DD HH24:MI:SS'),
      TO_DATE('2016-02-25 08:00:00', 'YYYY-MM-DD HH24:MI:SS'),
      1,
      2 ,
      'Missing Critical Tasks' , 
      'Configurable. If the staff member was required to put in a Critical task during the visit and did not.' ,
      1 ,
      0);

      INSERT INTO EXCP_LKUP 
  (EXCP_LKUP_SK ,
      REC_CREATE_TMSTP ,
      REC_UPDATE_TMSTP ,
      CHANGE_VERSION_ID ,
      EXCP_TYPE_LKUP_SK ,
      EXCP_NAME ,
      EXCP_DESC,
      EXCP_NON_FIXABLE_IND ,
      EXCP_ACK_IND     
    )
    VALUES
    (1013 ,
      TO_DATE('2016-02-25 08:00:00', 'YYYY-MM-DD HH24:MI:SS'),
      TO_DATE('2016-02-25 08:00:00', 'YYYY-MM-DD HH24:MI:SS'),
      1 ,
      2 ,
      'Late InCall' , 
      'Late InCall.  Configurable.  Based on agency threshold.' ,
      0 ,
      1);

      INSERT INTO EXCP_LKUP 
  (EXCP_LKUP_SK ,
      REC_CREATE_TMSTP ,
      REC_UPDATE_TMSTP ,
      CHANGE_VERSION_ID ,
      EXCP_TYPE_LKUP_SK ,
      EXCP_NAME ,
      EXCP_DESC,
      EXCP_NON_FIXABLE_IND ,
      EXCP_ACK_IND     
    )
    VALUES
    (1014 ,
      TO_DATE('2016-02-25 08:00:00', 'YYYY-MM-DD HH24:MI:SS'),
      TO_DATE('2016-02-25 08:00:00', 'YYYY-MM-DD HH24:MI:SS'),
      1 ,
      2 ,
      'Early OutCall' , 
      'Early OutCall.  Configurable.  Based on agency threshold.' ,
      0 ,
      1);

      INSERT INTO EXCP_LKUP 
  (EXCP_LKUP_SK ,
      REC_CREATE_TMSTP ,
      REC_UPDATE_TMSTP ,
      CHANGE_VERSION_ID ,
      EXCP_TYPE_LKUP_SK ,
      EXCP_NAME ,
      EXCP_DESC,
      EXCP_NON_FIXABLE_IND ,
      EXCP_ACK_IND     
    )
    VALUES
    (1015 ,
      TO_DATE('2016-02-25 08:00:00', 'YYYY-MM-DD HH24:MI:SS'),
      TO_DATE('2016-02-25 08:00:00', 'YYYY-MM-DD HH24:MI:SS'),
      1 ,
      2 ,
      'Short Visit' , 
      'Short Visit.  Configurable.  Based on agency threshold.' ,
      0 ,
      1);

      INSERT INTO EXCP_LKUP 
  (EXCP_LKUP_SK ,
      REC_CREATE_TMSTP ,
      REC_UPDATE_TMSTP ,
      CHANGE_VERSION_ID ,
      EXCP_TYPE_LKUP_SK ,
      EXCP_NAME ,
      EXCP_DESC,
      EXCP_NON_FIXABLE_IND ,
      EXCP_ACK_IND     
    )
    VALUES
    (1016 ,
      TO_DATE('2016-02-25 08:00:00', 'YYYY-MM-DD HH24:MI:SS'),
      TO_DATE('2016-02-25 08:00:00', 'YYYY-MM-DD HH24:MI:SS'),
      1 ,
      2 ,
      'No Show Exception' , 
      'This excpetion must trigger an alert to the Agency to indicate a staff member did not show up.' ,
      1 ,
      0);

      INSERT INTO EXCP_LKUP 
  (EXCP_LKUP_SK ,
      REC_CREATE_TMSTP ,
      REC_UPDATE_TMSTP ,
      CHANGE_VERSION_ID ,
      EXCP_TYPE_LKUP_SK ,
      EXCP_NAME ,
      EXCP_DESC,
      EXCP_NON_FIXABLE_IND ,
      EXCP_ACK_IND     
    )
    VALUES
    (1022 ,
      TO_DATE('2016-02-25 08:00:00', 'YYYY-MM-DD HH24:MI:SS'),
      TO_DATE('2016-02-25 08:00:00', 'YYYY-MM-DD HH24:MI:SS'),
      1 ,
      2 ,
      'Missing Reason Codes' , 
      'Any change made withing VV must have a reason code. If a reason code is not entered, an exception must appear on the Front-end.' ,
      1 ,
      0);

      INSERT INTO EXCP_LKUP 
  (EXCP_LKUP_SK ,
      REC_CREATE_TMSTP ,
      REC_UPDATE_TMSTP ,
      CHANGE_VERSION_ID ,
      EXCP_TYPE_LKUP_SK ,
      EXCP_NAME ,
      EXCP_DESC,
      EXCP_NON_FIXABLE_IND ,
      EXCP_ACK_IND     
    )
    VALUES
    (1024 ,
      TO_DATE('2016-02-25 08:00:00', 'YYYY-MM-DD HH24:MI:SS'),
      TO_DATE('2016-02-25 08:00:00', 'YYYY-MM-DD HH24:MI:SS'),
      1 ,
      2 ,
      'Unmatched Payroll And Scheduled Hours' , 
      'The system must display an exception when the payroll hours differ from the scheduled hours.' ,
      1 ,
      0);
*/