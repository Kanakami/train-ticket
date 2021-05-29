package com.example.orderGetTicketListByDateAndTripId.service;

import com.example.orderGetTicketListByDateAndTripId.entity.Order;
import com.example.orderGetTicketListByDateAndTripId.repository.OrderRepository;
import edu.fudan.common.util.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import com.example.orderGetTicketListByDateAndTripId.entity.*;

@Service
public class OrderGetTicketListByDateAndTripIdServiceImpl implements OrderGetTicketListByDateAndTripIdService {
    @Autowired
    private OrderRepository orderRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderGetTicketListByDateAndTripIdServiceImpl.class);

    @Override
    public Response getSoldTickets(Seat seatRequest, HttpHeaders headers) {
        ArrayList<Order> list = orderRepository.findByTravelDateAndTrainNumber(seatRequest.getTravelDate(),
                seatRequest.getTrainNumber());
        if (list != null && !list.isEmpty()) {
            Set ticketSet = new HashSet();
            for (Order tempOrder : list) {
                ticketSet.add(new Ticket(Integer.parseInt(tempOrder.getSeatNumber()),
                        tempOrder.getFrom(), tempOrder.getTo()));
            }
            LeftTicketInfo leftTicketInfo = new LeftTicketInfo();
            leftTicketInfo.setSoldTickets(ticketSet);
            OrderGetTicketListByDateAndTripIdServiceImpl.LOGGER.info("Left ticket info is: {}", leftTicketInfo.toString());
            return new Response<>(1, "Success", leftTicketInfo);
        } else {
            OrderGetTicketListByDateAndTripIdServiceImpl.LOGGER.info("Left ticket info is empty");
            return new Response<>(0, "Order is Null.", null);
        }
    }
}
