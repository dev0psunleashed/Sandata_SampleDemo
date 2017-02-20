package com.sandata.lab.rest.report.impl;

import static com.sandata.lab.rest.report.utils.log.OracleDataLogger.CreateLogger;
import static java.lang.String.format;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.sandata.lab.rest.report.model.*;
import org.apache.camel.Exchange;
import org.apache.cxf.message.MessageContentsList;

import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.common.utils.log.SandataLogger;
import com.sandata.lab.rest.report.api.DataService;
import com.sandata.lab.rest.report.model.enums.Columns;
import com.sandata.lab.rest.report.model.enums.Tables;

/**
 * Date: 9/1/15
 * Time: 8:47 PM
 */
@SuppressWarnings("unchecked")
public class RestDataService implements DataService {

    private OracleDataService oracleDataService;

    public OracleDataService getOracleDataService() {
        return oracleDataService;
    }

    public void setOracleDataService(OracleDataService oracleDataService) {
        this.oracleDataService = oracleDataService;
    }

              //Validate the dates

}
