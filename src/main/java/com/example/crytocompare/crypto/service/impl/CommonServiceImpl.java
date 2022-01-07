package com.example.crytocompare.crypto.service.impl;

import com.example.crytocompare.crypto.model.CryptoCurrentResponse;
import com.example.crytocompare.crypto.model.CryptoHistoricalResponse;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;

public class CommonServiceImpl {
    private RestTemplate restTemplate;
    HttpEntity<String> requestEntity;

    private void prepareHttpRestTemplate(String apiKey) {
        HttpHeaders headers = new HttpHeaders();
        if (apiKey != null) {
            headers.add("Apikey", apiKey);
        } //else throw exception
        requestEntity = new HttpEntity<>(headers);
        RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
        restTemplate = restTemplateBuilder
                .errorHandler(new RestTemplateResponseErrorHandler())
                .build();
    }

    protected ResponseEntity<CryptoHistoricalResponse> getCryptoHistoricalResponse(String url, String apiKey,
                                                                                   String fsym, String tsym,
                                                                                   Integer limit) {
        prepareHttpRestTemplate(apiKey);
        String urlTemplate = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("fsym", "{fsym}")
                .queryParam("tsym", "{tsym}")
                .queryParam("limit", "{limit}")
                .encode()
                .toUriString();
        Map<String, String> params = new HashMap<>();
        params.put("fsym", fsym);
        params.put("tsym", tsym);
        params.put("limit", limit.toString());
        ParameterizedTypeReference<CryptoHistoricalResponse> type = new ParameterizedTypeReference<CryptoHistoricalResponse>() {
        };
        return  restTemplate.exchange(urlTemplate, HttpMethod.GET, requestEntity, type, params);
    }

    protected ResponseEntity<CryptoCurrentResponse> getCryptoCurrentResponse(String url, String apiKey, String fsym,
                                                                             String tsyms) {
        prepareHttpRestTemplate(apiKey);
        String urlTemplate = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("fsym", "{fsym}")
                .queryParam("tsyms", "{tsyms}")
                .encode()
                .toUriString();
        Map<String, String> params = new HashMap<>();
        params.put("fsym", fsym);
        params.put("tsyms", tsyms);
        ParameterizedTypeReference<CryptoCurrentResponse> type = new ParameterizedTypeReference<CryptoCurrentResponse>() {
        };
        return  restTemplate.exchange(urlTemplate, HttpMethod.GET, requestEntity, type, params);
    }
}
