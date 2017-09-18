package com.redbee.weather.consumers;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Created by Gisela Lopez Giles on 9/15/17.
 */
@Service
public class RestConsumer {

    public static <String> String obtain(UriComponentsBuilder builder, HttpMethod method, Class<String> returnType) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = createHttpHeaders();
        ResponseEntity<String> response = restTemplate.exchange(
                builder.build().encode().toUri(),
                method,
                new HttpEntity<>(headers), returnType);
        return response.getBody();
    }

    public static HttpHeaders createHttpHeaders() {
        return new HttpHeaders();
    }
}
