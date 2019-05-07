package com.grouk.webfluxdemo.controller;

import com.grouk.webfluxdemo.pojo.User;
import com.grouk.webfluxdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

/**
 * @author lizhengjun
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("")
    public Mono<User> save(User user) {
        return userService.save(user);
    }

    @DeleteMapping("{username}")
    public Mono<Long> deleteByUsername(@PathVariable String username) {
        return userService.deleteByUsername(username);
    }

    @GetMapping("{username}")
    public Mono<User> findByUsername(@PathVariable String username) {
        return userService.findByUsername(username);
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<User> findAll() {
        return userService.findAll().delayElements(Duration.ofSeconds(2));
    }
}
