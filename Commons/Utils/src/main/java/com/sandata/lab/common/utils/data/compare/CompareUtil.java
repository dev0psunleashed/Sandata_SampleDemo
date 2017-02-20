package com.sandata.lab.common.utils.data.compare;

import com.sandata.lab.common.utils.java.JavaUtil;
import com.sandata.lab.data.model.data.Compare;
import com.sandata.lab.data.model.data.CompareResult;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Date: 9/5/16
 * Time: 8:26 PM
 */

public class CompareUtil {

    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static List<CompareResult> Compare(Compare compare) {

        if (compare == null) {
            return null;
        }

        if (compare.getOriginal() == null
                && compare.getUpdated() == null) {
            return null;
        }

        if (compare.getUpdated() == null) {
              return null;
        }

        List<CompareResult> result = new ArrayList<>();

        // This is a new record and we will only mark the created timestamp
        if (compare.getOriginal() == null
                && compare.getUpdated() != null) {

            CompareResult compareResult = new CompareResult();
            result.add(compareResult);

            try {

                Field field = compare.getUpdated().getClass().getDeclaredField("recordCreateTimestamp");
                field.setAccessible(true);

                compareResult.setDataPoint(JavaUtil.LowerToCamelUppercase(field.getName()));
                Date date = (Date) field.get(compare.getUpdated());
                if (date != null) {
                    compareResult.setUpdatedValue(simpleDateFormat.format(date));

                } else {
                    compareResult.setUpdatedValue(simpleDateFormat.format(new Date()));
                }

            } catch (NoSuchFieldException | IllegalAccessException e) {

                // The object doesn't have the RecordCreateTimestamp field for some reason!
                compareResult.setDataPoint("RecordCreateTimestamp");
                compareResult.setUpdatedValue(simpleDateFormat.format(new Date()));
            }

        } else {

            if (!compare.getUpdated().getClass().equals(compare.getOriginal().getClass())) {

                return null;
            }

            Field[] originalFields = compare.getOriginal().getClass().getDeclaredFields();
            for (Field originalField : originalFields) {
                originalField.setAccessible(true);

                if (originalField.getName().contains("serialVersionUID")) {
                    continue;
                }

                if (originalField.getType().getName().equals("java.util.List")) {
                    continue;
                }

                try {
                    Field updatedField = compare.getUpdated().getClass().getDeclaredField(originalField.getName());
                    updatedField.setAccessible(true);

                    Object original = originalField.get(compare.getOriginal());
                    Object updated = updatedField.get(compare.getUpdated());

                    if (original != null && updated != null
                            && !original.equals(updated)) {

                        CompareResult compareResult = new CompareResult();
                        compareResult.setDataPoint(JavaUtil.LowerToCamelUppercase(originalField.getName()));

                        if (original instanceof Date) {
                            Date originalDate = (Date)original;
                            Date updateDate = (Date)updated;

                            String originalDateString = simpleDateFormat.format(originalDate);
                            String updatedDateString = simpleDateFormat.format(updateDate);

                            if (!originalDateString.equals(updatedDateString)) {
                                compareResult.setOriginalValue(originalDateString);
                                compareResult.setUpdatedValue(updatedDateString);
                                result.add(compareResult);
                            }

                        } else {

                            compareResult.setOriginalValue(original.toString());
                            compareResult.setUpdatedValue(updated.toString());
                            result.add(compareResult);
                        }
                    }
                    else if (original == null && updated != null) {

                        CompareResult compareResult = new CompareResult();
                        compareResult.setDataPoint(JavaUtil.LowerToCamelUppercase(originalField.getName()));

                        if (updated instanceof Date) {
                            Date updateDate = (Date)updated;
                            compareResult.setUpdatedValue(simpleDateFormat.format(updateDate));

                        } else {

                            compareResult.setUpdatedValue(updated.toString());
                        }

                        result.add(compareResult);
                    }
                    else if (original != null && updated == null) {

                        CompareResult compareResult = new CompareResult();
                        compareResult.setDataPoint(JavaUtil.LowerToCamelUppercase(originalField.getName()));

                        if (original instanceof Date) {
                            Date originalDate = (Date)original;
                            compareResult.setOriginalValue(simpleDateFormat.format(originalDate));

                        } else {

                            compareResult.setOriginalValue(original.toString());
                        }

                        result.add(compareResult);
                    }

                } catch (Exception e) {

                    // Shouldn't happen...
                    e.printStackTrace();
                }
            }
        }

        return result;
    }
}
