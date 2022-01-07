package com.example.crytocompare.crypto.service.impl;

import com.example.crytocompare.crypto.dao.HistoricalRateRecordRepository;
import com.example.crytocompare.crypto.dto.HistoricalRateRecordDto;
import com.example.crytocompare.crypto.model.HistoricalRateRecord;
import com.example.crytocompare.crypto.service.HistoricalRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HistoricalRateServiceImpl implements HistoricalRateService {
    @Autowired
    HistoricalRateRecordRepository repository;

    @Override
    public List<HistoricalRateRecordDto> getHistoricalRecordsSince(long time) {
        List<HistoricalRateRecordDto> result = new ArrayList<>();
        List<HistoricalRateRecord> records = repository.findByTimeGreaterThanEqualOrderByTimeDesc(time);
        for (HistoricalRateRecord record: records) {
            result.add(HistoricalRateRecord.toDto(record));
        }
        return result;
    }
}
