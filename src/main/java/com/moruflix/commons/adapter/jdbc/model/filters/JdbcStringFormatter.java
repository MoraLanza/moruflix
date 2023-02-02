package com.moruflix.commons.adapter.jdbc.model.filters;


    public class JdbcStringFormatter implements JdbcFormatter {

        @Override
        public Object format(Object value) {
            if (value == null || "null".equalsIgnoreCase(value.toString())) {
                return value;
            }
            return "'" + value.toString() + "'";
        }
}
