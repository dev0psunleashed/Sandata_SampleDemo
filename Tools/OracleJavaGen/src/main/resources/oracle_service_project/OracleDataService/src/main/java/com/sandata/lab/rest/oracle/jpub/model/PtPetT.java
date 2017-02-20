package com.sandata.lab.data.model.jpub.model;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.STRUCT;
import oracle.jpub.runtime.MutableStruct;

public class PtPetT implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "COREDATA.PT_PET_T";
  public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

  protected MutableStruct _struct;

  protected static int[] _sqlType =  { 2,12,2,91,91,91,91,12,12,2,12,2,12,12,2 };
  protected static ORADataFactory[] _factory = new ORADataFactory[15];
  protected static final PtPetT _PtPetTFactory = new PtPetT();

  public static ORADataFactory getORADataFactory()
  { return _PtPetTFactory; }
  /* constructors */
  protected void _init_struct(boolean init)
  { if (init) _struct = new MutableStruct(new Object[15], _sqlType, _factory); }
  public PtPetT()
  { _init_struct(true); }
  public PtPetT(java.math.BigDecimal ptPetSk, String ptPetsId, java.math.BigDecimal ptEnvSk, java.sql.Timestamp recCreateTmstp, java.sql.Timestamp recUpdateTmstp, java.sql.Timestamp recEffTmstp, java.sql.Timestamp recTermTmstp, String recCreatedBy, String recUpdatedBy, java.math.BigDecimal changeVersionId, String changeReasonMemo, java.math.BigDecimal currRecInd, String petTyp, String sizeOfPet, java.math.BigDecimal aggressivePetInd) throws SQLException
  { _init_struct(true);
    setPtPetSk(ptPetSk);
    setPtPetsId(ptPetsId);
    setPtEnvSk(ptEnvSk);
    setRecCreateTmstp(recCreateTmstp);
    setRecUpdateTmstp(recUpdateTmstp);
    setRecEffTmstp(recEffTmstp);
    setRecTermTmstp(recTermTmstp);
    setRecCreatedBy(recCreatedBy);
    setRecUpdatedBy(recUpdatedBy);
    setChangeVersionId(changeVersionId);
    setChangeReasonMemo(changeReasonMemo);
    setCurrRecInd(currRecInd);
    setPetTyp(petTyp);
    setSizeOfPet(sizeOfPet);
    setAggressivePetInd(aggressivePetInd);
  }

  /* ORAData interface */
  public Datum toDatum(Connection c) throws SQLException
  {
    return _struct.toDatum(c, _SQL_NAME);
  }


  /* ORADataFactory interface */
  public ORAData create(Datum d, int sqlType) throws SQLException
  { return create(null, d, sqlType); }
  protected ORAData create(PtPetT o, Datum d, int sqlType) throws SQLException
  {
    if (d == null) return null; 
    if (o == null) o = new PtPetT();
    o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
    return o;
  }
  /* accessor methods */
  public java.math.BigDecimal getPtPetSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(0); }

  public void setPtPetSk(java.math.BigDecimal ptPetSk) throws SQLException
  { _struct.setAttribute(0, ptPetSk); }


  public String getPtPetsId() throws SQLException
  { return (String) _struct.getAttribute(1); }

  public void setPtPetsId(String ptPetsId) throws SQLException
  { _struct.setAttribute(1, ptPetsId); }


  public java.math.BigDecimal getPtEnvSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(2); }

  public void setPtEnvSk(java.math.BigDecimal ptEnvSk) throws SQLException
  { _struct.setAttribute(2, ptEnvSk); }


  public java.sql.Timestamp getRecCreateTmstp() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(3); }

  public void setRecCreateTmstp(java.sql.Timestamp recCreateTmstp) throws SQLException
  { _struct.setAttribute(3, recCreateTmstp); }


  public java.sql.Timestamp getRecUpdateTmstp() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(4); }

  public void setRecUpdateTmstp(java.sql.Timestamp recUpdateTmstp) throws SQLException
  { _struct.setAttribute(4, recUpdateTmstp); }


  public java.sql.Timestamp getRecEffTmstp() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(5); }

  public void setRecEffTmstp(java.sql.Timestamp recEffTmstp) throws SQLException
  { _struct.setAttribute(5, recEffTmstp); }


  public java.sql.Timestamp getRecTermTmstp() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(6); }

  public void setRecTermTmstp(java.sql.Timestamp recTermTmstp) throws SQLException
  { _struct.setAttribute(6, recTermTmstp); }


  public String getRecCreatedBy() throws SQLException
  { return (String) _struct.getAttribute(7); }

  public void setRecCreatedBy(String recCreatedBy) throws SQLException
  { _struct.setAttribute(7, recCreatedBy); }


  public String getRecUpdatedBy() throws SQLException
  { return (String) _struct.getAttribute(8); }

  public void setRecUpdatedBy(String recUpdatedBy) throws SQLException
  { _struct.setAttribute(8, recUpdatedBy); }


  public java.math.BigDecimal getChangeVersionId() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(9); }

  public void setChangeVersionId(java.math.BigDecimal changeVersionId) throws SQLException
  { _struct.setAttribute(9, changeVersionId); }


  public String getChangeReasonMemo() throws SQLException
  { return (String) _struct.getAttribute(10); }

  public void setChangeReasonMemo(String changeReasonMemo) throws SQLException
  { _struct.setAttribute(10, changeReasonMemo); }


  public java.math.BigDecimal getCurrRecInd() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(11); }

  public void setCurrRecInd(java.math.BigDecimal currRecInd) throws SQLException
  { _struct.setAttribute(11, currRecInd); }


  public String getPetTyp() throws SQLException
  { return (String) _struct.getAttribute(12); }

  public void setPetTyp(String petTyp) throws SQLException
  { _struct.setAttribute(12, petTyp); }


  public String getSizeOfPet() throws SQLException
  { return (String) _struct.getAttribute(13); }

  public void setSizeOfPet(String sizeOfPet) throws SQLException
  { _struct.setAttribute(13, sizeOfPet); }


  public java.math.BigDecimal getAggressivePetInd() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(14); }

  public void setAggressivePetInd(java.math.BigDecimal aggressivePetInd) throws SQLException
  { _struct.setAttribute(14, aggressivePetInd); }

}
