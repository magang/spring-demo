package com.example.demo.resource;

import com.example.demo.entity.Person;
import com.example.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author dustforest
 */
@RestController
@RequestMapping("/person")
public class PersonResource {
    @Autowired
    PersonService personService;

    @PostMapping()
    public Integer create(@RequestBody Person person) {
        return personService.create(person);
    }

    @GetMapping("/{id}")
    public Person get(@PathVariable Integer id) {
        return personService.get(id);
    }
}
