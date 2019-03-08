package com.grouk.jpacomplexdemo.repository;

import com.grouk.jpacomplexdemo.model.BaseEntity;
import com.grouk.jpacomplexdemo.model.CoffeeOrder;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author lizhengjun
 */
public interface CoffeeOrderRepository extends BaseRepository<CoffeeOrder, Long> {
    List<CoffeeOrder> findByCustomerOrderById(String customer);

    List<CoffeeOrder> findByItems_Name(String name);
}
