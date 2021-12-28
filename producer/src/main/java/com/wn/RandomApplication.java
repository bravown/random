package com.wn;


@EnableRabbit
@SpringBootApplication
@EnableTransactionManagement
@MapperScan("com.wn.mapper")
public class RandomApplication {

	public static void main(String[] args) {
		SpringApplication.run(RandomApplication.class, args);
	}

}
