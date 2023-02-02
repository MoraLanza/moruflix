package com.moruflix.commons.adapter.jdbc.model.filters;


import com.moruflix.commons.domain.filters.ContainsFilter;
import com.moruflix.commons.domain.filters.Filter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JdbcContainsFilter implements JdbcFilter {

    private String field;
    private Object value;

    @Override
    public String getConditionQuery() {
        if(value == null || value.toString().isEmpty()) {
            return field + " LIKE '%'";
        }
        return field + " LIKE '%" + value.toString() + "%'";
    }

    @Override
    public JdbcFilter fromDomain(Filter domainFilter) {
        ContainsFilter domain = (ContainsFilter) domainFilter;
        return new JdbcContainsFilter.JdbcContainsFilterBuilder()
                .field(domain.getKey())
                .value(domain.getValue())
                .build();
    }

    @Override
    public JdbcFilter formatValues(Map<String, JdbcFormatter> formatters) {
        return this;
    }

    @Override
    public JdbcFilter translateField(Map<String, String> translations) {
        if (translations.containsKey(field)) {
            String newField = translations.get(field);
            this.setField(newField);
        }
        return this;
    }
}
