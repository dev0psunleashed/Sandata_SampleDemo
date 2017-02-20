package com.sandata.lab.rules.call.matching.service.wildfly.rest;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.UUID;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
//import org.apache.activemq.ra.ActiveMQManagedConnectionFactory;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.PropertyInject;
import org.drools.core.command.impl.GenericCommand;
import org.drools.core.command.runtime.BatchExecutionCommandImpl;
import org.drools.core.command.runtime.rule.AgendaGroupSetFocusCommand;
import org.kie.api.KieServices;
import org.kie.api.executor.ExecutionResults;
import org.kie.server.api.commands.CommandScript;
import org.kie.server.api.marshalling.MarshallingFormat;
import org.kie.server.api.model.ServiceResponse;
import org.kie.server.api.model.ServiceResponsesList;
import org.kie.server.client.KieServicesClient;
import org.kie.server.client.KieServicesConfiguration;
import org.kie.server.client.KieServicesException;
import org.kie.server.client.KieServicesFactory;
import org.kie.server.client.RuleServicesClient;
import org.kie.server.client.jms.FireAndForgetResponseHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sandata.lab.common.utils.exception.SandataRuntimeException;

/**
 * Created by thanhxle on 12/21/2016.
 * @param <KieServicesConfiguration>
 */
public class KieServerJMSRestHandler_willrollback {
	
private final Logger logger = LoggerFactory.getLogger(KieServerRestHandler.class);
	
    @PropertyInject("{{wildfly.kie.server.user}}")
    private String wildflyKieServerUser = "";
    
    @PropertyInject("{{wildfly.kie.server.pwd}}")
    private String wildflyKieServerPassword = "";
    
    
    @PropertyInject("{{wildfly.kie.server.root.url}}")
    private String wildflyKieServerUrl = "";
    
    
    @PropertyInject("{{wildfly.kie.container.id}}")
    private String wildflyKieContainerId = "";

	
	    
	public void executeRules(final Exchange  exchange) throws SandataRuntimeException {

		try {
			//org.apache.activemq.ra.ActiveMQManagedConnectionFactory dd = new ActiveMQManagedConnectionFactory();
			
			final Message in = exchange.getIn();
			final Object body = in.getBody();
			final String agendaGroupName = (String) in.getHeader("agendaGroupName");
			// defined in kie project that deployed in wildfly
			final String kieSessionId = (String) in.getHeader("kieSessionId");
			
			//to make sure date format always applied
			System.setProperty("org.kie.server.json.format.date", "true");
			
			
			//TODO: 
			//1. setup messaging -amq cho wildlfy
			//2. connect
		
			final Properties props = new Properties();
			props.setProperty(Context.INITIAL_CONTEXT_FACTORY,
					  "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
			props.setProperty(Context.PROVIDER_URL, "tcp://localhost:61616");
			props.setProperty(Context.SECURITY_PRINCIPAL, "admin");
			props.setProperty(Context.SECURITY_CREDENTIALS, "admin");
			InitialContext context = new InitialContext(props);
			//("jms/RemoteConnectionFactory")
			ConnectionFactory connectionFactory = (ConnectionFactory)context.lookup("ConnectionFactory");
			Queue requestQueue = (Queue)context.lookup("dynamicQueues/queue/KIE.SERVER.REQUEST");
			Queue responseQueue = (Queue)context.lookup("dynamicQueues/queue/KIE.SERVER.RESPONSE");
					
			KieServicesConfiguration config = KieServicesFactory.newJMSConfiguration( connectionFactory, requestQueue, responseQueue, "admin", "admin");
		    //config.setResponseHandler(new FireAndForgetResponseHandler());
			
			//config.se
			//config.setMarshallingFormat(MarshallingFormat.XSTREAM);
			config.setMarshallingFormat(MarshallingFormat.JSON);
			
			// specify higher timeout, the default is too small (3 seconds)
			//config.setTimeout(5*60*1000);//5 mins
			
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
			//ruleClient.setResponseHandler(new FireAndForgetResponseHandler());
			List<GenericCommand<?>> commands = new ArrayList<GenericCommand<?>>();
			
	        if (body instanceof List) {
	            for (Object o : (List) body) {
	                commands.add((GenericCommand<?>) KieServices.Factory.get().getCommands().newInsert(o));
	            }
	        } else {
	        	commands.add((GenericCommand<?>) KieServices.Factory.get().getCommands().newInsert(body));
	        }
			
	        commands.add(new AgendaGroupSetFocusCommand(agendaGroupName));
			commands.add((GenericCommand<?>) KieServices.Factory.get().getCommands().newFireAllRules());
			
			//BatchExecutionCommand batchCommand = KieServices.Factory.get().getCommands().newBatchExecution(commands,kieSessionId);
			BatchExecutionCommandImpl batchCommand = new BatchExecutionCommandImpl(commands);
			batchCommand.setLookup(kieSessionId);
			
			ServiceResponse<org.kie.api.runtime.ExecutionResults> response = ruleClient.executeCommandsWithResults(wildflyKieContainerId, batchCommand);
			
			exchange.getIn().setBody(response.getMsg());

		} catch (Exception e) {

			String msg = String.format("%s: %s", e.getClass().getName(),
					e.getMessage());
			logger.error(msg);
			throw new SandataRuntimeException(msg, e);
		}
    }

}
