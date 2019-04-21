package com.grouk.cachewithredisdemo.repository;
;
import com.grouk.cachewithredisdemo.model.CoffeeOrder;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author lizhengjun
 */
public interface CoffeeOrderRepository extends JpaRepository<CoffeeOrder, Long> {
}
