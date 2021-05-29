package com.example.queryForStationId.controller;
import com.example.queryRouteById.service.QueryForStationIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpEntity;

import static org.springframework.http.ResponseEntity.ok;

// according to station name ---> query station id
@RestController
public class QueryForStationIdController {
    @Autowired
    QueryForStationIdService queryForStationIdService;

    @RequestMapping(value = "/stations/id/{stationNameForId}", method = RequestMethod.GET)
    public HttpEntity queryForStationId(@PathVariable(value = "stationNameForId")String stationName, @RequestHeader HttpHeaders headers){
        return ok(queryForStationIdService.queryForId(stationName, headers));
    }
}
