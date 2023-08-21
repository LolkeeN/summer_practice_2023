package com.vasyl.summer.practice.redis;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@Getter
@Setter
@RedisHash("RedisAuthToken")
public class RedisAuthToken {
    @Id
    private String userId;

    private Long created;

    @Indexed
    private String token;

    public RedisAuthToken() {
        this.created = System.currentTimeMillis();
    }

    @Override
    public String toString() {
        return "RedisAuthToken{" +
                "userId='" + userId + '\'' +
                ", created=" + created +
                ", token='" + token + '\'' +
                '}';
    }
}
