package com.sandata.one.aggregator.lookup.utils.data;

import com.sandata.lab.common.utils.exception.SandataRuntimeException;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

/**
 * Helper class to return string collection of reference values.
 * <p/>
 *
 * @author David Rutgos
 */
@SuppressWarnings("unchecked")
public class ReferenceValueUtil {

    private static String SANDATA_MODEL_DL_PACKAGE = "com.sandata.lab.data.model.dl.model";

    public static List GetReferenceValueList(String referenceEntity) throws SandataRuntimeException {

        try {
            String entityClass = String.format("%s.%s", SANDATA_MODEL_DL_PACKAGE, referenceEntity);
            return new ArrayList<>(EnumSet.allOf((Class<? extends Enum>)Class.forName(entityClass)));

        } catch (Exception e) {
            e.printStackTrace();
            throw new SandataRuntimeException(String.format("%s: EXCEPTION: GetReferenceValueList: %s",
                    ReferenceValueUtil.class.getName(), e.getMessage()), e);
        }
    }
}
