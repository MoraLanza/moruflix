package com.moruflix.commons.domain.filters;

import lombok.*;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper=false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EqualsFilter extends Filter {

    String key;
    String value;

    @Override
    public Filter build(String key, List<String> values) {
        return new EqualsFilterBuilder()
                .key(key)
                .value(values.get(0))
                .build();
    }
}
