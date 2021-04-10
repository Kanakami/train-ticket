package com.example.basicQueryForStationId.service;

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

import javax.annotation.Resource;

@Service
public class BasicQueryForStationIdServiceImpl implements BasicQueryForStationIdService {
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder){
        return builder.build();
    }

    @Autowired
    private RestTemplate restTemplate;

    private static final Logger LOGGER = LoggerFactory.getLogger(BasicQueryForStationIdServiceImpl.class);

    @Override
    public Response queryForId(String stationName, HttpHeaders headers) {
        BasicQueryForStationIdServiceImpl.LOGGER.info("[Basic Information Service][Query For Station Id] Station Id: {}", stationName);
        HttpEntity requestEntity = new HttpEntity( headers);
        ResponseEntity<Response> re = restTemplate.exchange(
                "http://10.176.122.15:31112/function/query-for-station-id/stations/id/" + stationName,
                HttpMethod.GET,
                requestEntity,
                Response.class);
        return  re.getBody();
    }
}
