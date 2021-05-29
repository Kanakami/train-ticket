package com.example.configRetrieve.service;
import edu.fudan.common.util.Response;
import org.springframework.http.HttpHeaders;

public interface ConfigRetrieveService {

    Response query(String name, HttpHeaders headers);
}
