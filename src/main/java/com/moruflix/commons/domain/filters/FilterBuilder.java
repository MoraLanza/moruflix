package com.moruflix.commons.domain.filters;


import com.moruflix.commons.application.exception.InvalidFiltersException;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Slf4j
public class FilterBuilder {

    private final Map<String, Filter> filterFactory = Map.of(
            "range", new RangeFilter(),
            "equals", new EqualsFilter(),
            "notequals", new NotEqualsFilter(),
            "in", new InListFilter(),
            "composite", new CompositeFilter(this),
            "contains", new ContainsFilter()
    );

    public List<Filter> build(Map<String, String> mappedFilters) {
        try {
            return doBuild(mappedFilters);
        } catch (IndexOutOfBoundsException ex) {
            log.error("Error de parseo de filtros", ex);
            throw new InvalidFiltersException();
        }
    }

    private List<Filter> doBuild(Map<String, String> mappedFilters) {
        List<Filter> filters = new ArrayList<>();
        mappedFilters.forEach((key, value) -> {
            List<String> valueSplit = Arrays.asList(value.split(","));
            Filter filter = buildFilter(key, valueSplit);
            filters.add(filter);
        });

        return filters;
    }

    public Filter buildFilter(String key, List<String> valueSplit) {
        String filterType;
        List<String> values;

        if(isComposite(valueSplit)) {
            filterType = "composite";
            values = valueSplit;
        }
        else {
            filterType = valueSplit.get(0);
            values = valueSplit.subList(1, valueSplit.size());
        }
        validate(filterType);
        return filterFactory.get(filterType).build(key, values);
    }

    private void validate(String filterType) {
        if (!filterFactory.containsKey(filterType)) {
            throw new InvalidFiltersException();
        }
    }

    private boolean isComposite(List<String> values) {
        return values.contains("or") || values.contains("and");
    }
}