package com.wn;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author 王宁 2021/12/27
 */
@EnableRabbit
@SpringBootApplication
@EnableTransactionManagement
@MapperScan("com.wn.mapper")
public class ConsumerAppliaction {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerAppliaction.class, args);
    }

}

