package com.example.getLeftTicketOfInterval.service;
import edu.fudan.common.util.Response;
import org.springframework.http.HttpHeaders;

public interface QueryRouteByIdService {

    Response queryRouteById(String routeId, HttpHeaders headers);
}
