package com.example.backendcloudservice.eception;

public class DeleteFile extends RuntimeException{
    public DeleteFile(String msg) {
        super(msg);
    }
}
