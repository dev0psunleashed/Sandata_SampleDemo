package com.sandata.lab.rest.payroll;

import com.sandata.lab.rest.payroll.utils.data.ServiceListUtil;
import org.junit.Test;
import org.springframework.util.Assert;

/**
 * Short description of the class.
 * <p/>
 * (Optional) Additional detail about the class that we may want to document
 *
 * @author David Rutgos
 */
public class ServiceListUtilTests extends BaseTestSupport {

    @Test
    public void should_convert_service_list_to_plsql_list_in_clause_single() throws Exception {

        Assert.isTrue(ServiceListUtil.FormatToSqlList(null) == null);
        Assert.isTrue(ServiceListUtil.FormatToSqlList("") == null);
        Assert.isTrue(ServiceListUtil.FormatToSqlList("rn").equals("'RN'"));
        Assert.isTrue(ServiceListUtil.FormatToSqlList("HhA,PCa,rn").equals("'HHA','PCA','RN'"));
    }

    @Override
    protected void onSetup() throws Exception {

    }
}
