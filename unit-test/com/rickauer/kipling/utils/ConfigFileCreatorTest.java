package com.rickauer.kipling.utils;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.jupiter.api.Test;

public class ConfigFileCreatorTest {

	
	
	@Test
	void requestHeaderTypeTest() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		Class<ConfigFileCreator> clazz = ConfigFileCreator.class;
		Method method = clazz.getDeclaredMethod("requestHeaderType");		// getDeclaredMethod for private methods, getMethod for public methods (https://www.baeldung.com/java-invoke-static-method-reflection)
		method.setAccessible(true);
		mockUserInput("g");
		String result = (String) method.invoke(null);
		System.out.println(result);
		assertEquals("gui", result);
		; // refactor method  
	}
	
	void mockUserInput(String data) {
		ByteArrayInputStream systemIn = new ByteArrayInputStream(data.getBytes());
		System.setIn(systemIn);
	}
	
	
}
