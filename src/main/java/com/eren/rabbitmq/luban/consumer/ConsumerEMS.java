package com.eren.rabbitmq.luban.consumer;

import com.eren.rabbitmq.luban.utils.ConnectionUtils;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class ConsumerEMS {
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(ConnectionUtils.QUEUE_NAME2, true, false, false, null);
        channel.exchangeDeclare(ConnectionUtils.EXCHANGE_NAME, BuiltinExchangeType.FANOUT);
        channel.queueBind(ConnectionUtils.QUEUE_NAME2, ConnectionUtils.EXCHANGE_NAME, "");
        DefaultConsumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
                                       byte[] body) throws IOException {
                // 消息id。手动回复通过id得到
                long id = envelope.getDeliveryTag();
                System.out.println(new String(body, "UTF-8"));
            }

        };


        // 监听队列
        // autoAck 自动回复 设置为false的话要手动编程回复
        channel.basicConsume(ConnectionUtils.QUEUE_NAME2, true, consumer);
    }
    }

