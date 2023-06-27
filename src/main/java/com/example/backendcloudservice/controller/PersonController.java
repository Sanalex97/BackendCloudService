package com.example.backendcloudservice.controller;

import com.example.backendcloudservice.model.dto.PersonDto;
import com.example.backendcloudservice.model.entity.Person;
import com.example.backendcloudservice.service.PersonService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@CrossOrigin
public class PersonController {
    @Autowired
    private PersonService personService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping({"/login"})
    @ResponseBody
    public ResponseEntity authorization(@RequestBody PersonDto personDto) throws IOException {
        return new ResponseEntity(this.personService.getUserAuthorization(convertToEntity(personDto)), HttpStatus.OK);
    }

    @PostMapping({"/logout"})
    public ResponseEntity deletingToken(@RequestHeader(name = "auth-token") String authToken) throws IOException {
        this.personService.deletingToken(authToken.split(" ")[1]);
        return new ResponseEntity(HttpStatus.OK);
    }

    private PersonDto convertToDto(Person person) {
        return this.modelMapper.map(person, PersonDto.class);
    }

    private Person convertToEntity(PersonDto personDto) {
        return this.modelMapper.map(personDto, Person.class);
    }
}
