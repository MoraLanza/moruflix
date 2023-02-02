package com.moruflix.commons.adapter.jdbc.model.filters;

import com.moruflix.commons.domain.filters.CompositeFilter;
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
public class JdbcCompositeFilter implements JdbcFilter{
    private String connector;
    private JdbcFilter actual;
    private JdbcFilter next;
    private JdbcFilterBuilder builder;

    JdbcCompositeFilter(JdbcFilterBuilder builder) {
        this.builder = builder;
    }

    @Override
    public String getConditionQuery() {
        return actual.getConditionQuery() + " " + connector + " " + next.getConditionQuery();
    }

    @Override
    public JdbcFilter fromDomain(Filter domainFilter) {
        CompositeFilter domain = (CompositeFilter) domainFilter;
        return new JdbcCompositeFilterBuilder()
                .connector(domain.getConnector().name())
                .actual(builder.build(domain.getActual()))
                .next(builder.build(domain.getNext()))
                .build();
    }

    @Override
    public JdbcFilter formatValues(Map<String, JdbcFormatter> formatters) {
        this.setActual(actual.formatValues(formatters));
        this.setNext(next.formatValues(formatters));
        return this;
    }

    @Override
    public JdbcFilter translateField(Map<String, String> translations) {
        this.setActual(actual.translateField(translations));
        this.setNext(next.translateField(translations));
        return this;
    }
}