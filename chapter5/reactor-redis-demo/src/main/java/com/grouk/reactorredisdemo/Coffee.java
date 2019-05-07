package com.grouk.reactorredisdemo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author lizhengjun
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Coffee {
    private Long id;

    private String name;

    private Long price;
}
