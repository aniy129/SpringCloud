package springcloud.springboot.service;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "hello")
public class RabbitmqReceiverService {

    @RabbitHandler
    public void process(String hello) {
        System.out.println("receiver:"+hello);
    }
}
