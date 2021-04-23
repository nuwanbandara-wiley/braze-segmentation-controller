package com.wiley.braze.segmentation.model;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Table(name = "event")
@Entity
public class EventDAO {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private UUID id;

    private String sessionId;

    private String eventName;

    private String payload;
}
