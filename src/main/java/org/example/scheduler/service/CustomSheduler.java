package org.example.scheduler.service;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Slf4j
@Component
public class CustomSheduler {

    private final SchedulerService schedulerService;

    @Autowired
    public CustomSheduler(SchedulerService schedulerService) {
        this.schedulerService = schedulerService;
    }
    @PostConstruct
    public void init(){
        log.debug("customSchedulerStart");
        Thread schedulerThread  = new Thread(()-> {
            while (true){
                try {
                    schedulerService.generateTreatmentTasks();
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
        }

        });
        schedulerThread.start();

    }
}
