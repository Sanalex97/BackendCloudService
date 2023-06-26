package com.example.backendcloudservice.service;

import com.example.backendcloudservice.eception.InputData;
import com.example.backendcloudservice.eception.UnauthorizedUser;
import com.example.backendcloudservice.model.entity.Person;
import com.example.backendcloudservice.log.Logger;
import com.example.backendcloudservice.repository.PersonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class PersonService {

    private final ConcurrentHashMap<String, Person> authToken = new ConcurrentHashMap<>();

    @Autowired
    private PersonRepo personRepo;

    private final Logger logger = Logger.getInstance();

    public HashMap<String, String> getUserAuthorization(Person person) throws IOException {
        System.out.println(person);
        String authToken = personRepo.getAuthTokenByUser(person.getLogin(), person.getPassword());

        if (authToken == null) {
            logger.addLog("Авторизация пользователя " + person.getLogin() + " не пройдена!" );
            throw new InputData("Bad credentials");
        }

        this.authToken.put(authToken, person);

        HashMap<String, String> map = new HashMap<>();
        map.put("auth-token", authToken);

        logger.addLog("Авторизация пользователя " + person.getLogin() + " прошла успешно!" );

        return map;
    }

    public void deletingToken(String authToken) {
        this.authToken.remove(authToken);
    }

    public void isErrorAuthorized(String authToken) {
        if (!this.authToken.containsKey(authToken)) {
            throw new UnauthorizedUser("Unauthorized error");
        }
    }

    public void isErrorInputData(String fileName) {
        if (fileName.isEmpty()) {
            throw new InputData("Error input data");
        }
    }

}
