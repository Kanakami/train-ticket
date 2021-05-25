package com.example.queryRouteById.controller;
import com.example.queryRouteById.service.TicketInfoQueryForStationIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpEntity;

import static org.springframework.http.ResponseEntity.ok;

// according to station name ---> query station id
@RestController
public class TicketInfoQueryForStationIdController {
    @Autowired
    TicketInfoQueryForStationIdService ticketInfoQueryForStationIdService;

    @RequestMapping(value = "/ticketinfo/{stationName}", method = RequestMethod.GET)
    public HttpEntity queryForStationId(@PathVariable(value = "stationName")String stationName, @RequestHeader HttpHeaders headers){
        return ok(ticketInfoQueryForStationIdService.queryForId(stationName, headers));
    }
}
