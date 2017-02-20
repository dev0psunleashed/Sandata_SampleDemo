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
import com.sandata.lab.security.auth.services.oracle.CoreDataService;
import com.sandata.lab.security.auth.utils.constants.Configs;
import org.apache.camel.Exchange;
import org.dozer.DozerBeanMapper;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.sandata.lab.security.auth.utils.log.AuthenticationLogger.CreateLogger;
import static java.lang.String.format;

public class UserService {

    private final static String _META_PACKAGE_NAME = "PKG_APP";

    private CoreDataService coreDataService;

    private RestDataService restDataService;

    public void setRestDataService(RestDataService restDataService) {
        this.restDataService = restDataService;
    }

    public CoreDataService getCoreDataService() {
        return coreDataService;
    }

    public void setCoreDataService(CoreDataService coreDataService) {
        this.coreDataService = coreDataService;
    }

    //region Settings

    /**
     * Handles creating new ApplicationUserSetting
     *
     * @param exchange
     * @throws Exception
     */
    public void createUserSetting(final Exchange exchange) throws Exception {

        SandataLogger logger = CreateLogger();

        try {

            ApplicationUserSetting applicationUserSetting = (ApplicationUserSetting) exchange.getIn().getBody();
            OracleMetadata metaData = DataMapper.getOracleMetadata(applicationUserSetting);

            String userGUID = applicationUserSetting.getUserGloballyUniqueIdentifier();
            String key = applicationUserSetting.getKey();

            List<ApplicationUserSetting> applicationUserSettings = getUserSettingByUserGUIDAndKey(userGUID, key);

            if (applicationUserSettings != null && applicationUserSettings.size() > 0) {

                throw new SandataRuntimeException(String.format("Key: %s already exists for user: %s", userGUID, key));
            }

            long result = restDataService.insertUserSetting(applicationUserSetting);

            exchange.getIn().setBody(result);

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new SandataRuntimeException(ex.getMessage(), ex);

        } finally {
            logger.stop();
        }
    }

    /**
     * Handles updating an existing ApplicationUserSetting
     *
     * @param exchange
     * @throws Exception
     */
    public void updateUserSetting(final Exchange exchange) throws Exception {
        SandataLogger logger = CreateLogger();

        try {
            ApplicationUserSetting applicationUserSetting = (ApplicationUserSetting) exchange.getIn().getBody();

            long result = restDataService.updateUserSetting(applicationUserSetting);

            if (result > 0) {
                logger.info(String.format("Successfully updated settings for user GUID: %s", applicationUserSetting.getUserGloballyUniqueIdentifier()));
            }

            exchange.getIn().setBody(result);

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new SandataRuntimeException(ex.getMessage(), ex);

        } finally {
            logger.stop();
        }
    }

    /**
     * Find ApplicationUserSetting by USER_GUID
     *
     * @param exchange
     */
    public void getUserSettingByUserGUID(final Exchange exchange) throws Exception {
        SandataLogger logger = CreateLogger();

        try {
            String userGUID = exchange.getIn().getHeader("user_guid", String.class);
            if (userGUID == null || userGUID.isEmpty()) {
                throw new SandataRuntimeException("'user_guid' parameter is required.");
            }

            List<ApplicationUserSetting> result = restDataService.getUserSettingByUserGUID(userGUID);

            if (result != null) {
                logger.info(String.format("Successfully retrieived %d settings for user GUID: %s", result.size(), userGUID));
            } else {

                logger.info(String.format("No settings for user GUID: %s", userGUID));
            }

            exchange.getIn().setBody(result);

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new SandataRuntimeException(ex.getMessage(), ex);

        } finally {
            logger.stop();
        }
    }

    public void updateUserAppConfig(final Exchange exchange) throws Exception {
        SandataLogger logger = CreateLogger();

        try {
            ApplicationUserKeyConfiguration applicationUserSetting = (ApplicationUserKeyConfiguration) exchange.getIn().getBody();

            long result = restDataService.update(_META_PACKAGE_NAME, "updateAppUserKeyConf", applicationUserSetting);

            if (result > 0) {
                logger.info(String.format("Successfully updated application user key config for user GUID: %s", applicationUserSetting.getUserGloballyUniqueIdentifier()));
            }

            exchange.getIn().setBody(result);

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new SandataRuntimeException(ex.getMessage(), ex);

        } finally {
            logger.stop();
        }
    }

