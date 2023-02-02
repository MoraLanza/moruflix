package com.moruflix.commons.adapter.jdbc.model.filters;

import com.moruflix.commons.domain.filters.Filter;
import com.moruflix.commons.domain.filters.RangeFilter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JdbcRangeFilter implements JdbcFilter {

    private String field;
    private Object from;
    private Object to;

    @Override
    public String getConditionQuery() {
        StringBuilder sb = new StringBuilder(field);
        if (from != null) {
            sb.append(" >= ");
            sb.append(from);
        }
        if (from != null && to != null) {
            sb.append(" AND ");
            sb.append(field);
        }
        if (to != null) {
            sb.append(" <= ");
            sb.append(to);
        }
        return sb.toString();
    }

    @Override
    public JdbcFilter fromDomain(Filter domainFilter) {
        RangeFilter domain = (RangeFilter) domainFilter;
        return new JdbcRangeFilterBuilder()
                .field(domain.getKey())
                .from(domain.getFrom())
                .to(domain.getTo())
                .build();
    }

    @Override
    public JdbcFilter formatValues(Map<String, JdbcFormatter> formatters) {
        if (formatters.containsKey(field)) {
            JdbcFormatter formatter = formatters.get(field);
            Object formattedFrom = formatter.format(this.from);
            Object formattedTo = formatter.format(this.to);
            this.setFrom(formattedFrom);
            this.setTo(formattedTo);
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
