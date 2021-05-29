package com.example.configRetrieve.controller;

import com.example.configRetrieve.service.ConfigRetrieveService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpEntity;

import static org.springframework.http.ResponseEntity.ok;

// according to station name ---> query station id
@RestController
public class ConfigRetrieveController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigRetrieveController.class);
    @Autowired
    ConfigRetrieveService configRetrieveService;

    @GetMapping(value = "/configs/{configName}")
    public HttpEntity retrieve(@PathVariable String configName, @RequestHeader HttpHeaders headers) {
        return ok(configRetrieveService.query(configName, headers));
    }
}
