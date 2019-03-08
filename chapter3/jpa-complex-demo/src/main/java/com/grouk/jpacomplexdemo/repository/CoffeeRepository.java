package com.grouk.jpacomplexdemo.repository;

import com.grouk.jpacomplexdemo.model.Coffee;
import org.springframework.data.repository.CrudRepository;

/**
 * @author lizhengjun
 */
public interface CoffeeRepository extends CrudRepository<Coffee, Long> {
}
