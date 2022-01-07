package com.example.crytocompare;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.EnableScheduling;

@Slf4j
@SpringBootApplication
@EnableScheduling
@ConditionalOnProperty(name="scheduler.enabled", matchIfMissing = true)
public class CrytocompareApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrytocompareApplication.class, args);
	}

}
