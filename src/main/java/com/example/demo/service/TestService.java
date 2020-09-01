package com.example.demo.service;

import com.alibaba.fastjson.TypeReference;
import com.example.demo.constant.Constants;
import com.example.demo.constant.TestDataConstants;
import com.example.demo.dto.CoinSymbol;
import com.example.demo.dto.IdName;
import com.example.demo.entity.Person;
import com.example.demo.mapper.PersonMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.mapper.ext.PersonExtMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author dustforest
 */
@Service
public class TestService {
    private static final Logger logger = LoggerFactory.getLogger(TestService.class);

    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    UserMapper userMapper;
    @Autowired
    HttpService httpService;
    @Autowired
    RedisService redisService;
    @Autowired
    PersonMapper personMapper;
    @Autowired
    PersonExtMapper personExtMapper;

    public String hello() {
        return TestDataConstants.HELLO;
    }

    public String jdbc() {
        List<Map<String, Object>> rtList = jdbcTemplate.queryForList("select * from person");
        return (String) rtList.get(0).get("name");
    }

    public String mybatisMap() {
        List<Map<String, Object>> rtList = userMapper.queryUserListMap();
        return (String) rtList.get(0).get("NAME");
    }

    public String mybatisDTO() {
        List<IdName> rtList = userMapper.queryUserListDto();
        return rtList.get(0).getName();
    }

    public Integer http() {
        CoinSymbol coinSymbol = httpService.get("https://api.diadata.org/v1/symbols", CoinSymbol.class);
        List<String> coins = coinSymbol.getSymbols();
        return coins.size();
    }

    public String log() {
        logger.info("---------log msg--------");
        return Constants.OK;
    }

    public LocalDateTime jacksonLocalDateTime() {
        LocalDateTime localDateTime = LocalDateTime.parse("2000-01-01 00:00:01", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        return localDateTime;
    }

    public Date jacksonDate() {
        return new Date();
    }

    public String redisString() {
        redisService.delete(TestDataConstants.REDIS_KEY);
        redisService.set(TestDataConstants.REDIS_KEY, TestDataConstants.TEST, TestDataConstants.REDIS_EXPIRE_SECONDS);
        return redisService.get(TestDataConstants.REDIS_KEY);
    }

    public Person redisObject() {
        redisService.delete(TestDataConstants.REDIS_KEY);
        Person person = personExtMapper.get(1);
        redisService.set(TestDataConstants.REDIS_KEY, person, TestDataConstants.REDIS_EXPIRE_SECONDS);
        return redisService.get(TestDataConstants.REDIS_KEY, Person.class);
    }

    public List<IdName> redisList() {
        redisService.delete(TestDataConstants.REDIS_KEY);
        List<IdName> users = userMapper.queryUserListDto();
        redisService.set(TestDataConstants.REDIS_KEY, users, TestDataConstants.REDIS_EXPIRE_SECONDS);
        return redisService.get(TestDataConstants.REDIS_KEY, new TypeReference<List<IdName>>() {
        });
    }

    public String redisPing() {
        return redisService.ping(TestDataConstants.HELLO);
    }
}
