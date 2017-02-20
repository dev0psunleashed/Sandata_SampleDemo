package com.sandata.lab.rules.call.matching.service.wildfly.rest;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import javax.jms.ConnectionFactory;
import javax.jms.Queue;
import javax.naming.Context;
import javax.naming.InitialContext;

//import org.apache.activemq.ra.ActiveMQManagedConnectionFactory;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.PropertyInject;
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
import org.kie.server.client.jms.FireAndForgetResponseHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sandata.lab.common.utils.exception.SandataRuntimeException;

/**
 * Created by thanhxle on 12/21/2016.
 * @param <KieServicesConfiguration>
 */
public class KieServerJMSHandler {
	
private final Logger logger = LoggerFactory.getLogger(KieServerJMSHandler.class);
	
	@PropertyInject("{{wildfly.kie.server.user}}")
	private String wildflyKieServerUser = "";

	@PropertyInject("{{wildfly.kie.server.pwd}}")
	private String wildflyKieServerPassword = "";


	@PropertyInject("{{wildfly.kie.server.root.url}}")
	private String wildflyKieServerUrl = "";


	@PropertyInject("{{wildfly.kie.container.id}}")
	private String wildflyKieContainerId = "";
	
	@PropertyInject("{{jms.provider.url}}")
	private String jmsProviderUrl = "http-remoting://localhost:8080";
	
	@PropertyInject("{{jms.remote.connection.factory}}")
	private String jmsremoteConnectionFactory = "jms/RemoteConnectionFactory";

	
	@PropertyInject("{{jms.user.name}}")
	private String jmsUserName = "jmsuser";
	
	@PropertyInject("{{jms.user.pwd}}")
	private String jmsUserPwd = "123456";
	

	public void executeRules(final Exchange  exchange) throws SandataRuntimeException {

		try {

			final Message in = exchange.getIn();
			final Object body = in.getBody();
			final String agendaGroupName = (String) in.getHeader("agendaGroupName");
			// defined in kie project that deployed in wildfly
			final String kieSessionId = (String) in.getHeader("kieSessionId");

            //to make sure date format always applied
			System.setProperty("org.kie.server.json.format.date", "true");
			
			Context namingContext = null;
			final Properties env = new Properties();
            env.put(Context.INITIAL_CONTEXT_FACTORY,
                    "org.jboss.naming.remote.client.InitialContextFactory");
            env.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming"); //java:jboss/exported
            //TODO: use remote and use local
            env.put(Context.PROVIDER_URL, jmsProviderUrl);
            //env.put(Context.PROVIDER_URL, "remote://localhost:4447");
            env.put(Context.SECURITY_PRINCIPAL, jmsUserName);
            env.put(Context.SECURITY_CREDENTIALS, jmsUserPwd);
            namingContext = new InitialContext(env);
 
            ConnectionFactory connectionFactory = (ConnectionFactory) namingContext.lookup(jmsremoteConnectionFactory);
            Queue requestQueue = (Queue) namingContext.lookup("jms/queue/KIE.SERVER.REQUEST");
			Queue responseQueue = (Queue) namingContext.lookup("jms/queue/KIE.SERVER.RESPONSE");
			
			
			KieServicesConfiguration config = KieServicesFactory.newJMSConfiguration( connectionFactory, requestQueue, responseQueue, jmsUserName, jmsUserPwd);
			// here you set response handler globally
			config.setResponseHandler(new FireAndForgetResponseHandler());			


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

			commands.add(new AgendaGroupSetFocusCommand(agendaGroupName));
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
