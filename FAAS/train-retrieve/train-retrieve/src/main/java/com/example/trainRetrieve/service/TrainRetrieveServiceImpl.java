package com.example.trainRetrieve.service;

import com.example.trainRetrieve.entity.TrainType;
import com.example.trainRetrieve.repository.TrainTypeRepository;
import edu.fudan.common.util.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class TrainRetrieveServiceImpl implements TrainRetrieveService {
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder){
        return builder.build();
    }

    @Autowired
//    private RestTemplate restTemplate;
    private TrainTypeRepository trainTypeRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(TrainRetrieveServiceImpl.class);

    @Override
    public Response retrieve(String id, HttpHeaders headers) {
        Optional<TrainType> trainType = trainTypeRepository.findById(id);
//        if (!trainType.isPresent()) {
//            return new Response<>(0, "here is no TrainType with the trainType id: " + id, null);
//        } else {
//            return new Response<>(1, "success", trainType.get());
//        }
        return trainType.map(type -> new Response<>(1, "success", type)).orElseGet(() -> new Response<>(0, "here is no TrainType with the trainType id: " + id, null));
    }
}
