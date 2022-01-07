package com.example.crytocompare.crypto.dao;

import com.example.crytocompare.crypto.model.HistoricalRateRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoricalRateRecordRepository extends JpaRepository<HistoricalRateRecord, Long>  {
    public List<HistoricalRateRecord> findByTimeGreaterThanEqualOrderByTimeDesc(Long time);
}
