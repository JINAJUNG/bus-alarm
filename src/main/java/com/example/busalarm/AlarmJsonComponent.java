package com.example.busalarm;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Slf4j
@Component
public class AlarmJsonComponent {
    private List<AlarmForm> alarmConfigs;

    public List<AlarmForm> getAlarmConfigs() {
        return alarmConfigs;
    }

    @PostConstruct
    public void init() throws IOException {
        readJson();
    }

    private void readJson() throws IOException {
        ObjectMapper om = new ObjectMapper();
        ClassPathResource resource = new ClassPathResource("busScheduleInfo.json");
        try (InputStream is = resource.getInputStream()) {
            CollectionType collectionType = om.getTypeFactory()
                    .constructCollectionType(List.class, AlarmForm.class);
            alarmConfigs = om.readValue(is, collectionType);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }
}
