package com.sandata.lab.rules.exceptions.processors;

import com.sandata.lab.data.model.dl.model.VisitException;
import com.sandata.lab.rules.call.model.State;
import com.sandata.lab.rules.exceptions.routes.VisitExceptionRoute;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Created by tom.dornseif on 2/18/2016.
 */
public class VisitExceptionProcessor implements Processor {
    private Logger visitExcpLog = LoggerFactory.getLogger("visitExcpLog");

    @Override
    public void process(Exchange exchange) throws Exception {
        Message in = exchange.getIn();
        if(in.getBody() instanceof VisitException)
        {
            String guid = UUID.randomUUID().toString();
            VisitException visitException = in.getBody(VisitException.class);

            //logging

            BigInteger id = visitException.getExceptionID();
            getVisitExcpLog().info(String.format("Processed VisitException:: VisitSK(%s), ExceptionID(%s)", visitException.getVisitSK().toString(),id.toString()));
            if(VisitExceptionRoute.exceptionLookupSize >= 0 && VisitExceptionRoute.getExceptionLookupHashtable().get(id) != null) {
                getVisitExcpLog().info(String.format("Rule was [ %s ], EXCP_NAME FROM TABLE WAS [ %s ]", State.getVisitExceptionFromExpectedSk(id), VisitExceptionRoute.getExceptionLookupHashtable().get(id).getExceptionName()));
            }
            else
            {
                getVisitExcpLog().info("ALERT MISSING DATA :: Sandata Error::  VisitExceptionRoute.exceptionLookupSize was zero or Lookup Object was NULL check table EXCP_LKUP for correct entries!");
            }

            // duplicates reported so far are to the second???
            in.setHeader("DUPLICATE_ID", getDuplicateHeaderId(visitException));
            in.setBody(visitException);
        }
    }

    private String getDuplicateHeaderId(VisitException visitException) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        Date now = new Date();
        String recUpdateTmstmp = simpleDateFormat.format(now);
        if(visitException.getRecordUpdateTimestamp() != null) {
            recUpdateTmstmp = simpleDateFormat.format(visitException.getRecordUpdateTimestamp());
        }
        if(visitException.getExceptionID() == null || visitException.getVisitSK() == null || recUpdateTmstmp == null) {
            String hdr = UUID.randomUUID().toString();
            getVisitExcpLog().info("DUPLICATE ENTRY POSSIBLE::Returned UUID which means we might put a duplicate in db but that preferable to missing one.");
            return hdr;
        }
        String excpId = visitException.getExceptionID().toString();
        String visitSk = visitException.getVisitSK().toString();
        String header =  String.format("%s%s%s",visitSk, excpId, recUpdateTmstmp);
        return header;
    }

    public Logger getVisitExcpLog() {
        if(this.visitExcpLog != null) {
            return this.visitExcpLog;
        }
        else {
            this.visitExcpLog = LoggerFactory.getLogger("visitExcpLog");
            return this.visitExcpLog;
        }
    }

    public void setVisitExcpLog(Logger visitExcpLog) {
        this.visitExcpLog = visitExcpLog;
    }
}
