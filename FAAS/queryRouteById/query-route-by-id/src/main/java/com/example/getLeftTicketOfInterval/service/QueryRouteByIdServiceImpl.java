package com.example.getLeftTicketOfInterval.service;

import com.example.getLeftTicketOfInterval.repository.RouteRepository;
import edu.fudan.common.util.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.example.getLeftTicketOfInterval.entity.Route;

import java.util.Optional;

@Service
public class QueryRouteByIdServiceImpl implements QueryRouteByIdService {
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder){
        return builder.build();
    }

    @Autowired
//    private RestTemplate restTemplate;
    private RouteRepository routeRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(QueryRouteByIdServiceImpl.class);

    @Override
    public Response queryRouteById(String routeId, HttpHeaders headers){
        Optional<Route> route = routeRepository.findById(routeId);
        if (!route.isPresent()) {
            return new Response<>(0, "No content with the routeId", null);
        } else {
            return new Response<>(1, "success", route.get());
        }
    }
}
