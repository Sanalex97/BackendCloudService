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


    @PostMapping("/file")
    public ResponseEntity uploadFileToServer(@RequestHeader(name = "auth-token") String authToken,
                                             @RequestParam(name = "filename") String fileName,
                                             @RequestPart("file") MultipartFile file) throws IOException {

        personService.isErrorAuthorized(authToken.split(" ")[1]);
        personService.isErrorInputData(fileName);

        filesStorageService.uploadFile(authToken.split(" ")[1], fileName, file);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/file")
    public ResponseEntity deletingFile(@RequestHeader(name = "auth-token") String authToken,
                                       @RequestParam(name = "filename") String fileName) {

        personService.isErrorAuthorized(authToken.split(" ")[1]);
        personService.isErrorInputData(fileName);

        filesStorageService.deletingFile(fileName);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/file")
    public ResponseEntity getFile(@RequestHeader(name = "auth-token") String authToken,
                                  @RequestParam(name = "filename") String fileName) throws IOException {
        personService.isErrorAuthorized(authToken.split(" ")[1]);
        personService.isErrorInputData(fileName);

        return ResponseEntity.ok().body(new InputStreamResource(filesStorageService.getFile(fileName)));
    }

    @PutMapping("/file")
    public ResponseEntity EditFileName(@RequestHeader(name = "auth-token") String authToken,
                                       @RequestParam(name = "filename") String fileName,
                                       @RequestBody String newFileName) {
        personService.isErrorAuthorized(authToken.split(" ")[1]);
        personService.isErrorInputData(fileName);

        newFileName = newFileName.split(":")[1];
        filesStorageService.editFileName(fileName, newFileName.substring(1, newFileName.length() - 2));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity getAllFiles(@RequestHeader(name = "auth-token") String authToken, @RequestParam(name = "limit") Integer limit) throws IOException {
        personService.isErrorAuthorized(authToken.split(" ")[1]);
        return filesStorageService.getAllFiles(authToken.split(" ")[1], limit);
    }
}
