package ar.com.ada.api.cursos.scheduler;

import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Scheduler {

    @Scheduled(cron = "0 0/1 * 1/1 * ?")
    public void cronJobSch() throws Exception {
        Date date = new Date();
        System.out.println("Funciona " + date);
    }
}