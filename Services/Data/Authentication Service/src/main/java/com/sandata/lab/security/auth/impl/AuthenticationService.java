package com.sandata.lab.security.auth.impl;


import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.sandata.lab.common.utils.messaging.Messaging;
import com.sandata.lab.security.auth.model.FindUserResult;
import com.sandata.lab.security.auth.utils.PasswordUtil;
import com.sandata.lab.security.auth.utils.constants.App;
import org.apache.camel.Exchange;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.PropertyInject;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.cxf.message.MessageContentsList;
import org.dozer.DozerBeanMapper;

import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.common.utils.log.SandataLogger;
import com.sandata.lab.common.utils.string.StringUtil;
import com.sandata.lab.data.model.dl.model.ApplicationModule;
import com.sandata.lab.data.model.dl.model.ApplicationSecureGroup;
import com.sandata.lab.data.model.dl.model.ApplicationUserRole;
import com.sandata.lab.data.model.dl.model.extended.ApplicationRoleExt;
import com.sandata.lab.data.model.dl.model.extended.ApplicationUserExt;
import com.sandata.lab.data.model.response.Response;
import com.sandata.lab.data.model.service.ServiceStatus;
import com.sandata.lab.security.auth.model.Email;
import com.sandata.lab.security.auth.model.UserToken;
import com.sandata.lab.security.auth.model.forgeRock.AccessToken;
import com.sandata.lab.security.auth.model.forgeRock.TokenInfo;
import com.sandata.lab.security.auth.model.forgeRock.User;
import com.sandata.lab.security.auth.services.IDMService;
import com.sandata.lab.security.auth.services.OpenAMService;
import com.sandata.lab.security.auth.utils.log.AuthenticationLogger;



public class AuthenticationService {

    @PropertyInject("{{min.password.length}}")
    private int minPassWordLength = 5;

    @PropertyInject("{{max.password.length}}")
    private int maxPasswordLength = 10;

    private IDMService idmService;
    private OpenAMService openAMService;
    private RestDataService restDataService;
    private UserService userService;


    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void setRestDataService(RestDataService restDataService) {
        this.restDataService = restDataService;
    }

    public void setOpenAMService(OpenAMService openAMService) {
        this.openAMService = openAMService;
    }

    public void setIdmService(IDMService idmService) {
        this.idmService = idmService;
    }


