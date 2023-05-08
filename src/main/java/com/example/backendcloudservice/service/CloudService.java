package com.example.backendcloudservice.service;

import com.example.backendcloudservice.eception.InputData;
import com.example.backendcloudservice.eception.UnauthorizedUser;
import com.example.backendcloudservice.model.User;
import com.example.backendcloudservice.model.UserFile;
import com.example.backendcloudservice.repository.UserRepository;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class CloudService {
    UserRepository userRepository;
    private ConcurrentHashMap<String, User> hashToken = new ConcurrentHashMap<>();

    public CloudService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public JSONObject getUserAuthorization(User user) {
        JSONObject authToken = userRepository.getUserAuthorization(user);
        if (authToken == null) {
            throw new InputData("Bad credentials");
        }
        hashToken.put(authToken.get("auth-token").toString(), user);
        return authToken;
    }

    public void deletingToken(String authToken) {
        hashToken.remove(authToken);
    }

    public void uploadFile(String authToken, String fileName, MultipartFile multipartFile) throws IOException {
        //todo проверка действительности токена
        //todo получение от репозитория информации об успешности загрузки и формирование ответа от сервера
        System.out.println(authToken);
        System.out.println(hashToken);
        if (!hashToken.containsKey(authToken)) {
            throw new UnauthorizedUser("Unauthorized error");
        } else if (fileName.isEmpty()) {
            throw new InputData("Error input data");
        }

        userRepository.uploadFile(fileName, multipartFile);
//        StringBuilder builder = new StringBuilder();
//        byte[] arraysByteFile = file.getBytes();
//        for (byte b : arraysByteFile) {
//            builder.append(String.format("%8s", Integer.toBinaryString(b & 0xFF))
//                    .replace(" ", "0")).append(" ");
//        }
//
//        System.out.println(builder);


    }

    public void deletingFile(String authToken, String fileName) {
        //todo проверка действительности токена
        //todo получение от репозитория информации об успешности удаления и формирование ответа от сервера
        if (!hashToken.containsKey(authToken)) {
            throw new UnauthorizedUser("Unauthorized error");
        } else if (fileName.isEmpty()) {
            throw new InputData("Error input data");
        }
        userRepository.deletingFile(fileName);
    }

    public boolean getFile(String authToken, String name) {
        //todo проверка действительности токена
        //todo получение от репозитория файла и формирование ответа от сервера
        userRepository.getFile(name);
        return true;
    }

    public boolean editFileName(String authToken, String name, String newName) {
        //todo проверка действительности токена
        //todo получение от репозитория информации об успешности изменения имени файла и формирование ответа от сервера
        userRepository.editFileName(name, newName);
        return true;
    }

    public JSONObject[] getAllFiles(String authToken, Integer limit) {
        //todo проверка действительности токена
        //todo получение от репозитория файлов и формирование ответа от сервера
        if (!hashToken.containsKey(authToken)) {
            throw new UnauthorizedUser("Unauthorized error");
        }
        return userRepository.getAllFiles(limit);
    }
}
