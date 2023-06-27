package com.example.backendcloudservice.controller;

import com.example.backendcloudservice.service.FilesStorageService;
import com.example.backendcloudservice.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@CrossOrigin
public class FilesController {
    @Autowired
    private FilesStorageService filesStorageService;

    @Autowired
    private PersonService personService;

    @PostMapping({"/file"})
    public ResponseEntity uploadFileToServer(@RequestHeader(name = "auth-token") String authToken, @RequestParam(name = "filename") String fileName, @RequestPart("file") MultipartFile file) throws IOException {
        this.personService.isErrorAuthorized(authToken.split(" ")[1]);
        this.personService.isErrorInputData(fileName, fileName);
        this.filesStorageService.uploadFile(authToken.split(" ")[1], fileName, file);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping({"/file"})
    public ResponseEntity deletingFile(@RequestHeader(name = "auth-token") String authToken, @RequestParam(name = "filename") String fileName) throws IOException {
        this.personService.isErrorAuthorized(authToken.split(" ")[1]);
        this.personService.isErrorInputData(authToken.split(" ")[1], fileName);
        this.filesStorageService.deletingFile(authToken.split(" ")[1], fileName);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping({"/file"})
    public ResponseEntity getFile(@RequestHeader(name = "auth-token") String authToken, @RequestParam(name = "filename") String fileName) throws IOException {
        this.personService.isErrorAuthorized(authToken.split(" ")[1]);
        this.personService.isErrorInputData(fileName, fileName);
        return ResponseEntity.ok().body(new InputStreamResource(this.filesStorageService.getFile(authToken.split(" ")[1], fileName)));
    }

    @PutMapping({"/file"})
    public ResponseEntity EditFileName(@RequestHeader(name = "auth-token") String authToken, @RequestParam(name = "filename") String fileName, @RequestBody String newFileName) throws IOException {
        this.personService.isErrorAuthorized(authToken.split(" ")[1]);
        this.personService.isErrorInputData(fileName, fileName);
        newFileName = newFileName.split(":")[1];
        this.filesStorageService.editFileName(authToken.split(" ")[1], fileName, newFileName.substring(1, newFileName.length() - 2));
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping({"/list"})
    public ResponseEntity getAllFiles(@RequestHeader(name = "auth-token") String authToken, @RequestParam(name = "limit") Integer limit) throws IOException {
        this.personService.isErrorAuthorized(authToken.split(" ")[1]);
        return this.filesStorageService.getAllFiles(authToken.split(" ")[1], limit);
    }
}
