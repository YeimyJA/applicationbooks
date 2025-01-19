package com.yeimy.applicationbooks.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConverterData implements IConvierteDatos {
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public <T> T getInformation(String json, Class<T> clase) {
        try {
            return objectMapper.readValue(json,clase);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

     public <T> T getInformationTree(String json) {
         try {
             return (T) objectMapper.readTree(json);
         } catch (JsonProcessingException e) {
             throw new RuntimeException(e);
         }
     }
}
