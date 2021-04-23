package com.wiley.braze.segmentation.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.wiley.braze.segmentation.model.EventDAO;

@Repository
public interface EventRepository extends CrudRepository<EventDAO, UUID> {

}
