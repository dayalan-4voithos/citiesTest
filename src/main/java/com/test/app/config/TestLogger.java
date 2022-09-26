package com.test.app.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestLogger {
	public static void log(TestLoggerLevel level, String logMessage, Class clazz) {
		Logger logger = LoggerFactory.getLogger(clazz);
		switch (level) {
		case ERROR:
			logger.error(logMessage);
			break;
		case INFO:
			logger.info(logMessage);
			break;
		case WARNING:
			logger.warn(logMessage);
			break;
		}
	}

	public static void log(TestLoggerLevel level, Exception ex, String logMessage, Class clazz) {
		log(level, logMessage, clazz);
		Logger logger = LoggerFactory.getLogger(clazz);
		logger.error(ex.getMessage());
	}
	private TestLogger() {

	}
}
