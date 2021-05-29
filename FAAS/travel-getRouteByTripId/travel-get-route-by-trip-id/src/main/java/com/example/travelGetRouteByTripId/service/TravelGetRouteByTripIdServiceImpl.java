package com.example.travelGetRouteByTripId.service;

import com.example.travelGetRouteByTripId.entity.*;
import com.example.travelGetRouteByTripId.repository.TripRepository;
import edu.fudan.common.util.JsonUtils;
import edu.fudan.common.util.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TravelGetRouteByTripIdServiceImpl implements TravelGetRouteByTripIdService {
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder){
        return builder.build();
    }

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private TripRepository tripRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(TravelGetRouteByTripIdServiceImpl.class);

    @Override
    public Response getRouteByTripId(String tripId, HttpHeaders headers) {
        Route route = null;
        if (null != tripId && tripId.length() >= 2) {
            TripId tripId1 = new TripId(tripId);
            Trip trip = tripRepository.findByTripId(tripId1);
            if (trip != null) {
                route = getRouteByRouteId(trip.getRouteId(), headers);
            }
        }
        if (route != null) {
            return new Response<>(1, "Success", route);
        } else {
            return new Response<>(0, "No Content", null);
        }
    }

    private Route getRouteByRouteId(String routeId, HttpHeaders headers) {
        TravelGetRouteByTripIdServiceImpl.LOGGER.info("[Travel Service][Get Route By Id] Route ID：{}", routeId);
        HttpEntity requestEntity = new HttpEntity(headers);
        ResponseEntity<Response> re = restTemplate.exchange(
//                "http://ts-route-service:11178/api/v1/routeservice/routes/" + routeId,
                "http://10.176.122.15:31112/function/query-route-by-id/routes/" + routeId,
                HttpMethod.GET,
                requestEntity,
                Response.class);
        Response routeRes = re.getBody();

        Route route1 = new Route();
        TravelGetRouteByTripIdServiceImpl.LOGGER.info("Routes Response is : {}", routeRes.toString());
        if (routeRes.getStatus() == 1) {
            route1 = JsonUtils.conveterObject(routeRes.getData(), Route.class);
            TravelGetRouteByTripIdServiceImpl.LOGGER.info("Route is: {}", route1.toString());
        }
        return route1;
    }
}
