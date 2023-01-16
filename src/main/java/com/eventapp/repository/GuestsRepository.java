package com.eventapp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.eventapp.models.Event;
import com.eventapp.models.Guests;

@Repository
public interface GuestsRepository extends CrudRepository<Guests,String>{
	Iterable<Guests> findByEvent(Event event);
	Guests findByRg(String rg);
}
