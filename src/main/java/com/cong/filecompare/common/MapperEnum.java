package com.cong.filecompare.common;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author cong
 * @date 2023/2/1 17:45
 */
public enum MapperEnum {

    INSTANCE;

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }
}
