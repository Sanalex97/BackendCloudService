package com.example.backendcloudservice.model;

public class User {
    private Long id;
    private String login;
    private String password;
    private String authToken;


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getAuthToken() {
        return authToken;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", authToken='" + authToken + '\'' +
                '}';
    }
}
