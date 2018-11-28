package springcloud.springboot.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ScheduleService {
    @Scheduled(fixedDelay = 100000)
    public void schedule() {
        System.out.println("定时任务:" + new Date());
    }
}
