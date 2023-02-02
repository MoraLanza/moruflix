package com.moruflix.commons.domain.filters;

import lombok.*;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper=false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContainsFilter extends Filter {

    String key;
    String value;

    @Override
    public Filter build(String key, List<String> values) {
        return new ContainsFilter.ContainsFilterBuilder()
                .key(key)
                .value(values == null || values.isEmpty()? null : values.get(0))
                .build();
    }
}
