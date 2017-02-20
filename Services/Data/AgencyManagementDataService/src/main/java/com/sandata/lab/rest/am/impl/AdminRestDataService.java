package com.sandata.lab.rest.am.impl;

import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.common.utils.log.SandataLogger;
import com.sandata.lab.data.model.response.Response;
import com.sandata.lab.rest.am.utils.log.OracleDataLogger;
import org.apache.camel.Exchange;
import org.apache.cxf.message.MessageContentsList;

import static java.lang.String.format;

/**
 * Data handler for the Administrative Staff endpoints;
 * <p/>
 *
 * @author David Rutgos
 */
@SuppressWarnings("unchecked")
public class AdminRestDataService {

    private OracleDataService oracleDataService;

    public void getAdminStaffPK(final Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = OracleDataLogger.CreateLogger(exchange);

        logger.start();

        try {

            MessageContentsList mcl = (MessageContentsList) exchange.getIn().getBody();

            String bsnEntId = (String) mcl.get(0);
            if (bsnEntId == null || bsnEntId.length() == 0) {
                throw new SandataRuntimeException("BusinessEntityID (bsn_ent_id) is required!");
            }

            int page = (Integer) exchange.getIn().getHeader("page");
            int pageSize = (Integer) exchange.getIn().getHeader("page_size");
            String sortOn = (String) exchange.getIn().getHeader("sort_on");
            String direction = (String) exchange.getIn().getHeader("direction");

            Response response = oracleDataService.getAdminStaffPK(
                    bsnEntId,
                    page,
                    pageSize,
                    sortOn,
                    direction
            );

            exchange.getIn().setHeader("TOTAL_ROWS", response.getTotalRows());
            exchange.getIn().setHeader("PAGE", response.getPage());
            exchange.getIn().setHeader("PAGE_SIZE", response.getPageSize());
            exchange.getIn().setHeader("ORDER_BY_COLUMN", response.getOrderByColumn());
            exchange.getIn().setHeader("ORDER_BY_DIRECTION", response.getOrderByDirection());

            exchange.getIn().setBody(response.getData());

        } catch (Exception e) {
            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(errMsg, e);

        } finally {
            logger.stop();
        }
    }

    public void getAdminStaffPtPK(final Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = OracleDataLogger.CreateLogger(exchange);

        logger.start();

        try {

            MessageContentsList mcl = (MessageContentsList) exchange.getIn().getBody();

            String patientId = (String) mcl.get(0);

            String bsnEntId = (String) mcl.get(1);
            if (bsnEntId == null || bsnEntId.length() == 0) {
                throw new SandataRuntimeException("BusinessEntityID (bsn_ent_id) is required!");
            }

            int page = (Integer) exchange.getIn().getHeader("page");
            int pageSize = (Integer) exchange.getIn().getHeader("page_size");
            String sortOn = (String) exchange.getIn().getHeader("sort_on");
            String direction = (String) exchange.getIn().getHeader("direction");

            Response response = oracleDataService.getAdminStaffPtPK(
                    patientId,
                    bsnEntId,
                    page,
                    pageSize,
                    sortOn,
                    direction
            );

            exchange.getIn().setHeader("TOTAL_ROWS", response.getTotalRows());
            exchange.getIn().setHeader("PAGE", response.getPage());
            exchange.getIn().setHeader("PAGE_SIZE", response.getPageSize());
            exchange.getIn().setHeader("ORDER_BY_COLUMN", response.getOrderByColumn());
            exchange.getIn().setHeader("ORDER_BY_DIRECTION", response.getOrderByDirection());

            exchange.getIn().setBody(response.getData());

        } catch (Exception e) {
            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(errMsg, e);

        } finally {
            logger.stop();
        }
    }

    public void getAdminStaffStaffXwalkPK(final Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = OracleDataLogger.CreateLogger(exchange);

        logger.start();

        try {

            MessageContentsList mcl = (MessageContentsList) exchange.getIn().getBody();

            String staffId = (String) mcl.get(0);

            String bsnEntId = (String) mcl.get(1);
            if (bsnEntId == null || bsnEntId.length() == 0) {
                throw new SandataRuntimeException("BusinessEntityID (bsn_ent_id) is required!");
            }

            int page = (Integer) exchange.getIn().getHeader("page");
            int pageSize = (Integer) exchange.getIn().getHeader("page_size");
            String sortOn = (String) exchange.getIn().getHeader("sort_on");
            String direction = (String) exchange.getIn().getHeader("direction");

            Response response = oracleDataService.getAdminStaffStaffXwalkPK(
                    staffId,
                    bsnEntId,
                    page,
                    pageSize,
                    sortOn,
                    direction
            );

            exchange.getIn().setHeader("TOTAL_ROWS", response.getTotalRows());
            exchange.getIn().setHeader("PAGE", response.getPage());
            exchange.getIn().setHeader("PAGE_SIZE", response.getPageSize());
            exchange.getIn().setHeader("ORDER_BY_COLUMN", response.getOrderByColumn());
            exchange.getIn().setHeader("ORDER_BY_DIRECTION", response.getOrderByDirection());

            exchange.getIn().setBody(response.getData());

        } catch (Exception e) {
            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(errMsg, e);

        } finally {
            logger.stop();
        }
    }

    public void setOracleDataService(OracleDataService oracleDataService) {
        this.oracleDataService = oracleDataService;
    }

	public void getAdminStaffStaffXref(Exchange exchange) {
		 SandataLogger logger = OracleDataLogger.CreateLogger(exchange);

	        logger.start();

	        try {

	            MessageContentsList mcl = (MessageContentsList) exchange.getIn().getBody();

	            String staffId = (String) mcl.get(0);

	            String bsnEntId = (String) mcl.get(1);
	            if (bsnEntId == null || bsnEntId.length() == 0) {
	                throw new SandataRuntimeException("BusinessEntityID (bsn_ent_id) is required!");
	            }

	            int page = (Integer) exchange.getIn().getHeader("page");
	            int pageSize = (Integer) exchange.getIn().getHeader("page_size");
	            String sortOn = (String) exchange.getIn().getHeader("sort_on");
	            String direction = (String) exchange.getIn().getHeader("direction");

	            Response response = oracleDataService.getAdminStaffStaffXref(
	                    staffId,
	                    bsnEntId,
	                    page,
	                    pageSize,
	                    sortOn,
	                    direction
	            );

	            exchange.getIn().setHeader("TOTAL_ROWS", response.getTotalRows());
	            exchange.getIn().setHeader("PAGE", response.getPage());
	            exchange.getIn().setHeader("PAGE_SIZE", response.getPageSize());
	            exchange.getIn().setHeader("ORDER_BY_COLUMN", response.getOrderByColumn());
	            exchange.getIn().setHeader("ORDER_BY_DIRECTION", response.getOrderByDirection());

	            exchange.getIn().setBody(response.getData());

	        } catch (Exception e) {
	            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
	            throw new SandataRuntimeException(errMsg, e);

	        } finally {
	            logger.stop();
	        }
		
	}
}
