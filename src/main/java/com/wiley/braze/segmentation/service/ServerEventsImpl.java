package com.wiley.braze.segmentation.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.wiley.braze.segmentation.model.EventResponse;

@Service
public class ServerEventsImpl implements ServerEvents {
    protected static final Map<String, SseEmitter> emitterMap = new HashMap<>();

    @Override
    public SseEmitter subscribe(String sessionId) {
        SseEmitter emitter = new SseEmitter();
        try {
            emitter.send(SseEmitter.event().name("onSubscribe"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        emitter.onCompletion(() -> emitterMap.remove(sessionId));
        emitter.onTimeout(() -> emitterMap.remove(sessionId));
        emitter.onError((e) -> emitterMap.remove(sessionId));

        emitterMap.put(sessionId, emitter);
        return emitter;
    }

    @Override
    public void dispatch(String sessionId, EventResponse response) {
        SseEmitter emitter = emitterMap.get(sessionId);
        if (emitter != null) {
            try {
                emitter.send(SseEmitter.event().name("onUpdate").data(response));
            } catch (IOException e) {
                emitterMap.remove(sessionId);
            }
        }
    }
}