    public void createUser(Exchange exchange) throws Exception {

        SandataLogger logger = AuthenticationLogger.CreateLogger();

        try {

            ApplicationUserExt applicationUserExt = (ApplicationUserExt) exchange.getIn().getBody();

            String userName = applicationUserExt.getUserName();

            if (!validateUserPhone(applicationUserExt)) {

                throw new SandataRuntimeException("Phone number contains non-numeric characters");

            } else if (!validateUserName(userName)) {

                throw new SandataRuntimeException("Username contains special characters");
            }

            long result = 0;

            //Does user already exist
            //User may already exist and be associated with another entity
            User existingUser = idmService.getUser(userName);

            String existingGUID = null;

            if (existingUser != null) {

                logger.info(String.format("User already exists for username: %s", userName));

                existingGUID = existingUser.getSandataGUID();
            }

            if (StringUtil.IsNullOrEmpty(existingGUID)) {

                String userGUID = generateGUID();
                applicationUserExt.setUserGloballyUniqueIdentifier(userGUID);
            } else {
                applicationUserExt.setUserGloballyUniqueIdentifier(existingGUID);
            }

            //Get details of the user role by retrieving the role from the db by it's sk value

            List<ApplicationUserRole> applicationUserRole = applicationUserExt.getApplicationUserRole();


            //Create new User instance and map ApplicationUserExt
            DozerBeanMapper mapper = new DozerBeanMapper(Arrays.asList(new String[]{"dozer/userMapping.xml"}));
            User user = mapper.map(applicationUserExt, User.class);

            ApplicationUserExt existingMetaUser = restDataService.getApplicationUserByUserName(userName);

            if (existingUser == null || existingMetaUser == null) {

                //Generate a temp password for the new user
                String password = PasswordUtil.newPassword();
                user.setPassword(password);

                user.setIsSandataAccess("NO");

                User createdUser = idmService.createUpdateUser(user);


                logger.info(String.format("Successfully created user with username: %s", userName));

                if (existingMetaUser == null) {
                    result = restDataService.insertUser(applicationUserExt);
                } else {
                    applicationUserExt.setApplicationUserSK(existingMetaUser.getApplicationUserSK());
                    result = restDataService.updateUser(applicationUserExt);
                }


                String from = "no-reply@sandata.com";
                String message = "Your temporary password is " + password + ". Please login and change, 8 char min length one capital and one number required.";

                idmService.emailUser(user.getEmail(), from, "text/html", "Password Reset", message);
                logger.info(String.format("Successfully sent email user to user: %s", userName));
            } else {

                idmService.createUpdateUser(user);


                ApplicationUserExt metaUser = restDataService.getApplicationUserByUserName(userName);


                if (metaUser == null) {
                    result = restDataService.insertUser(applicationUserExt);
                } else {

                    applicationUserExt.setApplicationUserSK(metaUser.getApplicationUserSK());
                    restDataService.updateUser(applicationUserExt);

                    result = metaUser.getApplicationUserSK().intValue();
                }

                logger.info(String.format("Successfully updated user with username: %s", userName));
            }

            userService.insertUpdateStaffAfmin(applicationUserExt);


            exchange.getIn().setBody(result);


        } catch (Exception ex) {
            ex.printStackTrace();
            throw new SandataRuntimeException(ex.getMessage(), ex);
        } finally {
            logger.stop();
        }
    }

    private boolean validateUserPhone(ApplicationUserExt applicationUserExt) throws Exception {

        if (validatePhone(applicationUserExt.getUserPhoneNumber())
                && validatePhone(applicationUserExt.getUserHomePhone())
                    && validatePhone(applicationUserExt.getUserMobilePhone())) {

            return true;
        }

        return false;
    }

    private boolean validatePhone(String phone) throws Exception {

        try {

            if (!StringUtil.IsNullOrEmpty(phone) && !NumberUtils.isNumber(phone)) {

                return false;
            }

            return true;

        } catch (Exception e) {

            e.printStackTrace();
            throw e;
        }
    }

    private boolean validateUserName(String userName) throws Exception {
        SandataLogger logger = AuthenticationLogger.CreateLogger();
        try {

            //SAN-4088 not allowed username contains apostrophe
            String invalidCharacters = "'";

            if (StringUtils.containsAny(userName, invalidCharacters.split(","))) {

                logger.info(String.format("Username contains special characters : " + invalidCharacters));

                return false;
            }

            return true;

        } catch (Exception e) {

            e.printStackTrace();
            throw e;
        }
    }

    public void deleteRole(Exchange exchange) throws Exception {
        SandataLogger logger = AuthenticationLogger.CreateLogger();

        try {

            MessageContentsList mcl = (MessageContentsList) exchange.getIn().getBody();


            long appRoleSk = (long) mcl.get(0);


            long result = restDataService.deleteRole(appRoleSk);
            result = 1;

            exchange.getIn().setBody(result);

            logger.info(String.format("Successfully deleted role with roleSK: %d", appRoleSk));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new SandataRuntimeException(ex.getMessage(), ex);
        }
    }

