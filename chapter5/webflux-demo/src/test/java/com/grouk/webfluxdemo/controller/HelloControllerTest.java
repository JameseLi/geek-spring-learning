package com.grouk.webfluxdemo.controller;

import org.junit.Test;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class HelloControllerTest {

    @Test
    public void hello() throws InterruptedException {
        WebClient webClient = WebClient.create("http://localhost:8080");
        Mono<String> resp = webClient
                .get().uri("/hello")
                .retrieve()
                .bodyToMono(String.class);
        resp.subscribe(System.out::println);
        TimeUnit.SECONDS.sleep(1);
    }
}