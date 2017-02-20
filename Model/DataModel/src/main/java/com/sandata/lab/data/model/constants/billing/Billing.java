package com.sandata.lab.data.model.constants.billing;


/**
 * Enumerates the various queues that are in the billing event process.
 * <p/>
 *
 * @author David Rutgos
 */
public class Billing {

    public enum EVENT {

        BILLING_PROCESS_VISITS ("BILLING_PROCESS_VISITS"),

        BILLING_PROCESS_APPROVED_VISITS ("BILLING_PROCESS_APPROVED_VISITS"),

        BILLING_PROCESS_UNAPPROVED_VISITS ("BILLING_PROCESS_UNAPPROVED_VISITS");

        private final String event;

        EVENT(final String event) {
            this.event = event;
        }

        @Override
        public String toString() {
            return this.event;
        }
    }
}
