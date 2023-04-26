package com.wn.util;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @ClassName RabbitMqUtil
 * @Author ZC
 * @Date 2022/7/2 21:21
 * @Version 1.0
 * @Description
 */
public class RabbitMqUtil {
    private final String host = "150.158.40.126";
    private final int port = 5672;
    private final String username = "guest";
    private final String password = "guest";
    private final String virtualHost = "";

    /**
     * 连接RabbitMq
     *
     * @return
     * @throws IOException
     * @throws TimeoutException
     */
    public Connection getConnection() throws IOException, TimeoutException {
        // 创建连接工厂对象
        ConnectionFactory factory = new ConnectionFactory();
        // 设置连接的主机的参数
        factory.setPort(5672);
        factory.setHost("150.158.40.126");
        factory.setUsername("guest");
        factory.setPassword("guest");
        Connection connection = factory.newConnection();
        return connection;

    }
}
