package com.qingcheng.code.common.example;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by liguohua on 16/10/1.
 */
@Component
@Configurable
@EnableScheduling
public class ScheduledTasks{
    private final   Logger logger = Logger.getLogger(this.getClass());
    //test data
    private SimpleDateFormat dateFormat(){
        return new SimpleDateFormat ("HH:mm:ss");
    }
    @Scheduled(fixedRate = 1000 * 30)
    public void reportCurrentTime(){
        logger.info("Scheduling Tasks Examples: The time is now " + dateFormat ().format (new Date()));
    }

    //每1分钟执行一次
    @Scheduled(cron = "0 */1 *  * * * ")
    public void reportCurrentByCron(){
        logger.info("Scheduling Tasks Examples By Cron: The time is now " + dateFormat ().format (new Date ()));
    }


}