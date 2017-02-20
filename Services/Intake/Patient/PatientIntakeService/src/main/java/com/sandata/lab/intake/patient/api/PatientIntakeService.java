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

package com.sandata.lab.intake.patient.api;

import org.apache.camel.Exchange;

/**
 * Staff intake service that defines the CRUD interface.
 * <p/>
 *
 * @author David Rutgos
 */
public interface PatientIntakeService {

    public void insertPatient(Exchange exchange);
    public void insertPatients(Exchange exchange);
    public void updatePatient(Exchange exchange);
    public void updatePatients(Exchange exchange);
    public void deletePatient(Exchange exchange);
    public void deletePatients(Exchange exchange);
}
