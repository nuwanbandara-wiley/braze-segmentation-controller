package com.wiley.braze.segmentation.model;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Event {
    private UUID id;
    private String sessionId;
    private String eventName;
    private String payload;
}
