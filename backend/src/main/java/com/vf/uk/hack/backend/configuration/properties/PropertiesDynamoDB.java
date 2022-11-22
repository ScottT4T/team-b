package com.vf.uk.hack.backend.configuration.properties;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(setterPrefix = "set")
public class PropertiesDynamoDB {
  boolean local;
  String environment;

  public String getTableName() {
    return String.format("%s-hackathon-mockserver", environment);
  }
}