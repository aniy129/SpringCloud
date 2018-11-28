package springcloud.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MsgController {
    @ResponseBody
    @RequestMapping("/msg")
    public  String index(){
        return  null;
    }
}
