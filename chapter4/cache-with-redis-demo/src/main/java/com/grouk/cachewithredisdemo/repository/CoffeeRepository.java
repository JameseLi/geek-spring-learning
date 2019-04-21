package com.grouk.cachewithredisdemo.repository;

import com.grouk.cachewithredisdemo.model.Coffee;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author lizhengjun
 */
public interface CoffeeRepository extends JpaRepository<Coffee, Long> {
}
