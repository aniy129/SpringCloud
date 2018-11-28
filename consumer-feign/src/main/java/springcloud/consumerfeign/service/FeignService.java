package springcloud.consumerfeign.service;

import org.springframework.stereotype.Component;

@Component
public class FeignService implements IHelloService {
    @Override
    public String sayHiFromClientOne(String name) {
        return "sorry "+name;
    }
}
