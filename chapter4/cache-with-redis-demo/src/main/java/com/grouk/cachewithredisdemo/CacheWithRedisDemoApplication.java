package com.grouk.cachewithredisdemo;

import com.grouk.cachewithredisdemo.service.CoffeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * cache 在spring中的使用，缓存放在Redis中
 *
 * @author lizhengjun
 */
@Slf4j
@EnableTransactionManagement
@EnableJpaRepositories
@EnableCaching(proxyTargetClass = true)
@SpringBootApplication
public class CacheWithRedisDemoApplication implements ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(CacheWithRedisDemoApplication.class, args);
    }


    @Autowired
    private CoffeeService coffeeService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        //第一次访问，调用sql,生成了cache
        log.info("Count: {}", coffeeService.findAllCoffee().size());
        //此时的多次调用都不会调用sql
        for (int i = 0; i < 10; i++) {
            log.info("Reading from cache.");
            coffeeService.findAllCoffee();
        }
        //清除缓存
        coffeeService.reloadCache();
        log.info("Reading after refresh.");
        //此时调用会再次调用sql
        coffeeService.findAllCoffee().forEach(c -> log.info("Coffee {}", c.getName()));
    }
}
