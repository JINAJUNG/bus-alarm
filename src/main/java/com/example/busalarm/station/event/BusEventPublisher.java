package com.example.busalarm.station.event;

import com.example.busalarm.AlarmForm;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class BusEventPublisher {
    private final ApplicationEventPublisher publisher;

    public BusEventPublisher(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    public void stationEvent(AlarmForm alarmForm) {
        publisher.publishEvent(new BusStationEvent(alarmForm));
    }
}
