package com.hedzic.ajdin.endorsementtracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class EndorsementTrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(EndorsementTrackerApplication.class, args);
	}

}

