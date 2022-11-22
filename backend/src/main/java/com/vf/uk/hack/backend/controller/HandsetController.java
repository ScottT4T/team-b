package com.vf.uk.hack.backend.controller;

import com.vf.uk.hack.backend.configuration.ConfigurationRepository;
import com.vf.uk.hack.backend.model.FilterRequest;
import com.vf.uk.hack.backend.model.database.DatabaseDevice;
import com.vf.uk.hack.backend.server.DBRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping(value = {""}, produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class HandsetController {

  private final ConfigurationRepository repositoryConfiguration;

  @GetMapping("/handsets")
  public List<DatabaseDevice> handsets(
    final @Valid @ModelAttribute FilterRequest request) {
    final DBRepository repository = repositoryConfiguration.getRepository();
    List<DatabaseDevice> devices = repository.listDevices();

    return devices.stream()
      .filter(device -> filter(device.getBrand(), request.getBrand()))
      .filter(device -> filter(device.getModel(), request.getModel()))
      .filter(device -> filter(device.getColour(), request.getColour()))
      .filter(device -> filter(device.getMemoryInternal(), request.getMemoryInternal()))
      .filter(device -> filter(device.getScreenSize(), request.getScreenSize()))
      .collect(Collectors.toList());
  }

  private boolean filter(final String deviceElement, final String requestElement) {
    if (requestElement == null) {
      return true;
    }

    final String[] requestElements = requestElement.split(",");
    for (String element : requestElements) {
      if(deviceElement.equals(element)) {
        return true;
      }
    }
    return false;
  }

}