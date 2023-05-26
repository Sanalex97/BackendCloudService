package com.example.backendcloudservice.service;

import com.example.backendcloudservice.eception.InputData;
import com.example.backendcloudservice.eception.UnauthorizedUser;
import com.example.backendcloudservice.entity.Person;
import com.example.backendcloudservice.repository.UserRepository;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class CloudService {
    UserRepository userRepository;
    private ConcurrentHashMap<String, Person> hashToken = new ConcurrentHashMap<>();

    public CloudService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public JSONObject getUserAuthorization(Person person) {
        JSONObject authToken = userRepository.getUserAuthorization(person);
        if (authToken == null) {
            throw new InputData("Bad credentials");
        }
        hashToken.put(authToken.get("auth-token").toString(), person);
        return authToken;
    }

    public void deletingToken(String authToken) {
        hashToken.remove(authToken);
    }

    public void uploadFile(String authToken, String fileName, MultipartFile multipartFile) throws IOException {
        System.out.println(authToken);
        System.out.println(hashToken);
        if (!hashToken.containsKey(authToken)) {
            throw new UnauthorizedUser("Unauthorized error");
        } else if (fileName.isEmpty()) {
            throw new InputData("Error input data");
        }
        userRepository.uploadFile(fileName, multipartFile);
    }

    public void deletingFile(String authToken, String fileName) {
        if (!hashToken.containsKey(authToken)) {
            throw new UnauthorizedUser("Unauthorized error");
        } else if (fileName.isEmpty()) {
            throw new InputData("Error input data");
        }
        userRepository.deletingFile(fileName);
    }

    public JSONObject getFile(String authToken, String fileName) throws IOException {
        if (!hashToken.containsKey(authToken)) {
            throw new UnauthorizedUser("Unauthorized error");
        } else if (fileName.isEmpty()) {
            throw new InputData("Error input data");
        }
        return userRepository.getFile(fileName);
    }

    public void editFileName(String authToken, String fileName, String newFileName) {
        if (!hashToken.containsKey(authToken)) {
            throw new UnauthorizedUser("Unauthorized error");
        } else if (fileName.isEmpty() || newFileName.isEmpty()) {
            throw new InputData("Error input data");
        }
        userRepository.editFileName(fileName, newFileName);
    }

    public JSONObject[] getAllFiles(String authToken, Integer limit) {
        if (!hashToken.containsKey(authToken)) {
            throw new UnauthorizedUser("Unauthorized error");
        }

        return userRepository.getAllFiles(limit);
    }
}
