package com.sandata.lab.data.model.constants.filter;

import java.io.Serializable;

public class Filter implements Serializable {

    private static final long serialVersionUID = 1L;

    public enum OPTIONS {

        EQUALS ("EQUALS"),

        CONTAINS ("CONTAINS"),

        STARTS_WITH ("STARTS_WITH"),

        ENDS_WITH ("ENDS_WITH");

        private final String option;

        private OPTIONS(final String option) {
            this.option = option;
        }

        @Override
        public String toString() {
            return this.option;
        }
    }
}
