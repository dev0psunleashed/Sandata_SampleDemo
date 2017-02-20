package com.sandata.lab.security.auth.impl;

import com.sandata.lab.common.utils.data.DataMapper;
import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.common.utils.log.SandataLogger;
import com.sandata.lab.common.utils.string.StringUtil;
import com.sandata.lab.data.model.dl.annotation.Mapping;
import com.sandata.lab.data.model.dl.annotation.OracleMetadata;
import com.sandata.lab.data.model.dl.model.*;
import com.sandata.lab.data.model.dl.model.extended.ApplicationRoleExt;
import com.sandata.lab.data.model.dl.model.extended.ApplicationUserExt;
import com.sandata.lab.data.model.response.Response;
import com.sandata.lab.security.auth.api.DataService;
import com.sandata.lab.security.auth.model.jpub.TmpAppRoleTab;
import com.sandata.lab.security.auth.model.jpub.TmpAppRoleTyp;
import com.sandata.lab.security.auth.services.oracle.CoreDataService;
import com.sandata.lab.security.auth.services.oracle.MetaDataService;
import com.sandata.lab.security.auth.services.oracle.enums.Columns;
import com.sandata.lab.security.auth.utils.log.AuthenticationLogger;
import com.sandata.lab.security.auth.utils.oracle.DataTransfomer;
import org.apache.commons.lang3.StringUtils;
import org.dozer.DozerBeanMapper;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static java.lang.String.format;

/**
 * Date: 9/1/15
 * Time: 8:47 PM
 */
@SuppressWarnings("unchecked")
public class RestDataService implements DataService {

    private final static String _PACKAGE_NAME = "PKG_APP";

    private MetaDataService metaDataService;

    private CoreDataService coreDataService;

    private DataTransfomer dataTransformer;

    public void setDataTransformer(DataTransfomer dataTransformer) {
        this.dataTransformer = dataTransformer;
    }

    public void setMetaDataService(MetaDataService metaDataService) {
        this.metaDataService = metaDataService;
    }

    public void setCoreDataService(CoreDataService coreDataService) {
        this.coreDataService = coreDataService;
    }

    /**
     * Insert the application user into metadata
     *
     * @param applicationUserExt
     * @return
     */
    public long insertUser(ApplicationUserExt applicationUserExt) {

        DozerBeanMapper mapper = new DozerBeanMapper(Arrays.asList(new String[]{"dozer/userExtMapping.xml"}));
        ApplicationUser applicationUser = mapper.map(applicationUserExt, ApplicationUser.class);

        Date date = new Date();

        String tenantId = metaDataService.getTenantSKByBE(applicationUserExt.getBusinessEntityID());
        setAppTenantSk(applicationUser, new BigInteger(tenantId));

        applicationUser.setRecordCreateTimestamp(date);
        applicationUser.setRecordUpdateTimestamp(date);


        return insert(_PACKAGE_NAME, "insertAppUser", applicationUser);
    }

    /**
     * Get the tenant SK from the metadata crosswalk by business entity id
     *
     * @param businessEntityID
     */
    public String getTenantSKByBE(String businessEntityID) {

        return metaDataService.getTenantSKByBE(businessEntityID);

    }

    public void setAppTenantSk(ApplicationUser applicationUser, BigInteger tenantSk) {

        if (applicationUser.getApplicationUserRole() != null) {
            for (ApplicationUserRole applicationUserRole : applicationUser.getApplicationUserRole()) {
                applicationUserRole.setApplicationTenantSK(tenantSk);
            }
        }
    }

