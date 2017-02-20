package com.sandata.lab.rules.call.matching.service.wildfly.jms;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import javax.jms.ConnectionFactory;
import javax.jms.Queue;
import javax.naming.Context;
import javax.naming.InitialContext;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.PropertyInject;
import org.drools.core.command.impl.GenericCommand;
import org.drools.core.command.runtime.BatchExecutionCommandImpl;
import org.drools.core.command.runtime.rule.AgendaGroupSetFocusCommand;
import org.kie.api.KieServices;
import org.kie.api.runtime.ExecutionResults;
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
 * Created by richardwu on 1/16/2017.
 * @param <KieServicesConfiguration>
 */
public class KieServerJmsHandler {

	private final Logger logger = LoggerFactory.getLogger(KieServerJmsHandler.class);
	
    // Set up all the default values
    private static final String DEFAULT_MESSAGE = "Hello, World!";
    private static final String DEFAULT_CONNECTION_FACTORY = "jms/RemoteConnectionFactory";
    private static final String DEFAULT_DESTINATION = "jms/queue/nonBridgedTestQueue";
    private static final String DEFAULT_MESSAGE_COUNT = "1";
    private static final String DEFAULT_USERNAME = "guest";
    private static final String DEFAULT_PASSWORD = "";
    private static final String INITIAL_CONTEXT_FACTORY = "org.jboss.naming.remote.client.InitialContextFactory";
    private static final String PROVIDER_URL = "http-remoting://127.0.0.1:8080";	
	

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

			final Message in = exchange.getIn();
			final Object body = in.getBody();
			final String agendaGroupName = (String) in.getHeader("agendaGroupName");
			// defined in kie project that deployed in wildfly
			final String kieSessionId = (String) in.getHeader("kieSessionId");

            String userName = System.getProperty("username", DEFAULT_USERNAME);
            String password = System.getProperty("password", DEFAULT_PASSWORD);

            //to make sure date format always applied
			System.setProperty("org.kie.server.json.format.date", "true");
			//System.setProperty("org.kie.server.json.date_format", "yyyy-MM-dd'T'hh:mm:ss.SSSZ");

			//can use JMS here KieServicesFactory.newJMSConfiguration
			//			KieServicesConfiguration config = KieServicesFactory.newRestConfiguration(wildflyKieServerUrl, wildflyKieServerUser, wildflyKieServerPassword);
//			KieServicesConfiguration config = KieServicesFactory.newJMSConfiguration(connectionFactory, requestQueue, responseQueue);


			final Properties env = new Properties();
			env.put(Context.INITIAL_CONTEXT_FACTORY, INITIAL_CONTEXT_FACTORY);
			env.put(Context.PROVIDER_URL, System.getProperty(Context.PROVIDER_URL, PROVIDER_URL));
			env.put(Context.SECURITY_PRINCIPAL, userName);
			env.put(Context.SECURITY_CREDENTIALS, password);
			InitialContext context = new InitialContext(env);
			//			InitialContext context = ...;
			Queue requestQueue = (Queue) context.lookup("jms/queue/KIE.SERVER.REQUEST");
			Queue responseQueue = (Queue) context.lookup("jms/queue/KIE.SERVER.RESPONSE");
			ConnectionFactory connectionFactory = (ConnectionFactory) context.lookup("jms/RemoteConnectionFactory");
			KieServicesConfiguration config = KieServicesFactory.newJMSConfiguration( connectionFactory, requestQueue, responseQueue, userName, password);
			// here you set response handler globally
			config.setResponseHandler(new FireAndForgetResponseHandler());			

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
