package com.vf.uk.hack.backend.controller;

import com.vf.uk.hack.backend.configuration.ConfigurationRepository;
import com.vf.uk.hack.backend.model.database.DatabaseDevice;
import com.vf.uk.hack.backend.model.raw.FilteredItems;
import com.vf.uk.hack.backend.server.DBRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@Slf4j
@RestController
@RequestMapping(value = {""}, produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class FilterController {

  private final ConfigurationRepository repositoryConfiguration;

  @GetMapping("/filter")
  public FilteredItems filter() {
    final DBRepository repository = repositoryConfiguration.getRepository();
    final List<DatabaseDevice> devices = repository.listDevices();
    FilteredItems filteredHandset = new FilteredItems();
    devices.forEach(databaseDevice -> {
      add(filteredHandset.getModel(),databaseDevice.getModel());
      add(filteredHandset.getBrand(),databaseDevice.getBrand());
      add(filteredHandset.getScreenSize(),databaseDevice.getScreenSize());
      add(filteredHandset.getMemoryInternal(),databaseDevice.getMemoryInternal());
      add(filteredHandset.getColour(),databaseDevice.getColour());
    });

    return filteredHandset;

  }

  private void add(Set<String> model, String model1) {
    if(model1 !=null){
      model.add(model1);
    }
  }

}
