package com.vf.uk.hack.backend.controller;

import com.vf.uk.hack.backend.model.HelloWorld;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = {""}, produces = MediaType.APPLICATION_JSON_VALUE)
public class BackendController {

  @GetMapping("**")
  public HelloWorld getHelloWorld() {
    log.info("Hello Called!");
    return new HelloWorld().setHello("world");
  }
}