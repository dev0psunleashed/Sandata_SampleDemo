package com.sandata.lab.rules.test;

import com.google.gson.Gson;
import com.sandata.lab.rules.call.model.CallPreferences;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: Tom Dornseif
 * Date: 7/13/2016
 * Time: 5:38 PM
 */
public class CallPrefereneTest {

    public static void main(String[] args) {
        RunTest();
    }
    @Test
    public static void RunTest() {
        Gson gson = new Gson();
        CallPreferences callPreferences = new CallPreferences();
        String callPreferencesString = gson.toJson(callPreferences);
        System.out.println(callPreferencesString);
    }
}