    public void getUser(Exchange exchange) {
        SandataLogger logger = AuthenticationLogger.CreateLogger();

        try {
            MessageContentsList mcl = (MessageContentsList) exchange.getIn().getBody();
            String businessEntityId = (String) mcl.get(1);
            if (businessEntityId == null) {
                throw new SandataRuntimeException("BusinessEntityID (bsn_ent_id) is required!");
            }

            String userName = (String) mcl.get(0);

            ApplicationUserExt applicationUserExt = getApplicationUserExt(userName, businessEntityId);

            if (userName == null) {
                throw new SandataRuntimeException("Username is required!");
            }

            if (applicationUserExt != null) {
                logger.info(String.format("Succesfully retrieived user '%s' for BusinessEntityID '%s'",
                        userName, businessEntityId));
            } else {

                logger.info(String.format("No user retrieived for username '%s' for BusinessEntityID '%s'",
                        userName, businessEntityId));
            }

            exchange.getIn().setBody(applicationUserExt);

        } catch (Exception ex) {

            ex.printStackTrace();
            throw new SandataRuntimeException(ex.getMessage(), ex);
        }
    }

    private ApplicationUserExt getApplicationUserExt(String userName, String businessEntityId) throws Exception {

        SandataLogger logger = AuthenticationLogger.CreateLogger();

        //Retrieve user from ForgeRock
        User user = idmService.getUser(userName);
        ApplicationUserExt applicationUserExt = null;

        if (user != null) {
            DozerBeanMapper mapper = new DozerBeanMapper(Arrays.asList(new String[]{"dozer/userMapping.xml"}));
            applicationUserExt = (ApplicationUserExt) mapper.map(user, ApplicationUserExt.class);

            ApplicationUserExt metaApplicationUserExt = null;

            if (!StringUtil.IsNullOrEmpty(businessEntityId)) {
                //Retrieve same user from metadata that corresponds with business entity
                metaApplicationUserExt = restDataService.getApplicationUserByUserName(userName, businessEntityId);
            } else {

                metaApplicationUserExt = restDataService.getApplicationUserByUserName(userName);
            }

            //If the user is not found in metadata, then the user does not exis for the business entity id
            if (metaApplicationUserExt == null) {
                if (!StringUtil.IsNullOrEmpty(businessEntityId)) {
                    logger.info(String.format("Username %s does not exist for BusinessEntityID %s", userName, businessEntityId));
                } else {
                    logger.info(String.format("Username %s does not exist", userName));
                }
                return null;

            } else {

                logger.info(String.format("Succesfully retrieived user '%s' for BusinessEntityID '%s'",
                        userName, businessEntityId));

                //Merge the data from both sources
                applicationUserExt.getApplicationUserRole().addAll(metaApplicationUserExt.getApplicationUserRole());
                applicationUserExt.getApplicationUserSetting().addAll(metaApplicationUserExt.getApplicationUserSetting());
                applicationUserExt.setUserGloballyUniqueIdentifier(metaApplicationUserExt.getUserGloballyUniqueIdentifier());
                applicationUserExt.setUserMiddleName(metaApplicationUserExt.getUserMiddleName());
                applicationUserExt.setApplicationUserSK(metaApplicationUserExt.getApplicationUserSK());
                applicationUserExt.setRecordCreateTimestamp(metaApplicationUserExt.getRecordCreateTimestamp());
                applicationUserExt.setRecordUpdateTimestamp(metaApplicationUserExt.getRecordUpdateTimestamp());
            }


        } else {
            logger.info(String.format("User:'%s' was not found in OpenIDM ",
                    userName));
        }

        return applicationUserExt;
    }

    public void userExistsForBsnEntId(Exchange exchange) {
        SandataLogger logger = AuthenticationLogger.CreateLogger();

        try {
            MessageContentsList mcl = (MessageContentsList) exchange.getIn().getBody();
            String businessEntityId = (String) mcl.get(1);
            if (businessEntityId == null) {
                throw new SandataRuntimeException("BusinessEntityID (bsn_ent_id) is required!");
            }

            String userName = (String) mcl.get(0);
            if (userName == null
                    || userName.isEmpty()) {
                throw new SandataRuntimeException("User name (username) is required!");
            }

            boolean userExists = false;

            //Retrieve user from ForgeRock
            User user = idmService.getUser(userName);

            if (user != null) {

                userExists = restDataService.isApplicationUserExists(userName, businessEntityId);
            }

            exchange.getIn().setBody(userExists);

        } catch (Exception ex) {

            ex.printStackTrace();
            throw new SandataRuntimeException(ex.getMessage(), ex);
        }
    }

