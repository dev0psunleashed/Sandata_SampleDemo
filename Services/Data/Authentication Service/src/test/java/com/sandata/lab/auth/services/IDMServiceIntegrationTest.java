package com.sandata.lab.auth.services;

import com.sandata.lab.auth.BaseTestSupport;
import com.sandata.lab.security.auth.model.forgeRock.User;
import com.sandata.lab.security.auth.services.IDMService;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;

import java.io.IOException;
import java.util.Date;

public class IDMServiceIntegrationTest extends BaseTestSupport {

    private IDMService iDMService;

    @Override
    protected void onSetup() throws IOException {

        iDMService = new IDMService();
    }



    @Test
    public void create_user() throws Exception
    {
        User user = new User();

        user.setAddress1("test 2");
        user.setCity("Brooklyn");
        user.setState("NY");
        user.setDisplayName("Ralph Smith");
        user.setEmail("test223sylvain@sandata.com");
        user.setHomePhone("7182154123");
        user.setFirstName("Ralph");
        user.setLastName("Smith");
        user.setSn("Smith");
        user.setMobilePhone("6465555555");
        user.setDescription("user account");
        user.setUserName("test223sylvain@sandata.com");;
       // user.setOrganization("Sandata");
       // user.setOrganizationalUnit("it");
        user.setUserStatus("Active");
        user.setUserType("it");
        user.setSandataAccess("YES");
        user.setDateOfBirth(new Date());


        iDMService.createUpdateUser(user);
    }

    @Test
    public void update_user() throws Exception
    {
        User user = new User();

        user.setAddress1("437 Kingston");
        user.setAddress2("Apt 2");
        user.setCity("Brooklyn");
        user.setState("NY");
        user.setDisplayName("Ralph Smith");
        user.setEmail("rsylva01@gmail.com");
        user.setHomePhone("7182154123");
        user.setFirstName("Ralph");
        user.setLastName("Smith");
        user.setSn("Smith");
        user.setMobilePhone("6465555555");
        user.setDescription("user account");
        user.setUserName("rsylva01@gmail.com");;
      //  user.setOrganization("Sandata");
      //  user.setOrganizationalUnit("it");
        user.setUserStatus("Active");
        user.setUserType("it");
        user.setSandataAccess("YES");
        user.setBusinessEntityID("1");
        user.setDateOfBirth(new Date());

        iDMService.createUpdateUser(user);
    }

    @Test
    public void delete_user() throws Exception
    {
        iDMService.deleteUser("rsylvain@sandata.com");
    }

    @Test
    public void get_user() throws Exception
    {
        User user = iDMService.getUser("george@sandata.com");
        assertNotNull(user);
    }

    @Test
    public void grant_access() throws Exception
    {
        iDMService.grantAccess("george@sandata.com","YES");
    }

    @Test
    public void change_user_password() throws Exception
    {
        iDMService.changePassword("george@sandata.com","Test.110");

    }

    @Test
    public void add_persona_to_user() throws Exception
    {
        iDMService.addPersonaToUser("rsylvain@sandata.com","intake");

    }

    @Test
    public void remove_persona_from_user() throws Exception
    {
        iDMService.removePersonaFromUser("rsylvain@sandata.com","admin admin");

    }

    @Test
    public void email_password_to_user() throws Exception
    {
        iDMService.emailUser("rsylvain@sandata.com","integration-test@sandata.com","text/html","TEST","email test");

    }

    @Test
    public void is_user_locked() throws Exception
    {
        iDMService.isUserLocked("rsylvain@sandata.com");
    }
}
