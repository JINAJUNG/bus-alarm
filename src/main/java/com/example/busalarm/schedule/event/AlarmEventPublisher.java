package com.example.busalarm.schedule.event;

import com.example.busalarm.station.BusStation;
import com.example.busalarm.station.VisitBus;
import com.example.busalarm.station.VisitBusRes;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AlarmEventPublisher {
    private final ApplicationEventPublisher publisher;

    public AlarmEventPublisher(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    public void busAlarmEvent(String cron, BusStation busStation, List<VisitBus> visitBus) {
        publisher.publishEvent(new BusAlarmEvent(cron, busStation, visitBus));
    }
}
