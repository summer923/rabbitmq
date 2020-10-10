package com.eren.rabbitmq;

import com.eren.rabbitmq.luban.utils.ConnectionUtils;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.annotation.Retention;

import static com.eren.rabbitmq.luban.test.RabbitmqConfig.EXCHANGE_TOPICS_INFORM;

@SpringBootTest
@RunWith(SpringRunner.class)
class RabbitmqApplicationTests {
    @Autowired
    RabbitTemplate rabbitTemplate;
    @Test
    public void  get(){
        rabbitTemplate.convertAndSend(EXCHANGE_TOPICS_INFORM,"inform.email" , "没什么");
    }


}
