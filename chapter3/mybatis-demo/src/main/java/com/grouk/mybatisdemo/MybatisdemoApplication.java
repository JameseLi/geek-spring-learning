package com.grouk.mybatisdemo;

import com.alibaba.druid.pool.DruidDataSource;
import com.grouk.mybatisdemo.mapper.CoffeeMapper;
import com.grouk.mybatisdemo.model.Coffee;
import lombok.extern.slf4j.Slf4j;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;

@SpringBootApplication
@MapperScan(value = "com.grouk.mybatisdemo.mapper")
@RestController
@Slf4j
public class MybatisdemoApplication {

    @Autowired
    private CoffeeMapper coffeeMapper;

//    @Autowired
//    private DataSource dataSource;

    public static void main(String[] args) {
        SpringApplication.run(MybatisdemoApplication.class, args);
    }

//    @Bean
//    public DataSource druidDataSource() {
//        return new DruidDataSource();
//    }

//    @GetMapping(value = "datasource")
//    public String datasource() {
//        return dataSource.toString();
//    }

    @GetMapping(value = "run")
    public void run() {
        Coffee c = Coffee.builder().name("espresso")
                .price(Money.of(CurrencyUnit.of("CNY"), 20.0)).build();
        Long id = coffeeMapper.save(c);
        log.info("Coffee {} => {}", id, c);

        c = coffeeMapper.findById(id);
        log.info("Coffee {}", c);
    }
}
