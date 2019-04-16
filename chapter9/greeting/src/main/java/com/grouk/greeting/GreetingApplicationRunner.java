package com.grouk.greeting;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;


/**
 * @author lizhengjun
 */
@Slf4j
public class GreetingApplicationRunner implements ApplicationRunner {

    private String name;

    public GreetingApplicationRunner() {
        this("Greeting");
    }

    public GreetingApplicationRunner(String name) {
        this.name = name;
        log.info("Initializing GreetingApplicationRunner for{}.",name);
    }

    public void run(ApplicationArguments args) throws Exception {
        log.info("Hello everyone,we all like Lizhengjun");
    }
}
