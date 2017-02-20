package com.sandata.lab.dl.doc.utils;

import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import org.joda.time.LocalDateTime;

import java.io.File;

/**
 * Helper methods for handling files.
 * <p/>
 *
 * @author David Rutgos
 */
public class FileUtil {

    public static void createDirectory(String directoryPath) throws SandataRuntimeException {

        try {
            File directory = new File(directoryPath);
            if(!directory.exists()) {
                boolean result = directory.mkdirs();
                if(!result) {
                    throw new SandataRuntimeException(String.format("FileUtil: Could not create [%s] path!", directoryPath));
                }
            }
        }
        catch (Exception e) {
            throw new SandataRuntimeException(String.format("FileUtil: createDirectory: %s", e.getMessage()), e);
        }
    }

    public static String timeStampFileName(String objectName, String fileExtension) {

        return timeStampFileName(objectName, fileExtension, LocalDateTime.now());
    }

    public static String timeStampFileName(String objectName, String fileExtension, LocalDateTime localDateTime) {

        String month = String.format("%02d", localDateTime.getMonthOfYear());
        String day = String.format("%02d", localDateTime.getDayOfMonth());


        String currentDateTimeString = String.format("%d%s%s-%d%d%d.%d",
                localDateTime.getYear(),
                month,
                day,
                localDateTime.getHourOfDay(),
                localDateTime.getMinuteOfHour(),
                localDateTime.getSecondOfMinute(),
                localDateTime.getMillisOfSecond());

        return String.format("%s-%s.%s", objectName, currentDateTimeString, fileExtension);
    }
}
