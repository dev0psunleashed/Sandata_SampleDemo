package com.sandata.lab.intake.staff.serialize;

import com.sandata.lab.common.utils.data.provider.GSONProvider;
import com.sandata.lab.data.model.staff.Staff;
import com.sandata.lab.intake.staff.BaseTestSupport;
import mockit.integration.junit4.JMockit;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.util.Assert;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Tests the GSON Serialization/Deserialization of model objects.
 * <p/>
 *
 * @author David Rutgos
 */
@RunWith(JMockit.class)
public class StaffSerializationTests extends BaseTestSupport {

    private DateFormat dateFormat;

    @Test
    public void should_serialize_a_simple_staff_object_to_json() throws Exception {

        Staff staff = new Staff();
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

        String json = new GSONProvider("yyyy-MM-dd HH:mm:ss").toJson(staff);

        Assert.notNull(json);
        Assert.isTrue(json.contains("\"firstName\": \"David\""));
        Assert.isTrue(json.contains("\"lastName\": \"Rutgos\""));
        Assert.isTrue(json.contains("\"middleInitial\": \"M\""));

        System.out.println(json);
    }

    @Override
    protected void onSetup() throws IOException {
        dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
    }
}
