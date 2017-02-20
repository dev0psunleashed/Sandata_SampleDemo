package com.sandata.lab.eligibility.leveldb;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.UUID;

import org.iq80.leveldb.DB;
import org.iq80.leveldb.Options;
import org.iq80.leveldb.impl.Iq80DBFactory;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.data.model.dl.model.Eligibility;

public class LevelDbStorageServiceTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(LevelDbStorageServiceTest.class);
    private static final String FILE_NAME = "target/sandata-eligibility-process/level-db/eligibility";

    @Test
    public void testLevelDbStorageService_should_successfully_put_get_and_delete() {
        // arrange
        final String key = UUID.randomUUID().toString();
        Eligibility expectedEligibility = new Eligibility();
        expectedEligibility.setEligibilitySK(BigInteger.TEN);
        expectedEligibility.setBenefitAmount(BigDecimal.valueOf(123.45));

        LevelDbStorageService levelDbStorageService = new LevelDbStorageService(FILE_NAME);

        // act: put, get, delete and then get to check for the same key

        // put
        levelDbStorageService.put(key, expectedEligibility);

        // get
        Eligibility actualEligibilityAfterPut = levelDbStorageService.get(key, Eligibility.class);

        // delete
        levelDbStorageService.delete(key);
        Eligibility actualEligibilityAfterDelete = levelDbStorageService.get(key, Eligibility.class);

        // assert
        assertThat(actualEligibilityAfterPut, notNullValue());
        assertThat(actualEligibilityAfterPut.getEligibilitySK(), equalTo(expectedEligibility.getEligibilitySK()));
        assertThat(actualEligibilityAfterPut.getBenefitAmount(), equalTo(expectedEligibility.getBenefitAmount()));
        assertThat(actualEligibilityAfterDelete, nullValue());
    }

    @Test
    public void testGet_should_not_throw_exception_when_key_not_in_level_db() {
        // arrange
        LevelDbStorageService levelDbStorageService = new LevelDbStorageService(FILE_NAME);

        // act
        Eligibility eligibility = levelDbStorageService.get("key not found", Eligibility.class);

        // assert
        assertThat(eligibility, nullValue());
    }

    @Test()
    public void testDelete_should_not_throw_exception_when_key_not_in_level_db() {
        // arrange
        LevelDbStorageService levelDbStorageService = new LevelDbStorageService(FILE_NAME);

        try {
            // act
            levelDbStorageService.delete("not exist key");
        } catch (Exception e) {
            fail("Exception is not expected, but it was thrown");
        }
    }

    @Test
    public void testSetMaxRetry_should_set_to_zero_when_max_retry_is_less_than_zero () {
        // arrange
        LevelDbStorageService levelDbStorageService = new LevelDbStorageService(FILE_NAME);

        // act
        levelDbStorageService.setMaxRetry(-1);

        // assert
        assertThat(levelDbStorageService.getMaxRetry(), equalTo(0));
    }

    @Test
    public void testSetMaxRetry_should_set_to_what_value_is_passed_when_max_retry_is_greater_than_zero () {
        // arrange
        LevelDbStorageService levelDbStorageService = new LevelDbStorageService(FILE_NAME);

        // act
        levelDbStorageService.setMaxRetry(5);

        // assert
        assertThat(levelDbStorageService.getMaxRetry(), equalTo(5));
    }

    @Test
    public void testSetRetryWait_should_set_to_zero_when_retry_wait_is_less_than_zero () {
        // arrange
        LevelDbStorageService levelDbStorageService = new LevelDbStorageService(FILE_NAME);

        // act
        levelDbStorageService.setRetryWait(-2);

        // assert
        assertThat(levelDbStorageService.getRetryWait(), equalTo(0));
    }

    @Test
    public void testSetRetryWait_should_set_to_what_value_is_passed_when_retry_wait_is_greater_than_zero () {
        // arrange
        LevelDbStorageService levelDbStorageService = new LevelDbStorageService(FILE_NAME);

        // act
        levelDbStorageService.setRetryWait(500);

        // assert
        assertThat(levelDbStorageService.getRetryWait(), equalTo(500));
    }

    @Test
    public void testPut_should_throw_IllegalArgumentException_when_value_is_null() {
        // arrange
        LevelDbStorageService levelDbStorageService = new LevelDbStorageService(FILE_NAME);

        try {
            // act
            levelDbStorageService.put("test key", null);

        } catch(IllegalArgumentException e) {
            // assert
            assertThat(e.getMessage(), equalTo("value must not be null"));
            return;
        }

        fail("IllegalArgumentException is expected to be thrown, but it was not.");
    }

    @Test
    public void testGetDb_should_throw_SandataRuntimeException_when_db_file_is_locked_after_max_retries() throws IOException {
        // arrange
        LevelDbStorageService levelDbStorageService = new LevelDbStorageService(FILE_NAME);
        levelDbStorageService.setMaxRetry(2);
        levelDbStorageService.setRetryWait(500);

        File file = new File(FILE_NAME);
        file.getParentFile().mkdirs();
        // lock DB file before testing to mimic OverlappingFileLockException
        try (DB db = Iq80DBFactory.factory.open(new File(FILE_NAME), new Options().createIfMissing(true))) {

            try {
                // act
                levelDbStorageService.put("testKey", "testValue");

            } catch (SandataRuntimeException e) {
                // assert
                assertThat(e.getMessage(),
                        allOf(containsString("Give up after 2 retries. Error when opening LevelDB with file"),
                                containsString(new File(FILE_NAME).toString())));
                return;
            }

            fail("SandataRuntimeException is expected to be thrown, but it was not.");

        } catch (Exception e) {
            LOGGER.error("An error happens when opening LevelDB file before testing", e);
            fail("Cannot run the test as cannot arrange data before testing");
        }
    }
}
