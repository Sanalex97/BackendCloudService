package com.example.backendcloudservice.service.file_storage;

import com.example.backendcloudservice.entity.FilesStorage;
import com.example.backendcloudservice.repository.FilesStorageRepo;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class FilesStorageServiceTest {
    @Autowired
    private FilesStorageRepo filesStorageRepo;

    private static FilesStorage filesStorage;

    private static String fileName = "homework.txt";
    private static String newFileName = "newHomework.txt";

    @BeforeAll
    static void setup() throws IOException {
        filesStorage = new FilesStorage(0, fileName,
                File.createTempFile(fileName, fileName.split("\\.")[1]));
    }

    @Test
    void saveFilesAndFindByNameFilesTest() {
        FilesStorage filesStorageSaved = filesStorageRepo.save(filesStorage);
        assertEquals(filesStorage.getName(), filesStorageSaved.getName());

        FilesStorage filesStorageFound = filesStorageRepo.findByName(fileName);
        assertEquals(filesStorage.getName(), filesStorageFound.getName());
    }

    @Test
    void deletingFile() {
        FilesStorage filesStorageSaved = filesStorageRepo.save(filesStorage);

        filesStorageRepo.delete(filesStorageSaved);

        FilesStorage filesStorageFound = filesStorageRepo.findByName(filesStorageSaved.getName());

        assertEquals(null, filesStorageFound);
    }

    @Test
    void editFileName() {
        FilesStorage filesStorageSaved = filesStorageRepo.save(filesStorage);

        filesStorageRepo.updateFileName(filesStorageSaved.getName(),
                newFileName);

        FilesStorage filesStorageUpdate = filesStorageRepo.findByName(newFileName);

        assertEquals(newFileName, filesStorageUpdate.getName());
    }

}