package com.vf.uk.hack.backend.configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import static com.vf.uk.hack.backend.server.LocalDynamoDBServer.PORT;

@Configuration
@DependsOn("propertiesDynamoDB")
public class ConfigurationDynamoDB {

//  @ConditionalOnProperty(name = "dxl.dynamo.local", havingValue = "true")
  @Bean("amazonDynamoDB")
  public AmazonDynamoDB createLocalserverDynamoDB(
    final @Value("${dxl.dynamo.endpoint:http://localhost:" + PORT + "}") String dynamoEndpoint) {
    return AmazonDynamoDBClientBuilder.standard()
      .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials("any", "any")))
      .withEndpointConfiguration(
        new EndpointConfiguration(dynamoEndpoint, "us-east-1"))
      .build();
  }

//  @ConditionalOnProperty(name = "dxl.dynamo.local", havingValue = "false", matchIfMissing = true)
//  @Bean("amazonDynamoDB")
//  @Primary
//  public AmazonDynamoDB createAmazonDynamoDB() {
//    return AmazonDynamoDBClientBuilder.standard().withRegion("eu-west-1").build();
//  }
}

