//package com.wn.mqlistener;
//
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.stereotype.Component;
//
//@Component
//public class RabbitMQListener {
//    /**
//     * 处理通知
//     */
//    @RabbitListener(queues = "ForeverQueue")
//    public void purchaseArgumentMqListener(String msg) {
//
//        System.out.println(msg);
//    }
//}