    /**
     * Search for a user in metadata by params
     *
     * @param bsnEnt
     * @param lastName
     * @param firstName
     * @param userName
     * @param roleName
     * @param page
     * @param pageSize
     * @param sortOn
     * @param sortDirection
     * @return
     * @throws Exception
     */
    public Response findUser(String bsnEnt, String lastName, String firstName, String userName, String roleName, int page,
                             int pageSize, String sortOn, String sortDirection) throws Exception {


        String sortColumn = Columns.META_USER_LAST_NAME.getValue();

        if (sortOn.equalsIgnoreCase("fn") || sortOn.equalsIgnoreCase("UserFirstName")) {
            sortColumn = Columns.META_USER_FIRST_NAME.getValue();
        } else if (sortOn.equalsIgnoreCase("un") || sortOn.equalsIgnoreCase("UserName")) {
            sortColumn = Columns.META_USER_NAME.getValue();
        } else if (sortOn.equalsIgnoreCase("rn") || sortOn.equalsIgnoreCase("RoleName")) {
            sortColumn = Columns.META_ROLE_NAME.getValue();
        } else if (sortOn.equalsIgnoreCase("ln") || sortOn.equalsIgnoreCase("UserLastName")){
            sortColumn = Columns.META_USER_LAST_NAME.getValue();
        }

        //All the above sortable fields are VARCHAR type, so it's valid to UPPER to make the sorting consistently.
        sortColumn = format(" UPPER(%s) ", sortColumn);

        Response response = metaDataService.findUser(bsnEnt, lastName, firstName, userName, roleName, page,
                pageSize, sortColumn, sortDirection);

        response.setOrderByColumn(sortOn.toUpperCase());
        response.setOrderByDirection(sortDirection.toUpperCase());

        return response;
    }

    public long deleteUserFromTenant(String businessEntityID, long userSk) throws Exception {
        String tenantId = metaDataService.getTenantSKByBE(businessEntityID);

        long result = metaDataService.deleteUserUserAppRolesForTenant(new BigDecimal(String.valueOf(userSk)), new BigDecimal(tenantId));

        return result;
    }

    public ApplicationUserExt getApplicationUserByUserName(String userName, String businessEntityID) {

        String tenantSk = metaDataService.getTenantSKByBE(businessEntityID);

        return metaDataService.getApplicationUserByUserName(userName, new BigDecimal(tenantSk));
    }

    public boolean isApplicationUserExists(String userName, String businessEntityID) {

        String tenantSk = metaDataService.getTenantSKByBE(businessEntityID);

        return metaDataService.applicationUserExists(userName, new BigDecimal(tenantSk));
    }

    public boolean isApplicationUserExists(String userName) {


        return metaDataService.applicationUserExists(userName);
    }

    public ApplicationUserExt getApplicationUserByUserName(String userName) {

        return metaDataService.getApplicationUserByUserName(userName);
    }

    public ApplicationSystemKeyConfiguration getApplicationSystemKeyConfiguration(String keyName) {

        return metaDataService.getAppSysConfigByKey(keyName);
    }

    public long updateUser(ApplicationUserExt applicationUserExt) throws Exception {

        SandataLogger logger = AuthenticationLogger.CreateLogger();

        try {

            DozerBeanMapper mapper = new DozerBeanMapper(Arrays.asList(new String[]{"dozer/userExtMapping.xml"}));
            ApplicationUser applicationUser = mapper.map(applicationUserExt, ApplicationUser.class);

            Date date = new Date();

            String tenantId = metaDataService.getTenantSKByBE(applicationUserExt.getBusinessEntityID());


            if (applicationUser.getApplicationUserSK() == null) {

                ApplicationUser applicationUserRetrieved = metaDataService.getApplicationUserByUserName(applicationUser.getUserName(), new BigDecimal(tenantId));
                applicationUser.setApplicationUserSK(applicationUserRetrieved.getApplicationUserSK());
                applicationUser.setRecordCreateTimestamp(applicationUserRetrieved.getRecordCreateTimestamp());
            } else {
                if (applicationUser.getApplicationUserRole() != null) {
                    for (ApplicationUserRole applicationUserRole : applicationUser.getApplicationUserRole()) {

                        if (applicationUserRole.getApplicationUserRoleSK() == null || applicationUserRole.getApplicationUserRoleSK().longValue() == 0) {

                            //Delete any existing roles from tenant for this user because this is a new user role
                            this.deleteUserFromTenant(applicationUserExt.getBusinessEntityID(), applicationUser.getApplicationUserSK().longValue());
                            break;
                        }
                    }
                }
            }


            setAppTenantSk(applicationUser, new BigInteger(tenantId));

            applicationUser.setRecordUpdateTimestamp(date);

            return update(_PACKAGE_NAME, "updateAppUser", applicationUser);
        }catch (Exception e){

            e.printStackTrace();
            throw new SandataRuntimeException(e.getMessage(), e);
        }
    }