    public void userExists(Exchange exchange) {
        SandataLogger logger = AuthenticationLogger.CreateLogger();

        try {
            MessageContentsList mcl = (MessageContentsList) exchange.getIn().getBody();
            String userName = (String) mcl.get(0);
            if (userName == null
                    || userName.isEmpty()) {
                throw new SandataRuntimeException("User name (username) is required!");
            }

            boolean userExists = false;

            //Retrieve user from ForgeRock
            User user = idmService.getUser(userName);

            if (user != null) {

                userExists = restDataService.isApplicationUserExists(userName);
            }

            exchange.getIn().setBody(userExists);

        } catch (Exception ex) {

            ex.printStackTrace();
            throw new SandataRuntimeException(ex.getMessage(), ex);
        }
    }

    public void updateUser(Exchange exchange) throws Exception {

        SandataLogger logger = AuthenticationLogger.CreateLogger();

        ApplicationUserExt applicationUserExt = (ApplicationUserExt) exchange.getIn().getBody();

        try {

            logger.info(String.format("Starting updated user with username: %s", applicationUserExt.getUserName()));

            if (!validateUserPhone(applicationUserExt)) {

                throw new SandataRuntimeException("Phone number contains non-numeric characters");

            } else if (!validateUserName(applicationUserExt.getUserName())) {

                throw new SandataRuntimeException("Username contains special characters");
            }

            //Create new User instance and map ApplicationUserExt
            DozerBeanMapper mapper = new DozerBeanMapper(Arrays.asList(new String[]{"dozer/userMapping.xml"}));
            User user = (User) mapper.map(applicationUserExt, User.class);

            idmService.createUpdateUser(user);

            long result = restDataService.updateUser(applicationUserExt);

            userService.insertUpdateStaffAfmin(applicationUserExt);

            result = 1;
            exchange.getIn().setBody(result);

            logger.info(String.format("Successfully updated user with username: %s", user.getUserName()));
        } catch (Exception e) {

            logger.info(String.format("Error updating user with username: %s", applicationUserExt.getUserName()));

            e.printStackTrace();
            throw new SandataRuntimeException(e.getMessage(), e);
        } finally {
            logger.stop();
        }
    }

    public void deleteUser(Exchange exchange) throws Exception {

        SandataLogger logger = AuthenticationLogger.CreateLogger();

        try {

            MessageContentsList mcl = (MessageContentsList) exchange.getIn().getBody();


            long userSk = (long) mcl.get(0);
            String businessEntityId = (String) mcl.get(1);

            if (businessEntityId == null) {
                throw new SandataRuntimeException("BusinessEntityID (bsn_ent_id) is required!");
            }

            ApplicationUserExt applicationUser = restDataService.getApplicationUser(userSk);

            userService.deleteStaffAdmin(applicationUser);

            long result = restDataService.deleteUserFromTenant(businessEntityId, userSk);

            idmService.deleteUser(applicationUser.getUserName());

            exchange.getIn().setBody(null);

            logger.info(String.format("Successfully deleted user with userSK: %d for businessEntityID: %s", userSk, businessEntityId));

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new SandataRuntimeException(ex.getMessage(), ex);
        }
    }


    public void findUser(Exchange exchange) throws Exception {

        SandataLogger logger = AuthenticationLogger.CreateLogger();

        try {

            MessageContentsList mcl = (MessageContentsList) exchange.getIn().getBody();
            String bsnEntId = (String) mcl.get(8);
            if (bsnEntId == null) {
                throw new SandataRuntimeException("BusinessEntityID (bsn_ent_id) is required!");
            }

            String lastName = (String) mcl.get(0);
            String firstName = (String) mcl.get(1);
            String userName = (String) mcl.get(2);
            String roleName = (String) mcl.get(3);
            int page = (int) mcl.get(4);
            int pageSize = (int) mcl.get(5);
            String sortOn = (String) mcl.get(6);
            String sortDirection = (String) mcl.get(7);

            Response response = restDataService.findUser(bsnEntId, lastName, firstName, userName, roleName, page,
                    pageSize, sortOn, sortDirection);

            response.setStatus(ServiceStatus.SUCCESS);

            exchange.getIn().setBody(response);

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new SandataRuntimeException(ex.getMessage(), ex);
        } finally {
            logger.stop();
        }
    }

