package com.example.application.entity.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * Created by kelvin keegan on 24/03/2022
 */

@Getter
@Setter
@AllArgsConstructor
public class ApiResponseBody {

    private int statusCode;
    private Object data;
    private String message;

}
