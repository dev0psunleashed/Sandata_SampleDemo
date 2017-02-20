package com.sandata.lab.rest.lookup;

import com.sandata.lab.data.model.dl.model.RateTypeName;
import com.sandata.lab.rest.lookup.utils.data.ReferenceValueUtil;
import org.junit.Test;
import org.springframework.util.Assert;

import java.util.List;

/**
 * Short description of the class.
 * <p/>
 * (Optional) Additional detail about the class that we may want to document
 *
 * @author David Rutgos
 */
public class ReferenceValueUtilTests extends BaseTestSupport {

    @Test
    public void should_convert_rate_type_name_string_to_enum_equivalent() throws Exception {

        try {
            //Object result = Enum.valueOf(RateTypeName.class, "Hourly");
            RateTypeName hourly = RateTypeName.fromValue("Hourly");
            Assert.notNull(hourly);

            RateTypeName threeHrsOrLess = RateTypeName.fromValue("Three Hours Or Less");
            Assert.notNull(threeHrsOrLess);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void should_return_list_of_enum_value_for_rate_type_name() throws Exception {

        List result = ReferenceValueUtil.GetReferenceValueList("RateTypeName");

        Assert.notNull(result);
        Assert.isTrue(result.size() == 8);
    }
    
    @Test
    public void should_return_list_of_enum_values_for_give_reference_class() throws Exception {

        List result = ReferenceValueUtil.GetReferenceValueList("ReferralTypeName");

        Assert.notNull(result);
        Assert.isTrue(result.size() == 3);
        /*Assert.isTrue(ReferralTypeName.ORGANIZATION == result.get(0));
        Assert.isTrue(ReferralTypeName.FAMILY == result.get(1));
        Assert.isTrue(ReferralTypeName.OTHER == result.get(2));*/
    }
    
    @Override
    protected void onSetup() throws Exception {
        
    }
}
