package com.example.backendcloudservice.service;

import com.example.backendcloudservice.eception.DeleteFile;
import com.example.backendcloudservice.eception.InputData;
import com.example.backendcloudservice.eception.UnauthorizedUser;
import com.example.backendcloudservice.entity.UserFile;
import com.example.backendcloudservice.repository.UserFileRepo;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class UserFileService {
    @Autowired
    UserFileRepo userFileRepo;

    public ResponseEntity uploadFile(String authToken, String fileName, MultipartFile file) throws IOException, NoSuchAlgorithmException {
        if (isError(authToken, fileName)) {
            if (userFileRepo.findByName(fileName) != null) {
                throw new InputData("Error input data");
            }
            InputStream in = file.getInputStream();
            java.io.File uploadFile = File.createTempFile(fileName, fileName.split("\\.")[1]);
            FileUtils.copyInputStreamToFile(in, uploadFile);
            userFileRepo.save(new UserFile(fileName, uploadFile));
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public void deletingFile(String authToken, String fileName) {
        if (isError(authToken, fileName)) {
            UserFile userFile = userFileRepo.findByName(fileName);
            if (userFile == null) {
                throw new InputData("Error input data");
            }
            userFileRepo.delete(userFile);
        }
    }

    public ResponseEntity getFile(String authToken, String fileName) throws IOException, NoSuchAlgorithmException {
        if (isError(authToken, fileName)) {
            UserFile userFile = userFileRepo.findByName(fileName);
            if (userFile == null) {
                throw new InputData("Error input data");
            }
            File file = userFile.getFile();
            InputStream in = new FileInputStream(file);

            return ResponseEntity.ok().body(new InputStreamResource(in));
        }
        return null;
    }

    public void editFileName(String authToken, String fileName, String newFileName) {
        if (isError(authToken, fileName)) {
            if (userFileRepo.findByName(fileName) == null) {
                throw new InputData("Error input data");
            }
            userFileRepo.updateFileName(fileName, newFileName);
        }
    }

    public ResponseEntity getAllFiles(String authToken, Integer limit) throws IOException {
        if (!PersonService.getHashToken().containsKey(authToken)) {
            throw new UnauthorizedUser("Unauthorized error");
        }

        List<UserFile> userFileList = userFileRepo.findAll();

        if (userFileList.isEmpty()) {
            throw new DeleteFile("Error getting file list");
        }

        List<HashMap<String, Object>> userFileLimitList = new ArrayList<>();

        int size = limit;
        if (userFileList.size() < limit) {
            size = userFileList.size();
        }

        for (int i = 0; i < size; i++) {
            UserFile userFile = userFileList.get(i);
            HashMap<String, Object> personFiles = new HashMap<>();
            personFiles.put("filename", userFile.getName());
            personFiles.put("size", userFile.getFile().length());
            userFileLimitList.add(personFiles);
        }

        return new ResponseEntity<>(userFileLimitList, HttpStatus.OK);
    }

    private boolean isError(String authToken, String fileName) {
        if (!PersonService.getHashToken().containsKey(authToken)) {
            throw new UnauthorizedUser("Unauthorized error");
        } else if (fileName.isEmpty()) {
            throw new InputData("Error input data");
        }
        return true;
    }
}
