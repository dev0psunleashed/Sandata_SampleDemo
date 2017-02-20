package com.sandata.lab.security.auth.services;

import com.google.gson.*;
import com.google.gson.annotations.SerializedName;
import com.sandata.lab.common.utils.data.provider.GSONProvider;
import com.sandata.lab.common.utils.string.StringUtil;
import com.sandata.lab.security.auth.model.forgeRock.User;
import com.sandata.lab.security.auth.utils.GsonDateDeSerializer;
import org.apache.camel.PropertyInject;

import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class IDMService extends RestServiceProxy {

    @PropertyInject("{{openidm.host}}")
    private String host = "http://dev-lab-odj01.sandata.com";

    @PropertyInject("{{openidm.port}}")
    private String port = "8085";

    @PropertyInject("{{openidm.user}}")
    private String userName = "IDM-manager";

    @PropertyInject("{{openidm.password}}")
    private String password = "GeorgeAdmin1";

    @PropertyInject("{{openidm.path}}")
    private String path = "/openidm/managed/user/";

    @PropertyInject("{{openidm.email.path}}")
    private String emailPath = "/openidm/external/email?_action=send";

    public static IDMService getInstance() {
        return new IDMService();
    }

    private HashMap getAuthHeaders() {
        HashMap<String, String> authValues = new HashMap<String, String>();

        authValues.put("X-OpenIDM-Username", userName);
        authValues.put("X-OpenIDM-Password", password);

        return authValues;
    }

    private String getRequestURL(String host, String port, String path, String user) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(host);
        stringBuilder.append(":");
        stringBuilder.append(port + path + user.toLowerCase());

        return stringBuilder.toString();
    }

    public User createUpdateUser(User user) throws Exception {

        try {

            User idmUser = getUser(user.getEmail());

            if(idmUser != null) {

                user.setPasswordAccountLockedTime(idmUser.getPasswordAccountLockedTime());
                user.setPasswordChangedTime(idmUser.getPasswordChangedTime());
            }

            
            HashMap<String, String> headerValues = new HashMap<String, String>();
            headerValues.put("Content-type", "application/json");

            headerValues.putAll(getAuthHeaders());

            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").create();

            String request = gson.toJson(user);
            URLEncoder.encode(request, java.nio.charset.StandardCharsets.UTF_8.toString());

            String requestURL = getRequestURL(host, port, path, user.getUserName());
            String response = this.putRequest(requestURL, headerValues, request);

            User createdUser = (User) gson.fromJson(response, User.class);

            return createdUser;
        } catch (Exception e) {

            e.printStackTrace();
            throw e;
        }
    }

    public User getUser(String email) throws Exception {

        try {

            HashMap<String, String> headerValues = new HashMap<String, String>();
            headerValues.put("Content-type", "application/json");

            headerValues.putAll(getAuthHeaders());

            String requestURL = getRequestURL(host, port, path, email);
            String response = this.getRequest(requestURL, headerValues);

            Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new GsonDateDeSerializer()).create();

            User createdUser = (User) gson.fromJson(response, User.class);

            if (!StringUtil.IsNullOrEmpty(createdUser.getCode()) && createdUser.getCode().equals("404")) {
                return null;
            }

            return createdUser;
        } catch (Exception e) {

            e.printStackTrace();
            throw e;
        }
    }

    public void deleteUser(String userName) throws Exception {

        try {
            HashMap<String, String> headerValues = new HashMap<String, String>();

            headerValues.putAll(getAuthHeaders());

            String requestURL = getRequestURL(host, port, path, userName);
            String response = this.deleteRequest(requestURL, headerValues);
        } catch (Exception e) {

            e.printStackTrace();
            throw e;
        }
    }

    public void updateUser(User user) throws Exception {
        try {

            HashMap<String, String> headerValues = new HashMap<String, String>();
            headerValues.put("Content-type", "application/json");

            headerValues.putAll(getAuthHeaders());


            Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new GsonDateDeSerializer()).setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
                    .create();

            String requestString = gson.toJson(user);
            JsonObject jsonObject = new JsonParser().parse(requestString).getAsJsonObject();

            Set<Map.Entry<String, JsonElement>> entrySet = jsonObject.entrySet();
            for (Map.Entry<String, JsonElement> entry : entrySet) {

                if ((entry.getKey().equals("SANDATAGUID") && entry.getValue() == null) || entry.getKey().equals("password") || entry.getKey().equals("Persona")) {
                    continue;
                }
                Map<String, String> map = new HashMap();
                map.put("operation", "replace");
                map.put("field", "/" + entry.getKey());

                map.put("value", entry.getValue().getAsString());

                String request = gson.toJson(map);

                request = "[" + request + "]";
                URLEncoder.encode(request, java.nio.charset.StandardCharsets.UTF_8.toString());

                String requestURL = getRequestURL(host, port, path, user.getUserName());
                String response = this.patchRequest(requestURL, headerValues, request);

            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    public void changePassword(String userName, String newPassword) throws Exception {

        try {

            HashMap<String, String> headerValues = new HashMap<String, String>();
            headerValues.put("Content-type", "application/json");

            headerValues.putAll(getAuthHeaders());


            Map<String, String> map = new HashMap();
            map.put("operation", "replace");
            map.put("field", "/password");
            map.put("value", newPassword);

            Gson gson = new GsonBuilder()
                    .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
                    .create();

            String request = gson.toJson(map);

            request = "[" + request + "]";
            URLEncoder.encode(request, java.nio.charset.StandardCharsets.UTF_8.toString());

            String requestURL = getRequestURL(host, port, path, userName);
            String response = this.patchRequest(requestURL, headerValues, request);
        } catch (Exception e) {

            e.printStackTrace();
            throw e;
        }
    }

    public void grantAccess(String userName, String value) throws Exception {

        try {


            HashMap<String, String> headerValues = new HashMap<String, String>();
            headerValues.put("Content-type", "application/json");

            headerValues.putAll(getAuthHeaders());


            Map<String, String> map = new HashMap();
            map.put("operation", "replace");
            map.put("field", "/SandataAccess");
            map.put("value", value);

            Gson gson = new GsonBuilder()
                    .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
                    .create();

            String request = gson.toJson(map);

            request = "[" + request + "]";
            URLEncoder.encode(request, java.nio.charset.StandardCharsets.UTF_8.toString());

            String requestURL = getRequestURL(host, port, path, userName);
            String response = this.patchRequest(requestURL, headerValues, request);

        } catch (Exception e) {

            e.printStackTrace();
            throw e;
        }
    }

    public void addPersonaToUser(String userName, String persona) throws Exception {

        try {

            HashMap<String, String> headerValues = new HashMap<String, String>();
            headerValues.put("Content-type", "application/json");

            headerValues.putAll(getAuthHeaders());


            Map<String, String> map = new HashMap();
            map.put("operation", "add");
            map.put("field", "Persona/-");
            map.put("value", persona);

            Gson gson = new GsonBuilder()
                    .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
                    .create();

            String request = gson.toJson(map);

            request = "[" + request + "]";
            URLEncoder.encode(request, java.nio.charset.StandardCharsets.UTF_8.toString());

            String requestURL = getRequestURL(host, port, path, userName);
            String response = this.patchRequest(requestURL, headerValues, request);
        } catch (Exception e) {

            e.printStackTrace();
            throw e;
        }
    }

    public void removePersonaFromUser(String userName, String persona) throws Exception {

        try {
            HashMap<String, String> headerValues = new HashMap<String, String>();
            headerValues.put("Content-type", "application/json");

            headerValues.putAll(getAuthHeaders());


            Map<String, String> map = new HashMap();
            map.put("operation", "remove");
            map.put("field", "/Persona");
            map.put("value", persona);

            Gson gson = new GsonBuilder()
                    .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
                    .create();

            String request = gson.toJson(map);

            request = "[" + request + "]";
            URLEncoder.encode(request, java.nio.charset.StandardCharsets.UTF_8.toString());

            String requestURL = getRequestURL(host, port, path, userName);
            String response = this.patchRequest(requestURL, headerValues, request);
        } catch (Exception e) {

            e.printStackTrace();
            throw e;
        }
    }

    public void emailUser(String userName, String from, String type, String subject, String message) throws Exception {

        try {

            HashMap<String, String> headerValues = new HashMap<String, String>();
            headerValues.put("Content-type", "application/json");
            headerValues.putAll(getAuthHeaders());


            Map<String, String> map = new HashMap();
            map.put("from", from);
            map.put("to", userName);
            map.put("type", type);
            map.put("subject", subject);
            map.put("body", message);

            Gson gson = new GsonBuilder()
                    .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
                    .create();

            String request = gson.toJson(map);

            URLEncoder.encode(request, java.nio.charset.StandardCharsets.UTF_8.toString());

            String requestURL = getRequestURL(host, port, emailPath, userName);
            String response = this.postRequest(requestURL, headerValues, request);
        } catch (Exception e) {

            e.printStackTrace();
            throw e;
        }
    }

    /**
     * Determine whether a user account is locked in ForgeRock
     *
     * @param userName
     * @return
     */
    public boolean isUserLocked(String userName) throws Exception {

        try {
            HashMap<String, String> headerValues = new HashMap<String, String>();
            headerValues.put("Content-type", "application/json");

            headerValues.putAll(getAuthHeaders());

            String requestURL = getRequestURL(host, port, path, userName);
            String response = this.getRequest(requestURL + "?_fields=pwdAccountLockedTime", headerValues);

            JsonParser jsonParser = new JsonParser();
            JsonElement result = jsonParser.parse(response);

            if (null == result.getAsJsonObject().get("pwdAccountLockedTime")) {
                return false;
            }
            String isLocked = result.getAsJsonObject().get("pwdAccountLockedTime").toString();

            return StringUtil.IsNullOrEmpty(isLocked) || isLocked.equals("null") ? false : true;
        } catch (Exception e) {

            e.printStackTrace();
            throw e;
        }
    }

}
