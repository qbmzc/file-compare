package com.cong.devola.config;

import com.fasterxml.jackson.databind.ObjectMapper;

public enum MapperEnum {
    INSTANCE;

    private final ObjectMapper objectMapper;

    MapperEnum() {
        objectMapper = new ObjectMapper();
    }

    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }
}
