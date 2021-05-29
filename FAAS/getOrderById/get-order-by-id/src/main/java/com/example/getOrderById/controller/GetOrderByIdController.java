package com.example.orderGetTicketListByDateAndTripId.controller;
import com.example.orderGetTicketListByDateAndTripId.service.GetOrderByIdService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.ResponseEntity.ok;

@RestController
public class GetOrderByIdController {
    @Autowired
    private GetOrderByIdService getOrderByIdService;
    private static final Logger LOGGER = LoggerFactory.getLogger(GetOrderByIdController.class);

    @RequestMapping(value = "/order/{orderId}", method = RequestMethod.GET)
    public HttpEntity getOrderById(@PathVariable String orderId, @RequestHeader HttpHeaders headers){
        GetOrderByIdController.LOGGER.info("[Get Order By Id Function] Order Id: {}", orderId);
        return ok(getOrderByIdService.getOrderById(orderId, headers));
    }
}
