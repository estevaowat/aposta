package com.ewcode.friendsbigball.infra.mapper;

import com.ewcode.friendsbigball.app.mapper.Mapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class JacksonObjectMapper implements Mapper {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public <T> T convert(String message, Class clazz) throws JsonProcessingException {
        return (T) objectMapper.readValue(message, clazz);
    }
    @Override
    public <T> T convert(byte[] message, Class clazz) throws IOException {
        return (T) objectMapper.readValue(message, clazz);
    }
    @Override
    public byte[] toByte(Object obj) throws JsonProcessingException {
        return objectMapper.writeValueAsBytes(obj);
    }
}