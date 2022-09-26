package com.test.app.service;

import java.util.List;

import com.test.app.dto.CityDto;
import com.test.app.generic.dto.PaginatedResponse;
import com.test.app.generic.dto.PaginationDto;

public interface CityService {
	public List<CityDto> getCityByName(String name);

	public PaginatedResponse getPaginatedCities(PaginationDto pagination);

	public CityDto updateCity(CityDto city);
}
