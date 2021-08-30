package com.example.busalarm.station.event;

import com.example.busalarm.AlarmForm;

public class BusStationEvent {
    private AlarmForm alarmForm;

    public BusStationEvent(AlarmForm alarmForm) {
        this.alarmForm = alarmForm;
    }

    public AlarmForm getAlarmForm() {
        return alarmForm;
    }

    public void setAlarmForm(AlarmForm alarmForm) {
        this.alarmForm = alarmForm;
    }
}
