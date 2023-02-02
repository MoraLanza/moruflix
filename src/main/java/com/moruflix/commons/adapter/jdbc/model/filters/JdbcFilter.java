package com.moruflix.commons.adapter.jdbc.model.filters;

import com.moruflix.commons.domain.filters.Filter;

import java.util.Map;

public interface JdbcFilter {

    String getConditionQuery();

    JdbcFilter fromDomain(Filter domain);

    JdbcFilter formatValues(Map<String, JdbcFormatter> formatters);

    JdbcFilter translateField(Map<String, String> translations);
}
