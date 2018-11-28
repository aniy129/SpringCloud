package springcloud.consumerfeign.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springcloud.consumerfeign.service.IHelloService;

@RestController
public class HomeController {
    @Autowired
    private IHelloService helloService;

    @GetMapping(value = "/hi")
    public String hi(@RequestParam String name) {
        return helloService.sayHiFromClientOne(name);
    }
}
