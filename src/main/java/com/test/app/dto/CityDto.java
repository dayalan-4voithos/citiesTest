package com.test.app.dto;

import com.test.app.domain.City;

public class CityDto {

	private Long id;

	private String name;

	private String photo;

	public CityDto(City city) {
		this.id = city.getId();
		this.name = city.getName();
		this.photo = city.getPhoto();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

}
