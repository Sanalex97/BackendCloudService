package com.example.backendcloudservice.repository;

import com.example.backendcloudservice.eception.InputData;
import com.example.backendcloudservice.entity.Person;
import com.example.backendcloudservice.entity.UserFile;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class UserRepository {
    @Autowired
    UserFileRepo userFileRepo;

    @Autowired
    PersonRepo personRepo;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public UserRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public JSONObject getUserAuthorization(Person person) {

     /*   String script = read("isAuthorization.sql");
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
        return null;*/
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
        if (userFileRepo.findByName(fileName) != null) {
            throw new InputData("Error input data");
        }

        String prefix = fileName.substring(fileName.lastIndexOf("."));
        java.io.File file = java.io.File.createTempFile(fileName, prefix);
        multipartFile.transferTo(file);

      //  UserFile userFile = new UserFile(fileName, file);

      //  userFileRepo.save(userFile);
    }

    public void deletingFile(String fileName) {
        System.out.println("deleting name file = " + userFileRepo.findByName(fileName));
        if (userFileRepo.findByName(fileName) == null) {
            throw new InputData("Error input data");
        }

        userFileRepo.delete(userFileRepo.findByName(fileName));
    }

    public JSONObject getFile(String fileName) throws IOException {
      /*  UserFile userFile = userFileRepo.findByName(fileName);

        if (userFile == null) {
            throw new InputData("Error input data");
        }

        FileInputStream input = new FileInputStream(userFile.getFile());

        JSONObject myFile = new JSONObject();
        myFile.put("hash", input.hashCode());
        myFile.put("file", input.readAllBytes());

        return myFile;*/
        return null;
    }

    public void editFileName(String fileName, String newFileName) {
        System.out.println("file name = " + fileName);
        System.out.println("newFileName = " + newFileName);

        if (userFileRepo.findByName(fileName) == null) {
            throw new InputData("Error input data");
        }
        userFileRepo.updateFileName(fileName, newFileName);
    }

    public JSONObject[] getAllFiles(Integer limit) {
     /*   List<UserFile> userFileList = userFileRepo.findAll();

        JSONObject[] myFiles = new JSONObject[limit];
        for (int i = 0; i < limit; i++) {
            myFiles[i] = new JSONObject();
        }

        int size = limit;
        if (userFileList.size() < limit) {
            size = userFileList.size();
        }

        for (int i = 0; i < size; i++) {
            UserFile userFile = userFileList.get(i);
            myFiles[i].put("filename", userFile.getName());
            myFiles[i].put("size", userFile.getFile().length());
        }*/

        return null;
    }

}
