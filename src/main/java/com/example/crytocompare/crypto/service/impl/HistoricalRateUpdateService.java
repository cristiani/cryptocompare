package com.example.crytocompare.crypto.service.impl;

import com.example.crytocompare.crypto.dao.HistoricalRateRecordRepository;
import com.example.crytocompare.crypto.dto.HistoricalRateRecordDto;
import com.example.crytocompare.crypto.model.CryptoHistoricalResponse;
import com.example.crytocompare.crypto.model.CryptoHistoricalResponseData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class HistoricalRateUpdateService extends CommonServiceImpl {

    @Autowired
    HistoricalRateRecordRepository repository;

    @Value( "${api.key}" )
    private String apiKey;

    @Value(("${api.historical.url}"))
    private String url;

    @Value( "${crypto.fsym}" )
    private String fsym;

    @Value( "${crypto.tsym}" )
    private String tsym;

    @Value( "${crypto.limit}" )
    private Integer limit;

    @Scheduled(fixedDelayString = "${update.interval}")
    public void updateHistoricalRates() throws InterruptedException {
        ResponseEntity<CryptoHistoricalResponse> response = getCryptoHistoricalResponse(url, apiKey, fsym, tsym, limit);
        if (response.getStatusCode().equals(HttpStatus.OK)) {
            CryptoHistoricalResponseData historicalData = Objects.requireNonNull(response.getBody()).getData();
            if (historicalData != null) {
                List<HistoricalRateRecordDto> historicalRecords = historicalData.getData();
                int count = 0;
                for (HistoricalRateRecordDto record : historicalRecords) {
                    try {
                        //we are not very careful to omit duplicates from Crypto API, so we have to make sure we can
                        //insert ignoring duplicates based on time field.
                        //this solution does not work with batch inserts
                        repository.save(HistoricalRateRecordDto.toEntity(record));
                    } catch (DataIntegrityViolationException e) {
                        count ++;
                    }
                }
                if (count > 0) {
                    log.debug("Found {} duplicate(s)", count);
                }
            }
        }
    }
}
