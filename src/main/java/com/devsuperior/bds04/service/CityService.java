package com.devsuperior.bds04.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.devsuperior.bds04.dto.CityDTO;
import com.devsuperior.bds04.entities.City;
import com.devsuperior.bds04.repositories.CityRepository;

@Service
public class CityService {

	@Autowired
	private CityRepository cityRepository;
	
	public List<CityDTO> findAll() {
		List<City> city = cityRepository.findAll(Sort.by("name"));
		return city.stream().map(obj -> new CityDTO(obj)).collect(Collectors.toList());
	}

	public CityDTO insert(CityDTO cityDto) {
		City city = new City();
		city.setName(cityDto.getName());
		cityRepository.save(city);
		return new CityDTO(city);
	}

}
