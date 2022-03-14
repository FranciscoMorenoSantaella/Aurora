package com.santaellamorenofrancisco ;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.santaellamorenofrancisco.model")
public class AuroraApp {

	public static void main(String[] args) {
		SpringApplication.run(AuroraApp.class, args);
	}

}
