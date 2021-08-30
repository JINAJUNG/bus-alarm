package com.example.busalarm.schedule;

import com.example.busalarm.schedule.event.BusAlarmEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
public class AlarmSchedule {

    private static final ObjectMapper om = new ObjectMapper();
    private final Scheduler scheduler;

    public AlarmSchedule(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    @EventListener
    public void handler(BusAlarmEvent busAlarmEvent) throws SchedulerException {
        log.info("스케쥴 등록 start");
        JobDetail jobDetail = getJobDetail(busAlarmEvent);
        Trigger jobTrigger = getJobTrigger(jobDetail, busAlarmEvent.getCron());
        scheduler.scheduleJob(jobDetail, jobTrigger);
        scheduler.start();
        log.info("스케쥴 등록 finish");
    }

    private JobDetail getJobDetail(BusAlarmEvent busAlarmEvent) {
        Map map = om.convertValue(busAlarmEvent.getBusStation(), Map.class);
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.putAll(map);
        jobDataMap.put("bus",busAlarmEvent.getBus());
        return JobBuilder.newJob(BusStopJob.class)
                .storeDurably()
                .usingJobData(jobDataMap)
                .build();
    }

    private Trigger getJobTrigger(JobDetail jobDetail, String cron) {
        return TriggerBuilder.newTrigger()
                .forJob(jobDetail)
                .withSchedule(CronScheduleBuilder.cronSchedule(cron))
                .build();
    }

}
