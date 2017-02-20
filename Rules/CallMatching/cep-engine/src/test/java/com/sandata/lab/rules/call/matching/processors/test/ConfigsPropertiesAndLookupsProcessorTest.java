/**
 * 
 */
package com.sandata.lab.rules.call.matching.processors.test;

import org.junit.Test;

import com.sandata.lab.rules.call.matching.processors.ConfigsPropertiesAndLookupsProcessor;
import com.sandata.lab.rules.call.model.VisitEventFact;

/**
 * @author thanhxle
 *
 * @Description :
 *
 * @date Apr 19, 2016
 * 
 * 
 */
public class ConfigsPropertiesAndLookupsProcessorTest extends BaseTestSupport {

    ConfigsPropertiesAndLookupsProcessor configsPropertiesAndLookupsProcessor;
    
    
    @Test
    public void test_proccess_method_exchange_is_VisitEventFact_instance() throws Exception{
        for(int i =1;i<4;i++){
            //input data
            VisitEventFact visitEventFact = new VisitEventFact();
            if(i == 1){
                visitEventFact.setDnis("DNISTest"+i);
                
            }else if(i ==2){
                visitEventFact.setDnis("DNISTest"+1);
            }else if(i ==3){
                visitEventFact.setDnis("DNISTest"+i);
            }
            exchange.getIn().setBody(visitEventFact);
            //executing sending message to the processor
            configsPropertiesAndLookupsProcessor.process(exchange);
            //compare actual and expected result
            if(i==1){
                assertEquals("DNISTest1",   exchange.getIn().getBody());
            }else if(i ==2){
                assertEquals("DNISTest1",   exchange.getIn().getBody());
            }else if(i ==3){
                assertEquals("DNISTest3",   exchange.getIn().getBody());
            }
            
            //sleep after every loop, assumption that, no 2 or more message being sent to the endpoint in every 1 second.
            //if more than one message in 1 second 
            Thread.sleep(300);
        }
    }


    /* (non-Javadoc)
     * @see com.sandata.lab.rules.call.matching.processors.test.BaseTestSupport#onSetup()
     */
    @Override
    protected void onSetup() {
        configsPropertiesAndLookupsProcessor = new ConfigsPropertiesAndLookupsProcessor();
    }
}
