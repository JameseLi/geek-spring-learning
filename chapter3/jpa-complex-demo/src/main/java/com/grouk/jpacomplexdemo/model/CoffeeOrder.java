package com.grouk.jpacomplexdemo.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "T_ORDER")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class CoffeeOrder extends BaseEntity implements Serializable {
    private static final long serialVersionUID = -576820958886631307L;

//    @Id
//    @GeneratedValue
//    private Long id;

    private String customer;

    @ManyToMany
    @JoinTable(name = "T_ORDER_COFFEE")
    @OrderBy("id")
    private List<Coffee> items;

    @Enumerated
    @Column(nullable = false)
    private OrderState state;

//    @Column(updatable = false)
//    @CreationTimestamp
//    private Date createTime;
//
//    @UpdateTimestamp
//    private Date updateTime;
}
