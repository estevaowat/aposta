package com.ewcode.friendsbigball.app.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;

public interface Mapper {
    <T> T convert(String message, Class clazz) throws JsonProcessingException;
    <T> T convert(byte[] message, Class clazz) throws IOException;
    byte[] toByte(Object obj) throws JsonProcessingException;

}
