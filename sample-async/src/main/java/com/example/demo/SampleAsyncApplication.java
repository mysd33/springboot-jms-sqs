package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

//JMSListnerの有効化
@EnableJms
@SpringBootApplication
public class SampleAsyncApplication {

	public static void main(String[] args) {
		SpringApplication.run(SampleAsyncApplication.class, args);
	}

}
