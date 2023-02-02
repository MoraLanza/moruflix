package com.moruflix.commons.domain.filters;

import lombok.Data;

import java.util.List;

@Data
public abstract class Filter {

    Filter build(String key, List<String> values) {
        throw new UnsupportedOperationException();
    }
}
