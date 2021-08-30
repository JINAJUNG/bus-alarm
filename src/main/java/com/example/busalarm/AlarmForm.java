package com.example.busalarm;

import java.util.List;

public class AlarmForm {
    private String stationName;
    private Integer mobileNo;
    private String cron;
    private List<String> bus;

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public Integer getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(Integer mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getCron() {
        return cron;
    }

    public void setCron(String cron) {
        this.cron = cron;
    }

    public List<String> getBus() {
        return bus;
    }

    public void setBus(List<String> bus) {
        this.bus = bus;
    }
}
