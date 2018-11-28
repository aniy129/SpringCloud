package springcloud.springboot.controller;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.Banner;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.WebAsyncTask;
import springcloud.springboot.dao.UserInfoDao;
import springcloud.springboot.entity.UserInfo;
import springcloud.springboot.helper.UiMethod;
import springcloud.springboot.service.UserInfoService;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Callable;

@Controller
public class HomeController implements ApplicationContextAware {
    @RequestMapping("/index")
    @ResponseBody
    public String index() {
        return "hello world";
    }

    @Value("${my.val}")
    String val;

    @Autowired
    UserInfoService userInfoService;

    @RequestMapping("/home")
    public String home(Model model) {
        List<UserInfo> userInfo = userInfoService.getAll();
        model.addAttribute("userInfo", userInfo);
        model.addAttribute("m", new UiMethod());
        model.addAttribute("ser", userInfoService);
        return "home";
    }

    @Cacheable(value = "user-key")
    @RequestMapping("/all")
    @ResponseBody
    public String all() {
        List<UserInfo> userInfo = userInfoService.getAll();
        return String.valueOf(userInfo);
    }

    @RequestMapping("/add")
    @ResponseBody
    public String add() {
        UserInfo userInfo = new UserInfo();
        userInfo.setName("小红");
        userInfo.setAge(17);
        userInfo.setBirthDay(new Timestamp(new Date().getTime()));
        userInfoService.add(userInfo);
        return "ok";
    }

    @Autowired
    RedisTemplate redisTemplate;

    @RequestMapping("/redis")
    @ResponseBody
    public String redis() {
        ValueOperations<String, String> operations = redisTemplate.opsForValue();
        String val = operations.get("myKey");
        if (val == null) {
            val = "yes";
            operations.set("myKey", val);
        }
        return val;
    }

    @Autowired
    @Qualifier("taskExecutor")
    private ThreadPoolTaskExecutor executor;

    //基于线程池的异步执行方法
    @RequestMapping("/async")
    @ResponseBody
    public WebAsyncTask<String> async(String name) {
        return new WebAsyncTask<>(10 * 1000L, executor,
                () -> name + "1000");
    }

    private ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }

}
