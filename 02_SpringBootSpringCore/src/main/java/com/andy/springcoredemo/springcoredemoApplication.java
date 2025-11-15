package com.andy.springcoredemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// @SpringBootApplication(
// 	scanBasePackages={
// 		"com.andy.springcoredemo",
// 		"com.andy.util"
// 	}
// )
@SpringBootApplication
public class springcoredemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(springcoredemoApplication.class, args);
	}

}