    public void insertUserAppConfig(final Exchange exchange) throws Exception {
        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        ApplicationUserKeyConfiguration applicationUserSetting = (ApplicationUserKeyConfiguration) exchange.getIn().getBody();

        if (restDataService.isDuplicateUserSetting(applicationUserSetting)) {
            throw new SandataRuntimeException(String.format("Setting for Key [%s] already exists for UserGUID [%s]!",
                    applicationUserSetting.getKeyName(), applicationUserSetting.getUserGloballyUniqueIdentifier()));
        }

        try {

            long result = restDataService.insert(_META_PACKAGE_NAME, "insertAppUserKeyConf", applicationUserSetting);

            if (result > 0) {
                logger.info(String.format("Successfully inserted application user key config for user GUID: %s", applicationUserSetting.getUserGloballyUniqueIdentifier()));
            }

            exchange.getIn().setBody(result);

        } catch (Exception ex) {

            ex.printStackTrace();
            throw new SandataRuntimeException(ex.getMessage(), ex);

        } finally {

            logger.stop();
        }
    }

    public void deleteUserAppConfig(final Exchange exchange) throws Exception {
        SandataLogger logger = CreateLogger();

        try {
            long sk = (long) exchange.getIn().getHeader("sequence_key");

            long result = restDataService.delete(_META_PACKAGE_NAME, "deleteAppUserKeyConf", sk);

            if (result > 0) {
                logger.info(String.format("Successfully deleted application user key config with sk: %d", sk));
            }

            exchange.getIn().setBody(result);

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new SandataRuntimeException(ex.getMessage(), ex);

        } finally {
            logger.stop();
        }
    }


    /**
     * Find ApplicationUserSetting by USER_GUID and KEY
     *
     * @param exchange
     */
    public void getUserSettingByUserGUIDAndKey(final Exchange exchange) throws Exception {
        SandataLogger logger = CreateLogger();

        try {
            String userGUID = exchange.getIn().getHeader("user_guid", String.class);
            if (userGUID == null || userGUID.isEmpty()) {
                throw new SandataRuntimeException("'user_guid' parameter is required.");
            }

            String key = exchange.getIn().getHeader("key", String.class);
            if (key == null || key.isEmpty()) {
                throw new SandataRuntimeException("'key' parameter is required.");
            }

            List<ApplicationUserSetting> result = getUserSettingByUserGUIDAndKey(userGUID, key);

            if (result != null) {
                logger.info(String.format("Successfully retrieived %d settings for user GUID and key: %s",
                        result.size(), userGUID, key));
            } else {

                logger.info(String.format("No settings for user GUID and key: %s",
                        userGUID, key));
            }

            exchange.getIn().setBody(result);

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new SandataRuntimeException(ex.getMessage(), ex);

        } finally {
            logger.stop();
        }
    }

    /**
     * Find ApplicationUserSettings by USER_GUID and KEY
     *
     * @param exchange
     */
    public void getUserAppConfigsByUserGUIDAndKey(final Exchange exchange) throws Exception {
        SandataLogger logger = CreateLogger();

        try {
            String userGUID = exchange.getIn().getHeader("user_guid", String.class);
            if (userGUID == null || userGUID.isEmpty()) {
                throw new SandataRuntimeException("'user_guid' parameter is required.");
            }

            String key = exchange.getIn().getHeader("key", String.class);
            if (key == null || key.isEmpty()) {
                throw new SandataRuntimeException("'key' parameter is required.");
            }

            List<ApplicationUserKeyConfiguration> result = restDataService.getUserAppConfigsByUserGUIDAndKey(userGUID, key);

            if (result != null) {
                logger.info(String.format("Successfully retrieived %d app user key configs for user GUID and key: %s",
                        result.size(), userGUID, key));
            } else {

                logger.info(String.format("No settings for user GUID and key: %s",
                        userGUID, key));
            }

            exchange.getIn().setBody(result);

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new SandataRuntimeException(ex.getMessage(), ex);

        } finally {
            logger.stop();
        }
    }

    /**
     * Delete ApplicationUserSetting by USER_GUID
     *
     * @param exchange
     */
    public void deleteUserSettingByUserGUID(final Exchange exchange) throws Exception {
        SandataLogger logger = CreateLogger();

        try {
            String userGUID = exchange.getIn().getHeader("user_guid", String.class);
            if (userGUID == null || userGUID.isEmpty()) {
                throw new SandataRuntimeException("'user_guid' parameter is required.");
            }

            ApplicationUserSetting applicationUserSetting = new ApplicationUserSetting();
            applicationUserSetting.setUserGloballyUniqueIdentifier(userGUID);

            long result = restDataService.deleteUserSetting(applicationUserSetting);

            exchange.getIn().setBody(result);

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new SandataRuntimeException(ex.getMessage(), ex);

        } finally {
            logger.stop();
        }
    }

