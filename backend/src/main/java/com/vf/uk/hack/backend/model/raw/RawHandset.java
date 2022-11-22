package com.vf.uk.hack.backend.model.raw;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class RawHandset {
  private List<RawDevice> devices;

  @Data
  public static class RawDevice {
    private String deviceId;
    private String make;
    private String model;
    private String displayName;
    private String memory;
    private String displayDescription;
    private String colourName;
    private String colourHex;
    private List<ImageURLs> listOfimageURLs;
    private Specification specification;
  }

  @Data
  public static class ImageURLs {
    private String imageName;
    private String imageURL;
  }

  @Data
  public static class Specification {
    private List<NameValuePair> prioritySpecifications;

    public Map<String,String> getPrioritySpecificationsMap() {
      return prioritySpecifications.stream()
        .collect(Collectors.toMap(NameValuePair::getName, NameValuePair::getValue));
    }
  }

  @Data
  public static class NameValuePair {
    private String name;
    private String value;
  }
}
