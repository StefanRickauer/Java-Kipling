package com.rickauer.kipling.utils;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public final class ConfigFileCheckerTest {

	@Test
	void isConfigurationFileValidValidInputTest() {
		String configFile = System.getProperty("user.dir") + "\\test-data\\gui" + "\\config.xml";
		assertTrue(ConfigFileChecker.isConfigurationFileValid(configFile));
	}
	
	@Test
	void isConfigurationFileValidInvalidInputTestOne() {
		String invalidConfigFile = System.getProperty("user.dir") + "\\test-data\\gui" + "\\guiTest.jar";
		assertFalse(ConfigFileChecker.isConfigurationFileValid(invalidConfigFile));
	}
	
	@Test
	void isConfigurationFileValidInvalidInputTestTwo() {
		String invalidConfigFile = "C:\\invalid\\path";
		assertFalse(ConfigFileChecker.isConfigurationFileValid(invalidConfigFile));
	}
}
