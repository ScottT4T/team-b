package com.vf.uk.hack.backend.model;

import lombok.Data;

@Data
public class FilterRequest {
  private String model;
  private String brand;
  private String screenSize;
  private String memoryInternal;
  private String colour;
}