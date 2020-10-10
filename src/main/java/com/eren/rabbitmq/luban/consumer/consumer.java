package com.eren.rabbitmq.luban.consumer;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.eren.rabbitmq.luban.utils.ConnectionUtils;
import com.rabbitmq.client.*;

public class consumer {
    public static void main(String[] args) throws IOException, TimeoutException {
        getMessage();
    }

    public static void getMessage() throws IOException, TimeoutException {
        Connection connection = ConnectionUtils.getConnection();
        Channel channel = connection.createChannel();

        DefaultConsumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
                                       byte[] body) throws IOException {
                // 消息id。手动回复通过id得到
                long id = envelope.getDeliveryTag();
                System.out.println(new String(body, "UTF-8"));
            }

        };
        channel.queueDeclare(ConnectionUtils.QUEUE_NAME, true, false, false, null);

        // 监听队列
        // autoAck 自动回复 设置为false的话要手动编程回复
        channel.basicConsume(ConnectionUtils.QUEUE_NAME, true, consumer);

    }
}
