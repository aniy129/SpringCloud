package springcloud.springboot.service;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "queue.2")
public class RabbitmqReceiverForQueue2 {
    @RabbitHandler
    public void process(String hello) {
        System.out.println("queue.2 receiver:" + hello);
    }
}
