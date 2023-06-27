package com.example.backendcloudservice.service;


import com.example.backendcloudservice.eception.InputData;
import com.example.backendcloudservice.eception.UnauthorizedUser;
import com.example.backendcloudservice.log.Logger;
import com.example.backendcloudservice.model.entity.Person;
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
        String authToken = this.personRepo.getAuthTokenByUser(person.getLogin(), person.getPassword());
        if (authToken == null) {
            this.logger.addLog(person.getLogin(), "авторизация не пройдена!");
            throw new InputData("Bad credentials");
        }
        this.authToken.put(authToken, person);
        HashMap<String, String> map = new HashMap<>();
        map.put("auth-token", authToken);
        this.logger.addLog(person.getLogin(), "авторизация прошла успешно!");
        return map;
    }

    public void deletingToken(String authToken) throws IOException {
        this.authToken.remove(authToken);
        this.logger.addLog(this.personRepo.getLoginPerson(authToken), "вышел из приложения.");
    }

    public void isErrorAuthorized(String authToken) throws IOException {
        if (!this.authToken.containsKey(authToken)) {
            this.logger.addLog(this.personRepo.getLoginPerson(authToken), "ошибка авторизации");
            throw new UnauthorizedUser("Unauthorized error");
        }
    }

    public void isErrorInputData(String authToken, String fileName) throws IOException {
        if (fileName.isEmpty()) {
            this.logger.addLog(this.personRepo.getLoginPerson(authToken), "ошибка входных данных");
            throw new InputData("Error input data");
        }
    }
}