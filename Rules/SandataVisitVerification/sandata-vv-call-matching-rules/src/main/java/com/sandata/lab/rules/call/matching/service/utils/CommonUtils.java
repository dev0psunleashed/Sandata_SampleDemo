package com.sandata.lab.rules.call.matching.service.utils;

import java.util.List;

/**
 * Created by thanhxle on 10/10/2016.
 */
public class CommonUtils {

    private CommonUtils() {

    }

    /**
     *
     * @param values List of string need to be convert to string with sql format, used in IN clause
     * @return comman seperated string
     */
    public static String convertListToStringParams (List<String> values) {
        StringBuilder resultStr = new StringBuilder();
        for (int i = 0; i< values.size() ; i ++ ) {

            resultStr.append("'").append(values.get(i)).append("'");
            if(i < values.size() - 1) {
                resultStr.append(",");
            }

        }

        return resultStr.toString();
    }


    public static String buildStaffIdLikeConditions (List<String> staffIds) {
        StringBuilder staffIdLikeConditions = new StringBuilder();
        if (staffIds != null && !staffIds.isEmpty()) {
            staffIdLikeConditions.append("AND ( ");
            int counter = 0;
            for (String staffId : staffIds) {
                if (counter != 0) {
                    staffIdLikeConditions.append(" OR ");
                }
                staffIdLikeConditions.append(" STAFF_ID = '");
                staffIdLikeConditions.append(staffId);
                staffIdLikeConditions.append("'");

                counter ++;
            }

            staffIdLikeConditions.append(" )");
        }
        return staffIdLikeConditions.toString();
    }


}
