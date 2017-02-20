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
import com.sandata.lab.data.model.schedule.Schedule;
import com.sandata.lab.data.model.wcf.schedule.WcfSchedule;
import com.sandata.lab.wcf.intake.utils.log.WcfIntakeServiceLogger;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Map Schedule entity to WcfSchedule entity.
 * <p/>
 *
 * @author David Rutgos
 */
public class ScheduleMapper extends AbstractErrorHandler {

    public ScheduleMapper() {
        super(WcfIntakeServiceLogger.CreateLogger());
    }

    public List<WcfSchedule> map(final List<Schedule> schedules) throws SandataRuntimeException {

        List<WcfSchedule> wcfSchedules = null;

        try {
            wcfSchedules = new ArrayList<>();

            for(Schedule schedule : schedules) {
                WcfSchedule wcfSchedule = map(schedule);
                wcfSchedules.add(wcfSchedule);
            }
        }
        catch (Exception e) {
            handleFatalError(e, "map(List<Schedule>)");
        }

        return wcfSchedules;
    }

    public WcfSchedule map(final Schedule schedule) throws SandataRuntimeException {

        String methodName = logger.getMethodName();
        logger.setMethodName("map");

        WcfSchedule wcfSchedule = null;

        try {
            wcfSchedule = new WcfSchedule();

            for (Field wcfField : wcfSchedule.getClass().getDeclaredFields()) {

                try {

                    // skip the serialVersionUID
                    if (wcfField.getName().equals("serialVersionUID")) {
                        continue;
                    }

                    Field scheduleField = schedule.getClass().getDeclaredField(wcfField.getName());
                    scheduleField.setAccessible(true);
                    wcfField.setAccessible(true);
                    Object value = scheduleField.get(schedule);
                    wcfField.set(wcfSchedule, value);
                }
                catch (NoSuchFieldException nsfe) {

                    // Keep this here to make sure that new fields weren't introduced and not mapped
                    logger.warn(String.format("Reflection: Field: [%s]: Exception: %s",
                            wcfField.getName(), nsfe.getMessage()));
                }
            }
        }
        catch (Exception e) {
            handleFatalError(e, "map");
        }

        logger.setMethodName(methodName);
        return wcfSchedule;
    }
}