    public long insertRoleUpdate(ApplicationRoleExt applicationRoleExt) throws Exception {
        String tenantId = metaDataService.getTenantSKByBE(applicationRoleExt.getBusinessEntityID());

        if (!StringUtil.IsNullOrEmpty(tenantId)) {
            applicationRoleExt.setApplicationTenantSK(new BigInteger(tenantId));
        }

        TmpAppRoleTyp tmpAppRoleTyp = dataTransformer.applicationRoletoTmpAppRoleTyp(applicationRoleExt);

        return metaDataService.insertUpdateRole(tmpAppRoleTyp);
    }

    public long deleteRole(long appRoleSk) {
        return metaDataService.deleteRole(appRoleSk);
    }


    public ApplicationRoleExt getRole(long appRoleSk) throws Exception {
        ApplicationRoleExt applicationRoleExt = new ApplicationRoleExt();

        TmpAppRoleTyp tmpAppRoleTyp = metaDataService.getRole(BigInteger.valueOf(appRoleSk));

        if (tmpAppRoleTyp == null) {
            return null;
        }

        applicationRoleExt = dataTransformer.tmpAppRoleTypToApplicationRole(tmpAppRoleTyp);

        return applicationRoleExt;
    }


    public List<ApplicationModule> getApplicationModules() {
        return metaDataService.getApplicationModules();
    }

    public List<ApplicationRoleExt> getRoles(String businessEntityID) throws Exception {
        List<ApplicationRoleExt> applicationRoleExts = new ArrayList<>();

        TmpAppRoleTab tmpAppRoleTab = metaDataService.getRoles(businessEntityID);

        for (TmpAppRoleTyp tmpAppRoleTyp : tmpAppRoleTab.getArray()) {

            ApplicationRoleExt applicationRoleExt = dataTransformer.tmpAppRoleTypToApplicationRole(tmpAppRoleTyp);
            applicationRoleExt.setBusinessEntityID(businessEntityID);
            applicationRoleExts.add(applicationRoleExt);
        }

        return applicationRoleExts;

    }

    public List<ApplicationSecureGroup> getApplicationSecureGroups() {

        return metaDataService.getApplicationSecureGroups();
    }

    /**
     * Update ApplicationUserSetting by USER_GUID and KEY
     *
     * @param userSetting
     * @return
     * @throws SandataRuntimeException
     */
    public long insertUserSetting(ApplicationUserSetting userSetting) throws SandataRuntimeException {
        return metaDataService.insertUpdateUserSetting(userSetting, true);
    }

    /**
     * Update ApplicationUserSetting by USER_GUID and KEY
     *
     * @param userSetting
     * @return
     * @throws SandataRuntimeException
     */
    public long updateUserSetting(ApplicationUserSetting userSetting) throws SandataRuntimeException {
        return metaDataService.insertUpdateUserSetting(userSetting, false);
    }

    /**
     * Update ApplicationUserSetting by USER_GUID and KEY
     *
     * @param userSetting
     * @return
     * @throws SandataRuntimeException
     */
    public long deleteUserSetting(ApplicationUserSetting userSetting) throws SandataRuntimeException {
        return metaDataService.deleteUserSetting(userSetting);
    }

    /**
     * Find ApplicationUserSetting by USER_GUID
     *
     * @param userGUID
     * @return List<ApplicationUserSetting>
     * @throws SandataRuntimeException
     */
    public List<ApplicationUserSetting> getUserSettingByUserGUID(final String userGUID) throws SandataRuntimeException {
        return metaDataService.getUserSettingByUserGUID(userGUID);
    }

    /**
     * Find ApplicationUserSetting by USER_GUID and KEY
     *
     * @param userGUID
     * @param key
     * @return List<ApplicationUserSetting>
     * @throws SandataRuntimeException
     */
    public List<ApplicationUserSetting> getUserSettingByUserGUIDAndKey(final String userGUID, final String key) throws SandataRuntimeException {
        return metaDataService.getUserSettingByUserGUIDAndKey(userGUID, key);
    }

