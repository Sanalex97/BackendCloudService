package com.example.backendcloudservice.model.builder;

import com.example.backendcloudservice.model.entity.Person;

public class PersonBuilder {
    protected String login;
    protected String password;
    protected String authToken;

    public PersonBuilder setLogin(String login) {
        this.login = login;
        return this;
    }

    public PersonBuilder setPassword(String password) {
        this.password = password;
        return this;
    }

    public PersonBuilder setAuthToken(String authToken) {
        this.authToken = authToken;
        return this;
    }

    public Person build() {
        if (login == null || password == null || authToken == null) {
            throw new IllegalArgumentException("Данные заполнены не полностью");
        } else if (password.length() < 8) {
            throw new IllegalArgumentException("Пароль заполнен не корректно");
        }

        Person person;
        person = new Person(login, password, authToken);
        return person;
    }
}
