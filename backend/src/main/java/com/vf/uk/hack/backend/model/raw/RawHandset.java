package com.vf.uk.hack.backend.model.raw;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class RawHandset {
  private List<RawDevices> deviceList;

  @Data
  public static class RawDevices {
    private List<RawDevice> devices;
  }

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

    public static Map<String,String> getListOfimageURLsMap(final List<ImageURLs> imageURLsList) {
      return imageURLsList != null ? imageURLsList.stream()
        .collect(Collectors.toMap(ImageURLs::getImageName, ImageURLs::getImageURL)) : Map.of();
    }
  }

  @Data
  public static class Specification {
    private List<NameValuePair> prioritySpecifications;

    public static Map<String,String> getPrioritySpecificationsMap(final Specification specification) {
      return specification != null ? specification.getPrioritySpecifications().stream()
        .collect(Collectors.toMap(NameValuePair::getName, NameValuePair::getValue)) : Map.of();
    }
  }

  @Data
  public static class NameValuePair {
    private String name;
    private String value;
  }
}
