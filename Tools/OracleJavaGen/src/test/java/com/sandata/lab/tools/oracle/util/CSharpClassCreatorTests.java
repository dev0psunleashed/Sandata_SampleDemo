package com.sandata.lab.tools.oracle.util;

import com.sandata.lab.tools.oracle.creator.CSharpClassCreator;
import org.junit.Test;

/**
 * Date: 8/29/15
 * Time: 3:10 PM
 */

public class CSharpClassCreatorTests {

    @Test
    public void should_generate_csharp_classes_from_java_model() throws Exception {

        CSharpClassCreator.getInstance().process();
    }
}
