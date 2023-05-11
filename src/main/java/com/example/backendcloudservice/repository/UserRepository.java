package com.example.backendcloudservice.repository;

import com.example.backendcloudservice.eception.InputData;
import com.example.backendcloudservice.model.FileEntity;
import com.example.backendcloudservice.model.User;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class UserRepository {
    @Autowired
    UserCrudRepo userCrudRepo;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public UserRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public JSONObject getUserAuthorization(User user) {
        String script = read("isAuthorization.sql");
        Map<String, String> params = new HashMap<>();
        params.put("login", user.getLogin());
        params.put("password", user.getPassword());
        List<String> list = namedParameterJdbcTemplate.query(
                script,
                params,
                (rs, rowNum) -> rs.getString("auth-token"));

        JSONObject authToken = new JSONObject();
        if (!list.isEmpty()) {
            authToken.put("auth-token", list.get(0));
            return authToken;
        }
        return null;
    }

    private static String read(String scriptFileName) {
        try (InputStream is = new ClassPathResource(scriptFileName).getInputStream();
             BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is))) {
            return bufferedReader.lines().collect(Collectors.joining("\n"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void uploadFile(String fileName, MultipartFile multipartFile) throws IOException {
        if (userCrudRepo.findByName(fileName) != null) {
            throw new InputData("Error input data");
        }

        String prefix = fileName.substring(fileName.lastIndexOf("."));
        File file = File.createTempFile(fileName, prefix);
        multipartFile.transferTo(file);

        FileEntity fileEntity = new FileEntity(fileName, file);

        userCrudRepo.save(fileEntity);
    }

    public void deletingFile(String fileName) {
        System.out.println("deleting name file = " + userCrudRepo.findByName(fileName));
        if (userCrudRepo.findByName(fileName) == null) {
            throw new InputData("Error input data");
        }

        userCrudRepo.delete(userCrudRepo.findByName(fileName));
    }

    public JSONObject getFile(String fileName) throws IOException {
        FileEntity fileEntity = userCrudRepo.findByName(fileName);

        if (fileEntity == null) {
            throw new InputData("Error input data");
        }

        FileInputStream input = new FileInputStream(fileEntity.getFile());

        JSONObject myFile = new JSONObject();
        myFile.put("hash", input.hashCode());
        myFile.put("file", input.readAllBytes());

        return myFile;
    }

    public void editFileName(String fileName, String newFileName) {
        System.out.println("file name = " + fileName);
        System.out.println("newFileName = " + newFileName);

        if (userCrudRepo.findByName(fileName) == null) {
            throw new InputData("Error input data");
        }
        userCrudRepo.updateFileName(fileName, newFileName);
    }

    public JSONObject[] getAllFiles(Integer limit) {
        List<FileEntity> fileEntityList = userCrudRepo.findAll();

        JSONObject[] myFiles = new JSONObject[limit];
        for (int i = 0; i < limit; i++) {
            myFiles[i] = new JSONObject();
        }

        int size = limit;
        if (fileEntityList.size() < limit) {
            size = fileEntityList.size();
        }

        for (int i = 0; i < size; i++) {
            FileEntity file = fileEntityList.get(i);
            myFiles[i].put("filename", file.getName());
            myFiles[i].put("size", file.getFile().length());
        }

        return myFiles;
    }

}
