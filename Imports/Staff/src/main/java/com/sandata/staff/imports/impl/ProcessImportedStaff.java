package com.sandata.staff.imports.impl;

import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.common.utils.log.SandataLogger;
import com.sandata.lab.data.model.response.Response;
import com.sandata.lab.data.model.staff.Staff;
import com.sandata.staff.imports.data.StaffResponse;
import com.sandata.staff.imports.services.StaffRestServiceProxy;
import com.sandata.staff.imports.utils.log.StaffImportLogger;
import org.apache.camel.Exchange;

import java.util.ArrayList;

public class ProcessImportedStaff
{
    private StaffRestServiceProxy staffRestServiceProxy;

    private SandataLogger staffImportLogger = StaffImportLogger.CreateLogger();

    public void setStaffRestServiceProxy(StaffRestServiceProxy staffRestServiceProxy) {
        this.staffRestServiceProxy = staffRestServiceProxy;
    }


    public void processImportedStaff(final Exchange exchange) throws Exception
    {

        try {

            staffImportLogger.logger().info("Starting processing of imported staff...");

            ArrayList<Staff> staff = (ArrayList<Staff>) exchange.getIn().getBody();

            if (staff.size() > 0)
            {
               StaffResponse response =  sendStaffToStaffIntakeService(staff);
            }
        }
        catch(Exception ex)
        {
            throw ex;
        }
    }

    private StaffResponse sendStaffToStaffIntakeService(ArrayList<Staff> staff) throws Exception
    {
       Response response = staffRestServiceProxy.postStaffImports(staff);

        if(response != null)
        {
            return (StaffResponse) response.getData();
        }
        else
        {
            staffImportLogger.logger().error("There was an error when sending staff to intake service after import");
            throw new SandataRuntimeException(String.format("exportPatients: ERROR: %s", "No response from the StaffIntakeProxy"));
        }
    }

}
