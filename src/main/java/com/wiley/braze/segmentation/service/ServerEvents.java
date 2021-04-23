package com.wiley.braze.segmentation.service;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.wiley.braze.segmentation.model.EventResponse;

public interface ServerEvents {
    SseEmitter subscribe(String sessionId);

    void dispatch(String sessionId, EventResponse response);
}
