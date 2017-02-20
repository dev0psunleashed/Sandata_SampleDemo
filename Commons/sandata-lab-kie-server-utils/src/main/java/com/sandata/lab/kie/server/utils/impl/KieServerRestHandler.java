package com.sandata.lab.kie.server.utils.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.drools.core.command.impl.GenericCommand;
import org.drools.core.command.runtime.BatchExecutionCommandImpl;
import org.drools.core.command.runtime.rule.AgendaGroupSetFocusCommand;
import org.kie.api.KieServices;
import org.kie.server.api.marshalling.MarshallingFormat;
import org.kie.server.api.model.ServiceResponse;
import org.kie.server.client.KieServicesClient;
import org.kie.server.client.KieServicesConfiguration;
import org.kie.server.client.KieServicesFactory;
import org.kie.server.client.RuleServicesClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.kie.server.utils.KieServerHandler;

/**
 * Created by thanhxle on 12/21/2016.
 */
public class KieServerRestHandler implements KieServerHandler {

    private final Logger logger = LoggerFactory.getLogger(KieServerRestHandler.class);
    
    //@PropertyInject("{{wildfly.kie.server.user}}")
    private String wildflyKieServerUser = "";
    
    //@PropertyInject("{{wildfly.kie.server.pwd}}")
    private String wildflyKieServerPassword = "";
    
    
    //@PropertyInject("{{wildfly.kie.server.root.url}}")
    private String wildflyKieServerUrl = "";
    
    
    //@PropertyInject("{{wildfly.kie.container.id}}")
    private String wildflyKieContainerId = "";
    
    public KieServerRestHandler(String wildflyKieServerUser, String wildflyKieServerPassword, String wildflyKieServerUrl, String wildflyKieContainerId) {
        this.wildflyKieServerUser = wildflyKieServerUser;
        this.wildflyKieServerPassword = wildflyKieServerPassword;
        this.wildflyKieServerUrl = wildflyKieServerUrl;
        this.wildflyKieContainerId = wildflyKieContainerId;
    }
    
    public void executeRules(final Exchange  exchange) throws SandataRuntimeException {

        try {
            
            final Message in = exchange.getIn();
            final Object body = in.getBody();
            final String agendaGroupName = (String) in.getHeader("agendaGroupName");
            // defined in kie project that deployed in wildfly
            final String kieSessionId = (String) in.getHeader("kieSessionId");
            
            //to make sure date format always applied
            System.setProperty("org.kie.server.json.format.date", "true");
            //System.setProperty("org.kie.server.json.date_format", "yyyy-MM-dd'T'hh:mm:ss.SSSZ");
            
            //can use JMS here KieServicesFactory.newJMSConfiguration
            KieServicesConfiguration config = KieServicesFactory.newRestConfiguration(wildflyKieServerUrl, wildflyKieServerUser, wildflyKieServerPassword);
            //KieServicesFactory.newKieServicesJMSClient(context, username, password)
            //fire and forget pattern
            //http://docs.jboss.org/drools/release/6.5.0.CR2/drools-docs/html/ch22.html
            //config.setResponseHandler(new FireAndForgetResponseHandler());
            
            config.setMarshallingFormat(MarshallingFormat.JSON);
            
            // specify higher timeout, the default is too small (3 seconds)
            config.setTimeout(5*60*1000);//5 mins
            
            Set<Class<?>> allClasses = new HashSet<Class<?>>();
            if (body instanceof List) {
                for (Object o : (List) body) {
                    allClasses.add(o.getClass());
                }
            } else {
                allClasses.add(body.getClass());
            }
            config.addExtraClasses(allClasses);
            

            KieServicesClient client  = KieServicesFactory.newKieServicesClient(config);
            RuleServicesClient ruleClient = client.getServicesClient(RuleServicesClient.class);
            //TODO:  Response handlers can only be configured for JMS client
            //ruleClient.setResponseHandler(new FireAndForgetResponseHandler());
            List<GenericCommand<?>> commands = new ArrayList<GenericCommand<?>>();
            
            if (body instanceof List) {
                for (Object o : (List) body) {
                    commands.add((GenericCommand<?>) KieServices.Factory.get().getCommands().newInsert(o));
                }
            } else {
                commands.add((GenericCommand<?>) KieServices.Factory.get().getCommands().newInsert(body));
            }

            if (agendaGroupName != null && !agendaGroupName.isEmpty()) {
                commands.add(new AgendaGroupSetFocusCommand(agendaGroupName));
            }

            commands.add((GenericCommand<?>) KieServices.Factory.get().getCommands().newFireAllRules());
            
            //BatchExecutionCommand batchCommand = KieServices.Factory.get().getCommands().newBatchExecution(commands,kieSessionId);
            BatchExecutionCommandImpl batchCommand = new BatchExecutionCommandImpl(commands);
            batchCommand.setLookup(kieSessionId);
            
            ServiceResponse response = ruleClient.executeCommands(wildflyKieContainerId, batchCommand);
            
            exchange.getIn().setBody(response.getMsg());

        } catch (Exception e) {

            String msg = String.format("%s: %s", e.getClass().getName(),
                    e.getMessage());
            logger.error(msg);
            throw new SandataRuntimeException(msg, e);
        }
    }
    
}
