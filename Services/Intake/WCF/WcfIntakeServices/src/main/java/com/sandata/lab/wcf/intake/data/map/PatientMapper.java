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
import com.sandata.lab.data.model.patient.Patient;
import com.sandata.lab.data.model.wcf.patient.WcfPatient;
import com.sandata.lab.wcf.intake.utils.log.WcfIntakeServiceLogger;

import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Map Patient entity to WcfPatient entity.
 * <p/>
 *
 * @author David Rutgos
 */
public class PatientMapper extends AbstractErrorHandler {

    private final LookupTableFieldMapper lookupTableFieldMapper;

    public PatientMapper(final LookupTableFieldMapper lookupTableFieldMapper) {
        super(WcfIntakeServiceLogger.CreateLogger());

        this.lookupTableFieldMapper = lookupTableFieldMapper;
    }

    public WcfPatient map(final Patient patient) throws SandataRuntimeException {

        String methodName = logger.getMethodName();
        logger.setMethodName("map");

        WcfPatient wcfPatient = null;

        try {
            wcfPatient = new WcfPatient();

            for (Field wcfField : wcfPatient.getClass().getDeclaredFields()) {

                try {
                    // skip the serialVersionUID
                    if (wcfField.getName().equals("serialVersionUID")) {
                        continue;
                    }

                    Field patientField = patient.getClass().getDeclaredField(wcfField.getName());
                    patientField.setAccessible(true);
                    wcfField.setAccessible(true);
                    Object value = patientField.get(patient);

                    // TODO: Ask Jasmin to make dateOfBirth into a DateTime object
                    if (wcfField.getName().equals("dateOfBirth") && value != null) {

                        Date dob = (Date) value;

                        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss", Locale.ENGLISH);

                        String dobString = dateFormat.format(dob);
                        wcfField.set(wcfPatient, dobString);
                    }
                    else {

                        wcfField.set(wcfPatient, value);
                    }
                }
                catch (NoSuchFieldException nsfe) {

                    if (! lookupTableFieldMapper.isLookupField(wcfField.getName())) {
                        // Keep this here to make sure that new fields weren't introduced and not mapped
                        logger.warn(String.format("Reflection: Field: [%s]: Exception: %s",
                                wcfField.getName(), nsfe.getMessage()));
                    }
                    else {

                        try {
                            Field patientField = patient.getClass().getDeclaredField(
                                    lookupTableFieldMapper.stripId(wcfField.getName()));

                            patientField.setAccessible(true);
                            wcfField.setAccessible(true);

                            Object value = patientField.get(patient);
                            wcfField.set(wcfPatient, lookupTableFieldMapper.mapFieldToId(wcfField.getName(), value));
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
        return wcfPatient;
    }
}
