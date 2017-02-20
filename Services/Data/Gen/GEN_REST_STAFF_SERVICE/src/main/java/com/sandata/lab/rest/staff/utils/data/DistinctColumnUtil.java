package com.sandata.lab.rest.staff.utils.data;

import com.sandata.lab.data.model.dl.model.DistinctColumn;

import java.util.ArrayList;
import java.util.List;

/**
 * Helper to convert distinct column results.
 * <p/>
 *
 * @author David Rutgos
 */
public class DistinctColumnUtil {

    public static List<String> toArray(List<DistinctColumn> distinctColumns) {

        List<String> result = new ArrayList<>();

        for (DistinctColumn distinctColumn : distinctColumns) {
            result.add(distinctColumn.getResult());
        }

        return result;
    }
}
