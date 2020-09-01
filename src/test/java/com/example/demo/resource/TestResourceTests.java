package com.example.demo.resource;

import com.example.demo.constant.TestDataConstants;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TestResourceTests {
    @Autowired
    private MockMvc mvc;

    @Test
    public void testHello() throws Exception {
        mvc.perform(get("/test/hello").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.is(TestDataConstants.HELLO)))
                .andReturn().getResponse().getContentAsString();
    }

    @Test
    public void testJdbc() throws Exception {
        mvc.perform(get("/test/jdbc").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.is(TestDataConstants.TEST)))
                .andReturn().getResponse().getContentAsString();
    }

    @Test
    public void testMybatisMap() throws Exception {
        mvc.perform(get("/test/mybatis/map").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.is(TestDataConstants.TEST)))
                .andReturn().getResponse().getContentAsString();
    }

    @Test
    public void testMybatisDTO() throws Exception {
        mvc.perform(get("/test/mybatis/dto").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.is(TestDataConstants.TEST)))
                .andReturn().getResponse().getContentAsString();
    }

    @Test
    public void testHttp() throws Exception {
        mvc.perform(get("/test/http").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.greaterThan(0)))
                .andReturn().getResponse().getContentAsString();
    }

    @Test
    public void testRedisString() throws Exception {
        mvc.perform(get("/test/redis/string").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.is(TestDataConstants.TEST)))
                .andReturn().getResponse().getContentAsString();
    }

    @Test
    public void testRedisObject() throws Exception {
        mvc.perform(get("/test/redis/object").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", Matchers.is(TestDataConstants.TEST)))
                .andReturn().getResponse().getContentAsString();
    }

    @Test
    public void testRedisList() throws Exception {
        mvc.perform(get("/test/redis/list").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name", Matchers.is(TestDataConstants.TEST)))
                .andReturn().getResponse().getContentAsString();
    }

    @Test
    public void testRedisPing() throws Exception {
        mvc.perform(get("/test/redis/ping").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.is(TestDataConstants.HELLO)))
                .andReturn().getResponse().getContentAsString();
    }
}
