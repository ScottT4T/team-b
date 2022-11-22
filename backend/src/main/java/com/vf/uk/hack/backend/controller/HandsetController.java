package com.vf.uk.hack.backend.controller;

import com.vf.uk.hack.backend.configuration.ConfigurationRepository;
import com.vf.uk.hack.backend.model.database.DatabaseDevice;
import com.vf.uk.hack.backend.server.DBRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = {""}, produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class HandsetController {

  private final ConfigurationRepository repositoryConfiguration;

  @GetMapping("/handsets")
  public List<DatabaseDevice> handsets() {
    final DBRepository repository = repositoryConfiguration.getRepository();
    final List<DatabaseDevice> devices = repository.listDevices();

    return devices;
  }
}