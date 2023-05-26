package com.example.backendcloudservice.controller;

import com.example.backendcloudservice.entity.File;
import com.example.backendcloudservice.entity.UserFile;
import com.example.backendcloudservice.service.UserFileService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Timed;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/file")
public class UserFileController {
    private final UserFileService userFileService;

    public UserFileController(UserFileService userFileService) {
        this.userFileService = userFileService;
    }

    @PostMapping
    public String uploadFileToServer(@RequestHeader(name = "auth-token") String authToken,
                                     @RequestParam(name = "filename") String fileName,
                                     @RequestPart File request
                                    /* @RequestParam("file") MultipartFile file1,
                                     @RequestHeader(name = "hash")String hash,
                                     @RequestHeader(name = "file")String file*/) throws IOException {


        System.out.println("hash ===== " + request);
        File file = (File) request;

//        //   userFileService.uploadFile(authToken.split(" ")[1], fileName, file);
        return "Success upload";
    }

    @DeleteMapping
    public void deletingFile(@RequestHeader(name = "auth-token") String authToken,
                             @RequestParam(name = "filename") String fileName) {
        System.out.println("NAME = " + fileName);
        userFileService.deletingFile(authToken.split(" ")[1], fileName);
    }

    @GetMapping
    public UserFile getFile(@RequestHeader(name = "auth-token") String authToken,
                            @RequestParam(name = "filename") String fileName) throws IOException {
        System.out.println("GET = " + fileName);
        return userFileService.getFile(authToken.split(" ")[1], fileName);
    }

    @PutMapping
    public void EditFileName(@RequestHeader(name = "auth-token") String authToken,
                             @RequestParam(name = "filename") String fileName,
                             @RequestBody String newFileName) {
        newFileName = newFileName.split(":")[1];
        userFileService.editFileName(authToken.split(" ")[1], fileName, newFileName.substring(1, newFileName.length() - 3));
    }

    @GetMapping("/list")
    public ResponseEntity getAllFiles(@RequestHeader(name = "auth-token") String authToken, @RequestParam(name = "limit") Integer limit) {
        System.out.println("limit = " + limit);
        return userFileService.getAllFiles(authToken.split(" ")[1], limit);
    }
}
