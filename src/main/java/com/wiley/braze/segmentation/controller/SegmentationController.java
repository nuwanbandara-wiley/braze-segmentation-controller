package com.wiley.braze.segmentation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.wiley.braze.segmentation.SegmentationApplication;
import com.wiley.braze.segmentation.model.Event;
import com.wiley.braze.segmentation.model.EventResponse;
import com.wiley.braze.segmentation.service.EventService;
import com.wiley.braze.segmentation.service.ServerEvents;

@CrossOrigin
@RestController
@RequestMapping(SegmentationApplication.BASE_URL + SegmentationController.URL)
public class SegmentationController {
    public static final String URL = "segmentation-controller";
    private final EventService eventService;
    private final ServerEvents serverEvents;

    @Autowired
    public SegmentationController(EventService eventService, ServerEvents serverEvents) {
        this.eventService = eventService;
        this.serverEvents = serverEvents;
    }

    @PostMapping(name = "/")
    public EventResponse index(@RequestBody Event event) {
        eventService.add(event);
        return EventResponse.builder()
                .eventName(event.getEventName())
                .triggerEvent(true)
                .build();
    }

    @RequestMapping(value = "/subscribe", consumes = MediaType.ALL_VALUE)
    public SseEmitter subscribe(@RequestParam String sessionId) {
        return this.serverEvents.subscribe(sessionId);
    }

    @PostMapping("/dispatch")
    public void dispatchEvent(@RequestParam String sessionId) {
        this.serverEvents.dispatch(sessionId, new EventResponse());
    }
}
