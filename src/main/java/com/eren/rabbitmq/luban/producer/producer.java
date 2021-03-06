package com.eren.rabbitmq.luban.producer;

import com.eren.rabbitmq.luban.utils.ConnectionUtils;
import com.rabbitmq.client.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

public class producer {
    public static void main(String[] args) throws Exception {

        // 创建连接
        Connection connection = ConnectionUtils.getConnection();

        Channel channel = connection.createChannel();
        // queue 队列名称
        // durable: 是否持久化队列
        // exclusive： 是否独占连接，队列只允许在该连接中访问。如果connection连接关闭则自动删除,如果将此参数设置为true可以用于临时队列的创建
        // autoDelete:自动删除，队列不在使用时是否删除队列。如果此参数和exclusive设置为true的话可以设置临时队列
        channel.queueDeclare(ConnectionUtils.QUEUE_NAME, true, false, false, null);

        // exchange: 交换机。如果设置为空的话使用默认交换机
        // routingKey: 路由key。如果是默认交换机的话。key必须设置为队列名称
        // props:消息的属性
        // body:消息的内容
        channel.basicPublish("", ConnectionUtils.QUEUE_NAME, null, "hello21".getBytes());
        channel.close();
        connection.close();

    }
}
