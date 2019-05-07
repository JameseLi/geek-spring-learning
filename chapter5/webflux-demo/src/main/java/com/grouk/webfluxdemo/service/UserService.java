package com.grouk.webfluxdemo.service;

import com.grouk.webfluxdemo.pojo.User;
import com.grouk.webfluxdemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author lizhengjun
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Mono<User> save(User user) {
        return userRepository.save(user)
                .onErrorResume(e -> userRepository.findByUsername(user.getUsername())
                        .flatMap(originalUser -> {
                            user.setId(originalUser.getId());
                            return userRepository.save(user);
                        }));
    }

    public Mono<Long> deleteByUsername(String username) {
        return userRepository.deleteByUsername(username);
    }

    public Mono<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public Flux<User> findAll() {
        return userRepository.findAll();
    }
}
