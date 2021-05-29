package com.example.travelGetTrainTypeByTripId.service;

import com.example.travelGetTrainTypeByTripId.entity.*;
import com.example.travelGetTrainTypeByTripId.repository.TripRepository;
import edu.fudan.common.util.JsonUtils;
import edu.fudan.common.util.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TravelGetTrainTypeByTripIdServiceImpl implements TravelGetTrainTypeByTripIdService {
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder){
        return builder.build();
    }

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private TripRepository tripRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(TravelGetTrainTypeByTripIdServiceImpl.class);

    @Override
    public Response getTrainTypeByTripId(String tripId, HttpHeaders headers) {
        TripId tripId1 = new TripId(tripId);
        TrainType trainType = null;
        Trip trip = tripRepository.findByTripId(tripId1);
        if (trip != null) {
            trainType = getTrainType(trip.getTrainTypeId(), headers);
        }
        if (trainType != null) {
            return new Response<>(1, "Success", trainType);
        } else {
            return new Response<>(0, "No Content", null);
        }
    }

    private TrainType getTrainType(String trainTypeId, HttpHeaders headers) {
        HttpEntity requestEntity = new HttpEntity(headers);
        ResponseEntity<Response<TrainType>> re = restTemplate.exchange(
//                "http://ts-train-service:14567/api/v1/trainservice/trains/" + trainTypeId,
                "http://10.176.122.15:31112/function/train-retrieve/trains/" + trainTypeId,
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<Response<TrainType>>() {
                });

        return re.getBody().getData();
    }
}
