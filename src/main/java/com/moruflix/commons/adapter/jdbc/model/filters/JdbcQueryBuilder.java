package com.moruflix.commons.adapter.jdbc.model.filters;


import org.apache.logging.log4j.util.Strings;

import java.util.List;

public class JdbcQueryBuilder {

    public static String buildCountQuery(String query, List<JdbcFilter> filters) {
        StringBuilder sb = appendFilters(query, filters);

        return "SELECT COUNT(*) FROM " + sb.toString();
    }

    public static String buildSelectQuery(String query, List<JdbcFilter> filters) {
        StringBuilder sb = appendFilters(query, filters);
        return "SELECT * FROM " + sb.toString();
    }

    public static String buildPagedSelectQuery(String query, String orderFieldName, List<JdbcFilter> filters, int page, int size) {
        StringBuilder sb = appendFilters(query, filters);
        if(!Strings.isEmpty(orderFieldName))
            sb.append(" ORDER BY ").append(orderFieldName).append(" DESC");
        int offset = page * size;
        sb.append(" OFFSET ").append(offset).append(" ROWS");
        sb.append(" FETCH NEXT ").append(size).append(" ROWS ONLY");

        return "SELECT * FROM " + sb.toString();
    }

    public static StringBuilder appendFilters(String query, List<JdbcFilter> filters) {
        StringBuilder sb = new StringBuilder(query);
        filters.forEach(filter -> {
            sb.append(" AND (");
            sb.append(filter.getConditionQuery());
            sb.append(")");
        });
        return sb;
    }

}
