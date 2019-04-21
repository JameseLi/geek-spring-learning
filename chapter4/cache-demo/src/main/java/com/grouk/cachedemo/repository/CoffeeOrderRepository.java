package com.grouk.cachedemo.repository;

import com.grouk.cachedemo.model.CoffeeOrder;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author lizhengjun
 */
public interface CoffeeOrderRepository extends JpaRepository<CoffeeOrder, Long> {
}
