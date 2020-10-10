package com.eren.rabbitmq.luban.utils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class ConnectionUtils {
    public static final String RABBITMQ_HOST = "localhost";
    public static final int RABBITMQ_PORT = 5672;
    public static final String RABBITMQ_USERNAME = "eren";
    public static final String RABBITMQ_PASSWORD = "eren";
    public static final String RABBITMQ_VIRTUALHOST = "testhost";
    public static final String QUEUE_NAME = "testQueue";
    public static final String EXCHANGE_NAME = "exchange";

    public static Connection getConnection() throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(RABBITMQ_HOST);
        connectionFactory.setPort(RABBITMQ_PORT);
        connectionFactory.setUsername(RABBITMQ_USERNAME);
        connectionFactory.setPassword(RABBITMQ_PASSWORD);
        connectionFactory.setVirtualHost("/");
        return connectionFactory.newConnection();
    }
}
