package com.example.backendcloudservice.service;

import com.example.backendcloudservice.eception.DeleteFile;
import com.example.backendcloudservice.eception.InputData;
import com.example.backendcloudservice.log.Logger;
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

    private final Logger logger = Logger.getInstance();

    public void uploadFile(String authToken, String fileName, MultipartFile file) throws IOException {
        if (this.filesStorageRepo.findByName(fileName) != null) {
            this.logger.addLog(this.personRepo.getLoginPerson(authToken), "ошибка загрузки файла:" + fileName + "!");
            throw new InputData("Error input data");
        }
        InputStream in = file.getInputStream();
        File uploadFile = File.createTempFile(fileName, fileName.split("\\.")[1]);
        FileUtils.copyInputStreamToFile(in, uploadFile);
        this.filesStorageRepo.save(new FilesStorage(this.personRepo.getIdPerson(authToken).longValue(), fileName, uploadFile));
        this.logger.addLog(this.personRepo.getLoginPerson(authToken), "загрузил на сервис файл:" + fileName);
    }

    public void deletingFile(String authToken, String fileName) throws IOException {
        FilesStorage filesStorage = this.filesStorageRepo.findByName(fileName);
        this.filesStorageRepo.delete(filesStorage);
        this.logger.addLog(this.personRepo.getLoginPerson(authToken), "удалил файл:" + fileName);
    }

    public InputStream getFile(String authToken, String fileName) throws IOException {
        FilesStorage filesStorage = this.filesStorageRepo.findByName(fileName);
        File file = filesStorage.getFile();
        this.logger.addLog(this.personRepo.getLoginPerson(authToken), "скачал файл:" + fileName);
        return new FileInputStream(file);
    }

    public void editFileName(String authToken, String fileName, String newFileName) throws IOException {
        this.filesStorageRepo.updateFileName(fileName, newFileName);
        this.logger.addLog(this.personRepo.getLoginPerson(authToken), "изменил имя файла:" + fileName + " на имя файла:" + newFileName);
    }

    public ResponseEntity getAllFiles(String authToken, Integer limit) {
        List<FilesStorage> filesStorageList = this.filesStorageRepo.findAllByCustomerId(this.personRepo.getIdPerson(authToken).longValue());
        if (filesStorageList.isEmpty())
            throw new DeleteFile("Error getting file list");
        List<HashMap<String, Object>> userFileLimitList = new ArrayList<>();
        int size = limit.intValue();
        if (filesStorageList.size() < limit.intValue())
            size = filesStorageList.size();
        for (int i = 0; i < size; i++) {
            FilesStorage filesStorage = filesStorageList.get(i);
            HashMap<String, Object> personFiles = new HashMap<>();
            personFiles.put("filename", filesStorage.getName());
            personFiles.put("size", Long.valueOf(filesStorage.getFile().length()));
            userFileLimitList.add(personFiles);
        }
        return new ResponseEntity(userFileLimitList, HttpStatus.OK);
    }
}
