package com.example.demo.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private int port;

    @Value("${spring.redis.timeout}")
    private int timeout;

    @Value("${spring.redis.jedis.pool.max-idle}")
    private int maxIdle;

    @Value("${spring.redis.jedis.pool.max-wait}")
    private long maxWaitMillis;

    @Value("${spring.redis.password}")
    private String password;

    @Value("${spring.redis.block-when-exhausted}")
    private boolean blockWhenExhausted;

    public RedisService() throws Exception {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
        // 连接耗尽时是否阻塞, false报异常,ture阻塞直到超时, 默认true
        jedisPoolConfig.setBlockWhenExhausted(blockWhenExhausted);
        // 是否启用pool的jmx管理功能, 默认true
        jedisPoolConfig.setJmxEnabled(true);
        if (host == null) {
            host = "192.168.0.101";
            port = 6379;
            password = "password";
        }
        jedisPool = new JedisPool(jedisPoolConfig, host, port, timeout, password);
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