    public void insertUpdateRole(Exchange exchange) throws Exception {
        try {

            ApplicationRoleExt applicationRoleExt = (ApplicationRoleExt) exchange.getIn().getBody();

            BigInteger roleSk = applicationRoleExt.getApplicationRoleSK();

            long result = restDataService.insertRoleUpdate(applicationRoleExt);

            if (roleSk != null && roleSk.compareTo(BigInteger.ZERO) == 1) {

                ProducerTemplate producerTemplate = exchange.getContext().createProducerTemplate();
                producerTemplate.sendBody(Messaging.Names.COMPONENT_TYPE_SEDA + App.ID.UPDATE_ROLE_USERS.toString(), applicationRoleExt);
            }

            exchange.getIn().setBody(result);

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new SandataRuntimeException(ex.getMessage(), ex);
        }
    }

    public void updateRoleUsers(Exchange exchange) throws Exception {
        try {

            final SandataLogger logger = AuthenticationLogger.CreateLogger();


            ApplicationRoleExt applicationRoleExt = (ApplicationRoleExt) exchange.getIn().getBody();

            final String bsnEntId = applicationRoleExt.getBusinessEntityID();
            String roleName = applicationRoleExt.getRoleName();

            int page = 1;
            int pageSize = 300;
            String sortOn = "rn";
            String sortDirection = "ASC";
            int updatedUsers = -1;
            int totalRows = 0;


            ProducerTemplate producerTemplate = exchange.getContext().createProducerTemplate();


            Response response = null;

            while (totalRows > updatedUsers) {

                response = restDataService.findUser(bsnEntId, null, null, null, roleName, page,
                        pageSize, sortOn, sortDirection);


                if (response != null) {

                    List<FindUserResult> findUserResults = (ArrayList) response.getData();

                    if (findUserResults != null && findUserResults.size() > 0) {

                        ExecutorService executor = Executors.newFixedThreadPool(20);

                        for (final FindUserResult findUserResult : findUserResults) {

                            executor.execute(
                                    new Runnable() {
                                        public void run() {
                                            try {

                                                ApplicationUserExt applicationUserExt = getApplicationUserExt(findUserResult.getUserName(), bsnEntId);

                                                userService.insertUpdateStaffAfmin(applicationUserExt);
                                                logger.info(String.format("Successfully updated admin status %s "));

                                            } catch (Exception ex) {
                                                ex.printStackTrace();
                                            }
                                        }
                                    });

                            updatedUsers++;
                        }

                        executor.shutdown();

                        while (!executor.awaitTermination(1L, TimeUnit.HOURS)) {
                            logger.info("Still waiting for the executor update user admins to finish");
                        }

                        page++;
                        updatedUsers = findUserResults.size();
                    } else {
                        break;
                    }
                } else {

                    break;
                }


            }


        } catch (Exception ex) {
            ex.printStackTrace();
            throw new SandataRuntimeException(ex.getMessage(), ex);
        }
    }


    public void getRole(Exchange exchange) throws Exception {
        try {

            MessageContentsList mcl = (MessageContentsList) exchange.getIn().getBody();
            long roleSk = (long) mcl.get(0);

            ApplicationRoleExt result = getRole(roleSk);

            exchange.getIn().setBody(result);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new SandataRuntimeException(ex.getMessage(), ex);
        }
    }

    public ApplicationRoleExt getRole(long roleSk) throws Exception {

        return restDataService.getRole(roleSk);
    }


