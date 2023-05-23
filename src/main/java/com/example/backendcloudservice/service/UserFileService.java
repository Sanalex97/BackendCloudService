package com.example.backendcloudservice.service;

import com.example.backendcloudservice.entity.UserFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class UserFileService {
    public String uploadFile(String authToken, String fileName, MultipartFile file) {
        return null;
    }

    public void deletingFile(String authToken, String fileName) {
    }

    public UserFile getFile(String authToken, String fileName) {
        return null;
    }

    public void editFileName(String authToken, String fileName, String substring) {
    }

    public List<UserFile> getAllFiles(String authToken, Integer limit) {
        return null;
    }
}
