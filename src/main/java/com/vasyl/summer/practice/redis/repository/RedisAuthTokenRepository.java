package com.vasyl.summer.practice.redis.repository;

import com.vasyl.summer.practice.redis.RedisAuthToken;
import org.springframework.data.repository.CrudRepository;

public interface RedisAuthTokenRepository extends CrudRepository<RedisAuthToken, String> {
}
