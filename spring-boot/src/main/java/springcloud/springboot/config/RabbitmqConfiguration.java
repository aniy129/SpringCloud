package springcloud.springboot.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitmqConfiguration {
    @Bean
    public Queue Queue() {
        return new Queue("hello");
    }

    @Bean
    public Queue queue1(){
        return new Queue("queue.1");
    }

    @Bean
    public Queue queue2(){
        return new Queue("queue.2");
    }

    @Bean
    public TopicExchange  exchange(){
        return new TopicExchange("exchange");
    }

    @Bean
    public Binding bindingExchange1(Queue queue1, TopicExchange exchange){
        return BindingBuilder.bind(queue1).to(exchange).with("queue.1");
    }

    @Bean
     Binding bindingExchange2(Queue queue2, TopicExchange  exchange){
        return BindingBuilder.bind(queue2).to(exchange).with("queue.#");
    }

}
