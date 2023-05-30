package com.example.backendcloudservice.controller;

import com.example.backendcloudservice.entity.Person;
import com.example.backendcloudservice.service.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class PersonController {
    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity authorization(@RequestBody Person person) {
        return personService.getUserAuthorization(person);
    }

    @PostMapping("/logout")
    public ResponseEntity deletingToken(@RequestHeader(name = "auth-token") String authToken) {
        personService.deletingToken(authToken.split(" ")[1]);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}