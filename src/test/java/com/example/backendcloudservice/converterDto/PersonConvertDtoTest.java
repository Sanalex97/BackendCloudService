package com.example.backendcloudservice.converterDto;

import com.example.backendcloudservice.model.dto.PersonDto;
import com.example.backendcloudservice.model.entity.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.convention.MatchingStrategies;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class PersonConvertDtoTest extends ConvertDto {

    private PersonDto personDto;
    private Person person;

    @BeforeEach
    public void setup() throws IOException {
        personDto = new PersonDto();
        personDto.setId(0);
        personDto.setLogin("user");
        personDto.setPassword("12345678");

        person = new Person("user2", "1234532435", "fdhdhfggdfggh");
        ;
    }

    @Test
    public void convertToEntity() {
        getMapper().getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        Person person1 = getMapper().map(personDto, Person.class);

        assertEquals(personDto.getLogin(), person1.getLogin());
        assertEquals(personDto.getPassword(), person1.getPassword());
    }

    @Test
    public void convertToDto() {
        getMapper().getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        PersonDto personDto1 = getMapper().map(person, PersonDto.class);

        assertEquals(person.getLogin(), personDto1.getLogin());
        assertEquals(person.getPassword(), personDto1.getPassword());
    }
}
