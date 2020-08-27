package com.example.demo.resource;

import com.example.demo.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Date;

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
}
