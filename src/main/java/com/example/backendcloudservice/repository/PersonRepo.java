package com.example.backendcloudservice.repository;

import com.example.backendcloudservice.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PersonRepo extends JpaRepository<Person, Long> {
    @Query(value = "select p.authToken from Person p where p.login = :login and p.password =:password")
    String getAuthTokenByUser(String login, String password);

    @Query(value = "select p.id from Person p where p.authToken = :authToken")
    Long getIdPerson(String authToken);
}