    public void getApplicationModules(Exchange exchange) {
        try {
            List<ApplicationModule> applicationModuleList = restDataService.getApplicationModules();

            exchange.getIn().setBody(applicationModuleList);

        } catch (Exception e) {
            e.printStackTrace();
            throw new SandataRuntimeException(e.getMessage(), e);
        }
    }

    public void getSecureGroups(Exchange exchange) {
        try {
            List<ApplicationSecureGroup> applicationSecureGroupList = restDataService.getApplicationSecureGroups();

            exchange.getIn().setBody(applicationSecureGroupList);

        } catch (Exception e) {
            e.printStackTrace();
            throw new SandataRuntimeException(e.getMessage(), e);
        }
    }

    public void getRoles(Exchange exchange) throws Exception {
        try {

            String businessEntityId = (String) exchange.getIn().getHeader("bsn_ent_id");

            if (businessEntityId == null) {
                throw new SandataRuntimeException("BusinessEntityID (bsn_ent_id) is required!");
            }

            List<ApplicationRoleExt> result = restDataService.getRoles(businessEntityId);

            exchange.getIn().setBody(result);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new SandataRuntimeException(ex.getMessage(), ex);
        }
    }

    public void getUserToken(Exchange exchange) throws Exception {

        try {

            String userHashBaseString = (String) exchange.getIn().getHeader("hash");

            ApplicationUserExt applicationUserExt = null;

            AccessToken accessToken = getToken(userHashBaseString);

            // Retrieve user only if authenticated.
            if (!StringUtil.IsNullOrEmpty(accessToken.getAccessToken())) {

                String[] authenticationHashArray = getUserPassHash(userHashBaseString.getBytes());
                applicationUserExt = getApplicationUserExt(authenticationHashArray[0], null);
            }

            UserToken userToken = new UserToken();

            userToken.setAccessToken(accessToken);
            userToken.setApplicationUserExt(applicationUserExt);

            exchange.getIn().setBody(userToken);

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new SandataRuntimeException(ex.getMessage(), ex);
        }

    }

    public void getToken(Exchange exchange) throws Exception {
        try {

            String userHashBaseString = (String) exchange.getIn().getHeader("hash");

            AccessToken accessToken = getToken(userHashBaseString);

            exchange.getIn().setBody(accessToken);

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new SandataRuntimeException(ex.getMessage(), ex);
        }
    }

    private AccessToken getToken(String userHashBaseString) throws Exception {

        SandataLogger logger = AuthenticationLogger.CreateLogger();

        byte[] userHashBase64 = userHashBaseString.getBytes();

        if (userHashBase64 != null && userHashBase64.length > 0) {

            String[] authenticationHashArray = getUserPassHash(userHashBase64);

            if (authenticationHashArray.length == 2) {
                AccessToken accessToken = openAMService.tokenRequest(authenticationHashArray[0], authenticationHashArray[1]);

                if (accessToken.getError() != null) {

                    logger.info(String.format("Succesfully authenticated user: %s", authenticationHashArray[0]));
                } else {
                    logger.info(String.format("Authentication failed for user: %s", authenticationHashArray[0]));
                }

                return accessToken;
            } else {
                throw new Exception("Invalid format for user authentication hash!!");
            }
        } else {
            throw new Exception("Authentication hash is null!!. Invalid!");
        }

    }

    public void getTokenInfo(Exchange exchange) {
        try {

            String token = (String) exchange.getIn().getHeader("guid");

            TokenInfo tokenInfo = openAMService.tokenInfoRequest(token);

            exchange.getIn().setBody(tokenInfo);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new SandataRuntimeException(ex.getMessage(), ex);
        }
    }

    public void refreshToken(Exchange exchange) {
        try {

            String token = (String) exchange.getIn().getHeader("guid");

            AccessToken accessToken = openAMService.refreshToken(token);

            exchange.getIn().setBody(accessToken);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new SandataRuntimeException(ex.getMessage(), ex);
        }
    }

