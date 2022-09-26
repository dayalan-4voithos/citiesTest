package com.test.app.domain;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.test.app.dto.CityDto;

@Entity(name = "city")
public class City {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "city_name")
	private String name;

	@Column(columnDefinition = "longtext CHARACTER SET utf8mb4")
	private String photo;

	public City() {

	}

	public City(String id, String name, String photo) {
		this.id = Long.valueOf(id);
		this.name = name;
		this.photo = photo;
	}

	public City(CityDto city) {
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

	@Override
	public int hashCode() {
		return Objects.hash(id, name, photo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		City other = (City) obj;
		return Objects.equals(id, other.id) && Objects.equals(name, other.name) && Objects.equals(photo, other.photo);
	}

	@Override
	public String toString() {
		return "{\"id\":" + id + ", \"name\":\"" + name + "\", \"photo\":\"" + photo + "\"}";
	}

}
