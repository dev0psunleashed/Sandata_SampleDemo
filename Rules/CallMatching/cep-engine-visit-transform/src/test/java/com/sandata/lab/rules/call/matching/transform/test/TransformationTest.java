package com.sandata.lab.rules.call.matching.transform.test;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.camel.EndpointInject;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.AdviceWithRouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.blueprint.CamelBlueprintTestSupport;
import org.junit.Before;
import org.junit.Test;

import com.sandata.lab.data.model.dl.model.collection.VisitEventDNISGroup;
import com.sandata.lab.data.model.dl.model.extended.VisitEventExt;
import com.sandata.lab.rules.call.model.VisitEventFact;

public class TransformationTest extends CamelBlueprintTestSupport {
    
    @EndpointInject(uri = "mock:VisitEventToCall-test-output")
    private MockEndpoint resultEndpoint;
    
    @Produce(uri = "direct:PROCESS_CALLS")
    private ProducerTemplate startEndpoint;
    
    @Produce(uri = "direct:RETRACTED_CALLS")
    private ProducerTemplate startEndpointRetractCall;
    
    
    
    
    @Override
    public void setUp() throws Exception {
        replaceRouteFromWith("processCalls", "direct:PROCESS_CALLS");
        replaceRouteFromWith("processRetractedCalls", "direct:RETRACTED_CALLS");
        super.setUp();
    }
    
    
    
   
    
    
    @Before
    public void init() throws Exception{
        
        //direct-aggregate route
       context.getRouteDefinition("processCalls").adviceWith(context, new AdviceWithRouteBuilder() {
            @Override
            public void configure() throws Exception {
                  interceptSendToEndpoint("activemq:queue:CEP_ENGINE_CALLS")
                          .skipSendToOriginalEndpoint()
                          .to("mock:AGGREGATE_VISIT_EVENTS");
            }
        });
       
       
       
       context.getRouteDefinition("processRetractedCalls").adviceWith(context, new AdviceWithRouteBuilder() {
           @Override
           public void configure() throws Exception {
                 interceptSendToEndpoint("activemq:queue:PROCESSED_CALLS")
                         .skipSendToOriginalEndpoint()
                         .to("mock:RETRACT_CALLS");
           }
       });
        
    }
    
    @Test
    public void transform() throws Exception {
    	String dnis = "8001231234";
    	VisitEventExt visit = getVisit("1231231234", dnis, null, "123456789");
    	VisitEventExt visit2 = getVisit("2231231235", dnis, null, "223456789");
    	VisitEventExt visit3 = getVisit("3231231236", dnis, null, "323456789");
    	
    	
    	VisitEventDNISGroup grp = new VisitEventDNISGroup();
    	List<VisitEventExt> visits = new ArrayList<VisitEventExt>();
    	visits.add(visit);visits.add(visit2);visits.add(visit3);
    	grp.setDnis("TestDnis");
    	
    	grp.setVisitEvents(visits);
    	
    	
    	MockEndpoint mockOut = getMockEndpoint("mock:AGGREGATE_VISIT_EVENTS");
    	//expectation
    	mockOut.setExpectedCount(3);
    	//send message to route
    	startEndpoint.sendBody(grp);

    	//assert
    	mockOut.assertIsSatisfied();
    	
    }
    
    
    @Test
    public void retract_call() throws Exception {
       
        VisitEventFact VisitEventFact = new VisitEventFact();
        VisitEventFact.setVisitEventExt(new VisitEventExt());
        
        MockEndpoint mockOut = getMockEndpoint("mock:RETRACT_CALLS");
        //expectation
        mockOut.setExpectedCount(1);
        //send message to route
        startEndpointRetractCall.sendBody(VisitEventFact);

        //assert
        mockOut.assertIsSatisfied();
        
    }
    

    
	@Override
	protected String getBlueprintDescriptor() {
		return "OSGI-INF/blueprint/blueprint.xml";
	}
    
    private String readFile(String filePath) throws Exception {
        String content;
        FileInputStream fis = new FileInputStream(filePath);
        try {
             content = createCamelContext().getTypeConverter().convertTo(String.class, fis);
        } finally {
            fis.close();
        }
        return content;
    }
    
    /***
     * 
     * @param ani
     * @param dnis
     * @param clientId
     * @param staffId
     * @param visitEventType
     * @param visitEventTimeStamp
     * @return
     */
    private VisitEventExt getVisit(String ani, String dnis, String clientId, String staffId) {
        VisitEventExt visit = new VisitEventExt();
    	visit.setStaffID(staffId);
    	//visit.setAni(ani);
    	//visit.setDnis(dnis);
    	//visit.setClientId(clientId);
    	//visit.setStaffId(staffId);
    	//visit.setVisitEventType(visitEventType);
    	visit.setVisitEventDatetime(Calendar.getInstance().getTime());
    	//visit.setTasks(getTasks(3));
		return visit;
	}


	/*private List<VisitTask> getTasks(int numTasks) {
		List<VisitTask> list = new ArrayList<VisitTask>();
		for(int i = 0;i < numTasks;i++) {
			VisitTask t = new VisitTask();
			t.setId(Integer.toString(i));
			t.setValue(Integer.toString(i));
			list.add(t);
		}
		return list;
	}*/

}
