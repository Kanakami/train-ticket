package com.example.configRetrieve.service;

import com.example.configRetrieve.repository.ConfigRepository;
import com.example.configRetrieve.entity.Config;
import edu.fudan.common.util.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ConfigRetrieveServiceImpl implements ConfigRetrieveService {
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder){
        return builder.build();
    }

    @Autowired
//    private RestTemplate restTemplate;
    private ConfigRepository configRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigRetrieveServiceImpl.class);

    @Override
    public Response query(String name, HttpHeaders headers) {
        Config config = configRepository.findByName(name);
        if (config == null) {
            return new Response<>(0, "No content", null);
        } else {
            return new Response<>(1, "Success", config);
        }
    }
}
