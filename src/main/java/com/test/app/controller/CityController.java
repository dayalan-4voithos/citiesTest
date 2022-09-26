package com.test.app.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.app.dto.CityDto;
import com.test.app.generic.dto.PaginatedResponse;
import com.test.app.generic.dto.PaginationDto;
import com.test.app.service.CityService;

@RestController
@RequestMapping("api/city/")
@CrossOrigin(origins ="http://localhost:3000")
public class CityController {

	@Autowired
	CityService cityService;

	@GetMapping("getByName")
	public List<CityDto> getByName(@PathParam(value = "name") String name) {
		return cityService.getCityByName(name);
	}

	@PostMapping("/getCitiesPaginated")
	public PaginatedResponse getCitiesPaginated(@RequestBody PaginationDto pagination) {
		return cityService.getPaginatedCities(pagination);
	}

	@PutMapping("/updateCities")
	public ResponseEntity<CityDto> updateCity(@RequestBody CityDto city) {
		return new ResponseEntity<>(cityService.updateCity(city), HttpStatus.OK);
	}
}
