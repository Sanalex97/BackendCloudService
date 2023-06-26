package com.example.backendcloudservice.converterDto;

import com.example.backendcloudservice.model.dto.FilesStorageDto;
import com.example.backendcloudservice.model.entity.FilesStorage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.convention.MatchingStrategies;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class FilesStorageConvertDto extends ConvertDto {
    private FilesStorageDto filesStorageDto;
    private FilesStorage filesStorage;

    private String fileName = "MyFile.txt";
    private File file = File.createTempFile(fileName, fileName.split("\\.")[1]);

    public FilesStorageConvertDto() throws IOException {
    }

    @BeforeEach
    public void setup() throws IOException {
        filesStorageDto = new FilesStorageDto();
        filesStorageDto.setId(0);
        filesStorageDto.setName(fileName);
        filesStorageDto.setFile(file);

        filesStorage = new FilesStorage(1, fileName, file);
    }

    @Test
    public void convertToEntity() {

        getMapper().getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        FilesStorage filesStorage1 = getMapper().map(filesStorageDto, FilesStorage.class);

        assertEquals(filesStorageDto.getName(), filesStorage1.getName());
        assertEquals(filesStorageDto.getFile(), filesStorage1.getFile());
    }

    @Test
    public void convertToDto() {
        getMapper().getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        FilesStorageDto filesStorageDto1 = getMapper().map(filesStorage, FilesStorageDto.class);

        assertEquals(filesStorage.getName(), filesStorageDto1.getName());
        assertEquals(filesStorage.getFile(), filesStorageDto1.getFile());
    }
}
