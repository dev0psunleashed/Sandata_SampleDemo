package com.sandata.lab.rules.call.matching.processors;

import com.sandata.lab.data.model.dl.model.Visit;
import com.sandata.lab.rules.call.matching.drools.BatchExecuters;

import com.sandata.lab.rules.call.model.State;
import com.sandata.lab.rules.call.model.VisitEventFact;
import com.sandata.lab.rules.call.model.VisitFact;
import org.apache.camel.Endpoint;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.PollingConsumer;
import org.apache.camel.Processor;
import org.drools.core.command.runtime.BatchExecutionCommandImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sandata.lab.rules.call.matching.app.AppContext;
import com.sandata.lab.rules.call.matching.drools.BatchExecuter;

import com.sandata.lab.rules.call.model.Schedule;

import java.util.Map;

import static java.lang.String.*;

public class ScheduleProcessorProducer implements Processor {

	public ScheduleProcessorProducer() {
		batchExecuters = new BatchExecuters();
	}
	public static String dnis;
	private BatchExecuters batchExecuters;
	Logger logger = LoggerFactory.getLogger(ScheduleProcessorProducer.class);
	@Override
	public void process(Exchange exchange) throws Exception {
		if(batchExecuters == null) {
			batchExecuters = new BatchExecuters();
		}

		ProducerPojo processorProducerPojo = new ProducerPojo();
		Endpoint endpoint = AppContext.getContext().getEndpoint("activemq:queue:CEP_ENGINE_SCHEDULES?destination.consumer.exclusive=true");
		Endpoint endpoint2 = AppContext.getContext().getEndpoint("activemq:queue:CEP_ENGINE_CALLS?destination.consumer.exclusive=true");
		Endpoint endpoint3 = AppContext.getContext().getEndpoint("activemq:queue:CEP_ENGINE_VISITS_BY_DNIS?destination.consumer.exclusive=true");

		PollingConsumer consumer = endpoint.createPollingConsumer();
		PollingConsumer consumer2 = endpoint2.createPollingConsumer();
		PollingConsumer consumer3 = endpoint3.createPollingConsumer();

		Exchange consumerCallExchange;
		Exchange consumerScheduleExchange;
		Exchange consumerVisitExchange;

		while((consumerCallExchange = consumer2.receive(30000)) != null)
		{
			VisitEventFact vEF = (VisitEventFact) consumerCallExchange.getIn().getBody(VisitEventFact.class);
			String dnis = vEF.getDnis();
			vEF.setState(State.NOT_MATCHED);
			logger.info(String.format(" Adding a calls to batch processor for dnis %s", dnis));
			batchExecuters.addCall(vEF);
			//if there were no calls there would be no schedules to retrieve
			while((consumerScheduleExchange = consumer.receive(30000)) != null)
			{
				logger.info(" Adding a schedule to batch processor");
				Schedule schedule = (Schedule) consumerScheduleExchange.getIn().getBody(Schedule.class);
				String scheduleDnis = schedule.getDnis();
				if(schedule.getState() == State.CREATE_UNPLANNED_VISIT) {
					logger.info(String.format("looks like dnis %s will have unplanned visits ", scheduleDnis ));
				}
				batchExecuters.addSchedule(schedule);
			}
			logger.info(" End of schedules loop ");

			/*while((consumerVisitExchange = consumer3.receive(2000)) != null)
			{
					rulesDefaultLogger.info(" Adding a inprogress visit to batch processor");
					VisitFact visitFact = consumerVisitExchange.getIn().getBody(VisitFact.class);
					String visitDnis = visitFact.getVisit().getVisitEvent().get(0).getDialedNumberIdentificationService();
					//check header also.
					String headerDnis = consumerVisitExchange.getIn().getHeader("DNIS", String.class);
				if(headerDnis != null)
					rulesDefaultLogger.info(String.format("visit had dnis : %s and header had dnis : %s  ", visitDnis, headerDnis ));
				else
					rulesDefaultLogger.info(String.format("visit had dnis : %s  header was missing or null ", visitDnis ));
				batchExecuters.addVisit(visitFact, visitDnis);
			}
			rulesDefaultLogger.info(" End of visits loop ");*/


		}
		logger.info(" End of calls loop");
		//If there were no facts we still need to run engine
		// to see if any schedules need expiring!
		for(Map.Entry<String, BatchExecuter> entry : batchExecuters.getWindows().entrySet() ) {
			int sizeOfCurrentCommandList = entry.getValue().getSizeOfCommandList();
			logger.info(format(": Size of command List for dnis %s was %s", entry.getKey(), Integer.toString(sizeOfCurrentCommandList)));
			if(sizeOfCurrentCommandList > 0)
			{
				final BatchExecuter batchExecuter = entry.getValue();
				logger.info(format("sending window for dnis: %s", entry.getKey()));
				if(batchExecuter == null)
				{
					logger.info("============== batchExecutor is null  !! ");
				}

				final BatchExecutionCommandImpl command  = batchExecuter.execute();
                if(command == null)
                {
                    logger.info("============== command is null ");
                }
				else if(processorProducerPojo == null)
				{
					logger.info("============== processorProducerPojo is null ");
				}
				else {
					logger.info("============== VALIDATION PASSED TO EXECUTE BATCH RULES! ");
					//in.setBody(command);
					processorProducerPojo.sendBatchExecuteCommand(command);
                }

				batchExecuter.sentExecuteCommand();
			}
			else
			{
				logger.info("No new commands in list");
			}
		}

	}
	
	public Exchange insert(Exchange exchange) {
		
		
		return exchange;
	}

}
