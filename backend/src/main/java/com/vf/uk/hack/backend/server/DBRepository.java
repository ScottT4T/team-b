package com.vf.uk.hack.backend.server;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.*;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.vf.uk.hack.backend.configuration.properties.PropertiesDynamoDB;
import com.vf.uk.hack.backend.configuration.properties.PropertiesMockServer;
import com.vf.uk.hack.backend.model.database.DatabaseEntity;
import com.vf.uk.hack.backend.model.database.DatabaseDevice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.time.LocalDateTime.now;

@Slf4j
@Service
public class DBRepository {

  private final AmazonDynamoDB amazonDynamoDB;
  private final PropertiesDynamoDB properties;
  private final DynamoDBMapper mapper;

  public DBRepository(final AmazonDynamoDB amazonDynamoDB,
                      final PropertiesMockServer propertiesMockServer) {
    this.amazonDynamoDB = amazonDynamoDB;
    this.properties = propertiesMockServer.dynamoPropertiesBuilder().build();

      DynamoDBMapperConfig mapperConfig =
        DynamoDBMapperConfig.builder()
          .withTableNameOverride(new DynamoDBMapperConfig.TableNameOverride(properties.getTableName()))
          .build();
    this.mapper = new DynamoDBMapper(amazonDynamoDB, mapperConfig);
  }

  public DatabaseDevice loadDevice(final String deviceId) throws IOException {
    return loadByHashKey(deviceId, DatabaseDevice.class).stream().findFirst().orElse(null);
  }

  public void saveDevice(final DatabaseDevice item) {
    item.setModifiedTime(now());
    mapper.save(item);
  }

  public void deleteAccount(final DatabaseDevice item) {
    mapper.delete(item);
  }

  public List<DatabaseDevice> listDevices() {
    DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
    final PaginatedScanList<DatabaseDevice> scan = mapper.scan(DatabaseDevice.class, scanExpression);
    return new ArrayList<>(scan);
  }

  private <T extends DatabaseEntity> List<T> loadByHashKey(final String hashKey, final Class<T> clazz) throws IOException {
    Map<String, AttributeValue> eav = new HashMap<>();
    eav.put(":v_id", new AttributeValue().withS(hashKey));

    DynamoDBQueryExpression<T> spec = new DynamoDBQueryExpression<T>()
        .withKeyConditionExpression("id = :v_id")
        .withExpressionAttributeValues(eav);

    return load(spec, clazz);
  }

  private <T extends DatabaseEntity> List<T> load(final DynamoDBQueryExpression<T> spec, final Class<T> clazz) throws IOException {
    try {

      return mapper.query(clazz, spec);
    } catch (Exception e) {
      log.error(
        "Problem loading session from session repository due to error message: {} ",
        e.getMessage());
      throw new IOException("Problem loading item from session repository");
    }
  }
}
