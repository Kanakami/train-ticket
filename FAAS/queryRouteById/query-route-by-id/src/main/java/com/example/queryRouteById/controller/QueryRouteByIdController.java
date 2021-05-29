package com.example.queryRouteById.controller;
import com.example.queryRouteById.service.QueryRouteByIdService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpEntity;

import static org.springframework.http.ResponseEntity.ok;

// according to station name ---> query station id
@RestController
public class QueryRouteByIdController {
    private static final Logger LOGGER = LoggerFactory.getLogger(QueryRouteByIdController.class);
    @Autowired
    QueryRouteByIdService queryRouteByIdService;

    @GetMapping(path = "/routes/{routeId}")
    public HttpEntity queryById(@PathVariable String routeId, @RequestHeader HttpHeaders headers) {
        QueryRouteByIdController.LOGGER.info("Route id: {}", routeId);
        return ok(queryRouteByIdService.queryRouteById(routeId, headers));
    }
}
