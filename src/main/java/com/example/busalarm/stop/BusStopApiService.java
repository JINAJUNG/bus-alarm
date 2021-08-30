package com.example.busalarm.stop;

import com.example.busalarm.discord.DiscordChannelService;
import com.example.busalarm.station.VisitBus;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class BusStopApiService {
    @Value("${service.key}")
    private String serviceKey;
    private final WebClient webClient;
    @Autowired
    private DiscordChannelService discordChannelService;

    public BusStopApiService(WebClient webClient) {
        this.webClient = webClient;
    }

    public void getBusStopList(Integer stationId, String stationName, List<VisitBus> bus) {
        log.info("in get bus stop list");
        Flux<String> keyword = webClient.get()
                .uri(uriBuilder ->
                        uriBuilder.path("/busarrivalservice/getBusArrivalList")
                                .queryParam("stationId", stationId)
                                .queryParam("serviceKey", serviceKey)
                                .build()
                ).retrieve()
                .bodyToFlux(String.class);
        keyword.subscribe(s -> {
            try {
                XmlMapper xmlMapper = new XmlMapper();
                BusStopRes busStopRes = xmlMapper.readValue(s, BusStopRes.class);
                List<Bus> collect = busStopRes.getBusList().stream()
                        .peek(x -> bus.stream()
                                .filter(y -> x.getRouteId().equals(y.getRouteId()))
                                .findAny()
                                .ifPresent(y -> {
                                    x.setBusName(y.getRouteName());
                                    x.setBusStationName(stationName);
                                }))
                        .filter(x -> x.getBusName() != null)
                        .collect(Collectors.toList());
                System.out.println(busStopRes);
                System.out.println(collect);

                for (Bus b : collect) {
                    discordChannelService.sendMessage(
                            b.getBusStationName(),
                            b.getBusName(),
                            b.getLocationNo1(),
                            b.getPredictTime1(),
                            b.getLocationNo2(),
                            b.getPredictTime2()
                    );
                }


                /*
                 * todo 버스 도착 정보 처리.
                 *  */
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
