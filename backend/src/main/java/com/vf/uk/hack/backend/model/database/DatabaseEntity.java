package com.vf.uk.hack.backend.model.database;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import static java.time.LocalDateTime.now;
import static java.time.LocalDateTime.ofEpochSecond;

public interface DatabaseEntity {

  class LocalDateTimeConverter implements DynamoDBTypeConverter<Long, LocalDateTime> {
    @Override
    public Long convert( final LocalDateTime time ) {
      return now().toEpochSecond(ZoneOffset.UTC);
    }

    @Override
    public LocalDateTime unconvert( final Long epochMilli ) {
      return ofEpochSecond(epochMilli, 0, ZoneOffset.UTC);
    }
  }
}