package com.example.demo.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author dustforest
 */
@Service
public class RedisService {
    private JedisPool jedisPool;

    public RedisService(Environment env) throws Exception {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(10);
        config.setMinIdle(0);
        jedisPool = new JedisPool(config,
                env.getProperty("spring.redis.host", "localhost"),
                Integer.parseInt(env.getProperty("spring.redis.port", "6379")),
                Integer.parseInt(env.getProperty("spring.redis.timeout", "1000")),
                env.getProperty("spring.redis.password", ""));
    }

    public Jedis getResource() {
        return jedisPool.getResource();
    }

    public void set(String key, String value, int seconds) {
        try (Jedis jedis = getResource()) {
            jedis.set(key, value);
            jedis.expire(key, seconds);
        }
    }

    public void set(String key, Object object, int seconds) {
        set(key, JSON.toJSONString(object), seconds);
    }

    public String get(String key) {
        try (Jedis jedis = getResource()) {
            return jedis.get(key);
        }
    }

    public <T> T get(String key, Class<T> objectClass) {
        try (Jedis jedis = getResource()) {
            return JSON.parseObject(jedis.get(key), objectClass);
        }
    }

    public <T> T get(String key, TypeReference<T> objectClass) {
        try (Jedis jedis = getResource()) {
            return JSON.parseObject(jedis.get(key), objectClass);
        }
    }

    public boolean exists(String key) {
        try (Jedis jedis = getResource()) {
            return jedis.exists(key);
        }
    }

    public void expire(String key, int seconds) {
        try (Jedis jedis = getResource()) {
            jedis.expire(key, seconds);
        }
    }

    public void delete(String key) {
        try (Jedis jedis = getResource()) {
            jedis.del(key);
        }
    }

    public String ping(String msg) {
        try (Jedis jedis = getResource()) {
            return jedis.ping(msg);
        }
    }
}
