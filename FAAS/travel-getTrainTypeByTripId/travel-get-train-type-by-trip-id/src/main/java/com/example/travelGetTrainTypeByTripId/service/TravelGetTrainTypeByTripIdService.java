package com.example.travelGetTrainTypeByTripId.service;
import edu.fudan.common.util.Response;
import org.springframework.http.HttpHeaders;

public interface TravelGetTrainTypeByTripIdService {

    Response getTrainTypeByTripId(String tripId, HttpHeaders headers);
}
