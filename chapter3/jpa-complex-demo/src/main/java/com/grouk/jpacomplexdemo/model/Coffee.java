package com.grouk.jpacomplexdemo.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;
import org.joda.money.Money;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "T_MENU")
/**
 * get 和 set方法
 */
@Data
@Builder
/**
 * 无参数构造函数
 * */
@NoArgsConstructor
/**
 * 全参数构造函数
 * */
@AllArgsConstructor
@ToString(callSuper = true)
public class Coffee extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 640559815029657342L;

//    @Id
//    @GeneratedValue
//    private Long id;

    private String name;

    @Column
    @Type(type = "org.jadira.usertype.moneyandcurrency.joda.PersistentMoneyAmount",
            parameters = {@org.hibernate.annotations.Parameter(name = "currencyCode", value = "CNY")}
    )
    private Money price;

//    @Column(updatable = false)
//    @CreationTimestamp
//    private Date createTime;
//
//    @UpdateTimestamp
//    private Date updateTime;
}
