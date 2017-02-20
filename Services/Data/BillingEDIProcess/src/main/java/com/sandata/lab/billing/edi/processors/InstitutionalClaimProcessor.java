package com.sandata.lab.billing.edi.processors;

import org.apache.camel.Exchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sandata.lab.billing.edi.impl.OracleDataService;
import com.sandata.lab.billing.edi.model.claim.ClaimMessage;

/**
 * Processor to create Institutional Claim in JSON format from database
 */
public class InstitutionalClaimProcessor {
    /**
     * Bean identification is configured in blueprint.xml
     */
    public static final String BEAN_NAME = "institutionalClaimProcessor";

    /**
     * Method {@link #createClaimMessage(Exchange)} is used in Camel routes
     */
    public static final String CREATE_CLAIM_MESSAGE_METHOD = "createClaimMessage";
    
    private static final Logger LOGGER = LoggerFactory.getLogger(InstitutionalClaimProcessor.class);

    private OracleDataService oracleDataService;

    public OracleDataService getOracleDataService() {
        return oracleDataService;
    }

    public void setOracleDataService(OracleDataService oracleDataService) {
        this.oracleDataService = oracleDataService;
    }

    public ClaimMessage createClaimMessage(final Exchange exchange) {
        ClaimMessage claimMessage = new ClaimMessage();
        
        // TODO: replace hard-coded values below ClaimMessage's properties with correct values from database
        claimMessage.setName("InstitutionalClaim.json");
        claimMessage.setInterchangeSubmitterIdType("ZZ");
        claimMessage.setInterchangeSubmitterId("SANDATA");
        claimMessage.setInterchangeReceiverIdType("ZZ");
        claimMessage.setInterchangeSubmitterId("EMEDNY");
        claimMessage.setGroupSubmitterId("SANDATA");
        claimMessage.setGroupReceiverId("EMEDNY");
        claimMessage.setControlNumber("2");
        claimMessage.setSegmentDelimiter("~");
        claimMessage.setElementDelimiter("*");
        claimMessage.setSubelementDelimiter(":");
        claimMessage.setRepetitionDelimiter("^");

        // TODO: add institutional claim transactions
        //List<Transaction> institutionalClaimTransactions = new ArrayList<Transaction>();
        //institutionalClaimTransactions.add(generateInstitutionalClaimTransaction(exchange));
        //claimMessage.setInstitutionalClaimTransactions(institutionalClaimTransactions);

        return claimMessage;
    }
}
