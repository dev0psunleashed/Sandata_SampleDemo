package com.sandata.lab.common.utils.query;

import com.sandata.lab.common.utils.string.StringUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Query helper utility methods.
 * <p/>
 *
 * @author David Rutgos
 */
public class QueryUtil {

    public static String ParamList(int count) {
        if (count <= 0) {
            return null;
        }

        StringBuilder params = new StringBuilder("(");
        for(int i = 0; i < count - 1; i++) {
            params.append("?,");
        }

        params.append("?)");
        return params.toString();
    }

    public static List<Object> DelimStringToParams(String data, String delimiter) {

        List<Object> params = new ArrayList<>();
        if (!StringUtil.IsNullOrEmpty(data)
                && !StringUtil.IsNullOrEmpty(data)) {

            String[] paramsArray = data.split(delimiter);
            for (String param : paramsArray) {
                params.add(param.toUpperCase());
            }
        }

        return params;
    }
}
