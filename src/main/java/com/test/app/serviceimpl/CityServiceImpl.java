package com.test.app.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.test.app.config.TestLogger;
import com.test.app.config.TestLoggerLevel;
import com.test.app.dao.CityDao;
import com.test.app.domain.City;
import com.test.app.dto.CityDto;
import com.test.app.generic.dto.PaginatedResponse;
import com.test.app.generic.dto.PaginationDto;
import com.test.app.service.CityService;

@Service
public class CityServiceImpl implements CityService {

	@Autowired
	CityDao cityDao;

	@Override
	public List<CityDto> getCityByName(String name) {
		List<City> cities = cityDao.findByNameContainingIgnoreCase(name);

		List<CityDto> citiesResponse = new ArrayList<>();
		if (cities != null) {

			cities.forEach(city -> citiesResponse.add(new CityDto(city)));
			return citiesResponse;
		}
		return citiesResponse;
	}

	@Override
	public PaginatedResponse getPaginatedCities(PaginationDto pagination) {

		Pageable page = PageRequest.of(pagination.getPageNumber(), pagination.getPageSize());
		Page<City> paginatedCitites = cityDao.findAll(page);

		List<CityDto> cities = new ArrayList<>();
		paginatedCitites.getContent().forEach(city -> cities.add(new CityDto(city)));
		pagination.setTotalPages(paginatedCitites.getTotalPages());

		PaginatedResponse response = new PaginatedResponse();
		response.setResults(cities);
		response.setPagination(pagination);

		return response;
	}

	@Override
	public CityDto updateCity(CityDto city) {
		City orginalCity = cityDao.findById(city.getId()).orElseThrow();
		if (!orginalCity.equals(new City(city))) {
			orginalCity.setName(city.getName());
			orginalCity.setPhoto(city.getPhoto());
			orginalCity = cityDao.save(orginalCity);
		} else {
			TestLogger.log(TestLoggerLevel.INFO, "Nothing to update", getClass());
		}
		return new CityDto(orginalCity);
	}

}
