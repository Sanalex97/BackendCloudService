package com.example.backendcloudservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "FilesStorage", schema = "cloudservice")
public class FilesStorage {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "customerId")
    private long customerId;
    @Column(name = "name")
    private String name;
    @Column(name = "file")
    private File file;

    public FilesStorage(long customerId, String name, File file) {
        this.customerId = customerId;
        this.name = name;
        this.file = file;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public File getFile() {
        return file;
    }


}
