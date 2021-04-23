package com.wiley.braze.segmentation.service;

import java.util.List;
import java.util.UUID;

import com.wiley.braze.segmentation.model.Event;

public interface EventService {
    void add(Event event);
    List<Event> getAll();
    void delete(UUID id);
}
