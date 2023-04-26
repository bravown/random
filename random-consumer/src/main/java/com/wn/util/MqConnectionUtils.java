package com.wn.util;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class MqConnectionUtils {
    public static Connection getConnection() throws IOException, TimeoutException {
        //定义一个链接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();
        //设置服务地址
        connectionFactory.setHost("150.158.40.126");
        //设定端口，注意，这里RabbitMQ的端口，不是管理页面的端口
        connectionFactory.setPort(5672);
        //设定用户名
        connectionFactory.setUsername("guest");
        //设定密码
        connectionFactory.setPassword("guest");
        //设定虚拟访问节点
        connectionFactory.setVirtualHost("/");
        return connectionFactory.newConnection();
    }
}