package com.example.orderGetTicketListByDateAndTripId.controller;
import com.example.orderGetTicketListByDateAndTripId.entity.Seat;
import com.example.orderGetTicketListByDateAndTripId.service.OrderGetTicketListByDateAndTripIdService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.ResponseEntity.ok;

@RestController
public class OrderGetTicketListByDateAndTripIdController {
    @Autowired
    private OrderGetTicketListByDateAndTripIdService orderGetTicketListByDateAndTripIdService;
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderGetTicketListByDateAndTripIdController.class);

    @PostMapping(value = "/order/tickets")
    public HttpEntity getTicketListByDateAndTripId(@RequestBody Seat seatRequest, @RequestHeader HttpHeaders headers) {
        OrderGetTicketListByDateAndTripIdController.LOGGER.info("[Order Service][Get Sold Ticket] Date: {}", seatRequest.getTravelDate().toString());
        return ok(orderGetTicketListByDateAndTripIdService.getSoldTickets(seatRequest, headers));
    }
}
