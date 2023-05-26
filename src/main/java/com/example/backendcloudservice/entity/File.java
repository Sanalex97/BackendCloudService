package com.example.backendcloudservice.entity;

import jakarta.persistence.Column;

import java.io.Serializable;

public class File implements Serializable {
    private String hash;
    private String file;

    public File() {
    }

    public File(String hash, String file) {
        this.hash = hash;
        this.file = file;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    @Override
    public String toString() {
        return "File{" +
                "hash='" + hash + '\'' +
                ", file='" + file + '\'' +
                '}';
    }
}
