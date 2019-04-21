package com.grouk.jedisdemo;

import com.grouk.jedisdemo.model.Coffee;
import lombok.extern.slf4j.Slf4j;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Map;

/**
 * jedis 在springboot中的使用方法
 *
 * jedis 支持哨兵模式
 * Jedis 也支持集群模式，但是集群模式不支持读写分离，如果需要支持读写分离需要根据业务进行定制
 *
 * @author lizhengjun
 */
@SpringBootApplication
@Slf4j
public class JedisDemoApplication implements ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(JedisDemoApplication.class, args);
    }

    @Autowired
    private JedisPool jedisPool;

    @Autowired
    private JedisPoolConfig jedisPoolConfig;

    @Bean
    @ConfigurationProperties("redis")
    public JedisPoolConfig jedisPoolConfig() {
        return new JedisPoolConfig();
    }

    @Bean(destroyMethod = "close")
    public JedisPool jedisPool(@Value("${redis.host}") String host) {
        return new JedisPool(jedisPoolConfig(), host);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        //输出jedisConfig的配置
        log.info(jedisPoolConfig.toString());

        Coffee espresso = Coffee.builder()
                .id(1L)
                .name("espresso")
                .price(Money.ofMinor(CurrencyUnit.of("CNY"), 2000))
                .build();
        Coffee latte = Coffee.builder()
                .id(2L)
                .name("latte")
                .price(Money.ofMinor(CurrencyUnit.of("CNY"), 2500))
                .build();
        try (Jedis jedis = jedisPool.getResource()) {
            jedis.hset("springbucks-menu", espresso.getName(), Long.toString(espresso.getPrice().getAmountMajorLong()));
            jedis.hset("springbucks-menu", latte.getName(), Long.toString(latte.getPrice().getAmountMajorLong()));
            Map<String, String> menu = jedis.hgetAll("springbucks-menu");
            log.info("menu{}", menu);

            String price = jedis.hget("springbucks-menu", espresso.getName());
            log.info("espresso - {}",
                    Money.ofMinor(CurrencyUnit.of("CNY"), Long.parseLong(price)));
        }
    }
}
