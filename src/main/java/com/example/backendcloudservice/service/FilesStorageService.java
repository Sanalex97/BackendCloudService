package com.example.backendcloudservice.service;

import com.example.backendcloudservice.eception.DeleteFile;
import com.example.backendcloudservice.eception.InputData;
import com.example.backendcloudservice.model.entity.FilesStorage;
import com.example.backendcloudservice.repository.FilesStorageRepo;
import com.example.backendcloudservice.repository.PersonRepo;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@Service
public class FilesStorageService {
    @Autowired
    FilesStorageRepo filesStorageRepo;

    @Autowired
    PersonRepo personRepo;

    public boolean uploadFile(String authToken, String fileName, MultipartFile file) throws IOException {
        if (filesStorageRepo.findByName(fileName) != null) {
            throw new InputData("Error input data");
        }

        InputStream in = file.getInputStream();
        java.io.File uploadFile = File.createTempFile(fileName, fileName.split("\\.")[1]);
        FileUtils.copyInputStreamToFile(in, uploadFile);
        filesStorageRepo.save(new FilesStorage(personRepo.getIdPerson(authToken), fileName, uploadFile));

        return true;
    }

    public void deletingFile(String fileName) {
        FilesStorage filesStorage = filesStorageRepo.findByName(fileName);

        if (filesStorage == null) {
            throw new InputData("Error input data");
        }

        filesStorageRepo.delete(filesStorage);
    }

    public InputStream getFile(String fileName) throws IOException {
        FilesStorage filesStorage = filesStorageRepo.findByName(fileName);
        if (filesStorage == null) {
            throw new InputData("Error input data");
        }

        File file = filesStorage.getFile();

        return new FileInputStream(file);
    }

    public void editFileName(String fileName, String newFileName) {
        if (filesStorageRepo.findByName(fileName) == null) {
            throw new InputData("Error input data");
        }

        filesStorageRepo.updateFileName(fileName, newFileName);
    }

    public ResponseEntity getAllFiles(String authToken, Integer limit) {

        List<FilesStorage> filesStorageList = filesStorageRepo.findAllByCustomerId(personRepo.getIdPerson(authToken));

        if (filesStorageList.isEmpty()) {
            throw new DeleteFile("Error getting file list");
        }

        List<HashMap<String, Object>> userFileLimitList = new ArrayList<>();

        int size = limit;
        if (filesStorageList.size() < limit) {
            size = filesStorageList.size();
        }

        for (int i = 0; i < size; i++) {
            FilesStorage filesStorage = filesStorageList.get(i);
            HashMap<String, Object> personFiles = new HashMap<>();
            personFiles.put("filename", filesStorage.getName());
            personFiles.put("size", filesStorage.getFile().length());
            userFileLimitList.add(personFiles);
        }

        return new ResponseEntity<>(userFileLimitList, HttpStatus.OK);
    }
}
