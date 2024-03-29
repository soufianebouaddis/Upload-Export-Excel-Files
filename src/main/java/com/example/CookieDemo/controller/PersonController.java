package com.example.CookieDemo.controller;

import com.example.CookieDemo.model.Person;
import com.example.CookieDemo.service.PersonService;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("person/")
public class PersonController {
    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }
    @PostMapping
    public ResponseEntity<Person> save(@RequestBody Person person){
        return ResponseEntity.ok(this.personService.savePerson(person));
    }
    @GetMapping
    public ResponseEntity<List<Person>> save(){
        return ResponseEntity.ok(this.personService.persons());
    }

}
