package com.example.backendcloudservice.repository;

import com.example.backendcloudservice.model.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface UserCrudRepo extends JpaRepository<FileEntity, String> {
    FileEntity findByName(String name);

    @Modifying
    @Transactional
    @Query("update FileEntity f set f.name=:newFileName where f.name=:fileName")
    void updateFileName(String fileName, String newFileName);

}