    public void isUserLocked(Exchange exchange) {

        SandataLogger logger = AuthenticationLogger.CreateLogger();

        try {

            String userName = (String) exchange.getIn().getHeader("username");

            boolean result = idmService.isUserLocked(userName);

            logger.info(String.format("User:%s account locked: %s", userName, Boolean.toString(result)));

            exchange.getIn().setBody(result);

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new SandataRuntimeException(ex.getMessage(), ex);
        }
    }

    public void resetPassword(Exchange exchange) throws Exception {

        SandataLogger logger = AuthenticationLogger.CreateLogger();

        try {

            String userName = (String) exchange.getIn().getBody();

            String tempPassword = PasswordUtil.newPassword();

            logger.info(String.format("Resetting password for user: %s", userName));

            if (!PasswordUtil.isValidPassword(tempPassword)) {

                throw new SandataRuntimeException(String.format("The new password for user %s contains invalid special characters. " +
                        " Valid special characters: %s ", userName, PasswordUtil.getSpecialChars()));
            }

            idmService.changePassword(userName, tempPassword);
            String message = "Your temporary password is " + tempPassword + ". Please login and change, 8 char min length one capital and one number required.";


            idmService.emailUser(userName, "no-reply@sandata", "text/html", "Password Reset", message);
            logger.info(String.format("Successfully sent email user to user: %s", userName));


        } catch (Exception ex) {
            ex.printStackTrace();
            throw new SandataRuntimeException(ex.getMessage(), ex);
        }
    }

    public void emailUser(Exchange exchange) throws Exception {
        SandataLogger logger = AuthenticationLogger.CreateLogger();

        try {

            String userName = (String) exchange.getIn().getHeader("user_name");


            Email email = (Email) exchange.getIn().getBody();
            idmService.emailUser(userName, "no-reply@sandata.com", email.getType(), email.getSubject(), email.getBody());

            logger.info(String.format("Email sent to user: %s", userName));

            exchange.getIn().setBody(true);

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new SandataRuntimeException(ex.getMessage(), ex);
        } finally {
            logger.stop();
        }
    }

    public void changePassword(Exchange exchange) throws Exception {

        SandataLogger logger = AuthenticationLogger.CreateLogger();

        try {

            String userHashBaseString = (String) exchange.getIn().getHeader("hash");

            byte[] userHashBase64 = userHashBaseString.getBytes();

            if (userHashBase64 != null && userHashBase64.length > 0) {

                String[] authenticationHashArray = getUserPassHash(userHashBase64);

                if (authenticationHashArray.length == 2) {

                    User user = idmService.getUser(authenticationHashArray[0]);
                    if (null == user) {
                        throw new SandataRuntimeException("User not found");
                    }

                    StringBuilder error = new StringBuilder();

                    if (!PasswordUtil.isValidPassword(user, authenticationHashArray[1], error)) {

                        throw new SandataRuntimeException(error.toString());
                    }

                    idmService.changePassword(authenticationHashArray[0], authenticationHashArray[1]);

                    logger.info(String.format("Successfully changed password for user: %s", authenticationHashArray[0]));

                    idmService.grantAccess(authenticationHashArray[0], "YES");

                    logger.info(String.format("Successfully granted access for user: %s", authenticationHashArray[0]));

                    exchange.getIn().setBody(true);

                } else {
                    throw new SandataRuntimeException("Invalid format for username and password");
                }
            } else {
                throw new SandataRuntimeException("Invalid format for username and password");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new SandataRuntimeException(ex.getMessage(), ex);
        } finally {
            logger.stop();
        }
    }


    private String generateGUID() {
        return UUID.randomUUID().toString();
    }


    private String[] getUserPassHash(byte[] hash) {
        String authenticationHash = new String(Base64.decodeBase64(hash), StandardCharsets.UTF_8);

        String[] authenticationHashArray = authenticationHash.split(":");

        return authenticationHashArray;
    }
}
