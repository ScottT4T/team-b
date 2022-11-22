package com.vf.uk.hack.backend.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class MockResponseMapper {
  private static final ObjectMapper MAPPER = createObjectMapper();

  private static ObjectMapper createObjectMapper() {
    return new ObjectMapper()
    .registerModule(new JavaTimeModule())
    .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
    .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
  }

  public static <T> T readValue(final String content, final Class<T> valueType) throws JsonProcessingException {
    return MAPPER.readValue(content, valueType);
  }

  public static <T> List<T> readListValue(final String content, final Class<T> valueType) throws JsonProcessingException {
    return MAPPER.readValue(content, MAPPER.getTypeFactory().constructCollectionType(List.class, valueType));
  }

  public static <T> T readValue(final File file, final Class<T> valueType) throws IOException {
    return MAPPER.readValue(file, valueType);
  }

  public static String writeValue(final Object data) throws JsonProcessingException {
    return MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(data);
  }
}