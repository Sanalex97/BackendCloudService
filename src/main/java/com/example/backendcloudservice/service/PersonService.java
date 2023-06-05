package com.example.backendcloudservice.service;

import com.example.backendcloudservice.eception.InputData;
import com.example.backendcloudservice.eception.UnauthorizedUser;
import com.example.backendcloudservice.entity.Person;
import com.example.backendcloudservice.repository.PersonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class PersonService {
    private static ConcurrentHashMap<String, Person> hashToken = new ConcurrentHashMap<>();

    @Autowired
    private PersonRepo personRepo;

    public HashMap<String, String> getUserAuthorization(Person person) {
        String authToken = personRepo.getAuthTokenByUser(person.getLogin(), person.getPassword());

        if (authToken == null) {
            throw new InputData("Bad credentials");
        }

        hashToken.put(authToken, person);

        HashMap<String, String> map = new HashMap<>();
        map.put("auth-token", authToken);

        return map;
    }

    public void deletingToken(String authToken) {
        hashToken.remove(authToken);
    }

    public static ConcurrentHashMap<String, Person> getHashToken() {
        return hashToken;
    }

    public static boolean isErrorAuthorized(String authToken) {
        if (!PersonService.getHashToken().containsKey(authToken)) {
            throw new UnauthorizedUser("Unauthorized error");
        }
        return true;
    }

    public static boolean isErrorInputData(String fileName) {
        if (fileName.isEmpty()) {
            throw new InputData("Error input data");
        }
        return true;
    }

}
