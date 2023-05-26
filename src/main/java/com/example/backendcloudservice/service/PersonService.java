package com.example.backendcloudservice.service;

import com.example.backendcloudservice.eception.InputData;
import com.example.backendcloudservice.entity.Person;
import com.example.backendcloudservice.repository.PersonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class PersonService {
    private static ConcurrentHashMap<String, Person> hashToken = new ConcurrentHashMap<>();

    @Autowired
    PersonRepo personRepo;

    public ResponseEntity getUserAuthorization(Person person) {
        String authToken = personRepo.getAuthTokenByUser(person.getLogin(), person.getPassword());

        if (authToken == null) {
            throw new InputData("Bad credentials");
        }

        hashToken.put(authToken, person);

        HashMap<String, String> map = new HashMap<>();
        map.put("auth-token", authToken);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    public void deletingToken(String authToken) {
        hashToken.remove(authToken);
    }

    public static ConcurrentHashMap<String, Person> getHashToken() {
        return hashToken;
    }
}
