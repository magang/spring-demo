package com.example.demo.resource;

import com.example.demo.dto.IdName;
import com.example.demo.entity.Person;
import com.example.demo.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * @author dustforest
 */
@RestController
@RequestMapping("/test")
public class TestResource {
    @Autowired
    TestService testService;

    @GetMapping("/hello")
    public String hello() {
        return testService.hello();
    }

    @GetMapping("/jdbc")
    public String jdbc() {
        return testService.jdbc();
    }

    @GetMapping("/mybatis/map")
    public String mybatisMap() {
        return testService.mybatisMap();
    }

    @GetMapping("/mybatis/dto")
    public String mybatisDTO() {
        return testService.mybatisDTO();
    }

    @GetMapping("/http")
    public Integer http() {
        return testService.http();
    }

    @GetMapping("/log")
    public String log() {
        return testService.log();
    }

    @GetMapping("/jackson/local")
    public LocalDateTime jacksonLocalDateTime() {
        return testService.jacksonLocalDateTime();
    }

    @GetMapping("/jackson/date")
    public Date jacksonDate() {
        return testService.jacksonDate();
    }

    @GetMapping("/redis/string")
    public String redisString() {
        return testService.redisString();
    }

    @GetMapping("/redis/object")
    public Person redisObject() {
        return testService.redisObject();
    }

    @GetMapping("/redis/list")
    public List<IdName> redisList() {
        return testService.redisList();
    }

    @GetMapping("/redis/ping")
    public String redisPing() {
        return testService.redisPing();
    }
}
