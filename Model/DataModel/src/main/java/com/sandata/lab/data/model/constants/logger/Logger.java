package com.sandata.lab.data.model.constants.logger;

public class Logger {

    public Logger() {
    }

    public static enum EVENT {

        LOGGER_PROCESS_MESSAGE("LOGGER_PROCESS_MESSAGE");

        private final String event;

        private EVENT(String event) {
            this.event = event;
        }

        public String toString() {
            return this.event;
        }
    }
}
