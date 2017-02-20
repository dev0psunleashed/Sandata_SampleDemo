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

import com.sandata.lab.data.model.staff.Staff;
import com.sandata.lab.data.model.wcf.staff.WcfStaff;
import com.sandata.lab.wcf.intake.BaseTestSupport;
import mockit.integration.junit4.JMockit;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.util.Assert;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Tests the mapping of WCF Staff entity.
 * <p/>
 *
 * @author David Rutgos
 */
@RunWith(JMockit.class)
public class StaffMapperTests extends BaseTestSupport {

    private Staff staff;

    private StaffMapper staffMapper;

    @Test
    public void should_map_wcf_staff_entity_to_normalized_staff_entity() throws Exception {

        WcfStaff wcfStaff = staffMapper.map(staff);

        Assert.notNull(wcfStaff);
        Assert.isTrue(wcfStaff.getFirstName().equals(staff.getFirstName()));
        Assert.isTrue(wcfStaff.getLastName().equals(staff.getLastName()));
        Assert.isTrue(wcfStaff.getMiddleInitial().equals(staff.getMiddleInitial()));
    }
    
    @Override
    protected void onSetup() throws Exception {

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);

        staff = new Staff();

        staff.setFirstName("David");
        staff.setLastName("Rutgos");
        staff.setMiddleInitial("M");
        staff.setEmail("drutgos@sandata.com");
        staff.setCellPhone("(912)-616-5337");
        staff.setHomePhone("(230)-898-7825");
        staff.setAddress1("1675 East 18th Street");
        staff.setAddress2("JMeter: Test Staff");
        staff.setCity("Brooklyn");
        staff.setZip("11229");
        staff.setIsOvernightOk(true);
        staff.setAgencyId(1);

        Date dob = dateFormat.parse("1974-04-03 00:00:00");
        staff.setDateOfBirth(dob);

        staff.setSsn("816-46-9325");

        staff.setType("Nurse");
        staff.setMaritalStatus("Single");
        staff.setGender("Female");
        staff.setLanguage("Russian");
        staff.setEthnicity("Asian");
        staff.setReligion("Christian");
        staff.setState("NY");

        Date firstDay = dateFormat.parse("2013-07-03 00:00:00");
        staff.setFirstDayWorked(firstDay);

        staff.setAgencyLocation("");

        Date releaseDate = dateFormat.parse("2015-07-14 00:00:00");
        staff.setReleasedDate(releaseDate);

        staffMapper = new StaffMapper(LookupTableFieldMapper.getInstance());
    }
}
