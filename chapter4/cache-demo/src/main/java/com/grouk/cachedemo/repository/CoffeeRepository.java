package com.grouk.cachedemo.repository;

import com.grouk.cachedemo.model.Coffee;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author lizhengjun
 */
public interface CoffeeRepository extends JpaRepository<Coffee, Long> {
}
