package com.test.app.servicecomponents;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.opencsv.CSVReader;
import com.test.app.config.TestLogger;
import com.test.app.config.TestLoggerLevel;
import com.test.app.dao.CityDao;
import com.test.app.domain.City;

@Component
public class CsvLoader {

	@Autowired
	CityDao cityDao;

	private static final String FILE_PATH = "cities.csv";

	@PostConstruct
	private void initializeDB() {
		if (cityDao.findAll().isEmpty()) {
			List<City> cities = getCitiesFromCsv();
			cityDao.saveAll(cities);
		}
	}

	private List<City> getCitiesFromCsv() {
		List<City> cityList = new ArrayList<>();
		try {
			List<String[]> data = readAllLines(Path.of(FILE_PATH));
			data.remove(0);
			data.stream().forEach(city -> cityList.add(new City(city[0], city[1], city[2])));
			TestLogger.log(TestLoggerLevel.INFO, cityList.toString(), getClass());
			return cityList;
		} catch (Exception e) {
			TestLogger.log(TestLoggerLevel.ERROR, e, e.getMessage(), getClass());
		}
		return cityList;
	}

	private List<String[]> readAllLines(Path filePath) throws Exception {
		try (Reader reader = Files.newBufferedReader(filePath)) {
			try (CSVReader csvReader = new CSVReader(reader)) {
				return csvReader.readAll();
			}
		}
	}
}
