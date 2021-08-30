package com.example.busalarm.station.event;

import com.example.busalarm.AlarmForm;
import com.example.busalarm.AlarmJsonComponent;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class BusStationEventRunner implements ApplicationRunner {
    private final BusEventPublisher publisher;
    private final AlarmJsonComponent jsonComponent;

    public BusStationEventRunner(BusEventPublisher publisher, AlarmJsonComponent jsonReader) {
        this.publisher = publisher;
        this.jsonComponent = jsonReader;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        for (AlarmForm form : jsonComponent.getAlarmConfigs()) {
            publisher.stationEvent(form);
        }
    }
}
