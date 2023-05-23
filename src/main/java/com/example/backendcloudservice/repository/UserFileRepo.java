package com.example.backendcloudservice.repository;

import com.example.backendcloudservice.entity.UserFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;


public interface UserFileRepo extends JpaRepository<UserFile, Long> {
    UserFile findByName(String name);

    @Modifying
    @Transactional
    @Query("update UserFile f set f.name=:newFileName where f.name=:fileName")
    void updateFileName(String fileName, String newFileName);

}
