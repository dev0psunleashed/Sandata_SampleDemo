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

#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package};

import mockit.integration.junit4.JMockit;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.util.Assert;
import java.io.IOException;

/**
 * Example tests file implementation. Delete/Rename before use!
 * <p/>
 *
 * @author David Rutgos
 */

// TODO: Delete/Rename ...
@RunWith(JMockit.class)
public class ExampleServiceTests extends BaseTestSupport {

    @Test
    public void should_do_something_and_assert_that_the_result_is_as_expected() throws Exception {
        // TODO: Write JUnit tests without external dependencies.
        // TODO: Use JMockit to inject external dependencies.
        // TODO: JUnit tests should be testing public api only.
        // TODO: Your JUnit tests represent an executable requirement.
        Assert.isTrue(true);
    }

    @Override
    protected void onSetup() throws IOException {
        // TODO: Initialize test properties
    }
}
