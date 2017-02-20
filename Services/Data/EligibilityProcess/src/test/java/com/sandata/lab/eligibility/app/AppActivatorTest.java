package com.sandata.lab.eligibility.app;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.SQLException;
import java.util.Hashtable;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggingEvent;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.Version;

public class AppActivatorTest {

    private AppenderSkeleton logAppenderMock;
    private BundleContext bundleContextMock;

    @Before
    public void setupMocks() throws SQLException {
        this.logAppenderMock = mock(AppenderSkeleton.class);
        Logger.getRootLogger().addAppender(this.logAppenderMock);

        Version versionMock = mock(Version.class);
        when(versionMock.getQualifier()).thenReturn("testQualifier");
        when(versionMock.getMajor()).thenReturn(1);
        when(versionMock.getMinor()).thenReturn(2);
        when(versionMock.getMicro()).thenReturn(3);

        Bundle bundleMock = mock(Bundle.class);
        when(bundleMock.getVersion()).thenReturn(versionMock);
        when(bundleMock.getHeaders()).thenReturn(new Hashtable<String, String>());
        when(bundleMock.getSymbolicName()).thenReturn("bundle-test");
        
        this.bundleContextMock = Mockito.mock(BundleContext.class);
        when(this.bundleContextMock.getBundle()).thenReturn(bundleMock);
    }

    @Test
    public void testStart() throws Exception {
        // arrange
        ArgumentCaptor<LoggingEvent> loggingEventCaptor = ArgumentCaptor.forClass(LoggingEvent.class);
        AppActivator appActivator = new AppActivator();

        // act
        appActivator.start(bundleContextMock);

        // assert
        verify(this.logAppenderMock, atLeastOnce()).doAppend(loggingEventCaptor.capture());
        assertThat(loggingEventCaptor.getValue().getRenderedMessage(), startsWith("Started:"));
        assertThat(loggingEventCaptor.getValue().getLevel(), equalTo(Level.INFO));
    }

    @Test
    public void testStop() throws Exception {
        // arrange
        ArgumentCaptor<LoggingEvent> loggingEventCaptor = ArgumentCaptor.forClass(LoggingEvent.class);
        AppActivator appActivator = new AppActivator();

        // act
        appActivator.stop(bundleContextMock);

        // assert
        verify(this.logAppenderMock, atLeastOnce()).doAppend(loggingEventCaptor.capture());
        assertThat(loggingEventCaptor.getValue().getRenderedMessage(), startsWith("Stopped:"));
        assertThat(loggingEventCaptor.getValue().getLevel(), equalTo(Level.INFO));
    }
}
