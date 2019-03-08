package com.grouk.jpademo.repository;

import com.grouk.jpademo.model.Coffee;
import org.springframework.data.repository.CrudRepository;

/**
 * @author lizhengjun
 */
public interface CoffeeRepository extends CrudRepository<Coffee, Long> {
}
