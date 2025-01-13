package br.sul.liter_alura.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectConverter {

    public static <T> T convertJsonToObject(String json, Class<T> tClass) {
        try {
            return new ObjectMapper().readValue(json, tClass);
        } catch (JsonProcessingException e) {
            System.out.println("Error in data conversion: " + e.getMessage());
            return null;
        }
    }
}
