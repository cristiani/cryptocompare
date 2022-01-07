package com.example.crytocompare.controller;

import com.example.crytocompare.crypto.controller.CurrentRateController;
import com.example.crytocompare.crypto.controller.HistoricalRateController;
import com.example.crytocompare.crypto.dto.HistoricalRateRecordDto;
import com.example.crytocompare.crypto.service.HistoricalRateService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(HistoricalRateController.class)
@EnableAutoConfiguration(exclude = {org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration.class})
public class HistoricalRateControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HistoricalRateService historicalRateService;

    @Test
    public void shouldReturnHistoricalRateListFromService() throws Exception {
        HistoricalRateRecordDto dto1 = new HistoricalRateRecordDto(1630022400L, 49166.31, 46376.81,
                46852.22, 27872.76, 1331637984.84, 49088.1, "direct",
                "");
        HistoricalRateRecordDto dto2 = new HistoricalRateRecordDto(1630108800L, 49306.47, 48386.9,
                49088.1, 12815.36, 626553869.3, 48918.9, "direct",
                "");
        HistoricalRateRecordDto dto3 = new HistoricalRateRecordDto(1630195200L, 49653.49, 47841.49,
                48918.9, 18983.79, 924625515.76, 48794.26, "direct",
                "");
        List<HistoricalRateRecordDto> dtos = new ArrayList<>();
        dtos.add(dto2);
        dtos.add(dto1);
        dtos.add(dto3);

        when(historicalRateService.getHistoricalRecordsSince(1630195200L)).thenReturn(dtos);
        this.mockMvc.perform(get("/api/historical_rate?time=1630195200"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith("application/json"))
                .andExpect(jsonPath("$.[0].time").value(1630108800))
                .andExpect(jsonPath("$.[1].time").value(1630022400))
                .andExpect(jsonPath("$.[2].time").value(1630195200));
    }
}
