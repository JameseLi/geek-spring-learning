package com.grouk.jpademo.repository;

import com.grouk.jpademo.model.CoffeeOrder;
import org.springframework.data.repository.CrudRepository;

/**
 * @author lizhengjun
 */
public interface CoffeeOrderRepository extends CrudRepository<CoffeeOrder, Long> {
}
