package com.example.queryForStationId.service;
import edu.fudan.common.util.Response;
import org.springframework.http.HttpHeaders;

public interface QueryForStationIdService {

    Response queryForId(String stationId, HttpHeaders headers);
}
