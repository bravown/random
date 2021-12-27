//package com.wn.config;
//
//import org.springframework.amqp.core.Binding;
//import org.springframework.amqp.core.BindingBuilder;
//import org.springframework.amqp.core.DirectExchange;
//import org.springframework.amqp.core.Queue;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.stereotype.Component;
//
///**
// * rabbitmq统一配置
// */
//@Component
//public class RabbitQueueConfig {
//
//    @Autowired
//    private DirectExchange commonExchange;
//
//    /**
//     * 定义队列
//     */
//    @Bean
//    public Queue queue() {
//        return new Queue("ForeverQueue", true);
//    }
//
//    /**
//     * 将交换机、路由规则、队列进行绑定
//     */
//    @Bean
//    public Binding BindingQueue(Queue queue) {
//        return BindingBuilder.bind(queue)
//                .to(commonExchange)
//                .with("1993");
//    }
//}
