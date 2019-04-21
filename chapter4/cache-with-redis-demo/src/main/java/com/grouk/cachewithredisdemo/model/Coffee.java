package com.grouk.cachewithredisdemo.model;

import lombok.*;
import org.hibernate.annotations.Type;
import org.joda.money.Money;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author lizhengjun
 */
@Entity
@Table(name = "T_COFFEE")
@Builder
@Data
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class Coffee extends BaseEntity implements Serializable {

    private String name;


    /**
     * 写入数据库是字段转换类型
     */
    @Type(type = "org.jadira.usertype.moneyandcurrency.joda.PersistentMoneyMinorAmount",
            parameters = {@org.hibernate.annotations.Parameter(name = "currencyCode", value = "CNY")})
    private Money price;
}
