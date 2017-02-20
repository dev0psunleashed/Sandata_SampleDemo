package com.sandata.lab.exports.payroll.routes.payroll.dynamic;


import com.sandata.lab.common.utils.data.provider.GSONProvider;
import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.common.utils.messaging.Messaging;
import com.sandata.lab.common.utils.string.StringUtil;
import com.sandata.lab.exports.payroll.impl.SchedulePayrollExportRoute;
import com.sandata.lab.exports.payroll.model.Settings;
import com.sandata.lab.exports.payroll.utils.PayrollProcessUtil;
import com.sandata.lab.exports.payroll.utils.constants.App;
import org.apache.camel.*;
import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.*;


public class PayrollRouteManager implements CamelContextAware {

    @PropertyInject("{{payroll.time.setting.format}}")
    private String payrollTimeFormat = "H:mm";

    private CamelContext camelContext;

    private static final String ROUTE_PREFIX = "PayrollRoute";

    /**
     * Updates routes with specified exchange.
     *
     * @param exchange Specified exchange.
     */
    public final void updateRoutes(final Exchange exchange) {
        HashMap<String, HashMap> bsnEntMap = (HashMap<String, HashMap>) exchange.getIn().getBody();

        for (Map.Entry<String, HashMap> entry : bsnEntMap.entrySet()) {

            String bsnEntID = entry.getKey();
            HashMap<String, HashMap> bsnEntsMap = (HashMap<String, HashMap>) exchange.getIn().getHeader("bsnEnts");


            HashMap<String, String> bsnEntInfo = bsnEntsMap.get(bsnEntID);

            String routeID = generateRouteID(bsnEntID);

            removeRoutes(routeID);
            createRoutes(routeID, entry.getValue(), bsnEntInfo);
        }
    }

    /**
     * Remove routes with specified routeID.
     *
     * @param routeID Specified routeID
     */
    private void removeRoutes(String routeID) {

        try {
            camelContext.stopRoute(routeID);
            camelContext.removeRoute(routeID);


        } catch (Exception e) {
            e.printStackTrace();

        }

    }


    /**
     * Create dynamic route for processing payroll based on agency settings
     *
     * @param routeID
     * @param agencySettings
     * @param bsnEntInfo
     */
    private void createRoutes(String routeID, HashMap<String, String> agencySettings, HashMap<String, String> bsnEntInfo) {

        try {

            String configJSON = agencySettings.get("config");

            Settings bsnEntSettings = (Settings) GSONProvider.FromJson(configJSON, Settings.class);
            String cron = createPayrollCron(bsnEntSettings);

            //test chron
            //cron = "0 0/5 * 1/1 * ? *";
            String timeZone = bsnEntInfo.get("timeZone");

            String fromUri = "quartz2://payroll//" + routeID + "?cron=" + cron + "&trigger.timeZone=" + timeZone;

            String toUri = "";

            switch (bsnEntInfo.get("prVendorName").toUpperCase()) {

                case "PAYPRO":
                    toUri = Messaging.Names.COMPONENT_TYPE_SEDA + App.ID.PAYPRO_PAYROLL_EXPORT_ROUTE.toString();
                    break;
                default:
                    break;
            }

            camelContext.addRoutes((RoutesBuilder) new SchedulePayrollExportRoute(routeID, fromUri, toUri, bsnEntInfo));

        } catch (Exception ex) {

            ex.printStackTrace();
        }
    }


    /**
     * Generate cron based of Agency Settings
     *
     * @param bsnEntSettings
     * @return
     */
    private String createPayrollCron(Settings bsnEntSettings) throws Exception {

        String weekDay = bsnEntSettings.getPayrollEndDay();

        Calendar startTime = getPayRollStartTime(bsnEntSettings.getPayrollEndTime());

        String cron = "0 */3 * ? * *";
        String cronArray[] = cron.split(" ");

        cronArray[1] = Integer.toString(startTime.get(Calendar.MINUTE));
        cronArray[2] = Integer.toString(startTime.get(Calendar.HOUR_OF_DAY));
        cronArray[5] = Integer.toString(PayrollProcessUtil.GetIntDayOfWeek(weekDay));

        cron = StringUtils.join(cronArray, " ");

        return cron;
    }

    private Calendar getPayRollStartTime(String time) throws Exception{

        SimpleDateFormat format = new SimpleDateFormat(payrollTimeFormat);

        try {

            if(StringUtil.IsNullOrEmpty(time)){

                Exception e = new Exception();

                throw new SandataRuntimeException("Payroll run time is not set!!");
            }

            Date date = format.parse(time);

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);

            return calendar;

        } catch (Exception e) {
            e.printStackTrace();

            throw e;
        }
    }


    private String generateRouteID(String bsnEntID) {

        return ROUTE_PREFIX + ":BSNENT:" + bsnEntID;
    }

    public final CamelContext getCamelContext() {
        return camelContext;
    }

    public final void setCamelContext(final CamelContext camelContext) {
        this.camelContext = camelContext;
    }
}
