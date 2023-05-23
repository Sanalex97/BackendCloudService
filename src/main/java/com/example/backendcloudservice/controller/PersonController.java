package com.example.backendcloudservice.controller;

import com.example.backendcloudservice.entity.Person;
import com.example.backendcloudservice.service.PersonService;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class PersonController {
    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping("/login")
    public Person authorization(@RequestBody Person person) {
        System.out.println(person);
        return personService.getUserAuthorization(person);
    }

    @PostMapping("/logout")
    public void deletingToken(@RequestHeader(name = "auth-token") String authToken) {
        System.out.println(authToken);
        personService.deletingToken(authToken.split(" ")[1]);
    }
}