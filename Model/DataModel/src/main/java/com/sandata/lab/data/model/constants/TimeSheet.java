package com.sandata.lab.data.model.constants;

/**
 * Enumerates the various queues that are in the timesheet event process.
 * <p/>
 *
 * @author David Rutgos
 */
public class TimeSheet {

    public enum EVENT {

        TIMESHEET_DETAIL_PROCESS ("TIMESHEET_DETAIL_PROCESS");

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
