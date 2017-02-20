package com.sandata.lab.rules.call.matching.processor;

import com.google.common.collect.TreeMultimap;
import com.google.gson.Gson;
import com.sandata.lab.data.model.dl.model.collection.VisitEventDNISGroup;
import com.sandata.lab.rules.cache.client.CacheClient;
import com.sandata.lab.rules.call.matching.app.AppContext;
import org.apache.camel.*;
import org.apache.camel.impl.DefaultCamelContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Array;
import java.util.*;


/**
 * Created with IntelliJ IDEA.
 * User: Tom Dornseif
 * Date: 6/2/2016
 * Time: 10:10 AM
 */
public class ValidBusinessEntity implements Processor {
    private static final String CEP_ENG_TRANSFORM_LOGGER = "transformLog";
    private Logger transformLog = LoggerFactory.getLogger(CEP_ENG_TRANSFORM_LOGGER);

    private Map<String, ArrayList<String>> dnisMap = new HashMap<String, ArrayList<String>>();
    static final String INVALID_ENTITY ="INVALID_ENTITY";
    private List<String> badDnisList = new ArrayList<>();
//    static final String dev250_7348 = "8557214901";
    /* There are 3 telephone numbers on account 20002, the account QA is using for testing.
    (855) 721-4908
    (866) 233-5743
    (877) 637-9471
    */
//    static final String qa252_20002_1 = "8557214908";
//    static final String qa252_20002_2 = "8662335743";
//    static final String qa252_20002_3 = "8776379471";
//    static final String demo251_20001 = "8555330115";
//    static final String demo251_20001 = "8557214904";
//    static final String [] dnis = new String[]{dev250_7348, qa252_20002_1,
//            qa252_20002_2, qa252_20002_3, demo251_20001};
    //etl = group 253

    @Override
    public void process(Exchange exchange) throws Exception {
        Message in = exchange.getIn();
        if(in.getBody() instanceof VisitEventDNISGroup) {
            VisitEventDNISGroup visitEventDNISGroup = in.getBody(VisitEventDNISGroup.class);
            //first fast check
            String dnis = visitEventDNISGroup.getDnis();
            if(ValidBusinessEntity.doesContainDnis((HashMap<String, ArrayList<String>>)dnisMap, dnis)) {
                in.setHeader("VALID_DNIS", "true");
                String bsnEntityId = (String)ValidBusinessEntity.getKeyByValue((HashMap<String, ArrayList<String>>)dnisMap, dnis);
                in.setHeader("businessEntityId", bsnEntityId);
            }
            else if(badDnisList.contains(dnis)){
                in.setHeader("VALID_DNIS", "false");
                getTransformLog().info(String.format("MISSING_DNIS_ALERT : DNIS=\"%s\" is not in CACHE if it was recently added to configuration database a refresh of the caching service maybe required!", dnis));
            }
            else {
                String bsnEntityId = checkCache(dnis);
                if(bsnEntityId != null) {
                    in.setHeader("VALID_DNIS", "true");
                    ValidBusinessEntity.addValueByKey((HashMap<String, ArrayList<String>>)dnisMap, bsnEntityId, dnis);
                    in.setHeader("businessEntityId",bsnEntityId );

                }
                else {
                    in.setHeader("VALID_DNIS", "false");
                    badDnisList.add(dnis);
                    getTransformLog().info(String.format("MISSING_DNIS_ALERT : DNIS=\"%s\" is not in CONFIG DATABASE CHECK APP_TENANT_KEY_CONF->MDW_DNIS_LIST_FOR_AGENCY_ID", dnis));

                }
            }
            //write a sample visitEventDNISGroup
            /*Gson gson = new Gson();
            String json = gson.toJson(visitEventDNISGroup);
            CamelContext context = AppContext.getContext();
            if(context.getStatus() == ServiceStatus.Started) {
                context.createProducerTemplate().sendBody("direct:callImportSamples", json);
            }
            */
            exchange.setIn(in);

        }
    }

    public static boolean doesContainDnis(HashMap<String, ArrayList<String>> dnisMap, String dnis) {
        for(ArrayList<String> list : dnisMap.values()) {
            if(list.contains(dnis)) {
                return true;
            }
        }
        return false;
    }

    public static HashMap<String, ArrayList<String>> addValueByKey(HashMap<String, ArrayList<String>> dnisMap, String bsnEntityId, String dnis) {
        ArrayList<String> list = null;
        if(dnisMap.containsKey(bsnEntityId)) {
            list = (ArrayList<String>)dnisMap.get(bsnEntityId);
        }
        else {
            list = new ArrayList<>();
        }
        list.add(dnis);
        dnisMap.put(bsnEntityId, list);
        return dnisMap;
    }

    private String checkCache(String dnis) {
        String bsnEntityId = null;
        try {
            CacheClient client = CacheClient.getClient();
            bsnEntityId = client.getDnisForAgencdyId(dnis);
        }
        catch(Exception ex) { ex.printStackTrace();}
        return bsnEntityId;
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

    public static String getKeyByValue(HashMap<String, ArrayList<String>> map, String value) {

        for(String key : map.keySet()) {
            ArrayList<String> list = map.get(key);
            if(list.contains(value)) {
                return key;
            }
        }
        return null;
        /*  JAVA 8
        return map.entrySet()
                .stream()
                .filter(entry -> Objects.equals(entry.getValue(), value))
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());*/
    }





}
