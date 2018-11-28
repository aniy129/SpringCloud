package springcloud.configclient.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope //只有加了此注解的地方才能刷新
public class HomeController {
    @Value("${foo}")
    String foo;

    @RequestMapping(value = "/hi")
    public String index() {
        return foo;
    }
}
