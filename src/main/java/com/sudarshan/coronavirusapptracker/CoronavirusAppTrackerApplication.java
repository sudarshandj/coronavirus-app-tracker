package com.sudarshan.coronavirusapptracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CoronavirusAppTrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoronavirusAppTrackerApplication.class, args);
	}

}
