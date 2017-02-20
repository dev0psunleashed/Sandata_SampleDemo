package com.sandata.lab.rules.call.matching.transform;


import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sandata.lab.rules.call.model.VisitEventFact;

import com.sandata.lab.data.model.dl.model.extended.VisitEventExt;

import java.text.SimpleDateFormat;


public class CallFactConverter implements Processor {
	private static final String CEP_ENG_TRANSFORM_LOGGER = "transformLog";
	private Logger transformLog = LoggerFactory.getLogger(CEP_ENG_TRANSFORM_LOGGER);
	private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ZZ");

	@Override
	public void process(Exchange exchange) throws Exception {

		Message in = exchange.getIn();
		transformLog.info("In CallFactConverter ::" );
		//transformLog.info("With type of " + exchange.getIn().getBody());//.toString());
		String bsnEntityId = (String)in.getHeader("businessEntityId");
		if(in.getBody() instanceof VisitEventExt) {
			this.getTransformLog().info(String.format("CallFactConverter ::  Validated message body is of type VisitEventExt with bsnEntityId in header of %s!", bsnEntityId));
			VisitEventExt visitEventExt = (VisitEventExt)in.getBody(VisitEventExt.class);
			String errorMsg = "";
			String ani = "(null)";
			if(visitEventExt.getAutomaticNumberIdentification() != null && !visitEventExt.getAutomaticNumberIdentification().isEmpty()) {
				ani = visitEventExt.getAutomaticNumberIdentification();
			}
			else {
				errorMsg += "* Missing ani *";
			}
			String dnis = "(null)";
			if(visitEventExt.getDialedNumberIdentificationService() != null && !visitEventExt.getDialedNumberIdentificationService().isEmpty()) {
				dnis = visitEventExt.getDialedNumberIdentificationService();
			}
			else {
				errorMsg += "* Missing dnis *";
			}
			String staffId = "(null)";
			if(visitEventExt.getStaffID() != null && !visitEventExt.getStaffID().isEmpty()) {
				staffId = visitEventExt.getStaffID();
			}
			else {
				errorMsg += "* Missing staffId *";
			}
			String ptId = "(not entered)";
			if(visitEventExt.getPatientID() != null && !visitEventExt.getPatientID().isEmpty()) {
				ptId = visitEventExt.getPatientID();
			}
			String visitEventDt = "(null)";
			if(visitEventExt.getVisitEventDatetime() != null) {
				visitEventDt = sdf.format(visitEventExt.getVisitEventDatetime());
			}
			else {
				errorMsg += "* Missing VisitEventDateTime *";
			}
			String infoString = String.format("CallFactConverter ::  VisitEventExt.automaticNumberIdentification = %s VisitEventExt.dialedNumberIdentificationService = %s" +
					" visitEventExt.getStaffID = %s visitEventExt.getPatientID = %s visitEventExt.getDtime = %s", ani, dnis, staffId, ptId, visitEventDt);

			this.getTransformLog().info(infoString);
			VisitEventFact visitEventFact = new VisitEventFact();
			visitEventFact.setVisitEventExt(visitEventExt);  //sets callInTime

			if(visitEventFact.getAni() != null && visitEventFact.getDnis() !=null && visitEventFact.getCallInTime() != null) {

				int taskCount = 0;
				visitEventFact.setBusinessEntityId(bsnEntityId);
				if(visitEventFact.getTasks() != null && visitEventFact.getTasks().size() > 0)
					taskCount = visitEventFact.getTasks().size();
				infoString = String.format("CallFactConverter :: VisitEvent converted to drools Call Fact: visitEventFact.ani = %s, visitEventFact.dnis = %s, with task size = %s, be_id from database = %s ",
						visitEventFact.getAni(), visitEventFact.getDnis(), Integer.toString(taskCount), bsnEntityId);
				this.getTransformLog().info(infoString);
/*
				//write a sample visitEvent
				visitEventFact.setVisitEventTypeCode(VisitEventTypeCode.MVV);
				//Sandata 40.810764, -73.663659
				BigDecimal lat = BigDecimal.ZERO;
				lat = lat.add(BigDecimal.valueOf(40.810764));
				BigDecimal lon = BigDecimal.ZERO;
				lon = lon.add(BigDecimal.valueOf(-73.663659));
				BigDecimal alt = BigDecimal.ZERO;
				BigDecimal accuracy = BigDecimal.ZERO;
				accuracy = accuracy.add(BigDecimal.valueOf(100));
				GPSLocation gpsLocation = new GPSLocation(lat, lon, alt, accuracy);
				visitEventFact.setGps(gpsLocation);
				Gson gson = new Gson();
				String json = gson.toJson(visitEventFact);
				CamelContext context = AppContext.getContext();
				if(context.getStatus() == ServiceStatus.Started) {
					context.createProducerTemplate().sendBody("direct:callImportSamples", json);
				}
*/
				exchange.getIn().setBody(visitEventFact, VisitEventFact.class);
			} else {
				getTransformLog().info("Critical error :: A critical piece of data was not properly imported from the call server." + errorMsg);
			}
		

		}
		else {
			this.getTransformLog().info(String.format("ERROR:: MESSAGE TYPE WASNT VisitEventExt %s", in.getBody().getClass().getName()));
		}
		

	}
	public Logger getTransformLog() {
		if(transformLog != null) {
			return transformLog;
		}
		else {
			this.transformLog = LoggerFactory.getLogger("transformLog");
			return this.transformLog;
		}
	}

	public void setTransformLog(Logger transformLog) {
		this.transformLog = transformLog;
	}



}