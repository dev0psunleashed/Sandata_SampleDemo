package com.sandata.lab.security.auth.utils.cache;

public enum Caches {


        BE_TNT_ID_XWALK("BE_TNT_XWALK");

        private String value;

        public String getValue() {
            return value;
        }

        private Caches(String value){
            this.value = value;
        }

}
