package com.example.getLeftTicketOfInterval.service;
import edu.fudan.common.util.Response;
import org.springframework.http.HttpHeaders;

public interface TicketInfoQueryForStationIdService {

    Response queryForId(String stationName, HttpHeaders headers);
}
