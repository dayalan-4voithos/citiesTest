package com.test.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.test.app.domain.City;

public interface CityDao extends PagingAndSortingRepository<City, Long>, JpaRepository<City, Long> {
	public List<City> findByNameContainingIgnoreCase(String name);
}
