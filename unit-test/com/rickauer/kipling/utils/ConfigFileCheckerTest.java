package com.rickauer.kipling.utils;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

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
	
	@Test
	void callConstructor() throws NoSuchMethodException, SecurityException {
		Class<ConfigFileChecker> clazz = ConfigFileChecker.class;
		Constructor<ConfigFileChecker> constructor = clazz.getDeclaredConstructor();
		constructor.setAccessible(true);
		
		Exception exception = assertThrows(InvocationTargetException.class, () -> constructor.newInstance());
		assertEquals(UnsupportedOperationException.class, exception.getCause().getClass());
		assertEquals("The utility class '" + ConfigFileChecker.class.getCanonicalName()
				+ "' is not supposed to be instantiated", exception.getCause().getMessage());
	}
}
