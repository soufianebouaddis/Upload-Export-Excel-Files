package com.example.CookieDemo.service;

import com.example.CookieDemo.model.Person;
import com.example.CookieDemo.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {
    private final PersonRepository repository;

    public PersonService(PersonRepository repository) {
        this.repository = repository;
    }

    public Person savePerson(Person person){
        return this.repository.save(person);
    }
    public List<Person> persons(){
        return this.repository.findAll();
    }
    public List<Person> saveAll(List<Person> persons){
        return this.repository.saveAll(persons);
    }
}
