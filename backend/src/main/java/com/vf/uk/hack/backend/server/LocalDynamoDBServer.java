package com.vf.uk.hack.backend.server;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.local.main.ServerRunner;
import com.amazonaws.services.dynamodbv2.local.server.DynamoDBProxyServer;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.vf.uk.hack.backend.configuration.ConfigurationDynamoDB;
import com.vf.uk.hack.backend.configuration.properties.PropertiesMockServer;
import com.vf.uk.hack.backend.model.database.DatabaseDevice;
import com.vf.uk.hack.backend.model.raw.RawHandset;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.text.CaseUtils;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Map;

import static com.vf.uk.hack.backend.utils.MockResponseMapper.readValue;
import static java.time.LocalDateTime.now;

@Slf4j
@Configuration
//@Profile("local-mocking") // only boots up if LOCAL!
//@ConditionalOnProperty(name = "dxl.mocking.support.enabled", havingValue = "true")
public class LocalDynamoDBServer {
  public static final int PORT = 3355;
  private static final String ENDPOINT = "http://localhost:" + PORT;

  private final String tableName;

  public LocalDynamoDBServer(final PropertiesMockServer properties) throws Exception {
    tableName = properties.dynamoPropertiesBuilder().build().getTableName();
    log.info( "##### DYNAMODB :: Starting Local DynamoDB Server #####");

    final AmazonDynamoDB amazonDynamoDB = new ConfigurationDynamoDB().createLocalserverDynamoDB(ENDPOINT);
    final DynamoDB dynamoDB = new DynamoDB(amazonDynamoDB);

    final DynamoDBMapperConfig mapperConfig =
      DynamoDBMapperConfig.builder()
        .withTableNameOverride(new DynamoDBMapperConfig.TableNameOverride(tableName))
        .build();
    final DynamoDBMapper mapper = new DynamoDBMapper(amazonDynamoDB, mapperConfig);

    startMockServer();
    createTable(mapper, dynamoDB);
    uploadMockFiles(mapper);
  }

  private void startMockServer() throws Exception {
    final String userDirectory = System.getProperty("user.dir");
    final String nativeLibPath = userDirectory
      + (userDirectory.endsWith("backend") ? "" : "/backend")
      + "/target/native-libs";

    System.setProperty("sqlite4java.library.path", nativeLibPath);

    final DynamoDBProxyServer server = ServerRunner.createServerFromCommandLineArgs(
              new String[]{"-inMemory", "-port", Integer.toString(PORT)});
    server.start();
    log.info( "DYNAMODB: STARTED: {}", ENDPOINT);
  }

  @SneakyThrows
  private void createTable(final DynamoDBMapper mapper, final DynamoDB dynamoDB) {
    try {
      log.info("DYNAMODB: Create table; please wait...");
      CreateTableRequest tableRequest = mapper.generateCreateTableRequest(DatabaseDevice.class);

      ProvisionedThroughput throughput = new ProvisionedThroughput(1L, 1L);
      tableRequest.setProvisionedThroughput(throughput);

      final Table table = dynamoDB.createTable(tableRequest);
      table.waitForActive();
      log.info("DYNAMODB: Success.  Table status: " + table.getDescription().getTableStatus());

    } catch (final Exception e) {
      log.error("DYNAMODB: Unable to create table: {}", e.getMessage(), e);
      throw e;
    }
  }

  private void uploadMockFiles(final DynamoDBMapper dynamoDB) throws URISyntaxException {
    final URL mockingURL = LocalDynamoDBServer.this.getClass().getResource("/data");
    if (mockingURL != null) {
      final File mockingFolder = new File(mockingURL.toURI());
      uploadFolder(dynamoDB, "", mockingFolder);
    }
  }

  private void uploadFolder(final DynamoDBMapper dynamoDB, final String folderPath, final File mockingFolder) {
    final File[] folderList = mockingFolder.listFiles();
    if (folderList != null) {
      for (final File file : folderList) {
        if (file.isDirectory()) {
          uploadFolder(dynamoDB, folderPath + file.getName() + "/", file);
        } else {
          final String key = file.getName();
          try {
            RawHandset rawHandset = readValue(file, RawHandset.class);

            rawHandset.getDevices().forEach(device -> {
              DatabaseDevice dbItem = new DatabaseDevice();
              dbItem.setId(device.getDeviceId());
              dbItem.setModifiedTime(now());
              dbItem.setDisplayName(device.getDisplayName());
              dbItem.setDisplayDescription(device.getDisplayDescription());
              dbItem.setBrand(camelCase(device.getMake()));
              dbItem.setModel(camelCase(device.getModel()));
              dbItem.setColour(device.getColourName());
              dbItem.setMemoryInternal(device.getMemory());
              Map<String, String> specMap = RawHandset.Specification.getPrioritySpecificationsMap(device.getSpecification());
              dbItem.setScreenSize(cleanScreenSize(specMap.get("Display Size")));
              final Map<String, String> imageMap = RawHandset.ImageURLs.getListOfimageURLsMap(device.getListOfimageURLs());
              dbItem.setImageURL("https://cdn.vodafone.co.uk/en/assets" + imageMap.get("imageURLs.full.hero"));
              dynamoDB.save(dbItem);
            });

          } catch (final IOException e) {
            log.error("IOException while putting file {} -> dynamoDB://{}/{}", file, tableName, key, e);
          }
        }
      }
    }
  }

  private String camelCase(final String text) {
    return CaseUtils.toCamelCase(text, true, ' ');
  }

  private String cleanScreenSize(final String size) {
    return size != null && size.endsWith("inch") ? size.substring(0, size.length()-4) : size;
  }
}