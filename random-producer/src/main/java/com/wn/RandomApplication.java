package com.wn;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class RandomApplication {

	public static void main(String[] args) {
		SpringApplication.run(RandomApplication.class, args);
	}

}
