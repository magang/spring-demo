package com.example.demo.service;

import com.example.demo.constant.TestDataConstants;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestServiceTests {
    @Autowired
    private TestService testService;

    @Test
    public void testHello() throws Exception {
        String result = testService.hello();
        Assert.assertEquals(TestDataConstants.HELLO, result);
    }

    @Test
    public void testJdbc() throws Exception {
        String result = testService.jdbc();
        Assert.assertEquals(TestDataConstants.TEST, result);
    }

    @Test
    public void testMybatisMap() throws Exception {
        String result = testService.mybatisMap();
        Assert.assertEquals(TestDataConstants.TEST, result);
    }

    @Test
    public void testMybatisDTO() throws Exception {
        String result = testService.mybatisDTO();
        Assert.assertEquals(TestDataConstants.TEST, result);
    }

    @Test
    public void testHttp() throws Exception {
        Integer result = testService.http();
        Assert.assertNotEquals(Integer.valueOf(0), result);
    }
}
