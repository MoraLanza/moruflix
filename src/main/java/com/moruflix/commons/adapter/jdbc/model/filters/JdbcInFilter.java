package com.moruflix.commons.adapter.jdbc.model.filters;

import com.moruflix.commons.domain.filters.Filter;
import com.moruflix.commons.domain.filters.InListFilter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JdbcInFilter implements JdbcFilter {

    private String field;
    private List<String> values;

    @Override
    public String getConditionQuery() {
        StringBuilder sb = new StringBuilder(field);
        sb.append(" IN (");
        sb.append(String.join(",", values));
        sb.append(")");
        return sb.toString();
    }

    @Override
    public JdbcFilter fromDomain(Filter domainFilter) {
        InListFilter domain = (InListFilter) domainFilter;
        return new JdbcInFilterBuilder()
                .field(domain.getKey())
                .values(domain.getValues())
                .build();
    }

    @Override
    public JdbcFilter formatValues(Map<String, JdbcFormatter> formatters) {
        if (formatters.containsKey(field)) {
            JdbcFormatter formatter = formatters.get(field);
            List<String> formattedValues = values.stream()
                    .map(value -> formatter.format(value).toString())
                    .collect(Collectors.toList());
            this.setValues(formattedValues);
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