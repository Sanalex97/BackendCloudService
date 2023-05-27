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
@Table(name = "UserFiles", schema = "cloudservice")
public class UserFile {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;
    /*  @Column(name = "hash")
      private String hash;*/
    @Column(name = "file")
    private File file;

    public UserFile(String name, File file) {
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
/*  public UserFile(String name, String hash, byte[] file) {
        this.name = name;
        this.hash = hash;
        this.file = file;
    }

    public String getName() {
        return name;
    }

    public String getHash() {
        return hash;
    }

    public byte[] getFile() {
        return file;
    }*/
}
