package com.moruflix.commons.adapter.jdbc.model.filters;

import com.moruflix.commons.domain.filters.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class JdbcFilterBuilder {
    private final Map<String, JdbcFormatter> formatters = Map.of(
            "title", new JdbcStringFormatter()
    );

    private final Map<Class, JdbcFilter> jdbcFilterFactory = Map.of(
            RangeFilter.class, new JdbcRangeFilter(),
            EqualsFilter.class, new JdbcEqualsFilter(),
            InListFilter.class, new JdbcInFilter(),
            CompositeFilter.class, new JdbcCompositeFilter(this),
            ContainsFilter.class, new JdbcContainsFilter()
    );

    public JdbcFilter build(Filter filter) {
        return jdbcFilterFactory.get(filter.getClass())
                .fromDomain(filter)
                .formatValues(formatters);
    }

    public List<JdbcFilter> build(List<Filter> filters) {
        return filters.stream()
                .map(this::build)
                .collect(Collectors.toList());
    }
}
