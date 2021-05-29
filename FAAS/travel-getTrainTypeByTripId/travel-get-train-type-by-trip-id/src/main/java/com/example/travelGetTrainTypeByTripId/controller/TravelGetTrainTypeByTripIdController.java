package com.example.travelGetTrainTypeByTripId.controller;
import com.example.travelGetTrainTypeByTripId.service.TravelGetTrainTypeByTripIdService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpEntity;

import static org.springframework.http.ResponseEntity.ok;

// according to station name ---> query station id
@RestController
public class TravelGetTrainTypeByTripIdController {
    private static final Logger LOGGER = LoggerFactory.getLogger(TravelGetTrainTypeByTripIdController.class);
    @Autowired
    TravelGetTrainTypeByTripIdService travelGetTrainTypeByTripIdService;

    @GetMapping(value = "/train_types/{tripId}")
    public HttpEntity getTrainTypeByTripId(@PathVariable String tripId,
                                           @RequestHeader HttpHeaders headers) {
        // TrainType
        return ok(travelGetTrainTypeByTripIdService.getTrainTypeByTripId(tripId, headers));
    }
}
