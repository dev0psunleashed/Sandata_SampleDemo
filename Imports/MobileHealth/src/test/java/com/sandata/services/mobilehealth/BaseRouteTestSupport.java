package com.sandata.services.mobilehealth;

import org.apache.camel.test.blueprint.CamelBlueprintTestSupport;

public abstract class BaseRouteTestSupport extends CamelBlueprintTestSupport {
    
    @Override
    protected String getBlueprintDescriptor() {
        return "/OSGI-INF/blueprint/blueprint-test.xml";
    }
    
    @Override
    protected String getBundleFilter() {
      /* prevent sandata-commons bundle from starting */
      return "(!(Bundle-SymbolicName=sandata-commons))";
    }

}
