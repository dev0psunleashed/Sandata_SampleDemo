package com.sandata.lab.data.model.constants.payroll;

/**
 * Enumerates the various queues that are in the payroll event process.
 * <p/>
 *
 * @author David Rutgos
 */
public class Payroll {

    public enum EVENT {

        PAYROLL_PROCESS_VISITS ("PAYROLL_PROCESS_VISITS"),

        PAYROLL_PROCESS_APPROVED_VISITS ("PAYROLL_PROCESS_APPROVED_VISITS"),

        PAYROLL_PROCESS_UNAPPROVED_VISITS ("PAYROLL_PROCESS_UNAPPROVED_VISITS");

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
