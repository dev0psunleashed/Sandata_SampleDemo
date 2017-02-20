package com.sandata.lab.data.model.constants.eligibility;

/**
 * Enumerates the various queues that are in the eligibility event process.
 */
public class Eligibility {
    public enum EVENT {

        ELIGIBILITY_PROCESS_SENDS_INQUIRY_270_REQUEST("ELIGIBILITY_PROCESS_SENDS_INQUIRY_270_REQUEST");

        private final String event;

        private EVENT(final String event) {
            this.event = event;
        }

        @Override
        public String toString() {
            return this.event;
        }
    }
}
