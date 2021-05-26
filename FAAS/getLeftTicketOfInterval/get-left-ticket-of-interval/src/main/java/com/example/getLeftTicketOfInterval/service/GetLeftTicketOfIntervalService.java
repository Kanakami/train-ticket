package com.example.getLeftTicketOfInterval.service;
import com.example.getLeftTicketOfInterval.entity.Seat;
import edu.fudan.common.util.Response;
import org.springframework.http.HttpHeaders;

public interface GetLeftTicketOfIntervalService {

    Response getLeftTicketOfInterval(Seat seatRequest, HttpHeaders headers);
}
