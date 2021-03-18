package com.example.getOrderById.service;

import com.example.getOrderById.entity.Order;
import com.example.getOrderById.repository.OrderRepository;
import edu.fudan.common.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class GetOrderByIdServiceImpl implements GetOrderByIdService{
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Response getOrderById(String orderId, HttpHeaders headers){
        Order order = orderRepository.findById(UUID.fromString(orderId));
        if(order == null){
            return new Response<>(0, "Order Not Found", null);
        }else{
            return new Response<>(1, "Success", order);
        }
    }
}
