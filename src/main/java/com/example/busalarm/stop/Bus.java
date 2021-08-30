package com.example.busalarm.stop;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Bus {
    private Integer routeId;
    private Integer locationNo1;
    private Integer predictTime1;
    private Integer locationNo2;
    private Integer predictTime2;

    private String busName;
    private String busStationName;

    public Integer getRouteId() {
        return routeId;
    }

    public void setRouteId(Integer routeId) {
        this.routeId = routeId;
    }

    public Integer getLocationNo1() {
        return locationNo1;
    }

    public void setLocationNo1(Integer locationNo1) {
        this.locationNo1 = locationNo1;
    }

    public Integer getPredictTime1() {
        return predictTime1;
    }

    public void setPredictTime1(Integer predictTime1) {
        this.predictTime1 = predictTime1;
    }

    public Integer getLocationNo2() {
        return locationNo2;
    }

    public void setLocationNo2(Integer locationNo2) {
        this.locationNo2 = locationNo2;
    }

    public Integer getPredictTime2() {
        return predictTime2;
    }

    public void setPredictTime2(Integer predictTime2) {
        this.predictTime2 = predictTime2;
    }

    public String getBusName() {
        return busName;
    }

    public void setBusName(String busName) {
        this.busName = busName;
    }

    public String getBusStationName() {
        return busStationName;
    }

    public void setBusStationName(String busStationName) {
        this.busStationName = busStationName;
    }
}
