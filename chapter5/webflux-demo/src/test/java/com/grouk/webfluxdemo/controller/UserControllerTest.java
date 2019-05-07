package com.grouk.webfluxdemo.controller;

import com.grouk.webfluxdemo.pojo.User;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import static org.junit.Assert.*;

public class UserControllerTest {

    @Test
    public void testFindAll() {
        WebClient webClient = WebClient.builder().baseUrl("http://127.0.0.1:8080").build();
        webClient.get().uri("/user")
                .accept(MediaType.APPLICATION_STREAM_JSON)
                .exchange()
                .flatMapMany(response -> response.bodyToFlux(User.class))
                .doOnNext(System.out::println)
                .blockLast();
    }
}