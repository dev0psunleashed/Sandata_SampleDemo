package com.sandata.lab.tools.oracle.main;

import com.sandata.lab.tools.oracle.creator.CSharpClassCreator;
import com.sandata.lab.tools.oracle.creator.JavaClassCreator;
import com.sandata.lab.tools.oracle.mapper.*;
import com.sandata.lab.util.process.XJCProcess;

/**
 * Date: 8/30/15
 * Time: 5:49 AM
 */

public class CreateModel {

    public static void main(String[] args) throws Exception {

        // Create Java Model classes from Logical Model XSD
        XJCProcess.getInstance().create();

        System.out.println("XJCProcess: **** Complete! ****");

        // Process Created Java Model classes
        JavaClassCreator javaClassCreator = JavaClassCreator.getInstance();
        javaClassCreator.process();

        System.out.println("JavaClassCreator: **** Complete! ****");

        // Create C# Model from Java Model classes
        CSharpClassCreator cSharpClassCreator = CSharpClassCreator.getInstance();
        cSharpClassCreator.setSourceFiles(javaClassCreator.getModelFiles());
        cSharpClassCreator.process();

        // Create mapping annotations
        /*PatientModelMapper.getInstance().process();
        EligibilityModelMapper.getInstance().process();
        ScheduleModelMapper.getInstance().process();
        ReferralMapper.getInstance().process();
        ReferenceMapper.getInstance().process();
        AuthMapper.getInstance().process();
        BillingRateMapper.getInstance().process();
        PayerMapper.getInstance().process();*/

        System.out.println("CSharpClassCreator: **** Complete! ****");
    }
}
