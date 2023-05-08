package com.example.backendcloudservice.repository;

import com.example.backendcloudservice.model.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCrudRepo extends JpaRepository<FileEntity, String> {
    FileEntity findByName(String name);
}
