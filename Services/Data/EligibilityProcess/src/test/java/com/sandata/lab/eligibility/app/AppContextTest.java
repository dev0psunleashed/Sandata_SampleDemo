package com.sandata.lab.eligibility.app;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

import org.apache.camel.CamelContext;
import org.hamcrest.CoreMatchers;
import org.junit.Test;

public class AppContextTest {

    @Test
    public void testGetContext_return_null_when_not_initialized() {
        // act
        CamelContext context = AppContext.getContext();

        // assert
        assertThat(context, nullValue());
    }

    @Test
    public void testInitCamelContext_should_return_context_passed_in_param() {
        // arrange
        CamelContext contextMock = mock(CamelContext.class);

        // act
        CamelContext context = AppContext.initCamelContext(contextMock);

        // assert
        assertThat(context, CoreMatchers.is(contextMock));
    }
}
