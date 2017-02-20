/*
 * Copyright (c) 2015. Sandata Technologies, LLC
 * 26 Harbor Park Drive, Port Washington, NY 11050, 800-544-7263
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of Sandata Technologies, LLC
 * ("Confidential Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered into with
 * Sandata.
 */

package com.sandata.lab.wcf.intake.data.map;

import com.sandata.lab.common.utils.error.AbstractErrorHandler;
import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.common.utils.log.SandataLogger;
import com.sandata.lab.data.model.staff.Staff;
import com.sandata.lab.data.model.wcf.staff.WcfStaff;
import com.sandata.lab.wcf.intake.utils.log.WcfIntakeServiceLogger;

import java.lang.reflect.Field;

/**
 * Map Staff entity to WcfStaff entity.
 * <p/>
 *
 * @author David Rutgos
 */
public class StaffMapper extends AbstractErrorHandler {

    private final LookupTableFieldMapper lookupTableFieldMapper;

    public StaffMapper(final LookupTableFieldMapper lookupTableFieldMapper) {
        super(WcfIntakeServiceLogger.CreateLogger());

        this.lookupTableFieldMapper = lookupTableFieldMapper;
    }

    //TODO: Reminder: Look into using Dozer as oru mapper.

    public WcfStaff map(final Staff staff) throws SandataRuntimeException {

        String methodName = logger.getMethodName();
        logger.setMethodName("map");

        WcfStaff wcfStaff = null;

        try {
            wcfStaff = new WcfStaff();

            for (Field wcfField : wcfStaff.getClass().getDeclaredFields()) {

                try {
                    // skip the serialVersionUID
                    if (wcfField.getName().equals("serialVersionUID")) {
                        continue;
                    }

                    Field staffField = staff.getClass().getDeclaredField(wcfField.getName());
                    staffField.setAccessible(true);
                    wcfField.setAccessible(true);
                    Object value = staffField.get(staff);
                    wcfField.set(wcfStaff, value);
                }
                catch (NoSuchFieldException nsfe) {

                    if (! lookupTableFieldMapper.isLookupField(wcfField.getName())) {
                        // Keep this here to make sure that new fields weren't introduced and not mapped
                        logger.warn(String.format("Reflection: Field: [%s]: Exception: %s",
                                wcfField.getName(), nsfe.getMessage()));
                    }
                    else {

                        try {
                            Field staffField = staff.getClass().getDeclaredField(
                                    lookupTableFieldMapper.stripId(wcfField.getName()));

                            staffField.setAccessible(true);
                            wcfField.setAccessible(true);

                            Object value = staffField.get(staff);
                            wcfField.set(wcfStaff, lookupTableFieldMapper.mapFieldToId(wcfField.getName(), value));
                        }
                        catch (Exception e) {
                            // Keep this here to make sure that new fields weren't introduced and not mapped
                            logger.warn(String.format("Reflection: Field: [%s]: Exception: %s",
                                    wcfField.getName(), nsfe.getMessage()));
                        }
                    }
                }
            }
        }
        catch (Exception e) {
            handleFatalError(e, "map");
        }

        logger.setMethodName(methodName);
        return wcfStaff;
    }
}
