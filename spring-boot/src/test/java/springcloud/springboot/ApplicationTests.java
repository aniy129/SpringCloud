package springcloud.springboot;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import springcloud.springboot.controller.HomeController;
import springcloud.springboot.service.RabbitmqSenderService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

    private MockMvc mvc;

    @Before
    public void setUp() {
        mvc = MockMvcBuilders.standaloneSetup(new HomeController()).build();
    }

    @Test
    public void getHello() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/index").accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Autowired
    RabbitmqSenderService rabbitmqSenderService;

    @Test
    public void send() {
        rabbitmqSenderService.send();
    }

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void send1() {
        for (int i=0;i<1000;i++){
            rabbitTemplate.convertAndSend("exchange", "queue.1", "send1:this is a sample msg num."+i);
        }
    }

    @Test
    public void send2() {
        rabbitTemplate.convertAndSend("exchange", "queue.2", "send2:this is a sample msg;");
    }

    @Autowired
    TemplateEngine templateEngine;
    @Test
    public void templateTest(){
        Context context = new Context();
        context.setVariable("text", "我的模板解析器");
        String text = templateEngine.process("tem", context);
        System.out.println(text);
    }
}
