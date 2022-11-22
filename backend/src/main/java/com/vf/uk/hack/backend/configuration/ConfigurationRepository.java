package com.vf.uk.hack.backend.configuration;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.vf.uk.hack.backend.configuration.properties.PropertiesMockServer;
import com.vf.uk.hack.backend.server.DBRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConfigurationRepository {

  private final PropertiesMockServer properties;
  private final AmazonDynamoDB amazonDynamo;

  public DBRepository getRepository() {
    return new DBRepository(amazonDynamo, properties);
  }
}