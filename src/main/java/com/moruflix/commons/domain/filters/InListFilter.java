package com.moruflix.commons.domain.filters;

import lombok.*;
import org.apache.logging.log4j.util.Strings;

import java.util.List;
import java.util.stream.Collectors;

@Data
@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InListFilter extends Filter {

    String key;
    List<String> values;

    @Override
    public Filter build(String key, List<String> values) {
        List<String> valuesWithoutEmpty = values.stream()
                .filter(value -> !Strings.isEmpty(value))
                .collect(Collectors.toList());
        return new InListFilterBuilder()
                .key(key)
                .values(valuesWithoutEmpty)
                .build();
    }
}
