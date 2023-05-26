package com.example.backendcloudservice.repository;

import com.example.backendcloudservice.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PersonRepo extends JpaRepository<Person, Long> {
    @Query("select u.authToken from Person u where u.login = :login and u.password =:password")
          String getAuthTokenByUser(String login, String password);
}
