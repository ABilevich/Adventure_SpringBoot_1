package com.andy.sprigcoredemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
	scanBasePackages={
		"com.andy.sprigcoredemo",
		"com.andy.util"
	}
)
public class SprigcoredemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SprigcoredemoApplication.class, args);
	}

}
