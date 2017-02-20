package com.sandata.lab.common.utils.rest;

import com.sandata.lab.common.utils.string.StringUtil;
import com.sandata.lab.data.model.dl.model.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TimeZone;

/**
 * Date: 9/5/15
 * Time: 6:40 PM
 */

public class RestUtil {

    public static String PathName(String className, String prefix) {

        String pathName = StringUtil.SplitByCapital(className, "_");

        pathName = StringUtil.RemovePrefix(pathName, prefix);
        if (pathName.length() == 0) {
            return className.toLowerCase();
        }
        else {
            if (pathName.startsWith("_")) {
                pathName = pathName.substring(1);
            }
            return String.format("%s/%s", prefix.toLowerCase(), pathName.toLowerCase());
        }
    }

    /**
     * Clients may have some parameters likes param1=&param1=value1&param1=, the paramList will have 3 items with 2 empty valuevalues
     * so we normalize the input list by removing the empty values
     * @param paramList
     * @return
     */
    public static List<String> normalizeParamList(List<String> paramList) {
        if (paramList == null || paramList.isEmpty()) {
            return paramList;
        }

        // Filter out null or empty elements in list
        List<String> normalizedList = new ArrayList<String>();
        for (String str : paramList) {
            if (str != null && !str.isEmpty()) {
                normalizedList.add(str);
            }
        }

        return normalizedList;
    }
    
    /**
     * Validates the input <code>data</code> with required TimezoneName field.
     * If the data is invalid, then return errMsg.
     * @param data
     * @return
     */
    public static String validateRequiredTimezoneName(Object data) {
        String errMsg = "";
        String objectClass = data != null ? data.getClass().getSimpleName() : "Null Object";
        
        try {
        
            if (data instanceof BusinessEntity || data instanceof Contract || data instanceof Payer
                    || data instanceof Patient || data instanceof Schedule || data instanceof ScheduleEvent
                    || data instanceof Staff || data instanceof VisitEvent) {
                if (data != null) {
                    Field timezoneField = data.getClass().getDeclaredField("timezoneName");
                    timezoneField.setAccessible(true);
                    
                    String timezoneNameValue = (String) timezoneField.get(data);
                    
                    if (StringUtil.IsNullOrEmpty(timezoneNameValue)) {
                        errMsg = String.format("%s timezoneName field must be provided", objectClass);
                    } else if (!Arrays.asList(TimeZone.getAvailableIDs()).contains(timezoneNameValue)) {
                        errMsg = String.format("%s timezoneName value=(%s) is not a valid Timezone ID", objectClass, timezoneNameValue);
                    }
                    
                }
                
            }
        } catch (NoSuchFieldException nsfe) {
            errMsg = String.format("NoSuchFieldException: %s does not have field TimezoneName", objectClass);
        } catch (SecurityException se) {
            errMsg = String.format("SecurityException: %s security violation", objectClass);
        } catch (IllegalAccessException iae) {
            errMsg = String.format("IllegalAccessException: %s enforcing Java language access control and the timezoneName field is inaccessible", 
                    objectClass);
        }
        
        return errMsg;
    }
}
