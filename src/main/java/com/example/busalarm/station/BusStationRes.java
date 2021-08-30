package com.example.busalarm.station;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class BusStationRes implements Serializable {
    private String comMsgHeader;
    private Map<String, Object> header;
    private List<BusStation> busStations;

    public String getComMsgHeader() {
        return comMsgHeader;
    }

    public void setComMsgHeader(String comMsgHeader) {
        this.comMsgHeader = comMsgHeader;
    }

    @JacksonXmlProperty(localName = "msgHeader")
    public Map<String, Object> getHeader() {
        return header;
    }

    public void setHeader(Map<String, Object> header) {
        this.header = header;
    }

    @JacksonXmlProperty(localName = "msgBody")
    public List<BusStation> getBusStations() {
        return busStations;
    }

    public void setBusStations(List<BusStation> busStations) {
        this.busStations = busStations;
    }
}
