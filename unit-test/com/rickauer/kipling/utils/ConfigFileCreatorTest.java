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
	void requestConfigurationFilePathTest() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		mockUserInput("C:\\some\\path\\someFile.xml");
		String configFilePath = (String) getStaticMethodByName("requestConfigurationFilePath").invoke(null);
		assertEquals("C:\\some\\path\\someFile.xml", configFilePath);
	}
	
	@Test
	void requestConfigurationFilePathInvalidInputTestOne() throws IllegalArgumentException, SecurityException {
		mockUserInput("C:\\some\\path\\someFile.docx");
		
		Exception exception = assertThrows(InvocationTargetException.class, () -> getStaticMethodByName("requestConfigurationFilePath").invoke(null));
		assertEquals(RuntimeException.class, exception.getCause().getClass());
		assertEquals("Error: File 'C:\\some\\path\\someFile.docx' must be an XML file.", exception.getCause().getMessage());
	}
	
	@Test
	void requestConfigurationFilePathInvalidInputTestTwo() throws IllegalArgumentException, SecurityException {
		mockUserInput("C:\\tmp\\test.xml");
		
		Exception exception = assertThrows(InvocationTargetException.class, () -> getStaticMethodByName("requestConfigurationFilePath").invoke(null));
		assertEquals(RuntimeException.class, exception.getCause().getClass());
		assertEquals("Error: File 'C:\\tmp\\test.xml' already exists.", exception.getCause().getMessage());
	}
	
	@Test
	void requestHeaderTypeGuiTest() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {

		mockUserInput("g");
		String headerType = (String) getStaticMethodByName("requestHeaderType").invoke(null);
		assertEquals("gui", headerType);
	}
	
	@Test
	void requestHeaderTypeConsoleTest() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		mockUserInput("c");
		String headerType = (String) getStaticMethodByName("requestHeaderType").invoke(null);
		assertEquals("console", headerType);
	}
	
	@Test
	void requestHeaderTypeDefaultTest() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		mockUserInput("something else");
		String headerType = (String) getStaticMethodByName("requestHeaderType").invoke(null);
		assertEquals("console", headerType);
	}
	
	@Test
	void requestHeaderTypeInvalidInputTest() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		mockUserInput("1234");
		String headerType = (String) getStaticMethodByName("requestHeaderType").invoke(null);
		assertEquals("console", headerType);
	}	
	
	@Test
	void retrieveJDKPathTest() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		String jdkPath = (String) getStaticMethodByName("retrieveJDKPath").invoke(null);
		assertTrue(jdkPath.equals("%java_home%"));
	}
	
	@Test
	void requestJDKPathValidInputTest() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		mockUserInput("C:\\Program Files\\Java\\jdk-17");
		String jdkPath = (String) getStaticMethodByName("requestJDKPath").invoke(null);
		assertTrue(jdkPath.equals("C:\\Program Files\\Java\\jdk-17"));
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
	
	@Test
	void requestJARPathTest() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		mockUserInput("C:\\tmp\\sortbyvlaue.jar");
		String jarPath = (String) getStaticMethodByName("requestJARPath").invoke(null);
		assertEquals("C:\\tmp\\sortbyvlaue.jar", jarPath);
	}
	
	@Test
	void requestJARPathInvalidFileTest() {
		mockUserInput("C:\\tmp\\test.xml");
		
		Exception exception = assertThrows(InvocationTargetException.class, () -> getStaticMethodByName("requestJARPath").invoke(null));
		assertEquals(RuntimeException.class, exception.getCause().getClass());
		assertEquals("Error: File 'C:\\tmp\\test.xml' must be a JAR file.", exception.getCause().getMessage());
	}
	
	@Test
	void requestJARPathInvalidPathTest() {
		mockUserInput("C:\\invalid\\path\\test.jar");
		
		Exception exception = assertThrows(InvocationTargetException.class, () -> getStaticMethodByName("requestJARPath").invoke(null));
		assertEquals(RuntimeException.class, exception.getCause().getClass());
		assertEquals("Error: File 'C:\\invalid\\path\\test.jar' does not exist.", exception.getCause().getMessage());
	}
	
	void requestEXEPathTest() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		mockUserInput("C:\\tmp\\myProgram.exe");
		String jarPath = (String) getStaticMethodByName("requestEXEPath").invoke(null);
		assertEquals("C:\\tmp\\myProgram", jarPath);
	}
	
	@Test
	void requestEXEPathInvalidFileTest() {
		mockUserInput("C:\\tmp\\test.xml");
		
		Exception exception = assertThrows(InvocationTargetException.class, () -> getStaticMethodByName("requestEXEPath").invoke(null));
		assertEquals(RuntimeException.class, exception.getCause().getClass());
		assertEquals("Error: File 'C:\\tmp\\test.xml' must be an EXE file.", exception.getCause().getMessage());
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
