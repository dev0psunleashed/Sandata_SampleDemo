package com.sandata.staff.imports;

import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.common.utils.log.SandataLogger;
import com.sandata.lab.data.model.staff.Staff;
import com.sandata.staff.imports.impl.ProcessImportedStaff;
import com.sandata.staff.imports.services.StaffRestServiceProxy;
import com.sandata.staff.imports.utils.log.StaffImportLogger;
import mockit.Expectations;
import mockit.Mocked;
import mockit.integration.junit4.JMockit;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.ArrayList;

@RunWith(JMockit.class)
public class ProcessImportStaffTest extends BaseTestSupport
{
    private ProcessImportedStaff processImportedStaff;

    @Mocked(stubOutClassInitialization = true)
    private StaffImportLogger staffImportLogger;

    @Mocked(stubOutClassInitialization = true)
    private StaffRestServiceProxy staffRestServiceProxy;

    @Override
    protected void onSetup() throws IOException {

    }

    @Before
    public void setup()
    {
       processImportedStaff = new ProcessImportedStaff();
        processImportedStaff.setStaffRestServiceProxy(staffRestServiceProxy);
    }

    @Test
    public void staff_data_is_empty_and_staff_proxy_endpoint_is_not_called() throws Exception
    {
        final ArrayList<Staff> staff = new ArrayList<>();

        new Expectations() {{ // an "expectation block"

            staffRestServiceProxy.postStaffImports(staff);
            times = 0;

        }};

        exchange.getIn().setBody(staff);

        processImportedStaff.processImportedStaff(exchange);
    }

    @Test(expected = SandataRuntimeException.class)
    public void staffintakerestservice_return_null_and_exception_is_thrown() throws Exception
    {
        final ArrayList<Staff> staff = new ArrayList<>();

        Staff staff1 = new Staff();

        staff.add(staff1);

        new Expectations() {{ // an "expectation block"

            // Record an expectation, with a given value to be returned:
            staffRestServiceProxy.postStaffImports(staff); result = null;
            times = 1;

            staffImportLogger.logger().error("There was an error when sending staff to intake service after import");
            times = 1;

        }};

        exchange.getIn().setBody(staff);

        processImportedStaff.processImportedStaff(exchange);
    }


}
