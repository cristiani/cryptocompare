package com.example.crytocompare.crypto.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CryptoHistoricalResponse {
    @JsonProperty
    private String Response;
    @JsonProperty
    private String Message;
    @JsonProperty
    private Boolean HasWarning;
    @JsonProperty
    private Integer Type;
    @JsonProperty
    private CryptoHistoricalResponseData Data;
}
