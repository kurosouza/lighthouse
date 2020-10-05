package com.cwinterative.lighthouse;

import com.cwinterative.utils.FileWriter;
import com.cwinterative.utils.StandardFileWriter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public FileWriter fileWriter() {
		return new StandardFileWriter();
	}

}
