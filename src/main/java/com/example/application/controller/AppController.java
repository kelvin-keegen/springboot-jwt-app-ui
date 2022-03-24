package com.example.application.controller;

import com.example.application.entity.models.ApiResponseBody;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by kelvin keegan on 24/03/2022
 */

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class AppController {


    @GetMapping(path = "/status")
    public ApiResponseBody AWS_HealthChecker() {

        return new ApiResponseBody(200,null,"Application is up and running");
    }

}
