package com.example.busalarm.schedule;

import com.example.busalarm.station.VisitBus;
import com.example.busalarm.stop.BusStopApiService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class BusStopJob implements Job {
    private final BusStopApiService busStopApiService;

    public BusStopJob(BusStopApiService busStopApiService) {
        this.busStopApiService = busStopApiService;
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        log.info("잡 실행 => {}", jobExecutionContext.getJobDetail());
        JobDetail jobDetail = jobExecutionContext.getJobDetail();
        JobDataMap jobDataMap = jobDetail.getJobDataMap();
        List<VisitBus> bus = (List)jobDataMap.get("bus");
        busStopApiService.getBusStopList((Integer) jobDataMap.get("stationId"),
                (String)jobDataMap.get("stationName"),bus);
    }
}
