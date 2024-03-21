package com.rickauer.kipling.utils;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public final class ConfigFileContentCheckerTest {
	
	@Test
	void checkHeaderTypeValidityValidInputTestOne() {
		assertDoesNotThrow(() -> ConfigFileContentChecker.checkHeaderTypeValidity("gui"));
	}
	
	@Test
	void checkHeaderTypeValidityValidInputTestTwo() {
		assertDoesNotThrow(() -> ConfigFileContentChecker.checkHeaderTypeValidity("console"));
	}
	
	@Test
	void checkHeaderTypeValidityInvalidInput() {
		assertThrows(RuntimeException.class, () -> ConfigFileContentChecker.checkHeaderTypeValidity("invalid"));
	}

	@Test
	void checkConfigurationFileValidityValidInputTestOne() {
		String configFilePath = "C:\\test-data\\gui\\config.xml";
		assertDoesNotThrow( () -> ConfigFileContentChecker.checkConfigurationFileValidity(configFilePath));
	}
	
	@Test
	void checkConfigurationFileValidityInalidInputTestOne() {
		String configFilePath = System.getProperty("user.dir") + "\\test-data\\gui" + "\\config.xml";
		Exception exception = assertThrows(RuntimeException.class, () -> ConfigFileContentChecker.checkConfigurationFileValidity(configFilePath));
		assertEquals("Error: File '" + configFilePath + "' already exists.", exception.getMessage());
	}
	;// add more test cases
}
