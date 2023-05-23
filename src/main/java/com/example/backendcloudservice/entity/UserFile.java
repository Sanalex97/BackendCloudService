package com.example.backendcloudservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "files", schema = "cloudservice")
public class UserFile {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;
    @Column(name = "file")
    private java.io.File file;

    public UserFile(String name, java.io.File file) {
        this.name = name;
        this.file = file;
    }

    public String getName() {
        return name;
    }

    public java.io.File getFile() {
        return file;
    }

}
