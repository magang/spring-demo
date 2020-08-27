package com.example.demo.resource;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author dustforest
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PersonResourceTests {
    private static final Logger logger = LoggerFactory.getLogger(PersonResourceTests.class);

    @Autowired
    private MockMvc mvc;

    @Test
    @Transactional
    @Rollback(value = true)
    public void testCreatingPerson() throws Exception {
        String response = mvc.perform(post("/person").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"hello\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.is(Integer.valueOf(1))))
                .andReturn().getResponse().getContentAsString();
        logger.info("response: " + response);
    }
}
