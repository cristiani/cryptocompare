package com.example.crytocompare.crypto.controller;

import com.example.crytocompare.crypto.model.CryptoCurrentResponse;
import com.example.crytocompare.crypto.service.CurrentRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Validated
@RestController
@RequestMapping("/api")
public class CurrentRateController {
    @Autowired
    CurrentRateService service;

    @GetMapping(value="/current_rate", produces = MediaType.APPLICATION_JSON_VALUE)
    public CryptoCurrentResponse getCurrentRate(){
        return service.getCurrentRate();
    }
}
