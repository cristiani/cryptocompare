package com.example.crytocompare.crypto.service;

import com.example.crytocompare.crypto.dto.HistoricalRateRecordDto;

import java.util.List;

public interface HistoricalRateService {
    public List<HistoricalRateRecordDto> getHistoricalRecordsSince(long time);
}
