package com.example.backendcloudservice.service;

import com.example.backendcloudservice.eception.DeleteFile;
import com.example.backendcloudservice.eception.InputData;
import com.example.backendcloudservice.eception.UnauthorizedUser;
import com.example.backendcloudservice.entity.UserFile;
import com.example.backendcloudservice.repository.UserFileRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserFileService {
    @Autowired
    UserFileRepo userFileRepo;

    public String uploadFile(String authToken, String fileName, MultipartFile file) throws IOException {
        if (isError(authToken, fileName)) {
            if (userFileRepo.findByName(fileName) == null) {
                throw new InputData("Error input data");
            }
            String prefix = fileName.substring(fileName.lastIndexOf("."));
            File file1 = File.createTempFile(fileName, prefix);
            file.transferTo(file1);
            // userFileRepo.save(new UserFile(fileName,file.getBytes()));
        }
        return null;
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

    public UserFile getFile(String authToken, String fileName) {
        if (isError(authToken, fileName)) {
            UserFile userFile = userFileRepo.findByName(fileName);
            if (userFile == null) {
                throw new InputData("Error input data");
            }
            return userFile;
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

    public ResponseEntity getAllFiles(String authToken, Integer limit) {
        if (!PersonService.getHashToken().containsKey(authToken)) {
            throw new UnauthorizedUser("Unauthorized error");
        }
        List<UserFile> userFileLimitList = new ArrayList<>();
        List<UserFile> userFileList = userFileRepo.findAll();

        System.out.println("FINDALL === " + userFileRepo.findAll());

        if (userFileList.isEmpty()) {
            throw new DeleteFile("Error getting file list");
        }

        for (int i = 0; i < limit; i++) {
            userFileLimitList.add(userFileList.get(i));
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
