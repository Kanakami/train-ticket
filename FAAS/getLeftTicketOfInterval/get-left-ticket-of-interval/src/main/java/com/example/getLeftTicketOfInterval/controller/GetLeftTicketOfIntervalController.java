package com.example.getLeftTicketOfInterval.controller;
import com.example.getLeftTicketOfInterval.entity.Seat;
import com.example.getLeftTicketOfInterval.service.GetLeftTicketOfIntervalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpEntity;

import static org.springframework.http.ResponseEntity.ok;

// according to station name ---> query station id
@RestController
public class GetLeftTicketOfIntervalController {
    private static final Logger LOGGER = LoggerFactory.getLogger(GetLeftTicketOfIntervalController.class);
    @Autowired
    GetLeftTicketOfIntervalService getLeftTicketOfIntervalService;

    @PostMapping(value = "/seats/left_tickets")
    public HttpEntity getLeftTicketOfInterval(@RequestBody Seat seatRequest, @RequestHeader HttpHeaders headers) {
        // int
        return ok(getLeftTicketOfIntervalService.getLeftTicketOfInterval(seatRequest, headers));
    }
}
