package com.example.busalarm.stop;

import com.example.busalarm.station.BusStation;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class BusStopRes implements Serializable {
    private String comMsgHeader;
    private Map<String, Object> header;
    private List<Bus> busList;

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
    public List<Bus> getBusList() {
        return busList;
    }

    public void setBusList(List<Bus> busList) {
        this.busList = busList;
    }
}
