package com.example.ticketinfoQueryForStationId.controller;
import com.example.ticketinfoQueryForStationId.service.BasicQueryForStationIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpEntity;

import static org.springframework.http.ResponseEntity.ok;

// according to station name ---> query station id
@RestController
public class QueryForStationIdController {
    @Autowired
    BasicQueryForStationIdService basicQueryForStationIdService;

    @RequestMapping(value = "/basic/{stationName}", method = RequestMethod.GET)
    public HttpEntity queryForStationId(@PathVariable(value = "stationName")String stationName, @RequestHeader HttpHeaders headers){
        return ok(basicQueryForStationIdService.queryForId(stationName, headers));
    }
}
