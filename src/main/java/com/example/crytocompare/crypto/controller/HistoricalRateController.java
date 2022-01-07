package com.example.crytocompare.crypto.controller;


import com.example.crytocompare.crypto.dto.HistoricalRateRecordDto;
import com.example.crytocompare.crypto.service.HistoricalRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Validated
@RestController
@RequestMapping("/api")
public class HistoricalRateController {

    @Autowired
    HistoricalRateService service;

    @GetMapping(value="/historical_rate", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<HistoricalRateRecordDto> getHistoricalRates(@RequestParam(value="time") @NotNull @NotEmpty Long time) {
        return service.getHistoricalRecordsSince(time);
    }
}
