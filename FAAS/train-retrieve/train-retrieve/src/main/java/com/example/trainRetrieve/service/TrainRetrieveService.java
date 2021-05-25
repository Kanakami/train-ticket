package com.example.trainRetrieve.service;
import edu.fudan.common.util.Response;
import org.springframework.http.HttpHeaders;

public interface TrainRetrieveService {

    Response retrieve(String id, HttpHeaders headers);
}
