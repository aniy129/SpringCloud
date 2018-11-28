package springcloud.springboot.service;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "queue.1")
public class RabbitmqReceiverForQueue1 {
    @RabbitHandler
    public void process(String hello) {
        System.out.println("queue.1 receiver:" + hello);
    }
}
