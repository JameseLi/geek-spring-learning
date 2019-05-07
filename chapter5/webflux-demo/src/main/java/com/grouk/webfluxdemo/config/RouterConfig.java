package com.grouk.webfluxdemo.config;

import com.grouk.webfluxdemo.hander.TimeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

/**
 * @author lizhengjun
 */
@Configuration
public class RouterConfig {

    @Autowired
    private TimeHandler timeHandler;

    @Bean
    public RouterFunction<ServerResponse> timeRouter() {
        return route(GET("/time"), req -> timeHandler.getTime(req))
                .andRoute(GET("/date"), timeHandler::getDate)
                .andRoute(GET("/times"), timeHandler::sendTimePerSec);
    }
}
