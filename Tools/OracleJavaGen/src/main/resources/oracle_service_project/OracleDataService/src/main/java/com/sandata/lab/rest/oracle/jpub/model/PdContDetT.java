package com.sandata.lab.data.model.jpub.model;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.STRUCT;
import oracle.jpub.runtime.MutableStruct;

public class PdContDetT implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "COREDATA.PD_CONT_DET_T";
  public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

  protected MutableStruct _struct;

  protected static int[] _sqlType =  { 2,91,91,91,91,12,12,2,12,2,12,12,12,12,12,12,12,12,12,12,12,1,12,12,12,12,12,12,12,12,12,12,12,12 };
  protected static ORADataFactory[] _factory = new ORADataFactory[34];
  protected static final PdContDetT _PdContDetTFactory = new PdContDetT();

  public static ORADataFactory getORADataFactory()
  { return _PdContDetTFactory; }
  /* constructors */
  protected void _init_struct(boolean init)
  { if (init) _struct = new MutableStruct(new Object[34], _sqlType, _factory); }
  public PdContDetT()
  { _init_struct(true); }
  public PdContDetT(java.math.BigDecimal pdContDetSk, java.sql.Timestamp recCreateTmstp, java.sql.Timestamp recUpdateTmstp, java.sql.Timestamp recEffTmstp, java.sql.Timestamp recTermTmstp, String recCreatedBy, String recUpdatedBy, java.math.BigDecimal currRecInd, String changeReasonMemo, java.math.BigDecimal changeVersionId, String ptId, String beId, String pdContDetTypName, String contRel, String pdFirstName, String pdMiddleName, String pdLastName, String pdSuffixName, String pdAddr1, String pdAddr2, String pdCity, String pdState, String pdPstlCode, String pdHomePhone, String pdWorkPhone, String pdMobilePhone, String pdPhoneOther, String pdPhoneOther1, String pdFax, String pdEmail, String pdEmailOther1, String pdEmailOther2, String pdEmailOther3, String pdZip4) throws SQLException
  { _init_struct(true);
    setPdContDetSk(pdContDetSk);
    setRecCreateTmstp(recCreateTmstp);
    setRecUpdateTmstp(recUpdateTmstp);
    setRecEffTmstp(recEffTmstp);
    setRecTermTmstp(recTermTmstp);
    setRecCreatedBy(recCreatedBy);
    setRecUpdatedBy(recUpdatedBy);
    setCurrRecInd(currRecInd);
    setChangeReasonMemo(changeReasonMemo);
    setChangeVersionId(changeVersionId);
    setPtId(ptId);
    setBeId(beId);
    setPdContDetTypName(pdContDetTypName);
    setContRel(contRel);
    setPdFirstName(pdFirstName);
    setPdMiddleName(pdMiddleName);
    setPdLastName(pdLastName);
    setPdSuffixName(pdSuffixName);
    setPdAddr1(pdAddr1);
    setPdAddr2(pdAddr2);
    setPdCity(pdCity);
    setPdState(pdState);
    setPdPstlCode(pdPstlCode);
    setPdHomePhone(pdHomePhone);
    setPdWorkPhone(pdWorkPhone);
    setPdMobilePhone(pdMobilePhone);
    setPdPhoneOther(pdPhoneOther);
    setPdPhoneOther1(pdPhoneOther1);
    setPdFax(pdFax);
    setPdEmail(pdEmail);
    setPdEmailOther1(pdEmailOther1);
    setPdEmailOther2(pdEmailOther2);
    setPdEmailOther3(pdEmailOther3);
    setPdZip4(pdZip4);
  }

  /* ORAData interface */
  public Datum toDatum(Connection c) throws SQLException
  {
    return _struct.toDatum(c, _SQL_NAME);
  }


  /* ORADataFactory interface */
  public ORAData create(Datum d, int sqlType) throws SQLException
  { return create(null, d, sqlType); }
  protected ORAData create(PdContDetT o, Datum d, int sqlType) throws SQLException
  {
    if (d == null) return null; 
    if (o == null) o = new PdContDetT();
    o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
    return o;
  }
  /* accessor methods */
  public java.math.BigDecimal getPdContDetSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(0); }

  public void setPdContDetSk(java.math.BigDecimal pdContDetSk) throws SQLException
  { _struct.setAttribute(0, pdContDetSk); }


  public java.sql.Timestamp getRecCreateTmstp() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(1); }

  public void setRecCreateTmstp(java.sql.Timestamp recCreateTmstp) throws SQLException
  { _struct.setAttribute(1, recCreateTmstp); }


  public java.sql.Timestamp getRecUpdateTmstp() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(2); }

  public void setRecUpdateTmstp(java.sql.Timestamp recUpdateTmstp) throws SQLException
  { _struct.setAttribute(2, recUpdateTmstp); }


  public java.sql.Timestamp getRecEffTmstp() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(3); }

  public void setRecEffTmstp(java.sql.Timestamp recEffTmstp) throws SQLException
  { _struct.setAttribute(3, recEffTmstp); }


  public java.sql.Timestamp getRecTermTmstp() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(4); }

  public void setRecTermTmstp(java.sql.Timestamp recTermTmstp) throws SQLException
  { _struct.setAttribute(4, recTermTmstp); }


  public String getRecCreatedBy() throws SQLException
  { return (String) _struct.getAttribute(5); }

  public void setRecCreatedBy(String recCreatedBy) throws SQLException
  { _struct.setAttribute(5, recCreatedBy); }


  public String getRecUpdatedBy() throws SQLException
  { return (String) _struct.getAttribute(6); }

  public void setRecUpdatedBy(String recUpdatedBy) throws SQLException
  { _struct.setAttribute(6, recUpdatedBy); }


  public java.math.BigDecimal getCurrRecInd() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(7); }

  public void setCurrRecInd(java.math.BigDecimal currRecInd) throws SQLException
  { _struct.setAttribute(7, currRecInd); }


  public String getChangeReasonMemo() throws SQLException
  { return (String) _struct.getAttribute(8); }

  public void setChangeReasonMemo(String changeReasonMemo) throws SQLException
  { _struct.setAttribute(8, changeReasonMemo); }


  public java.math.BigDecimal getChangeVersionId() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(9); }

  public void setChangeVersionId(java.math.BigDecimal changeVersionId) throws SQLException
  { _struct.setAttribute(9, changeVersionId); }


  public String getPtId() throws SQLException
  { return (String) _struct.getAttribute(10); }

  public void setPtId(String ptId) throws SQLException
  { _struct.setAttribute(10, ptId); }


  public String getBeId() throws SQLException
  { return (String) _struct.getAttribute(11); }

  public void setBeId(String beId) throws SQLException
  { _struct.setAttribute(11, beId); }


  public String getPdContDetTypName() throws SQLException
  { return (String) _struct.getAttribute(12); }

  public void setPdContDetTypName(String pdContDetTypName) throws SQLException
  { _struct.setAttribute(12, pdContDetTypName); }


  public String getContRel() throws SQLException
  { return (String) _struct.getAttribute(13); }

  public void setContRel(String contRel) throws SQLException
  { _struct.setAttribute(13, contRel); }


  public String getPdFirstName() throws SQLException
  { return (String) _struct.getAttribute(14); }

  public void setPdFirstName(String pdFirstName) throws SQLException
  { _struct.setAttribute(14, pdFirstName); }


  public String getPdMiddleName() throws SQLException
  { return (String) _struct.getAttribute(15); }

  public void setPdMiddleName(String pdMiddleName) throws SQLException
  { _struct.setAttribute(15, pdMiddleName); }


  public String getPdLastName() throws SQLException
  { return (String) _struct.getAttribute(16); }

  public void setPdLastName(String pdLastName) throws SQLException
  { _struct.setAttribute(16, pdLastName); }


  public String getPdSuffixName() throws SQLException
  { return (String) _struct.getAttribute(17); }

  public void setPdSuffixName(String pdSuffixName) throws SQLException
  { _struct.setAttribute(17, pdSuffixName); }


  public String getPdAddr1() throws SQLException
  { return (String) _struct.getAttribute(18); }

  public void setPdAddr1(String pdAddr1) throws SQLException
  { _struct.setAttribute(18, pdAddr1); }


  public String getPdAddr2() throws SQLException
  { return (String) _struct.getAttribute(19); }

  public void setPdAddr2(String pdAddr2) throws SQLException
  { _struct.setAttribute(19, pdAddr2); }


  public String getPdCity() throws SQLException
  { return (String) _struct.getAttribute(20); }

  public void setPdCity(String pdCity) throws SQLException
  { _struct.setAttribute(20, pdCity); }


  public String getPdState() throws SQLException
  { return (String) _struct.getAttribute(21); }

  public void setPdState(String pdState) throws SQLException
  { _struct.setAttribute(21, pdState); }


  public String getPdPstlCode() throws SQLException
  { return (String) _struct.getAttribute(22); }

  public void setPdPstlCode(String pdPstlCode) throws SQLException
  { _struct.setAttribute(22, pdPstlCode); }


  public String getPdHomePhone() throws SQLException
  { return (String) _struct.getAttribute(23); }

  public void setPdHomePhone(String pdHomePhone) throws SQLException
  { _struct.setAttribute(23, pdHomePhone); }


  public String getPdWorkPhone() throws SQLException
  { return (String) _struct.getAttribute(24); }

  public void setPdWorkPhone(String pdWorkPhone) throws SQLException
  { _struct.setAttribute(24, pdWorkPhone); }


  public String getPdMobilePhone() throws SQLException
  { return (String) _struct.getAttribute(25); }

  public void setPdMobilePhone(String pdMobilePhone) throws SQLException
  { _struct.setAttribute(25, pdMobilePhone); }


  public String getPdPhoneOther() throws SQLException
  { return (String) _struct.getAttribute(26); }

  public void setPdPhoneOther(String pdPhoneOther) throws SQLException
  { _struct.setAttribute(26, pdPhoneOther); }


  public String getPdPhoneOther1() throws SQLException
  { return (String) _struct.getAttribute(27); }

  public void setPdPhoneOther1(String pdPhoneOther1) throws SQLException
  { _struct.setAttribute(27, pdPhoneOther1); }


  public String getPdFax() throws SQLException
  { return (String) _struct.getAttribute(28); }

  public void setPdFax(String pdFax) throws SQLException
  { _struct.setAttribute(28, pdFax); }


  public String getPdEmail() throws SQLException
  { return (String) _struct.getAttribute(29); }

  public void setPdEmail(String pdEmail) throws SQLException
  { _struct.setAttribute(29, pdEmail); }


  public String getPdEmailOther1() throws SQLException
  { return (String) _struct.getAttribute(30); }

  public void setPdEmailOther1(String pdEmailOther1) throws SQLException
  { _struct.setAttribute(30, pdEmailOther1); }


  public String getPdEmailOther2() throws SQLException
  { return (String) _struct.getAttribute(31); }

  public void setPdEmailOther2(String pdEmailOther2) throws SQLException
  { _struct.setAttribute(31, pdEmailOther2); }


  public String getPdEmailOther3() throws SQLException
  { return (String) _struct.getAttribute(32); }

  public void setPdEmailOther3(String pdEmailOther3) throws SQLException
  { _struct.setAttribute(32, pdEmailOther3); }


  public String getPdZip4() throws SQLException
  { return (String) _struct.getAttribute(33); }

  public void setPdZip4(String pdZip4) throws SQLException
  { _struct.setAttribute(33, pdZip4); }

}
