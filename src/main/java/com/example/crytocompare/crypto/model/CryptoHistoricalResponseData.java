package com.example.crytocompare.crypto.model;

import com.example.crytocompare.crypto.dto.HistoricalRateRecordDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CryptoHistoricalResponseData {
    @JsonProperty
    private Boolean Aggregated;
    @JsonProperty
    private Long TimeFrom;
    @JsonProperty
    private Long TimeTo;
    @JsonProperty
    private List<HistoricalRateRecordDto> Data;
}
