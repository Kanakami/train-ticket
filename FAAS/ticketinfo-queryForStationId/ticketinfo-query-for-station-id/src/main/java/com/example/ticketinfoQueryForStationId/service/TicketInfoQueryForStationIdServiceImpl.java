package com.example.ticketinfoQueryForStationId.service;

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
public class TicketInfoQueryForStationIdServiceImpl implements TicketInfoQueryForStationIdService {
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder){
        return builder.build();
    }

    @Autowired
    private RestTemplate restTemplate;

    private static final Logger LOGGER = LoggerFactory.getLogger(TicketInfoQueryForStationIdServiceImpl.class);

    @Override
    public Response queryForId(String stationName, HttpHeaders headers) {
        TicketInfoQueryForStationIdServiceImpl.LOGGER.info("[TicketInfo Information Service][Query For Station Id] Station Id: {}", stationName);
        HttpEntity requestEntity = new HttpEntity( headers);
        ResponseEntity<Response> re = restTemplate.exchange(
                "http://10.176.122.15:31112/function/basic-query-for-station-id/basic/" + stationName,
                HttpMethod.GET,
                requestEntity,
                Response.class);
        return  re.getBody();
    }
}
