package com.example.getOrderById.service;

import edu.fudan.common.util.Response;
import org.springframework.http.HttpHeaders;

public interface GetOrderByIdService {
    Response getOrderById(String orderId, HttpHeaders headers);
}
