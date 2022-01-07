package com.example.crytocompare.service;


import com.example.crytocompare.crypto.dao.HistoricalRateRecordRepository;
import com.example.crytocompare.crypto.dto.HistoricalRateRecordDto;
import com.example.crytocompare.crypto.model.CryptoHistoricalResponse;
import com.example.crytocompare.crypto.model.CryptoHistoricalResponseData;
import com.example.crytocompare.crypto.service.impl.HistoricalRateUpdateService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;

//not working yet - we need to change the autowiring from field to constructor so for test to feed a mocked object
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = HistoricalRateUpdateService.class)
@EnableAutoConfiguration(exclude = {org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration.class})
@TestPropertySource(properties = "scheduler.enabled=false")
public class HistoricalRateUpdateServiceTest {

    @Mock
    HistoricalRateRecordRepository repository;

    @Autowired
    private HistoricalRateUpdateService updateService;

    @Autowired
    private RestTemplate restTemplate;

    private MockRestServiceServer mockServer;
    private final ObjectMapper mapper = new ObjectMapper();

    @Before
    public void init() {
        mockServer = MockRestServiceServer.createServer(restTemplate);
    }

    @Test
    public void shouldProcessCryptoResponseAndSaveUpdateDB() throws URISyntaxException, JsonProcessingException, InterruptedException {
        HistoricalRateRecordDto dto = new HistoricalRateRecordDto(1630022400L, 49166.31, 46376.81,
                46852.22, 27872.76, 1331637984.84, 49088.1, "direct",
                "");
        List<HistoricalRateRecordDto> dtos= new ArrayList<>();
        dtos.add(dto);
        CryptoHistoricalResponseData data = new CryptoHistoricalResponseData(false, 1631059200L,
                1639699200L, dtos);
        CryptoHistoricalResponse response = new CryptoHistoricalResponse("Success", "",
                false, 100, data);
        mockServer.expect(ExpectedCount.once(),
                        requestTo(new URI("https://min-api.cryptocompare.com/data/v2/histoday?fsym=BTC&tsym=USD&limit=100")))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(mapper.writeValueAsString(response))
                );

        doNothing().when(repository).save(HistoricalRateRecordDto.toEntity(dto));

        updateService.updateHistoricalRates();
    }
}
