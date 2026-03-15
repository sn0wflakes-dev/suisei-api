package com.sn0w.suisei.api.infra.database.redis.repository;

import com.sn0w.suisei.api.core.domain.otp.Otp;

import java.util.Optional;

public interface RedisRepository {
    void setValue(String key, String value, long ttl);
    String getValue(String key);
    void deleteValue(String key);
}
