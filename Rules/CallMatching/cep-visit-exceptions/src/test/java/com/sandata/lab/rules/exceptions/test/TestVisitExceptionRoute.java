package com.sandata.lab.rules.exceptions.test;

import com.sandata.lab.data.model.dl.model.VisitException;
import org.apache.camel.Exchange;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.blueprint.CamelBlueprintTestSupport;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by tom.dornseif on 4/22/2016.
 */
public class TestVisitExceptionRoute extends CamelBlueprintTestSupport {

    private Logger logger = LoggerFactory.getLogger(TestVisitExceptionRoute.class);

    private SimpleDateFormat simpleDateFormat =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    protected String getBlueprintDescriptor() {
        String tmp = super.getBlueprintDescriptor();
        return "/OSGI-INF/blueprint/blueprint.xml,/OSGI-INF/blueprint/kie.xml";
    }
/***
    @Override
    protected Properties useOverridePropertiesWithPropertiesComponent() {
        Properties properties = super.useOverridePropertiesWithPropertiesComponent();
        Object o = new Object() {
        };
        String className = o.getClass().getEnclosingClass().getName();
        String methodName = o.getClass().getEnclosingMethod().getName();
        String typeName = o.getClass().getEnclosingMethod().getReturnType().getName();

        if(properties != null) {
            for (Map.Entry p : properties.entrySet()) {
                String tmp = "property = " + p.getKey().toString() + ", value = " + p.getValue();

                org.slf4j.LoggerFactory.getLogger(o.getClass().getEnclosingClass()).info(String.format(
                        "%s : method = %s : super returned type : %s with value of %s ", className, methodName, typeName, tmp));
            }
        }
        else
        {
            properties = new Properties();
            properties.setProperty("activeMQ.endpoint.CEP_VISIT_EXCEPTIONS", "seda:CEP_VISIT_EXCEPTIONS");
            properties.setProperty("activeMQ.endpoint.CEP_VISIT_EXCEPTIONS_REQUEST", "mock:CEP_VISIT_EXCEPTIONS_REQUEST");

        }

        return properties;
    }
*/
    @Test
    public void testVisitExceptionRequests() throws Exception {
        MockEndpoint mock1 = getMockEndpoint("mock:CEP_VISIT_EXCEPTIONS_REQUEST");
        //Create 3 unique visitExceptions
        Date created = simpleDateFormat.parse("2016-04-22 10:00:05");
        Date updated = new Date(created.getTime());
        //String state = State.ACTUAL_HOURS_EXCEED_SCHEDULED_HOURS.getVisitException();
        Integer visitSk = Integer.parseInt("10001");
        for(int i = 0; i < 4;i++) {
            VisitException visitException1 = getVisitException(created, updated, visitSk, "0");
            VisitException visitException2 = getVisitException(created, updated, visitSk, "1");
            VisitException visitException3 = getVisitException(created, updated, visitSk, "2");
            VisitException visitException4 = getVisitException(created, updated, visitSk, "3");
            VisitException visitException5 = getVisitException(created, updated, visitSk, "4");
            VisitException visitException6 = getVisitException(created, updated, visitSk, "5");
            VisitException visitException7 = getVisitException(created, updated, visitSk, "6");
            VisitException visitException10 = getVisitException(created, updated, visitSk, "8");
            VisitException visitException11 = getVisitException(created, updated, visitSk, "10");
            VisitException visitException12 = getVisitException(created, updated, visitSk, "11");
            VisitException visitException13 = getVisitException(created, updated, visitSk, "18");
            VisitException visitException14 = getVisitException(created, updated, visitSk, "19");
            VisitException visitException15 = getVisitException(created, updated, visitSk, "20");
            VisitException visitException16 = getVisitException(created, updated, visitSk, "21");
            VisitException visitException17 = getVisitException(created, updated, visitSk, "29");
            VisitException visitException18 = getVisitException(created, updated, visitSk, "18");
            VisitException visitException19 = getVisitException(created, updated, visitSk, "19");
            VisitException visitException20 = getVisitException(created, updated, visitSk, "20");
            VisitException visitException21 = getVisitException(created, updated, visitSk, "21");
            VisitException visitException22 = getVisitException(created, updated, visitSk, "21");
            VisitException visitException23 = getVisitException(created, updated, visitSk, "23");
            VisitException visitException24 = getVisitException(created, updated, visitSk, "21");
            VisitException visitException25 = getVisitException(created, updated, visitSk, "29");
            template.sendBody("seda:CEP_VISIT_EXCEPTIONS", visitException1);
            template.sendBody("seda:CEP_VISIT_EXCEPTIONS", visitException2);
            template.sendBody("seda:CEP_VISIT_EXCEPTIONS", visitException3);
            template.sendBody("seda:CEP_VISIT_EXCEPTIONS", visitException4);
            template.sendBody("seda:CEP_VISIT_EXCEPTIONS", visitException5);
            template.sendBody("seda:CEP_VISIT_EXCEPTIONS", visitException6);
            template.sendBody("seda:CEP_VISIT_EXCEPTIONS", visitException7);
            template.sendBody("seda:CEP_VISIT_EXCEPTIONS", visitException10);
            template.sendBody("seda:CEP_VISIT_EXCEPTIONS", visitException11);
            template.sendBody("seda:CEP_VISIT_EXCEPTIONS", visitException12);
            template.sendBody("seda:CEP_VISIT_EXCEPTIONS", visitException13);
            template.sendBody("seda:CEP_VISIT_EXCEPTIONS", visitException14);
            template.sendBody("seda:CEP_VISIT_EXCEPTIONS", visitException15);
            template.sendBody("seda:CEP_VISIT_EXCEPTIONS", visitException16);
            template.sendBody("seda:CEP_VISIT_EXCEPTIONS", visitException17);
            template.sendBody("seda:CEP_VISIT_EXCEPTIONS", visitException18);
            template.sendBody("seda:CEP_VISIT_EXCEPTIONS", visitException19);
            template.sendBody("seda:CEP_VISIT_EXCEPTIONS", visitException20);
            template.sendBody("seda:CEP_VISIT_EXCEPTIONS", visitException21);
            template.sendBody("seda:CEP_VISIT_EXCEPTIONS", visitException22);
            template.sendBody("seda:CEP_VISIT_EXCEPTIONS", visitException23);
            template.sendBody("seda:CEP_VISIT_EXCEPTIONS", visitException24);
            template.sendBody("seda:CEP_VISIT_EXCEPTIONS", visitException25);
            visitSk += 1;

        }

        VisitException visitException1b = getVisitException(created, updated, Integer.parseInt("10001"), "10001");
        VisitException visitException26 = getVisitException(created, updated, Integer.parseInt("10001"), "10026");
        VisitException visitException1c = getVisitException(created, updated, Integer.parseInt("10001"), "10001");


        mock1.setExpectedMessageCount(101);

        template.sendBody("seda:CEP_VISIT_EXCEPTIONS", visitException26);
        mock1.assertIsSatisfied();
        StringBuffer bufferMsg = new StringBuffer();
        for(Exchange exchange : mock1.getExchanges()) {
            VisitException visitExceptionRecvd = exchange.getIn().getBody(VisitException.class);
            bufferMsg.append( getVisitExceptionMsg(visitExceptionRecvd));
            bufferMsg.append(System.lineSeparator());
        }
        logger.info(bufferMsg.toString());
    }

