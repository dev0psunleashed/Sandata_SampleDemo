package com.sandata.lab.wcf.export.data.map;

import com.sandata.lab.common.utils.error.AbstractErrorHandler;
import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.data.model.schedule.Schedule;
import com.sandata.lab.data.model.wcf.schedule.WcfSchedule;
import com.sandata.lab.wcf.export.utils.log.WcfExportServiceLogger;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Map WcfSchedule entity to Schedule entity.
 * <p/>
 *
 * @author David Rutgos
 */
public class WcfScheduleMapper extends AbstractErrorHandler {

    public WcfScheduleMapper() {
        super(WcfExportServiceLogger.CreateLogger());
    }

    public List<Schedule> map(final List<WcfSchedule> wcfSchedules) throws SandataRuntimeException {

        List<Schedule> schedules = null;

        try {
            schedules = new ArrayList<>();

            for(WcfSchedule wcfSchedule : wcfSchedules) {
                Schedule schedule = map(wcfSchedule);
                schedules.add(schedule);
            }
        }
        catch (Exception e) {
            handleFatalError(e, "map(List<WcfSchedule>)");
        }

        return schedules;
    }

    public Schedule map(final WcfSchedule wcfSchedule) throws SandataRuntimeException {

        String methodName = logger.getMethodName();
        logger.setMethodName("map");

        Schedule schedule = null;

        try {
            schedule = new Schedule();

            for (Field scheduleField : schedule.getClass().getDeclaredFields()) {

                try {

                    // skip the serialVersionUID
                    if (scheduleField.getName().equals("serialVersionUID")) {
                        continue;
                    }

                    Field wcfField = wcfSchedule.getClass().getDeclaredField(scheduleField.getName());
                    wcfField.setAccessible(true);
                    scheduleField.setAccessible(true);
                    Object value = wcfField.get(wcfSchedule);
                    scheduleField.set(schedule, value);
                }
                catch (NoSuchFieldException nsfe) {

                    // Keep this here to make sure that new fields weren't introduced and not mapped
                    logger.warn(String.format("Reflection: Field: [%s]: Exception: %s",
                            scheduleField.getName(), nsfe.getMessage()));
                }
            }
        }
        catch (Exception e) {
            handleFatalError(e, "map(WcfSchedule)");
        }

        logger.setMethodName(methodName);
        return schedule;
    }
}
