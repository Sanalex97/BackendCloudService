package com.example.backendcloudservice.repository;

import com.example.backendcloudservice.model.FileEntity;
import com.example.backendcloudservice.model.User;
import com.example.backendcloudservice.model.UserFile;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class UserRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    UserCrudRepo userCrudRepo;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public UserRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

   /* public UserRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }*/

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
        String prefix = fileName.substring(fileName.lastIndexOf("."));
        File file = File.createTempFile(fileName, prefix);
        multipartFile.transferTo(file);

        FileEntity fileEntity = new FileEntity(fileName, file);

        userCrudRepo.save(fileEntity);
        //todo загрузка файла в БД

    }

    public void deletingFile(String fileName) {
        //todo удаление файла из БД
        System.out.println("deletingggggggg");
        System.out.println("deleting name file = " + userCrudRepo.findByName(fileName));
        userCrudRepo.delete(userCrudRepo.findByName(fileName));
       /* for (FileEntity fileEntity : userCrudRepo.findAll()) {
            if (fileEntity.getFile().getName().equals(fileName)) {
                userCrudRepo.delete(fileEntity);
                break;
            }

        }*/
    }

    public boolean getFile(String name) {
        //todo получение файла из БД
        return true;
    }

    public boolean editFileName(String name, String newName) {
        //todo внесение изменений в имени файла в БД
        return true;
    }

    public JSONObject[] getAllFiles(Integer limit) {
        //todo получение файлов из БД
        List<FileEntity> fileEntityList = userCrudRepo.findAll();
        System.out.println("fileList = " + fileEntityList);
        System.out.println("SIZE list = " + fileEntityList.size());

        JSONObject[] myFiles = new JSONObject[limit];
        for(int i = 0 ; i < limit; i++) {
            myFiles[i] = new JSONObject();
        }

        int size = limit;
        if(fileEntityList.size() < limit){
            size = fileEntityList.size();
        }

        for (int i = 0; i < size; i++) {
            FileEntity file = fileEntityList.get(i);
            myFiles[i].put("filename", file.getName());
            myFiles[i].put("size", (int)file.getFile().length());
        }

        return myFiles;
    }

}
