package com.example.backendcloudservice.converterDto;

import org.modelmapper.ModelMapper;

public class ConvertDto {
    private ModelMapper mapper = new ModelMapper();

    public ModelMapper getMapper() {
        return mapper;
    }
}
