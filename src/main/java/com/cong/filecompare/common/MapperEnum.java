package com.cong.filecompare.common;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 *  This is an enum class used to create a singleton instance of an ObjectMapper. It provides a getter method to access the ObjectMapper instance.
 */
public enum MapperEnum {
    INSTANCE;

    private final ObjectMapper objectMapper;

    private MapperEnum() {
        objectMapper= new ObjectMapper();
    }




    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }
}
