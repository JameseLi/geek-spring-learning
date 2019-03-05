package com.grouk.simplejdbcdemo;

import lombok.Builder;
import lombok.Data;

/**
 * @author lizhengjun
 */
@Data
@Builder
public class Foo {

    private long id;

    private String bar;
}
