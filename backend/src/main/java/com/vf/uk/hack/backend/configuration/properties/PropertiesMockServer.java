package com.vf.uk.hack.backend.configuration.properties;

import com.vf.uk.hack.backend.configuration.properties.PropertiesDynamoDB.PropertiesDynamoDBBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class PropertiesMockServer {

  @ConfigurationProperties(prefix = "dxl.dynamo")
  @Bean("propertiesDynamoDB")
  @Primary
  public PropertiesDynamoDBBuilder dynamoPropertiesBuilder() {
    return new PropertiesDynamoDBBuilder();
  }
}