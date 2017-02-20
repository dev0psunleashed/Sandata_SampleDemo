package com.sandata.lab.eligibility.utils.data;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.sandata.lab.eligibility.model.inquiry.ClientFileDetail;
import com.sandata.lab.eligibility.model.inquiry.Gender;

public class DataMapperTest {

    @Test
    public void testMap_should_map_gender_type_name_male_to_gender_male() {
        // arrange
        Map<String, Object> record = new HashMap<String, Object>();
        record.put("PT_GENDER_TYP_NAME", "MALE");

        DataMapper dataMapper = new DataMapper();

        // act
        ClientFileDetail clientFileDetail = (ClientFileDetail) dataMapper.map(record,
                "com.sandata.lab.eligibility.model.inquiry.ClientFileDetail");

        // assert
        assertThat(clientFileDetail, notNullValue());
        assertThat(clientFileDetail.getSubscriberGender(), equalTo(Gender.Male));
        assertThat(clientFileDetail.getPatientGender(), equalTo(Gender.Male));
    }

    @Test
    public void testMap_should_map_gender_type_name_female_to_gender_female() {
        // arrange
        Map<String, Object> record = new HashMap<String, Object>();
        record.put("PT_GENDER_TYP_NAME", "FEMALE");

        DataMapper dataMapper = new DataMapper();

        // act
        ClientFileDetail clientFileDetail = (ClientFileDetail) dataMapper.map(record,
                "com.sandata.lab.eligibility.model.inquiry.ClientFileDetail");

        // assert
        assertThat(clientFileDetail, notNullValue());
        assertThat(clientFileDetail.getSubscriberGender(), equalTo(Gender.Female));
        assertThat(clientFileDetail.getPatientGender(), equalTo(Gender.Female));
    }

    @Test
    public void testMap_should_map_gender_type_name_other_to_gender_unknown() {
        // arrange
        Map<String, Object> record = new HashMap<String, Object>();
        record.put("PT_GENDER_TYP_NAME", "OTHER");

        DataMapper dataMapper = new DataMapper();

        // act
        ClientFileDetail clientFileDetail = (ClientFileDetail) dataMapper.map(record,
                "com.sandata.lab.eligibility.model.inquiry.ClientFileDetail");

        // assert
        assertThat(clientFileDetail, notNullValue());
        assertThat(clientFileDetail.getSubscriberGender(), equalTo(Gender.Unknown));
        assertThat(clientFileDetail.getPatientGender(), equalTo(Gender.Unknown));
    }

    @Test
    public void testMap_should_map_gender_type_name_invalid_to_gender_unknown() {
        // arrange
        Map<String, Object> record = new HashMap<String, Object>();
        record.put("PT_GENDER_TYP_NAME", "invalid");

        DataMapper dataMapper = new DataMapper();

        // act
        ClientFileDetail clientFileDetail = (ClientFileDetail) dataMapper.map(record,
                "com.sandata.lab.eligibility.model.inquiry.ClientFileDetail");

        // assert
        assertThat(clientFileDetail, notNullValue());
        assertThat(clientFileDetail.getSubscriberGender(), equalTo(Gender.Unknown));
        assertThat(clientFileDetail.getPatientGender(), equalTo(Gender.Unknown));
    }
}
