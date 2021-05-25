package com.example.trainRetrieve.controller;
import com.example.trainRetrieve.entity.TrainType;
import com.example.trainRetrieve.service.TrainRetrieveService;
import edu.fudan.common.util.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpEntity;

import static org.springframework.http.ResponseEntity.ok;

// according to station name ---> query station id
@RestController
public class TrainRetrieveController {
    private static final Logger LOGGER = LoggerFactory.getLogger(TrainRetrieveController.class);
    @Autowired
    TrainRetrieveService trainRetrieveService;

    @GetMapping(value = "/trains/{id}")
    public HttpEntity retrieve(@PathVariable String id, @RequestHeader HttpHeaders headers) {
        TrainRetrieveController.LOGGER.info("Train id: {}", id);
        return ok(trainRetrieveService.retrieve(id, headers));
    }
}
