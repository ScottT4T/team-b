package com.vf.uk.hack.backend.model.database;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverted;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DatabaseDevice implements DatabaseEntity {

  @DynamoDBHashKey
  private String id;
  @DynamoDBTypeConverted( converter = LocalDateTimeConverter.class )
  private LocalDateTime modifiedTime;

  @DynamoDBAttribute
  private String displayName;
  @DynamoDBAttribute
  private String displayDescription;
  @DynamoDBAttribute
  private String brand;
  @DynamoDBAttribute
  private String model;
  @DynamoDBAttribute
  private String screenSize;
  @DynamoDBAttribute
  private String memoryInternal;
  @DynamoDBAttribute
  private String colour;
  @DynamoDBAttribute
  private String imageURL;
}