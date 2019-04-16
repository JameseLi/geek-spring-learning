package com.grouk.springbootautoconfig;


import com.grouk.greeting.GreetingApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 *
 * @author lizhengjun
 */
@Configuration
@ConditionalOnClass(GreetingApplicationRunner.class)
public class GreetingAutoConfigure {

    @Bean
    @ConditionalOnMissingBean(GreetingApplicationRunner.class)
    @ConditionalOnProperty(name = "greeting.enabled", havingValue = "true", matchIfMissing = true)
    public GreetingApplicationRunner greetingApplicationRunner() {
        return new GreetingApplicationRunner();
    }

}
