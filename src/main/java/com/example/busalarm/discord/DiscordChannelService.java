package com.example.busalarm.discord;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DiscordChannelService {
    private WebClient webClient;
    private String contentSample = "%s 정류소에 %s번 버스 %s개 전, %s분 남았습니다.";
    @Value("${discord.url}")
    private String url;

    @PostConstruct
    public void init() {
        webClient = WebClient.create(url);
    }

    public void sendMessage(String station, String busNumber,
                            Integer busLocation, Integer beforeArrival,
                            Integer nextBusLocation, Integer nextBeforeArrival) {
        String content = getContent(station, busNumber, busLocation, beforeArrival, nextBusLocation, nextBeforeArrival);
        List<Map<String, Object>> embeds = new ArrayList<>();
        embeds.add(getField(busLocation, beforeArrival));
        DiscordWebhook body = new DiscordWebhook(content);
        body.setEmbeds(embeds);

        webClient.post()
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(body), DiscordWebhook.class)
                .retrieve()
                .bodyToMono(Void.class)
                .subscribe();
    }

    private String getContent(String station, String busNumber, Integer busLocation, Integer beforeArrival, Integer nextBusLocation, Integer nextBeforeArrival) {
        String content = String.format
                (contentSample, station, busNumber, busLocation, beforeArrival);
        if (nextBeforeArrival != null || nextBusLocation != null) {
            content = content + String.format(" 다음 버스는 %s개 전, %s분 남았습니다.", nextBusLocation, nextBeforeArrival);
        } else {
            content = content + " 다음 버스는 도착 정보가 없습니다.";
        }
        return content;
    }

    private Map<String, Object> getField(Integer location, Integer beforeArrival) {
        Map<String, Object> field = new HashMap<>();
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> m = new HashMap<>();
        m.put("name", "도착예정시간");
        m.put("value", location);
        Map<String, Object> m2 = new HashMap<>();
        m2.put("name", "남은 정류소");
        m2.put("value", beforeArrival);
        list.add(m);
        list.add(m2);
        field.put("fields",list);
        return field;
    }
}
