package com.example.backendcloudservice.service.person;

import com.example.backendcloudservice.eception.InputData;
import com.example.backendcloudservice.model.entity.Person;
import com.example.backendcloudservice.repository.PersonRepo;
import com.example.backendcloudservice.service.PersonService;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class PersonServiceTest {
    @Mock
    private PersonRepo personRepo;

    @InjectMocks
    @Resource
    private PersonService personService;

    @Test
    void getUserAuthorization_OK() throws IOException {
        given(personRepo.getAuthTokenByUser("user1", "12345678"))
                .willReturn("qwertyzxcVBNMgfdsjfg");

        HashMap<String, String> authToken = personService.getUserAuthorization(new Person("user1", "12345678", null));

        assertThat(authToken.get("auth-token")).isEqualTo("qwertyzxcVBNMgfdsjfg");
    }

    @Test()
    void getUserAuthorization_Exception() {
        given(personRepo.getAuthTokenByUser("user1", "12345678"))
                .willThrow(InputData.class);

        assertThrows(InputData.class,
                () ->
                        personService.getUserAuthorization(new Person("user1", "12345678", null)));
    }

}