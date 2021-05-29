package com.example.travelGetRouteByTripId.service;
import edu.fudan.common.util.Response;
import org.springframework.http.HttpHeaders;

public interface TravelGetRouteByTripIdService {

    Response getRouteByTripId(String tripId, HttpHeaders headers);
}