    /**
     * Find ApplicationUserSettings by USER_GUID and KEY
     *
     * @param userGUID
     * @param key
     * @return List<ApplicationUserSetting>
     * @throws SandataRuntimeException
     */
    public List<ApplicationUserKeyConfiguration> getUserAppConfigsByUserGUIDAndKey(final String userGUID, final String key) throws SandataRuntimeException {
        return metaDataService.getUserSettingsByUserGUIDAndKey(userGUID, key);
    }

    public ApplicationUserExt getApplicationUser(long userSk) {


        return metaDataService.getApplicationUserBySk(new BigDecimal(userSk));

    }


    @Override
    public void get(String packageName, String methodName, Object data, String className, BigDecimal sequenceKey, String[] params) throws SandataRuntimeException {

        SandataLogger logger = AuthenticationLogger.CreateLogger();

        try {
            Object result;

            if (sequenceKey != null) {
                result = metaDataService.executeGet(
                        packageName,
                        methodName,
                        className,
                        sequenceKey
                );

                ArrayList result2 = (ArrayList<Object>) result;

                if (result2 != null && result2.size() > 0) {
                    result = result2.get(0);

                }

            } else {
                result = metaDataService.executeGet(
                        packageName,
                        methodName,
                        className,
                        params
                );

            }

            data = result;
        } catch (Exception e) {

            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(errMsg, e);

        } finally {
            logger.stop();
        }
    }


    public long delete(String packageName, String methodName, long sequenceKey) throws SandataRuntimeException {

        SandataLogger logger = AuthenticationLogger.CreateLogger();

        try {

            long result = metaDataService.execute(
                    packageName,
                    methodName,
                    sequenceKey
            );

            logger.stop();

            return result;
        } catch (Exception e) {

            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(errMsg, e);
        }
    }

