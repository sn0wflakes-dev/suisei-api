package com.sn0w.suisei.api.adapter.outbound.database.redis.repository;

public interface RedisRepository {
    void setValue(String key, String value, long ttl);
    String getValue(String key);
    void deleteValue(String key);
}
