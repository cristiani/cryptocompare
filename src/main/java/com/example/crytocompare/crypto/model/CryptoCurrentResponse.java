package com.example.crytocompare.crypto.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CryptoCurrentResponse {
    @JsonProperty("USD")
    private Double USD;
    @JsonProperty("JPY")
    private Double JPY;
    @JsonProperty("EUR")
    private Double EUR;
}
