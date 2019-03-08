package com.grouk.jpademo;

import com.grouk.jpademo.model.Coffee;
import com.grouk.jpademo.model.CoffeeOrder;
import com.grouk.jpademo.repository.CoffeeOrderRepository;
import com.grouk.jpademo.repository.CoffeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Arrays;
import java.util.Collections;

@SpringBootApplication
@EnableJpaRepositories
@Slf4j
public class JpademoApplication implements ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(JpademoApplication.class, args);
    }

    @Autowired
    private CoffeeRepository coffeeRepository;

    @Autowired
    private CoffeeOrderRepository coffeeOrderRepository;

    private void initOrders() {
        Coffee espresso = Coffee.builder()
                .name("espresso")
                .price(Money.of(CurrencyUnit.of("CNY"), 20.0))
                .build();
        coffeeRepository.save(espresso);
        log.info("Coffee: {}", espresso);

        Coffee latte = Coffee.builder().name("latte")
                .price(Money.of(CurrencyUnit.of("CNY"), 30.0))
                .build();
        coffeeRepository.save(latte);
        log.info("Coffee: {}", latte);

        CoffeeOrder order = CoffeeOrder.builder()
                .customer("Li Lei")
                .state(0)
                .items(Collections.singletonList(espresso))
                .build();
        coffeeOrderRepository.save(order);
        log.info("order: {}", order);

        order = CoffeeOrder.builder()
                .customer("Li Lei")
                .items(Arrays.asList(espresso, latte))
                .state(0)
                .build();
        coffeeOrderRepository.save(order);
        log.info("order: {}", order);
    }


    @Override
    public void run(ApplicationArguments args) throws Exception {
        initOrders();
    }
}
