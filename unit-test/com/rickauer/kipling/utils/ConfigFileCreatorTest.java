package com.rickauer.kipling.utils;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class ConfigFileCreatorTest {

	static Class<ConfigFileCreator> clazz;
	
	@BeforeAll
	static void initializeTestData() {
		clazz = ConfigFileCreator.class;
	}
	
	
	@Test
	void requestHeaderTypeGuiTest() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {

		mockUserInput("g");
		String result = (String) getRequestHeaderType().invoke(null);
		assertEquals("gui", result);
	}
	
	@Test
	void requestHeaderTypeConsoleTest() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		mockUserInput("c");
		String result = (String) getRequestHeaderType().invoke(null);
		assertEquals("console", result);
	}
	
	@Test
	void requestHeaderTypeDefaultTest() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		mockUserInput("something else");
		String result = (String) getRequestHeaderType().invoke(null);
		assertEquals("console", result);
	}
	
	@Test
	void requestHeaderTypeInvalidInputTest() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		mockUserInput("1234");
		String result = (String) getRequestHeaderType().invoke(null);
		assertEquals("console", result);
	}
	
	private Method getRequestHeaderType() throws NoSuchMethodException, SecurityException {
		Method method = clazz.getDeclaredMethod("requestHeaderType");	// getDeclaredMethod for private methods, getMethod for public methods (https://www.baeldung.com/java-invoke-static-method-reflection)
		method.setAccessible(true);
		
		return method;
	}
	
	private void mockUserInput(String data) {
		ByteArrayInputStream systemIn = new ByteArrayInputStream(data.getBytes());
		System.setIn(systemIn);
	}
}
