package com.example.getLeftTicketOfInterval.service;

import com.example.getLeftTicketOfInterval.repository.StationRepository;
import edu.fudan.common.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import com.example.getLeftTicketOfInterval.entity.Station;

@Service
public class QueryForStationIdServiceImpl implements QueryForStationIdService{
    @Autowired
    private StationRepository stationRepository;

    @Override
    public Response queryForId(String stationName, HttpHeaders headers) {
        Station station = stationRepository.findByName(stationName);

        if (station  != null) {
            return new Response<>(1, "Success", station.getId());
        } else {
            return new Response<>(0, "Not exists", stationName);
        }
    }
}
