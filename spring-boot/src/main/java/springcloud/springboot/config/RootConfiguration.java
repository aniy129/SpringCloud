package springcloud.springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class RootConfiguration {
    //不使用注解 手动加载过滤器
//    @Bean
//    public FilterRegistrationBean loginFilter(){
//        FilterRegistrationBean registration=new FilterRegistrationBean();
//        registration.setFilter(new MyFilter());
////        registration.setName("Login");
////        registration.setOrder(1);
////        registration.addUrlPatterns("/*");
//        return registration;
//    }
    @Bean("taskExecutor")
    public ThreadPoolTaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(5);
        taskExecutor.setMaxPoolSize(10);
        taskExecutor.setQueueCapacity(10);
        taskExecutor.setThreadNamePrefix("asyncTask");
        return taskExecutor;
    }

}
