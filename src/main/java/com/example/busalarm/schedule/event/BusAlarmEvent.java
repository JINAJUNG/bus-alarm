package com.example.busalarm.schedule.event;

import com.example.busalarm.station.BusStation;
import com.example.busalarm.station.VisitBus;

import java.util.List;

public class BusAlarmEvent {
    private String cron;
    private BusStation busStation;
    private List<VisitBus> bus;

    public BusAlarmEvent(String cron, BusStation busStation, List<VisitBus> bus) {
        this.cron = cron;
        this.busStation = busStation;
        this.bus = bus;
    }

    public String getCron() {
        return cron;
    }

    public void setCron(String cron) {
        this.cron = cron;
    }

    public BusStation getBusStation() {
        return busStation;
    }

    public void setBusStation(BusStation busStation) {
        this.busStation = busStation;
    }

    public List<VisitBus> getBus() {
        return bus;
    }

    public void setBus(List<VisitBus> bus) {
        this.bus = bus;
    }
}
