package com.wiley.braze.segmentation.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class EventResponse {
    private String eventName;
    private Boolean triggerEvent;
}
