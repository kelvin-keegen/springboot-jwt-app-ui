package com.example.application.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vaadin.flow.component.UI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by kelvin keegan on 29/03/2022
 */

@Service
@Slf4j
public class RetrievalService {


    public Object GetObjValue(Object objectInput,String keyOfObject) {

            ObjectMapper objectMapper = new ObjectMapper();

            Map<String,Object> stringObjectMap = objectMapper.convertValue(objectInput,Map.class);

            return stringObjectMap.get(keyOfObject);

    }

    public Object WriteJsonObject(Object objectInput) {

        ObjectMapper objectMapper = new ObjectMapper();
        String json = null;

        try {

            json = objectMapper.writeValueAsString(objectInput);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return json;
    }

}
