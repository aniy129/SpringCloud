package springcloud.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import springcloud.springboot.entity.UserInfo;
import springcloud.springboot.service.MongodbService;

import java.util.List;

@Controller
public class MongodbController {
    @Autowired
    MongodbService mongodbService;

    @RequestMapping("/save")
    @ResponseBody
    public String save() {
        boolean save = mongodbService.save();
        return String.valueOf(save);
    }

    @RequestMapping("/mongo")
    @ResponseBody
    public String mongo() {
        List<UserInfo> save = mongodbService.getAll();
        return String.valueOf(save);
    }
    @RequestMapping("/search")
    @ResponseBody
    public String search() {
        List<UserInfo> save = mongodbService.search();
        return String.valueOf(save);
    }
}
