package com.moruflix.commons.domain.filters;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.util.List;
@Data
@EqualsAndHashCode(callSuper=false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public class RangeFilter extends Filter {

    String key;
    String from;
    String to;

    @Override
    public Filter build(String key, List<String> values) {
        return new RangeFilterBuilder()
                .key(key)
                .from(getValue(values.get(0)))
                .to(values.size() > 1 ? getValue(values.get(1)) : null)
                .build();
    }

    private String getValue(String value) {
        return StringUtils.isEmpty(value) ? null : value;
    }
}
