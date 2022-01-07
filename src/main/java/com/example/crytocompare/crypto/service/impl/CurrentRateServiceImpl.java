package com.example.crytocompare.crypto.service.impl;

import com.example.crytocompare.crypto.model.CryptoCurrentResponse;
import com.example.crytocompare.crypto.service.CurrentRateService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class CurrentRateServiceImpl extends CommonServiceImpl implements CurrentRateService {
    @Value( "${api.key}" )
    private String apiKey;

    @Value(("${api.current.url}"))
    private String url;

    @Value( "${crypto.fsym}" )
    private String fsym;

    @Value( "${crypto.tsyms}" )
    private String tsyms;

    @Override
    public CryptoCurrentResponse getCurrentRate() {
        ResponseEntity<CryptoCurrentResponse> response = getCryptoCurrentResponse(url, apiKey, fsym, tsyms);
        if (response.getStatusCode().equals(HttpStatus.OK)) {
            return Objects.requireNonNull(response.getBody());
        }
        return null;
    }
}
