package com.example.demo.service;

import com.example.demo.entity.Person;
import com.example.demo.mapper.PersonMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author dustforest
 */
@Service
public class PersonService {
    private static final Logger logger = LoggerFactory.getLogger(PersonService.class);

    @Autowired
    PersonMapper personMapper;

    public Integer create(Person person) {
        return personMapper.insert(person);
    }

    public Person get(Integer id) {
        return personMapper.get(id);
    }
}
