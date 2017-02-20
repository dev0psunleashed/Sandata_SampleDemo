package com.sandata.lab.auth.impl;

import com.sandata.lab.auth.BaseTestSupport;
import com.sandata.lab.data.model.dl.model.ApplicationUserRole;
import com.sandata.lab.data.model.dl.model.extended.ApplicationRoleExt;
import com.sandata.lab.data.model.dl.model.extended.ApplicationUserExt;
import com.sandata.lab.data.model.dl.model.extended.VisitEventExt;
import com.sandata.lab.security.auth.impl.RestDataService;
import com.sandata.lab.security.auth.impl.UserService;
import com.sandata.lab.security.auth.services.oracle.CoreDataService;
import mockit.Expectations;
import mockit.Mocked;
import mockit.integration.junit4.JMockit;
import static mockit.Deencapsulation.*;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@RunWith(JMockit.class)
public class UserServiceTest extends BaseTestSupport {

    private CoreDataService coreDataService;
    private RestDataService restDataService;

    private UserService userService;


    @Override
    protected void onSetup() throws IOException {

        userService = new UserService();

        coreDataService = new CoreDataService();
        restDataService = new RestDataService();

        userService.setCoreDataService(coreDataService);
        userService.setRestDataService(restDataService);
    }

    @Test
    public void transform_eight_calls_to_three_visiteventgroups() throws Exception
    {
        ApplicationUserExt applicationUserExt = new ApplicationUserExt();
        applicationUserExt.setBusinessEntityID("1");

        List<ApplicationUserRole> applicationUserRoles = new ArrayList();
        ApplicationUserRole applicationUserRole = new ApplicationUserRole();

        applicationUserRole.setApplicationRoleSK(new BigInteger("1"));
        applicationUserRole.setApplicationTenantSK(new BigInteger("1"));

        applicationUserRoles.add(applicationUserRole);

       /*

        new Expectations(visitEventDataService) {{

            invoke(visitEventDataService, "returnNewCalls", calls);
            result = newcalls; times = 1;
        }};

        List<VisitEventDNISGroup> visitEventDNISGroups = visitEventDataService.transformCallsToVisitEventGroups(null,calls);

        int groupSize = visitEventDNISGroups.size();

        assertTrue(groupSize == 3);


        for(VisitEventDNISGroup visitEventDNISGroup: visitEventDNISGroups)
        {
            int size = visitEventDNISGroup.getVisitEvents().size();

            if(visitEventDNISGroup.getDnis().equalsIgnoreCase(dnis))
            {
                assertTrue(size == 2);
            }

            if(visitEventDNISGroup.getDnis().equalsIgnoreCase(dnis2))
            {
                assertTrue(size == 2);
            }

            if(visitEventDNISGroup.getDnis().equalsIgnoreCase(dnis3))
            {
                assertTrue(size == 3);
            }

        }*/
    }





}
