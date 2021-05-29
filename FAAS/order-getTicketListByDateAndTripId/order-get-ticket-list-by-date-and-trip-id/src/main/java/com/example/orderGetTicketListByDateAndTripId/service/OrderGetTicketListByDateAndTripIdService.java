package com.example.orderGetTicketListByDateAndTripId.service;

import edu.fudan.common.util.Response;
import org.springframework.http.HttpHeaders;
import com.example.orderGetTicketListByDateAndTripId.entity.Seat;

public interface OrderGetTicketListByDateAndTripIdService {
    Response getSoldTickets(Seat seatRequest, HttpHeaders headers);
}
