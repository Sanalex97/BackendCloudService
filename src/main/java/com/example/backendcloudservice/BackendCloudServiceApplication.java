package com.example.backendcloudservice;

import com.example.backendcloudservice.model.entity.Person;
import com.example.backendcloudservice.model.builder.PersonBuilder;
import com.example.backendcloudservice.repository.PersonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Arrays;

@SpringBootApplication
public class BackendCloudServiceApplication implements CommandLineRunner {
    @Autowired
    PersonRepo personRepo;

    public static void main(String[] args) {
        SpringApplication.run(BackendCloudServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Person person1 = new PersonBuilder()
                .setLogin("user1")
                .setPassword("12345678")
                .setAuthToken("hgfhkghfkgjfjg12hkhk344443k4Uhc").build();

        Person person2 = new PersonBuilder()
                .setLogin("user2")
                .setPassword("87654321")
                .setAuthToken("LkJhkghfkgjfjg12hkhk344923k4MNj").build();

        Person person3 = new PersonBuilder()
                .setLogin("user3")
                .setPassword("12348765")
                .setAuthToken("tyuhkghfkgjfjg12hkhk765443k4fdR").build();

        personRepo.saveAll(new ArrayList<>(Arrays.asList(person1, person2, person3)));
    }

}
