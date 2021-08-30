package com.example.busalarm.station;

import com.example.busalarm.AlarmForm;
import com.example.busalarm.schedule.event.AlarmEventPublisher;
import com.example.busalarm.station.event.BusStationEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class BusStationApiService {
    @Value("${service.key}")
    private String serviceKey;
    private final WebClient webClient;
    private final AlarmEventPublisher alarmEventPublisher;
    private static final XmlMapper xmlMapper = new XmlMapper();

    public BusStationApiService(WebClient webClient, AlarmEventPublisher alarmEventPublisher) {
        this.webClient = webClient;
        this.alarmEventPublisher = alarmEventPublisher;
    }

    @Async
    @EventListener
    public void handler(BusStationEvent event) {
        AlarmForm alarmForm = event.getAlarmForm();
        Flux<String> keyword = webClient.get()
                .uri(uriBuilder ->
                        uriBuilder.path("/busstationservice/getBusStationList")
                                .queryParam("keyword", alarmForm.getStationName())
                                .queryParam("serviceKey", serviceKey)
                                .build()
                ).retrieve()
                .bodyToFlux(String.class);
        keyword.subscribe(s -> {
            try {
                BusStationRes busStationRes = xmlMapper.readValue(s, BusStationRes.class);
                BusStation busStation = busStationRes.getBusStations().stream().filter(x ->
                        x.getMobileNo().equals(alarmForm.getMobileNo()) &&
                                x.getStationName().equals(alarmForm.getStationName()))
                        .findFirst()
                        .orElseThrow(() -> new RuntimeException(String.format("not found station. %s, %s",
                                alarmForm.getStationName(), alarmForm.getMobileNo())));

                stationVisitBus(alarmForm, busStation);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void stationVisitBus(AlarmForm alarmForm, BusStation busStation) {
        Mono<String> mono = webClient.get()
                .uri(uriBuilder ->
                        uriBuilder.path("/busstationservice/getBusStationViaRouteList")
                                .queryParam("stationId", busStation.getStationId())
                                .queryParam("serviceKey", serviceKey)
                                .build()
                ).retrieve()
                .bodyToMono(String.class);
        mono.subscribe(x -> {
            try {
                VisitBusRes visitBusRes = xmlMapper.readValue(x, VisitBusRes.class);
                List<VisitBus> collect = visitBusRes.getVisitBuses().stream()
                        .filter(z -> alarmForm.getBus().stream().anyMatch(y -> y.equals(z.getRouteName())))
                        .collect(Collectors.toList());
                alarmEventPublisher.busAlarmEvent(alarmForm.getCron(), busStation, collect);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        });
    }
}
