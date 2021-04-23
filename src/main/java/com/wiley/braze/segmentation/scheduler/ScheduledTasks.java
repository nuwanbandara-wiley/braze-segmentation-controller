package com.wiley.braze.segmentation.scheduler;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.wiley.braze.segmentation.model.Event;
import com.wiley.braze.segmentation.model.EventResponse;
import com.wiley.braze.segmentation.service.EventService;
import com.wiley.braze.segmentation.service.ServerEvents;

@Component
public class ScheduledTasks {
    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    private EventService eventService;
    private ServerEvents serverEvents;

    public ScheduledTasks(EventService eventService, ServerEvents serverEvents) {
        this.eventService = eventService;
        this.serverEvents = serverEvents;
    }

    @Scheduled(fixedRate = 1000)
    public void readQueue() {
        log.info("Reading queue now at {}", dateFormat.format(new Date()));
        List<Event> eventList = this.eventService.getAll();
        eventList.forEach(event -> {
            this.serverEvents.dispatch(event.getSessionId(), EventResponse.builder()
                    .eventName(event.getEventName())
                    .triggerEvent(true)
                    .build());
            this.eventService.delete(event.getId());
            log.info("deleted {}", event.getId());
        });
    }
}