    /**
     * Get User setting by USER GUID and setting key
     *
     * @param userGUID
     * @param key
     * @throws Exception
     */
    public List<ApplicationUserSetting> getUserSettingByUserGUIDAndKey(String userGUID, String key) throws Exception {
        SandataLogger logger = CreateLogger();

        try {

            return restDataService.getUserSettingByUserGUIDAndKey(userGUID, key);

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new SandataRuntimeException(ex.getMessage(), ex);

        } finally {
            logger.stop();
        }
    }
    //endregion Settings


    //region User

    public long deleteStaffAdmin(ApplicationUserExt applicationUserExt) throws Exception {

        long result = coreDataService.deleteAdminStaffAdminStaffID(applicationUserExt.getBusinessEntityID(), applicationUserExt.getUserName());
        result = coreDataService.deleteAdminStaffRelAdminStaffID(applicationUserExt.getBusinessEntityID(), applicationUserExt.getUserName());

        return result;
    }


    public long insertUpdateStaffAfmin(ApplicationUserExt applicationUserExt) throws Exception {

        try {


            String userStatus = applicationUserExt.getUserStatus();

            List<String> userAdminGroups = isStaffAdmin(applicationUserExt);

            if ((!StringUtil.IsNullOrEmpty(userStatus) && userStatus.equalsIgnoreCase("Inactive")) || userAdminGroups == null || (userAdminGroups.size() < 1)) {

                long result = coreDataService.deleteAdminStaffAdminStaffID(applicationUserExt.getBusinessEntityID(), applicationUserExt.getUserName());
                result = coreDataService.deleteAdminStaffRelAdminStaffID(applicationUserExt.getBusinessEntityID(), applicationUserExt.getUserName());
                coreDataService.deleteAdminStaffRoleAdminStaffID(applicationUserExt.getBusinessEntityID(), applicationUserExt.getUserName());

                return result;
            }

            if(userAdminGroups.size() > 0) {

                long result = 0;

                DozerBeanMapper mapper = new DozerBeanMapper(Arrays.asList(new String[]{"dozer/userExtToStaffMapping.xml"}));
                AdministrativeStaff administrativeStaff = mapper.map(applicationUserExt, AdministrativeStaff.class);

                Date newDate = new Date();
                administrativeStaff.setRecordUpdateTimestamp(newDate);
                administrativeStaff.setRecordCreateTimestamp(newDate);
                administrativeStaff.setRecordEffectiveTimestamp(newDate);
                administrativeStaff.setRecordCreatedBy("Authentication Service");
                administrativeStaff.setRecordUpdatedBy("Authentication Service");


                AdministrativeStaff existingAdministrativeStaff =
                        restDataService.getAdministrativeStaffByAdminStaffID(administrativeStaff.getBusinessEntityID(), administrativeStaff.getAdministrativeStaffID());


                if (existingAdministrativeStaff == null) {

                    result = insert("PKG_ADMIN", "insertAdminStaff", administrativeStaff);

                } else {

                    administrativeStaff.setAdministrativeStaffSK(existingAdministrativeStaff.getAdministrativeStaffSK());
                    administrativeStaff.setRecordTerminationTimestamp(existingAdministrativeStaff.getRecordTerminationTimestamp());
                    administrativeStaff.setRecordCreateTimestamp(existingAdministrativeStaff.getRecordCreateTimestamp());
                    administrativeStaff.setCurrentRecordIndicator(existingAdministrativeStaff.isCurrentRecordIndicator());


                    update(administrativeStaff);

                }

                //Cleanup any existing entries in Role table
                List<AdministrativeStaffRoleCrossReference> administrativeStaffRoleCrossReferences =
                        restDataService.getAdministrativeStaffRoleByAdminStaffID(administrativeStaff.getBusinessEntityID(), administrativeStaff.getAdministrativeStaffID());

                if (administrativeStaffRoleCrossReferences.size() > 0) {
                    for (AdministrativeStaffRoleCrossReference existingAdministrativeStaffRoleCrossReference : administrativeStaffRoleCrossReferences) {

                        coreDataService.execute("PKG_ADMIN", "deleteAdminStaffRoleXref", existingAdministrativeStaffRoleCrossReference.getAdministrativeStaffRoleCrossReferenceSK());
                    }
                }


                if (userAdminGroups == null || userAdminGroups.size() < 1) {
                    return 0;
                }

                for (String adminGroup : userAdminGroups) {


                    AdministrativeStaffRoleCrossReference administrativeStaffRoleCrossReference = new AdministrativeStaffRoleCrossReference();
                    administrativeStaffRoleCrossReference.setBusinessEntityID(administrativeStaff.getBusinessEntityID());
                    administrativeStaffRoleCrossReference.setAdministrativeStaffID(administrativeStaff.getAdministrativeStaffID());

                    Date date = new Date();
                    administrativeStaffRoleCrossReference.setAdministrativeStaffRoleEffectiveDate(date);
                    administrativeStaffRoleCrossReference.setAdministrativeStaffRoleName(adminGroup);
                    administrativeStaffRoleCrossReference.setChangeVersionID(new BigInteger("1"));
                    administrativeStaffRoleCrossReference.setRecordCreatedBy("Authentication Service");
                    administrativeStaffRoleCrossReference.setRecordCreateTimestamp(date);
                    administrativeStaffRoleCrossReference.setRecordUpdatedBy("Authentication Service");
                    administrativeStaffRoleCrossReference.setRecordUpdateTimestamp(date);
                    administrativeStaffRoleCrossReference.setRecordEffectiveTimestamp(date);

                    SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy HH:mm");
                    String dateInString = "31-12-9999 12:00";
                    administrativeStaffRoleCrossReference.setAdministrativeStaffRoleTerminationDate(sdf.parse(dateInString));

                    insert("PKG_ADMIN", "insertAdminStaffRoleXref", administrativeStaffRoleCrossReference);
                }
            }

            return 0;
        } catch (Exception e) {

            e.printStackTrace();
            throw e;
        }
    }

