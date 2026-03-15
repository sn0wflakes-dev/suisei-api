package com.sn0w.suisei.api.infra.database.redis;

import com.sn0w.suisei.api.infra.database.redis.repository.RedisRepository;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

import java.time.Duration;

@Repository
public class RedisRepositoryImpl implements RedisRepository {

    private final StringRedisTemplate redis;

    public RedisRepositoryImpl(
            StringRedisTemplate redis) {
        this.redis = redis;
    }

    @Override
    public void setValue(String key, String value, long ttl) {
        redis.opsForValue().set(key, value, Duration.ofSeconds(ttl));
    }

    @Override
    public String getValue(String key) {
        return redis.opsForValue().get(key);
    }

    @Override
    public void deleteValue(String key) {
        redis.delete(key);
    }
}
