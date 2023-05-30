package com.example.backendcloudservice.controller;

import com.example.backendcloudservice.service.UserFileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

@RestController
@CrossOrigin
//@RequestMapping("/file")
public class UserFileController {
    private final UserFileService userFileService;

    public UserFileController(UserFileService userFileService) {
        this.userFileService = userFileService;
    }

    @PostMapping("/file")
    public ResponseEntity uploadFileToServer(@RequestHeader(name = "auth-token") String authToken,
                                             @RequestParam(name = "filename") String fileName,
                                             @RequestPart("file") MultipartFile file) throws IOException, NoSuchAlgorithmException {

        return userFileService.uploadFile(authToken.split(" ")[1], fileName, file);

    }

    @DeleteMapping("/file")
    public ResponseEntity deletingFile(@RequestHeader(name = "auth-token") String authToken,
                                       @RequestParam(name = "filename") String fileName) {

        userFileService.deletingFile(authToken.split(" ")[1], fileName);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/file")
    public ResponseEntity getFile(@RequestHeader(name = "auth-token") String authToken,
                                  @RequestParam(name = "filename") String fileName) throws IOException, NoSuchAlgorithmException {

        return userFileService.getFile(authToken.split(" ")[1], fileName);
    }

    @PutMapping("/file")
    public ResponseEntity EditFileName(@RequestHeader(name = "auth-token") String authToken,
                                       @RequestParam(name = "filename") String fileName,
                                       @RequestBody String newFileName) {

        newFileName = newFileName.split(":")[1];
        userFileService.editFileName(authToken.split(" ")[1], fileName, newFileName.substring(1, newFileName.length() - 2));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity getAllFiles(@RequestHeader(name = "auth-token") String authToken, @RequestParam(name = "limit") Integer limit) throws IOException {

        return userFileService.getAllFiles(authToken.split(" ")[1], limit);
    }
}
