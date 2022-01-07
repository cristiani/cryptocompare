package com.example.crytocompare.crypto.model;


import com.example.crytocompare.crypto.dto.HistoricalRateRecordDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Getter
@Setter
@Accessors(chain=true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "crypto_records")
public class HistoricalRateRecord implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private Long id;
    @NotEmpty(message = "Time of exchange rate is required")
    @Column(unique=true)
    private Long time;
    @NotEmpty(message = "Highest exchange rate is required")
    private Double high;
    @NotEmpty(message = "Lowest exchange rate is required")
    private Double low;
    @NotEmpty
    private Double open;
    @NotEmpty
    private Double volumeFrom;
    @NotEmpty
    private Double volumeTo;
    @NotEmpty
    private Double close;
    @NotEmpty
    @Column(name="conversion_type")
    private String conversionType;
    @NotEmpty
    @Column(name="conversion_symbol")
    private String conversionSymbol = "";

    public static HistoricalRateRecordDto toDto(HistoricalRateRecord record) {
        return new HistoricalRateRecordDto(record.time, record.high, record.low, record.open, record.volumeFrom,
                record.volumeTo, record.close, record.conversionType, record.conversionSymbol);
    }
}
