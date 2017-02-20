
package com.sandata.lab.dl.loc;
import com.sandata.lab.common.utils.data.provider.GSONProvider;
import com.sandata.lab.dl.loc.services.GoogleRestServiceProxy;
import com.sandata.lab.dl.loc.services.google.GeoCoderService;
import org.junit.Before;
import org.junit.Test;


import java.util.Calendar;
import java.util.GregorianCalendar;


/**
 * Created by Ralph on 1/11/2016.
 */
public class GeoCodeServiceIntegrationTest
{

    private GoogleRestServiceProxy googleRestServiceProxy;

    @Before
    public void setUp()
    {
        googleRestServiceProxy = new GoogleRestServiceProxy();
        googleRestServiceProxy.setGsonProvider(new GSONProvider());
    }


    @Test
    public void getCoordinatesFromAddress() throws Exception {

        googleRestServiceProxy.geoCodeAddress("26 Harbor Park Drive Port Washington NY");



    }

}