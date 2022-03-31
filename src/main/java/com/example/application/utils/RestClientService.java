package com.example.application.utils;

import com.example.application.entity.models.ApiResponseBody;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;


/**
 * Created by kelvin keegan on 24/03/2022
 */

@Service
@RequiredArgsConstructor
@Slf4j
public class RestClientService {

    private final WebClient.Builder client;

    public ApiResponseBody Http_GET_ResponseBody(String link,String token) {

        try {

            ApiResponseBody apiResponseBody = client.build()
                    .get()
                    .uri(link)
                    .headers(httpHeaders -> httpHeaders.setBearerAuth(token))
                    .retrieve()
                    .bodyToMono(ApiResponseBody.class)
                    .block();

            if (apiResponseBody != null) {

                if (apiResponseBody.getStatusCode() != 0 && !apiResponseBody.getMessage().isEmpty()) {

                    return apiResponseBody;

                } else if (apiResponseBody.getStatusCode() == 500 && apiResponseBody.getMessage().isEmpty()) {

                    log.error("Generic Internal server error check remote sever for logs");
                    return new ApiResponseBody(500,null,"Internal server error");

                } else {

                    log.error("Status Code is 0 or empty. Please investigate!");
                    return new ApiResponseBody(500,null,"Something went wrong!");

                }
            } else  {

                log.error("No data or communication received from API server");
                return new ApiResponseBody(500,null,"Remote is unavailable!");
            }

        } catch (Exception exception) {

            log.error("An exception has been caught: [Exception] {}",exception.getMessage());
            return new ApiResponseBody(500,null, "Fatal encounter");

        }

    }

    public ApiResponseBody Http_POST_ResponseBody(String link, MediaType mediaType, Object requestBody,String token) {

        try {

            ApiResponseBody apiResponseBody = client.build()
                    .post()
                    .uri(link)
                    .contentType(mediaType)
                    .headers(httpHeaders -> httpHeaders.setBearerAuth(token))
                    .body(BodyInserters.fromValue(requestBody))
                    .retrieve()
                    .bodyToMono(ApiResponseBody.class)
                    .block();

            if (apiResponseBody != null) {

                if (apiResponseBody.getStatusCode() != 0 && !apiResponseBody.getMessage().isEmpty()) {

                    return apiResponseBody;

                } else if (apiResponseBody.getStatusCode() == 500 && apiResponseBody.getMessage().isEmpty()) {

                    log.error("Generic Internal server error check remote sever for logs");
                    return new ApiResponseBody(500,null,"Internal server error");

                } else {

                    log.error("Status Code is 0 or empty. Please investigate!");
                    return new ApiResponseBody(500,null,"Something went wrong!");

                }
            } else  {

                log.error("No data or communication received from API server");
                return new ApiResponseBody(500,null,"Remote is unavailable!");
            }

        } catch (Exception exception) {

            log.error("An exception has been caught: [Exception] {}",exception.getMessage());
            return new ApiResponseBody(500,null, "Fatal encounter");

        }

    }
}
