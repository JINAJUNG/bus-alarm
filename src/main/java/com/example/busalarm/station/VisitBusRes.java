package com.example.busalarm.station;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.List;
import java.util.Map;

public class VisitBusRes {
    private String comMsgHeader;
    private Map<String, Object> header;
    private List<VisitBus> visitBuses;

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
    public List<VisitBus> getVisitBuses() {
        return visitBuses;
    }

    public void setVisitBuses(List<VisitBus> visitBuses) {
        this.visitBuses = visitBuses;
    }
}
