package com.rickauer.kipling.utils;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
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
		String result = (String) getStaticMethodByName("requestHeaderType").invoke(null);
		assertEquals("gui", result);
	}
	
	@Test
	void requestHeaderTypeConsoleTest() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		mockUserInput("c");
		String result = (String) getStaticMethodByName("requestHeaderType").invoke(null);
		assertEquals("console", result);
	}
	
	@Test
	void requestHeaderTypeDefaultTest() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		mockUserInput("something else");
		String result = (String) getStaticMethodByName("requestHeaderType").invoke(null);
		assertEquals("console", result);
	}
	
	@Test
	void requestHeaderTypeInvalidInputTest() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		mockUserInput("1234");
		String result = (String) getStaticMethodByName("requestHeaderType").invoke(null);
		assertEquals("console", result);
	}	
	
	@Test
	void retrieveJDKPathTest() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		String path = (String) getStaticMethodByName("retrieveJDKPath").invoke(null);
		assertTrue(path.equals("%java_home%"));
	}
	
	@Test
	void requestJDKPathValidInputTest() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		mockUserInput("C:\\Program Files\\Java\\jdk-17");
		String path = (String) getStaticMethodByName("requestJDKPath").invoke(null);
		assertTrue(path.equals("C:\\Program Files\\Java\\jdk-17"));
	}
	
	@Test
	void requestJDKPathInvalidInputTest() throws IllegalArgumentException, SecurityException {
		mockUserInput("A:\\Path\\does\\not/exist");
		
		// The "RuntimeException" will be wrapped with the "InvocationTargetException" 
		// https://www.baeldung.com/java-lang-reflect-invocationtargetexception
		
		Exception exception = assertThrows(InvocationTargetException.class, () -> getStaticMethodByName("requestJDKPath").invoke(null));
		assertEquals(RuntimeException.class, exception.getCause().getClass());
		assertEquals("Error: File 'A:\\Path\\does\\not/exist' does not exist.", exception.getCause().getMessage());
	}
	
	private Method getStaticMethodByName(String methodIdentifier) throws NoSuchMethodException, SecurityException {
		Method method = clazz.getDeclaredMethod(methodIdentifier);
		method.setAccessible(true);
		
		return method;
	}
	
	private void mockUserInput(String data) {
		ByteArrayInputStream systemIn = new ByteArrayInputStream(data.getBytes());
		System.setIn(systemIn);
	}
}