    private List<String> isStaffAdmin(ApplicationUserExt applicationUserExt) throws Exception {

        SandataLogger logger = CreateLogger();

        List<ApplicationUserRole> applicationUserRoles = applicationUserExt.getApplicationUserRole();

        List<String> adminUserGroups = new ArrayList();

        ApplicationSystemKeyConfiguration applicationSystemKeyConfiguration =
                restDataService.getApplicationSystemKeyConfiguration(Configs.get_AdminGroupsKey());

        if (applicationSystemKeyConfiguration != null && !StringUtil.IsNullOrEmpty(applicationSystemKeyConfiguration.getSystemKeyConfigurationValue())) {

            logger.info(String.format("Retrieved App System Configuration for key %s", Configs.get_AdminGroupsKey()));

            if (applicationUserRoles != null && applicationUserRoles.size() > 0) {

                String tenantId = restDataService.getTenantSKByBE(applicationUserExt.getBusinessEntityID());

                String[] groups = applicationSystemKeyConfiguration.getSystemKeyConfigurationValue().split(",");

                BigDecimal tenantSK = null;

                if (!StringUtil.IsNullOrEmpty(tenantId)) {

                    tenantSK = new BigDecimal(tenantId);

                } else {

                    logger.info(String.format("No tenant sk was found for business entity id '%s'", applicationUserExt.getBusinessEntityID()));
                }

                for (ApplicationUserRole applicationUserRole : applicationUserRoles) {


                    if (tenantSK.toBigInteger().equals(applicationUserRole.getApplicationTenantSK())) {


                        ApplicationRoleExt applicationRoleExt = restDataService.getRole(applicationUserRole.getApplicationRoleSK().intValue());

                        List<ApplicationSecureGroup> applicationSecureGroups = applicationRoleExt.getApplicationSecureGroups();

                        if (applicationSecureGroups == null) {
                            return null;
                        }

                        for (ApplicationSecureGroup applicationSecureGroup : applicationSecureGroups) {

                            for (String adminGroup : groups) {

                                if (adminGroup.equalsIgnoreCase(applicationSecureGroup.getSecureGroupName())) {

                                    adminUserGroups.add(adminGroup);
                                }

                            }
                        }
                    }
                }
            }

        }

        return adminUserGroups;
    }


    //endregion User

    public long insert(String packageName, String methodName, Object data) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger();

        Connection connection = null;

        try {

            connection = coreDataService.getOracleConnection();
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

            coreDataService.closeOracleConnection(connection);

            logger.stop();
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

    public void update(Object data) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger();

        Connection connection = null;

        try {

            connection = coreDataService.getOracleConnection();
            connection.setAutoCommit(false);
            long returnVal = executeRecursive(connection, data, false /* UPDATE */, -999);

            if (returnVal > 0) {
                connection.commit();
            } else {
                throw new SandataRuntimeException("Update was not successful!");
            }
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

            coreDataService.closeOracleConnection(connection);

            logger.stop();
        }
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
            if (sK == null || sK.intValue() < 1) {

                bShouldInsert = true;
            }

            //     setSk(jpubType, returnVal, "setAppUserSk");

            long result = 0;

            setTimeStamp(jpubType, "setRecUpdateTmstp");


            if (bShouldInsert) {

                setTimeStamp(jpubType, "setRecCreateTmstp");

                result = coreDataService.execute(
                        connection,
                        oracleMetadata.packageName(),
                        oracleMetadata.insertMethod(),
                        jpubType
                );
            } else {

                if (data instanceof ApplicationUser) {
                    returnVal = ((ApplicationUser) data).getApplicationUserSK().intValue();
                }

                // UPDATE
                result = coreDataService.execute(
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