    @Override
    public long update(String packageName, String methodName, Object data) throws SandataRuntimeException {

        SandataLogger logger = AuthenticationLogger.CreateLogger();

        Connection connection = null;

        //String className = "com.sandata.lab.data.model.dl.model." + methodParts[3];

        try {


            connection = metaDataService.getOracleConnection();
            connection.setAutoCommit(false);

            long returnVal = executeRecursive(connection, data, false /* UPDATE */, -999);
            if (returnVal > 0) {

                connection.commit();
            } else {
                throw new SandataRuntimeException("Update was not successful!");
            }

            return returnVal;
        } catch (Exception e) {

            if (connection != null) {

                try {
                    connection.rollback();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(errMsg, e);
        } finally {

            metaDataService.closeOracleConnection(connection);

            logger.stop();
        }
    }

    @Override
    public long insert(String packageName, String methodName, Object data) throws SandataRuntimeException {

        SandataLogger logger = AuthenticationLogger.CreateLogger();

        Connection connection = null;

        try {

            connection = metaDataService.getOracleConnection();
            connection.setAutoCommit(false);
            long returnVal = executeRecursive(connection, data, true, -999);
            if (returnVal > 0) {

                connection.commit();
            } else {
                throw new SandataRuntimeException("Insert was not successful!");
            }

            return returnVal;
        } catch (Exception e) {

            if (connection != null) {

                try {
                    connection.rollback();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(errMsg, e);
        } finally {

            metaDataService.closeOracleConnection(connection);

            logger.stop();
        }
    }

    public boolean isDuplicateUserSetting(ApplicationUserKeyConfiguration applicationUserSetting) throws SandataRuntimeException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            connection = metaDataService.getOracleConnection();
            connection.setAutoCommit(true);

            String sql = "SELECT 1 AS RESULT FROM DUAL WHERE EXISTS (SELECT 1 FROM APP_USER_KEY_CONF " +
                            "  WHERE USER_GUID = ? AND KEY_NAME = ?)";

            preparedStatement = connection.prepareStatement(sql);

            int index = 1;
            preparedStatement.setString(index++, applicationUserSetting.getUserGloballyUniqueIdentifier());
            preparedStatement.setString(index++, applicationUserSetting.getKeyName());

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String result = resultSet.getString("RESULT");
                if (StringUtils.isNotEmpty(result)
                        && result.equals("1")) {
                    return true;
                }
            }

            return false;

        } catch (Exception e) {

            // Rollback
            if (connection != null) {

                try {
                    connection.rollback();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            throw new SandataRuntimeException(String.format("%s: %s",
                            e.getClass().getName(), e.getMessage()), e);

        } finally {

            // Close the ResultSet
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            // Close the statement
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            metaDataService.closeOracleConnection(connection);
        }
    }

    private void setSk(final Object jpubType, long sequenceKey, String setSkMethodName) throws Exception {

        if (sequenceKey <= 0) {
            return;
        }

        try {

            Method method = jpubType.getClass().getDeclaredMethod(setSkMethodName, BigDecimal.class);
            method.invoke(jpubType, BigDecimal.valueOf(sequenceKey));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setTimeStamp(final Object jpubType, String setTimeStampMethodName) throws Exception {


        try {

            Method method = jpubType.getClass().getDeclaredMethod(setTimeStampMethodName, java.sql.Timestamp.class);
            method.invoke(jpubType, new java.sql.Timestamp(new Date().getTime()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private BigInteger getSk(final Object data) {
        try {

            Field[] fields = data.getClass().getDeclaredFields();

            for (Field field : fields) {
                field.setAccessible(true);

                Mapping mapping = field.getAnnotation(Mapping.class);
                if (mapping != null && mapping.index() == 0) {

                    return (BigInteger) field.get(data);
                }

            }

            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * @param businessEntityID
     * @param adminStaffID
     * @return
     */
    public AdministrativeStaff getAdministrativeStaffByAdminStaffID(String businessEntityID, String adminStaffID) {

        return coreDataService.getAdminStaffByAdminStaffID(businessEntityID, adminStaffID);
    }

    public List<AdministrativeStaffRoleCrossReference> getAdministrativeStaffRoleByAdminStaffID(String businessEntityID, String adminStaffID) {

        return coreDataService.getAdminStaffRoleByAdminStaffID(businessEntityID, adminStaffID);
    }

    private long executeRecursive(final Connection connection, final Object data, boolean bShouldInsert, long returnVal)
            throws SandataRuntimeException {

        try {
            OracleMetadata oracleMetadata = DataMapper.getOracleMetadata(data);

            if (data instanceof ApplicationUserRole) {
                ((ApplicationUserRole) data).setActiveRoleIndicator(true);

            }

            Object jpubType = new DataMapper().map(data);

            BigInteger sK = getSk(data);

            //Determine whether to insert or update by getting the primary sk
            if (sK == null || sK.longValue() < 1) {

                bShouldInsert = true;
            }

            setSk(jpubType, returnVal, "setAppUserSk");

            long result = 0;

            setTimeStamp(jpubType, "setRecUpdateTmstp");


            if (bShouldInsert) {

                setTimeStamp(jpubType, "setRecCreateTmstp");

                result = metaDataService.execute(
                        connection,
                        oracleMetadata.packageName(),
                        oracleMetadata.insertMethod(),
                        jpubType
                );
            } else {

                if (data instanceof ApplicationUser) {
                    returnVal = ((ApplicationUser) data).getApplicationUserSK().longValue();
                }

                // UPDATE
                result = metaDataService.execute(
                        connection,
                        oracleMetadata.packageName(),
                        oracleMetadata.updateMethod(),
                        jpubType
                );
            }

            if (result > 0) {

                if (returnVal == -999) {
                    returnVal = result;
                }

                // Check if there are any lists that need to be inserted
                for (Field field : data.getClass().getDeclaredFields()) {

                    field.setAccessible(true);

                    Object property = field.get(data);
                    if (property != null && property instanceof List) {

                        List list = (List) property;
                        for (Object object : list) {

                            // Try to insert the object!

                            // WARNING: RECURSIVE!!!!
                            long insertResponse = executeRecursive(connection, object, bShouldInsert, returnVal);
                            if (insertResponse == -1) {
                                if (bShouldInsert) {
                                    throw new SandataRuntimeException(format("INSERT: Failed: [%s]",
                                            object.getClass().getName()));
                                } else {
                                    throw new SandataRuntimeException(format("UPDATE: Failed: [%s]",
                                            object.getClass().getName()));
                                }
                            }
                        }
                    }
                }

                // SUCCESS
                return returnVal;

            } // if (result > 0)

            // FAILED
            return -1;
        } catch (Exception e) {
            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(errMsg, e);
        }
    }
}
