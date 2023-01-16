package com.eventapp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.eventapp.models.Event;

@Repository
public interface EventRepository extends CrudRepository<Event, String>{
	Event findByCode(long code);
}
