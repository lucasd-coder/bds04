package com.devsuperior.bds04.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.bds04.dto.EventDTO;
import com.devsuperior.bds04.entities.City;
import com.devsuperior.bds04.entities.Event;
import com.devsuperior.bds04.repositories.EventRepository;

@Service
public class EventService {

	@Autowired
	private EventRepository eventRepository;

	@Transactional
	public EventDTO insert(EventDTO eventDto) {
		Event event = new Event();
		event.setName(eventDto.getName());
		event.setUrl(eventDto.getUrl());
		event.setDate(eventDto.getDate());
		event.setCity(new City(eventDto.getCityId(), null));
		event = eventRepository.save(event);

		return new EventDTO(event);
	}
		
	public Page<EventDTO> findAll(Pageable pageable) {
		 Page<Event> listEvent = eventRepository.findAll(pageable);
		 return listEvent.map(obj -> new EventDTO(obj));
	}
}
