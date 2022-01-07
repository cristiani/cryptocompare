package com.example.crytocompare.controller;


import com.example.crytocompare.crypto.controller.CurrentRateController;
import com.example.crytocompare.crypto.model.CryptoCurrentResponse;
import com.example.crytocompare.crypto.service.CurrentRateService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CurrentRateController.class)
@EnableAutoConfiguration(exclude = {org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration.class})
public class CurrentRateControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CurrentRateService currentRateService;

    @Test
    public void shouldReturnCurrentRateFromService() throws Exception {
        CryptoCurrentResponse response = new CryptoCurrentResponse(49023.51, 5553605.98, 43459.59);

        when(currentRateService.getCurrentRate()).thenReturn(response);
        this.mockMvc.perform(get("/api/current_rate"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith("application/json"))
                .andExpect(jsonPath("$.USD").value(49023.51))
                .andExpect(jsonPath("$.JPY").value(5553605.98))
                .andExpect(jsonPath("$.EUR").value(43459.59));
    }
}
