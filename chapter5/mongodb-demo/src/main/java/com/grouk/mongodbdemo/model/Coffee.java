package com.grouk.mongodbdemo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.money.Money;

import java.util.Date;

/**
 * @author lizhengjun
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Coffee {
    private String id;

    private String name;

    private Money price;

    private Date createTime;

    private Date updateTime;
}
