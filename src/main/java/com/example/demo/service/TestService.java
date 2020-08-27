package com.example.demo.service;

import com.example.demo.constant.Constants;
import com.example.demo.constant.TestDataConstants;
import com.example.demo.dto.CoinSymbol;
import com.example.demo.dto.IdName;
import com.example.demo.mapper.UserMapper;
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
}
