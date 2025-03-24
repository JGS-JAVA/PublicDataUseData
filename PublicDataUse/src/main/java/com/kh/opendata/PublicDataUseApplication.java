package com.kh.opendata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "com.kh.opendata.restcontroller")
@SpringBootApplication
public class PublicDataUseApplication {

	public static void main(String[] args) {
		SpringApplication.run(PublicDataUseApplication.class, args);
	}

}
