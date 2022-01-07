package com.example.crytocompare;

import com.example.crytocompare.crypto.controller.CurrentRateController;
import com.example.crytocompare.crypto.controller.HistoricalRateController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CrytocompareApplicationTests {
	@Autowired
	CurrentRateController currentRateController;

	@Autowired
	HistoricalRateController historicalRateController;

	@Test
	void contextLoads() {
		assertThat(currentRateController).isNotNull();
		assertThat(historicalRateController).isNotNull();
	}

}