    private String getVisitExceptionMsg(VisitException visitExceptionRecvd) {
        String msg = "****************************************  TEST RESULTS *********************";
        msg += System.lineSeparator();
        msg += ":: VisitException ==> ";
        msg += System.lineSeparator();
        msg += "::::  VISIT_SK ==> ";
        msg += visitExceptionRecvd.getVisitSK();
        msg += System.lineSeparator();
        msg += "::::  EXCP_LOOKUP_TYPE ==> ";
        msg += visitExceptionRecvd.getExceptionID().toString();
        msg += System.lineSeparator();
        msg += "::::  REC_CREATE_TMSTP ==> ";
        msg += visitExceptionRecvd.getRecordCreateTimestamp().toString();
        msg += System.lineSeparator();
        msg += "::::  REC_UPDATE_TMSTP ==> ";
        msg += visitExceptionRecvd.getRecordUpdateTimestamp().toString();
        return msg;
    }

    private VisitException getVisitException(Date created, Date updated, Integer visitSk, String excpID) {
        VisitException visitException = new VisitException();
        visitException.setRecordCreateTimestamp(created);
        visitException.setRecordUpdateTimestamp(updated);
        BigInteger vSk = BigInteger.valueOf(Integer.parseInt(visitSk.toString()));
        visitException.setVisitSK(vSk);
        visitException.setExceptionID(BigInteger.valueOf(Integer.parseInt(excpID)));
        return visitException;
    }
}
