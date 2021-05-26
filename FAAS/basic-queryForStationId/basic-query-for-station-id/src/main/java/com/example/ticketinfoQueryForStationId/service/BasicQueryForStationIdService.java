package com.example.getLeftTicketOfInterval.service;
import edu.fudan.common.util.Response;
import org.springframework.http.HttpHeaders;

public interface BasicQueryForStationIdService {

    Response queryForId(String stationName, HttpHeaders headers);
}
