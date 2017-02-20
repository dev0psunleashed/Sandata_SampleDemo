package com.sandata.lab.security.auth.utils;

import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.common.utils.string.StringUtil;
import com.sandata.lab.security.auth.model.forgeRock.User;
import org.apache.camel.PropertyInject;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PasswordUtil {

    @PropertyInject("{{min.password.length}}")
    private static int minPassWordLength = 9;

    @PropertyInject("{{max.password.length}}")
    private static int maxPasswordLength = 11;

    private static String _UPPER_CASE_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static String _LOWER_CASE_CHARS = "abcdefghijklmnopqrstuvwxyz";
    private static String _NUMERIC_CHARS = "0123456789";
    private static String _SPECIAL_CHARS = "-._?#[]@$';=";

    public static String getSpecialChars() {

        return _SPECIAL_CHARS;
    }

    public static String newPassword() {
        SecureRandom random = new SecureRandom();

        int passwordLength = random.nextInt(maxPasswordLength - minPassWordLength) + minPassWordLength;
        int availableChars = passwordLength;

        int specialLength = 1;
        int numericsLength = 1;
        availableChars -= (specialLength + numericsLength);

        int lowerCaseLength = (availableChars - specialLength - numericsLength) / 2;
        availableChars -= lowerCaseLength;
        int upperCaseLength = availableChars;

        //Generate a new password with the require character lengths
        return generatePassword(lowerCaseLength, upperCaseLength, numericsLength, specialLength);
    }

    public static String generatePassword(int lowerCaseLength, int upperCaseLength, int numericsLength,
                                          int specialCharactersLength) {
        String upperCaseChars = _UPPER_CASE_CHARS;
        String lowerCaseChars = _LOWER_CASE_CHARS;
        String numericChars = _NUMERIC_CHARS;
        String specialChars = _SPECIAL_CHARS;

        SecureRandom secureRandom = new SecureRandom();

        List<String> passwordSubString = new ArrayList<>();


        passwordSubString.add(
                RandomStringUtils.random(upperCaseLength, 0, 0, false, false,
                        upperCaseChars.toCharArray(), secureRandom)
        );

        passwordSubString.add(
                RandomStringUtils.random(lowerCaseLength, 0, 0, false, false,
                        lowerCaseChars.toCharArray(), secureRandom)
        );

        passwordSubString.add(
                RandomStringUtils.random(numericsLength, 0, 0, false, false,
                        numericChars.toCharArray(), secureRandom)
        );

        passwordSubString.add(
                RandomStringUtils.random(specialCharactersLength, 0, 0, false, false,
                        specialChars.toCharArray(), secureRandom)
        );

        //Create password string
        StringBuilder stringBuilder = new StringBuilder();
        for (String s : passwordSubString) {
            stringBuilder.append(s);
        }

        //Split password String to List
        List<Character> characters = new ArrayList<Character>();
        for (char c : stringBuilder.toString().toCharArray()) {
            characters.add(c);
        }

        //Shuffle the order of elements in the array
        Collections.shuffle(characters);

        //Recreate password string
        StringBuilder stringBuilder2 = new StringBuilder();
        for (Character s : characters) {
            stringBuilder2.append(s);
        }

        return stringBuilder2.toString();
    }

    public static String[] getUserPassHash(byte[] hash) {
        String authenticationHash = new String(Base64.decodeBase64(hash), StandardCharsets.UTF_8);

        String[] authenticationHashArray = authenticationHash.split(":");

        return authenticationHashArray;
    }

    public static boolean isValidPassword(User user, String password, StringBuilder errorMessage) {

        if ((!StringUtil.IsNullOrEmpty(user.getFirstName()) && StringUtils.containsIgnoreCase(password, user.getFirstName()))
                || !StringUtil.IsNullOrEmpty(user.getUserName()) && StringUtils.containsIgnoreCase(password, user.getUserName())
                || !StringUtil.IsNullOrEmpty(user.getLastName()) && StringUtils.containsIgnoreCase(password, user.getLastName())) {

            errorMessage.append("Invalid password. Password cannot container username, first name, or last name");

            return false;
        }

        if (!isValidPassword(password)) {

            errorMessage.append(String.format("The new password for user %s contains invalid special characters. " +
                    " Valid special characters: %s ", user.getUserName(), PasswordUtil.getSpecialChars()));


            return false;
        }

        return true;
    }

    public static boolean isValidPassword(String password) {

        StringBuilder allowedCharacters = new StringBuilder();

        String allowedSpecialCharacters = _SPECIAL_CHARS.replace("[]", "\\[\\]").replace("-", "\\-");

        allowedCharacters.append(_UPPER_CASE_CHARS).append(_LOWER_CASE_CHARS).append(_NUMERIC_CHARS)
                .append(allowedSpecialCharacters);

        return (password.matches("[" + allowedCharacters.toString() + "]+"));
    }
}
