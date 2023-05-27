package com.example.backendcloudservice.service;

import com.example.backendcloudservice.eception.DeleteFile;
import com.example.backendcloudservice.eception.InputData;
import com.example.backendcloudservice.eception.UnauthorizedUser;
import com.example.backendcloudservice.entity.UserFile;
import com.example.backendcloudservice.repository.UserFileRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class UserFileService {
    @Autowired
    UserFileRepo userFileRepo;

    public ResponseEntity uploadFile(String authToken, String fileName, MultipartFile file) throws IOException {
        if (isError(authToken, fileName)) {
            if (userFileRepo.findByName(fileName) != null) {
                throw new InputData("Error input data");
            }
            String prefix = fileName.substring(fileName.lastIndexOf("."));
            File file1 = File.createTempFile(fileName, prefix);
            file.transferTo(file1);
            System.out.println(file1);
            userFileRepo.save(new UserFile(fileName, file1));
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

    public ResponseEntity getFile(String authToken, String fileName) throws IOException {
        if (isError(authToken, fileName)) {
            UserFile userFile = userFileRepo.findByName(fileName);
            if (userFile == null) {
                throw new InputData("Error input data");
            }

            File file = userFile.getFile();
            Path path = Paths.get(file.getPath());
            String name = file.getName();
            String originalFileName = file.getName();
            String contentType = Files.probeContentType(file.toPath());
            byte[] content = null;
            try {
                content = Files.readAllBytes(path);
            } catch (final IOException e) {
            }
            MultipartFile result = new MockMultipartFile(name,
                    originalFileName, contentType, content);

            return new ResponseEntity<>(result, HttpStatus.OK);
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

        List<UserFile> userFileList = userFileRepo.findAll();

        System.out.println("FINDALL === " + userFileRepo.findAll());

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
        System.out.println("USERFILELIMIT === " + userFileLimitList);
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
