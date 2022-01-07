package com.example.crytocompare.crypto.dto;

import com.example.crytocompare.crypto.model.HistoricalRateRecord;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class HistoricalRateRecordDto {
    @JsonProperty
    private Long time;
    @JsonProperty
    private Double high;
    @JsonProperty
    private Double low;
    @JsonProperty
    private Double open;
    @JsonProperty("volumefrom")
    private Double volumeFrom;
    @JsonProperty("volumeto")
    private Double volumeTo;
    @JsonProperty
    private Double close;
    @JsonProperty
    private String conversionType;
    @JsonProperty
    private String conversionSymbol = "";

    public static HistoricalRateRecord toEntity(HistoricalRateRecordDto dto) {
        return new HistoricalRateRecord(null, dto.time, dto.high, dto.low, dto.open, dto.volumeFrom, dto.volumeTo,
                dto.close, dto.conversionType, dto.conversionSymbol);
    }
}
