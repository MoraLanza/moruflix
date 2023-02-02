package com.moruflix.commons.adapter.jdbc.model.filters;

import com.moruflix.commons.domain.filters.EqualsFilter;
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
public class JdbcEqualsFilter implements JdbcFilter {

    private String field;
    private Object value;

    @Override
    public String getConditionQuery() {
        if(value == null || "NULL".equalsIgnoreCase(value.toString())) {
            return field + " IS NULL";
        }
        return field + " = " + value.toString();
    }

    @Override
    public JdbcFilter fromDomain(Filter domainFilter) {
        EqualsFilter domain = (EqualsFilter) domainFilter;
        return new JdbcEqualsFilterBuilder()
                .field(domain.getKey())
                .value(domain.getValue())
                .build();
    }

    @Override
    public JdbcFilter formatValues(Map<String, JdbcFormatter> formatters) {
        if (formatters.containsKey(field)) {
            JdbcFormatter formatter = formatters.get(field);
            Object formattedValue = formatter.format(value);
            this.setValue(formattedValue);
        }
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
