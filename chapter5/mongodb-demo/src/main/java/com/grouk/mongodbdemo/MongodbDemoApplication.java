package com.grouk.mongodbdemo;

import com.grouk.mongodbdemo.converter.MoneyReadConverter;
import com.grouk.mongodbdemo.converter.MoneyWriteConverter;
import com.grouk.mongodbdemo.model.Coffee;
import lombok.extern.slf4j.Slf4j;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;
import org.springframework.data.mongodb.core.query.Update;
import reactor.core.scheduler.Schedulers;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

/**
 * @author lizhengjun
 */
@SpringBootApplication
@Slf4j
public class MongodbDemoApplication implements ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(MongodbDemoApplication.class, args);
    }

    @Autowired
    private ReactiveMongoTemplate mongoTemplate;
    private CountDownLatch cdl = new CountDownLatch(2);

    @Bean
    public MongoCustomConversions customConversions() {
        return new MongoCustomConversions(
                Arrays.asList(
                        new MoneyReadConverter(),
                        new MoneyWriteConverter()
                ));
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        startFromInsertion(() -> {
            log.info("Runnable");
            decreaseHighPrice();
        });
        log.info("after starting");
        cdl.await();
    }

    private void startFromInsertion(Runnable runnable) {
        mongoTemplate.insertAll(initCoffee())
                .publishOn(Schedulers.elastic())
                .doOnNext(c -> log.info("Next: {}", c))
                .doOnComplete(runnable)
                .doFinally(s -> {
                    cdl.countDown();
                    log.info("Finally 1, {}", s);
                })
                .count()
                .subscribe(c -> log.info("Insert {} records", c));
    }

    private void decreaseHighPrice() {
        mongoTemplate.updateMulti(query(where("price").gte(3000L)),
                new Update().inc("price", -500L)
                        .currentDate("updateTime"), Coffee.class)
                .doFinally(s -> {
                    cdl.countDown();
                    log.info("Finally 2, {}", s);
                })
                .subscribe(r -> log.info("Result is {}", r));
    }

    private List<Coffee> initCoffee() {
        Coffee espresso = Coffee.builder()
                .name("espresso")
                .price(Money.of(CurrencyUnit.of("CNY"), 20.0))
                .createTime(new Date())
                .updateTime(new Date())
                .build();

        Coffee latte = Coffee.builder()
                .name("latte")
                .price(Money.of(CurrencyUnit.of("CNY"), 30.0))
                .createTime(new Date())
                .updateTime(new Date())
                .build();

        return Arrays.asList(espresso, latte);
    }
}
