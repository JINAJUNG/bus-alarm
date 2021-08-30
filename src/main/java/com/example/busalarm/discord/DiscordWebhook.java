package com.example.busalarm.discord;

import lombok.Data;

import java.util.List;
import java.util.Map;

public class DiscordWebhook {
    private String content;
    private List<Map<String, Object>> embeds;

    public DiscordWebhook(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<Map<String, Object>> getEmbeds() {
        return embeds;
    }

    public void setEmbeds(List<Map<String, Object>> embeds) {
        this.embeds = embeds;
    }
}
