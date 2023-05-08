package com.example.backendcloudservice.model;

public class UserFile {
    private String name;
    private Long size;

    @Override
    public String toString() {
        return "UserFile{" +
                "name='" + name + '\'' +
                ", size=" + size +
                '}';
    }

    public UserFile(String name, Long size) {
        this.name = name;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public Long getSize() {
        return size;
    }
}
