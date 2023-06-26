package com.example.backendcloudservice.repository;

import com.example.backendcloudservice.model.entity.FilesStorage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface FilesStorageRepo extends JpaRepository<FilesStorage, Long> {
    FilesStorage findByName(String name);

    List<FilesStorage> findAllByCustomerId(long costumerId);

    @Modifying
    @Transactional
    @Query(value = "update FilesStorage f set f.name=:newFileName where f.name=:fileName")
    void updateFileName(String fileName, String newFileName);

}
