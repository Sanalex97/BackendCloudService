package com.example.backendcloudservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "files", schema = "cloudservice")
public class FileEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;
    @Column(name = "file")
    private File file;

    public FileEntity(String name, File file) {
        this.name = name;
        this.file = file;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    /*    @Column(name = "hash")
    private String hash;*/

   /* @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "binfile")
    private String binFile;

    public FileEntity(String binFile, String hash) {
        this.binFile = binFile;
        this.hash = hash;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getBinFile() {
        return binFile;
    }

    public void setBinFile(String binFile) {
        this.binFile = binFile;
    }*/

}
