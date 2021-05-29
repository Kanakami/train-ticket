package com.example.travelGetRouteByTripId.controller;
import com.example.travelGetRouteByTripId.service.TravelGetRouteByTripIdService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpEntity;

import static org.springframework.http.ResponseEntity.ok;

// according to station name ---> query station id
@RestController
public class TravelGetRouteByTripIdController {
    private static final Logger LOGGER = LoggerFactory.getLogger(TravelGetRouteByTripIdController.class);
    @Autowired
    TravelGetRouteByTripIdService travelGetRouteByTripIdService;

    @GetMapping(value = "/routes/{tripId}")
    public HttpEntity getRouteByTripId(@PathVariable String tripId,
                                       @RequestHeader HttpHeaders headers) {
        TravelGetRouteByTripIdController.LOGGER.info("[Get Route By Trip ID] TripId: {}", tripId);
        //Route
        return ok(travelGetRouteByTripIdService.getRouteByTripId(tripId, headers));
    }
}
