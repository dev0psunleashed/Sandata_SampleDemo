package com.sandata.lab.security.auth.model.jpub;/*@lineinfo:filename=TmpAppRoleType*//*@lineinfo:user-code*//*@lineinfo:1^1*/import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.STRUCT;
import oracle.jpub.runtime.MutableStruct;


public class TmpAppRoleTyp implements ORAData, ORADataFactory {
    public static final String _SQL_NAME = "METADATA.TMP_APP_ROLE_TYP";
    public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

    /* connection management */
    protected Connection __onn = null;
    protected javax.sql.DataSource __dataSource = null;

    public TmpAppRoleTyp()
    { _init_struct(true); }

    protected ORAData create(TmpAppRoleTyp o, Datum d, int sqlType) throws SQLException
    {
        if (d == null) return null;
        if (o == null) o = new TmpAppRoleTyp();
        o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
        return o;
    }

    /* ORAData interface */
    public Datum toDatum(Connection c) throws SQLException
    {
        return _struct.toDatum(c, _SQL_NAME);
    }


    public java.util.Map getTypeMap() {
        return m_typeMap;
    }

    private static java.util.Map m_typeMap = null;


    protected MutableStruct _struct;

    protected static int[] _sqlType = {2, 2, 91, 91, 12, 2, 2003, 2003, 2003};
    protected static ORADataFactory[] _factory = new ORADataFactory[9];

    static {
        _factory[6] = TmpAppModPrivTab.getORADataFactory();
        _factory[7] = TmpBaseAppFuncTab.getORADataFactory();
        _factory[8] = TmpAppSecrGrpTab.getORADataFactory();
    }

    protected static final TmpAppRoleTyp _TmpAppRoleTypeFactory = new TmpAppRoleTyp();

    public static ORADataFactory getORADataFactory() {
        return _TmpAppRoleTypeFactory;
    }

    /* constructors */
    protected void _init_struct(boolean init) {
        if (init) _struct = new MutableStruct(new Object[9], _sqlType, _factory);
    }

    public TmpAppRoleTyp(Connection c) /*throws SQLException*/ {
        _init_struct(true);
        __onn = c;
    }

    public TmpAppRoleTyp(java.math.BigDecimal appRoleSk, java.math.BigDecimal appTenantSk, java.sql.Timestamp recCreateTmstp, java.sql.Timestamp recUpdateTmstp, String roleName, java.math.BigDecimal canImpersonateInd, TmpAppModPrivTab appModPrivList, TmpBaseAppFuncTab baseAppFuncList, TmpAppSecrGrpTab appSecrGrpList) throws SQLException {
        _init_struct(true);
        setAppRoleSk(appRoleSk);
        setAppTenantSk(appTenantSk);
        setRecCreateTmstp(recCreateTmstp);
        setRecUpdateTmstp(recUpdateTmstp);
        setRoleName(roleName);
        setCanImpersonateInd(canImpersonateInd);
        setAppModPrivList(appModPrivList);
        setBaseAppFuncList(baseAppFuncList);
        setAppSecrGrpList(appSecrGrpList);
    }

    public TmpAppRoleTyp(java.math.BigDecimal appRoleSk, java.math.BigDecimal appTenantSk, String roleName, java.math.BigDecimal canImpersonateInd, TmpAppModPrivTab appModPrivList, TmpAppSecrGrpTab appSecrGrpList) throws SQLException {
        _init_struct(true);
        setAppRoleSk(appRoleSk);
        setAppTenantSk(appTenantSk);
        setRoleName(roleName);
        setCanImpersonateInd(canImpersonateInd);
        setAppModPrivList(appModPrivList);
        setAppSecrGrpList(appSecrGrpList);
    }


    /* ORADataFactory interface */
    public ORAData create(Datum d, int sqlType) throws SQLException {
        return create(null, d, sqlType);
    }

    protected void setValueFrom(TmpAppRoleTyp o) {
        _struct = o._struct;
    }

    /* accessor methods */
    public java.math.BigDecimal getAppRoleSk() throws SQLException {
        return (java.math.BigDecimal) _struct.getAttribute(0);
    }

    public void setAppRoleSk(java.math.BigDecimal appRoleSk) throws SQLException {
        _struct.setAttribute(0, appRoleSk);
    }


    public java.math.BigDecimal getAppTenantSk() throws SQLException {
        return (java.math.BigDecimal) _struct.getAttribute(1);
    }

    public void setAppTenantSk(java.math.BigDecimal appTenantSk) throws SQLException {
        _struct.setAttribute(1, appTenantSk);
    }


    public java.sql.Timestamp getRecCreateTmstp() throws SQLException {
        return (java.sql.Timestamp) _struct.getAttribute(2);
    }

    public void setRecCreateTmstp(java.sql.Timestamp recCreateTmstp) throws SQLException {
        _struct.setAttribute(2, recCreateTmstp);
    }


    public java.sql.Timestamp getRecUpdateTmstp() throws SQLException {
        return (java.sql.Timestamp) _struct.getAttribute(3);
    }

    public void setRecUpdateTmstp(java.sql.Timestamp recUpdateTmstp) throws SQLException {
        _struct.setAttribute(3, recUpdateTmstp);
    }


    public String getRoleName() throws SQLException {
        return (String) _struct.getAttribute(4);
    }

    public void setRoleName(String roleName) throws SQLException {
        _struct.setAttribute(4, roleName);
    }


    public java.math.BigDecimal getCanImpersonateInd() throws SQLException {
        return (java.math.BigDecimal) _struct.getAttribute(5);
    }

    public void setCanImpersonateInd(java.math.BigDecimal canImpersonateInd) throws SQLException {
        _struct.setAttribute(5, canImpersonateInd);
    }


    public TmpAppModPrivTab getAppModPrivList() throws SQLException {
        return (TmpAppModPrivTab) _struct.getAttribute(6);
    }

    public void setAppModPrivList(TmpAppModPrivTab appModPrivList) throws SQLException {
        _struct.setAttribute(6, appModPrivList);
    }


    public TmpBaseAppFuncTab getBaseAppFuncList() throws SQLException {
        return (TmpBaseAppFuncTab) _struct.getAttribute(7);
    }

    public void setBaseAppFuncList(TmpBaseAppFuncTab baseAppFuncList) throws SQLException {
        _struct.setAttribute(7, baseAppFuncList);
    }


    public TmpAppSecrGrpTab getAppSecrGrpList() throws SQLException {
        return (TmpAppSecrGrpTab) _struct.getAttribute(8);
    }

    public void setAppSecrGrpList(TmpAppSecrGrpTab appSecrGrpList) throws SQLException {
        _struct.setAttribute(8, appSecrGrpList);
    }


}/*@lineinfo:generated-code*/