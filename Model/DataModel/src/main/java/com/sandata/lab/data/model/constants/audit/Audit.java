package com.sandata.lab.data.model.constants.audit;

/**
 * Date: 9/5/16
 * Time: 10:39 PM
 */

public class Audit {

    public enum EVENT {

        AUDIT_CHANGED_EVENT ("AUDIT_CHANGED_EVENT");

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
