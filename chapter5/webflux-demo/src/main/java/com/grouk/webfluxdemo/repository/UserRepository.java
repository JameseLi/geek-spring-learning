package com.grouk.webfluxdemo.repository;

import com.grouk.webfluxdemo.pojo.User;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

/**
 * @author lizhengjun
 */
public interface UserRepository extends ReactiveCrudRepository<User, String> {
    /**
     * 根据用户名称查询用户详情
     *
     * @param username 用户名称
     * @return Mono<User>
     */
    Mono<User> findByUsername(String username);

    /**
     * 根据用户名称删除用户
     *
     * @param username 用户名称
     * @return Mono<Long>
     */
    Mono<Long> deleteByUsername(String username);
}
