package com.wiley.braze.segmentation.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wiley.braze.segmentation.model.Event;
import com.wiley.braze.segmentation.model.EventDAO;
import com.wiley.braze.segmentation.repository.EventRepository;

@Service
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;

    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public void add(Event event) {
        EventDAO eventDAO = EventDAO.builder()
                .eventName(event.getEventName())
                .payload(event.getPayload())
                .sessionId(event.getSessionId())
                .build();
        eventRepository.save(eventDAO);
    }

    @Override
    public List<Event> getAll() {
        List<Event> events = new ArrayList<>();
        this.eventRepository.findAll().forEach(event -> events.add(Event.builder()
                .id(event.getId())
                .sessionId(event.getSessionId())
                .eventName(event.getEventName())
                .payload(event.getPayload())
                .build()));

        return events;
    }

    @Override
    public void delete(UUID id) {
        this.eventRepository.deleteById(id);
    }


}
