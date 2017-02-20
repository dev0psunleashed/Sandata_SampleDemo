package com.sandata.lab.auth.utils;

import com.sandata.lab.auth.BaseTestSupport;
import com.sandata.lab.security.auth.model.forgeRock.User;
import com.sandata.lab.security.auth.utils.PasswordUtil;
import org.junit.Test;

import static org.junit.Assert.*;

public class PasswordUtilTest {


    @Test
    public void password_contains_only_invalid_characters_returns_false() {

        String password = "::";

        assertFalse(PasswordUtil.isValidPassword(password));

    }

    @Test
    public void password_contains_only_valid_and_invalid_characters_returns_false() {

        String password = "abc23::";

        assertFalse(PasswordUtil.isValidPassword(password));

    }

    @Test
    public void password_contains_only_valid_characters_returns_true() {

        String password = "Aa123$';=";

        assertTrue(PasswordUtil.isValidPassword(password));
    }

    @Test
    public void password_doesnt_contains_username_returns_true() {

        User user = new User();
        user.setFirstName("Test1");
        user.setLastName("Test2");

        String password = "Aa123$';=";

        StringBuilder error = new StringBuilder();

        PasswordUtil.isValidPassword(user, password, error);

        assertEquals(error.toString(), "");
    }


    @Test
    public void password_contains_username_returns_error_message() {

        User user = new User();
        user.setFirstName("Test1");
        user.setLastName("Test2");

        String password = "test123$';=";

        StringBuilder error = new StringBuilder();

        PasswordUtil.isValidPassword(user, password, error);

        assertNotEquals(error.toString(), "");
    }

    @Test
    public void generate_password() {

        String password = PasswordUtil.newPassword();

        assertTrue(password.length() >= 8);
    }
}
