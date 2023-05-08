package com.example.backendcloudservice.controller;

import com.example.backendcloudservice.model.User;
import com.example.backendcloudservice.model.UserFile;
import com.example.backendcloudservice.service.CloudService;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@RestController
public class CloudServiceController {

    CloudService cloudService;

    public CloudServiceController(CloudService cloudService) {
        this.cloudService = cloudService;
    }

    @PostMapping("/login")
    public JSONObject authorization(@RequestBody User user) {
        System.out.println(user);
        return cloudService.getUserAuthorization(user);
    }

    @PostMapping("/logout")
    public void deletingToken(@RequestHeader(name = "auth-token") String authToken) {
        System.out.println(authToken);
        cloudService.deletingToken(authToken.split(" ")[1]);
    }

    @PostMapping("/file")
    public String uploadFileToServer(@RequestHeader(name = "auth-token") String authToken,
                                     @RequestParam(name = "filename") String fileName,
                                     @RequestPart("file") MultipartFile file) throws IOException {

        cloudService.uploadFile(authToken.split(" ")[1], fileName, file);
        return "Success upload";
    }

    @DeleteMapping("/file")
    public void deletingFile(@RequestHeader(name = "auth-token") String authToken,
                             @RequestParam(name = "filename") String fileName) {
        System.out.println("NAME = " + fileName);
        cloudService.deletingFile(authToken.split(" ")[1], fileName);
    }

    @GetMapping("/file")
    public String getFile(@RequestParam String authToken, String filename) {
        System.out.println("GET = " + filename);
        cloudService.getFile(authToken.split(" ")[1], filename);
        return null;
    }

    @PutMapping("/file")
    public String EditFileName(@RequestParam String authToken, String filename, String newName) {
        //todo распарсить тело запроса для получения нового имени и его погрузить в параметры метода
        cloudService.editFileName(authToken.split(" ")[1], filename, newName);
        return null;
    }

    @GetMapping("/list")
    public JSONObject[] getAllFiles(@RequestHeader(name = "auth-token") String authToken,  @RequestParam(name = "limit") Integer limit) {
        System.out.println("limit = " + limit);
        return cloudService.getAllFiles(authToken.split(" ")[1], limit);
    }
}
