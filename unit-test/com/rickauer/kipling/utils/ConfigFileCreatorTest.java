package com.rickauer.kipling.utils;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;

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
		
		try (Scanner scanner = new Scanner(System.in)) {
		
			String configFilePath = (String) getStaticMethodByName("requestConfigurationFilePath").invoke(null, scanner);
			scanner.close();
			assertEquals("C:\\some\\path\\someFile.xml", configFilePath);
		}
	}
	
	@Test
	void requestConfigurationFilePathInvalidInputTestOne() throws IllegalArgumentException, SecurityException {
		mockUserInput("C:\\some\\path\\someFile.docx");
		
		try (Scanner scanner = new Scanner(System.in)) {
			Exception exception = assertThrows(InvocationTargetException.class, () -> getStaticMethodByName("requestConfigurationFilePath").invoke(null, scanner));
			assertEquals(RuntimeException.class, exception.getCause().getClass());
			assertEquals("requestConfigurationFilePath(): Error: File 'C:\\some\\path\\someFile.docx' must be an XML file.", exception.getCause().getMessage());
		}
		
	}
	
	@Test
	void requestConfigurationFilePathInvalidInputTestTwo() throws IllegalArgumentException, SecurityException {
		mockUserInput("C:\\tmp\\test.xml");
		
		try (Scanner scanner = new Scanner(System.in)) {
			Exception exception = assertThrows(InvocationTargetException.class, () -> getStaticMethodByName("requestConfigurationFilePath").invoke(null, scanner));
			assertEquals(RuntimeException.class, exception.getCause().getClass());
			assertEquals("requestConfigurationFilePath(): Error: File 'C:\\tmp\\test.xml' already exists.", exception.getCause().getMessage());
		} 
		
	}
	
	@Test
	void requestHeaderTypeGuiTest() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {

		mockUserInput("g");
		
		try (Scanner scanner = new Scanner(System.in)) {
			String headerType = (String) getStaticMethodByName("requestHeaderType").invoke(null, scanner);
			assertEquals("gui", headerType);
		}
	}
	
	@Test
	void requestHeaderTypeConsoleTest() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		mockUserInput("c");
		
		try (Scanner scanner = new Scanner(System.in)) {
			String headerType = (String) getStaticMethodByName("requestHeaderType").invoke(null, scanner);
			assertEquals("console", headerType);
		}
		
	}
	
	@Test
	void requestHeaderTypeDefaultTest() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		mockUserInput("something else");
		
		try (Scanner scanner = new Scanner(System.in)) {
			String headerType = (String) getStaticMethodByName("requestHeaderType").invoke(null, scanner);
			assertEquals("console", headerType);
		}
	}
	
	@Test
	void requestHeaderTypeInvalidInputTest() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		mockUserInput("1234");
		
		try (Scanner scanner = new Scanner(System.in)) {
			String headerType = (String) getStaticMethodByName("requestHeaderType").invoke(null, scanner);
			assertEquals("console", headerType);
		}
	}	
	
	@Test
	void retrieveJDKPathTest() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		try (Scanner scanner = new Scanner(System.in)) {
			String jdkPath = (String) getStaticMethodByName("retrieveJDKPath").invoke(null, scanner);
			assertTrue(jdkPath.equals("%java_home%"));
		}
	}
	
	@Test
	void requestJDKPathValidInputTest() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		mockUserInput("C:\\tmp"); // Assert with space in path failed.
		
		try (Scanner scanner = new Scanner(System.in)) {
			String jdkPath = (String) getStaticMethodByName("requestJDKPath").invoke(null, scanner);
			assertTrue(jdkPath.equals("C:\\tmp"));
		}
	}
	
	@Test
	void requestJDKPathInvalidInputTest() throws IllegalArgumentException, SecurityException {
		mockUserInput("A:\\Path\\does\\not/exist");
		
		// The "RuntimeException" will be wrapped with the "InvocationTargetException" 
		// https://www.baeldung.com/java-lang-reflect-invocationtargetexception
		
		try (Scanner scanner = new Scanner(System.in)) {
			Exception exception = assertThrows(InvocationTargetException.class, () -> getStaticMethodByName("requestJDKPath").invoke(null, scanner));
			assertEquals(RuntimeException.class, exception.getCause().getClass());
			assertEquals("requestJDKPath(): Error: File 'A:\\Path\\does\\not/exist' does not exist.", exception.getCause().getMessage());
		}
	}
	
	@Test
	void requestJARPathTest() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		mockUserInput("C:\\tmp\\sortbyvalue.jar");
		
		try (Scanner scanner = new Scanner(System.in)) {
			String jarPath = (String) getStaticMethodByName("requestJARPath").invoke(null, scanner);
			assertEquals("C:\\tmp\\sortbyvalue.jar", jarPath);
		}
	}
	
	@Test
	void requestJARPathInvalidFileTest() {
		mockUserInput("C:\\tmp\\test.xml");
		
		try (Scanner scanner = new Scanner(System.in)) {
			Exception exception = assertThrows(InvocationTargetException.class, () -> getStaticMethodByName("requestJARPath").invoke(null, scanner));
			assertEquals(RuntimeException.class, exception.getCause().getClass());
			assertEquals("requestJARPath(): Error: File 'C:\\tmp\\test.xml' must be a JAR file.", exception.getCause().getMessage());
		}
	}
	
	@Test
	void requestJARPathInvalidPathTest() {
		mockUserInput("C:\\invalid\\path\\test.jar");
		
		try (Scanner scanner = new Scanner(System.in)) {
			Exception exception = assertThrows(InvocationTargetException.class, () -> getStaticMethodByName("requestJARPath").invoke(null, scanner));
			assertEquals(RuntimeException.class, exception.getCause().getClass());
			assertEquals("requestJARPath(): Error: File 'C:\\invalid\\path\\test.jar' does not exist.", exception.getCause().getMessage());
		}
	}
	
	@Test
	void requestEXEPathTest() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		mockUserInput("C:\\tmp\\myProgram.exe");
		
		try (Scanner scanner = new Scanner(System.in)) {
			String jarPath = (String) getStaticMethodByName("requestEXEPath").invoke(null, scanner);
			assertEquals("C:\\tmp\\myProgram.exe", jarPath);
		}
	}
	
	@Test
	void requestEXEPathInvalidFileTest() {
		mockUserInput("C:\\tmp\\test.xml");
		
		try (Scanner scanner = new Scanner(System.in)) {
			Exception exception = assertThrows(InvocationTargetException.class, () -> getStaticMethodByName("requestEXEPath").invoke(null, scanner));
			assertEquals(RuntimeException.class, exception.getCause().getClass());
			assertEquals("requestEXEPath(): Error: File 'C:\\tmp\\test.xml' must be an EXE file.", exception.getCause().getMessage());
		}
	}
	
	private Method getStaticMethodByName(String methodIdentifier) throws NoSuchMethodException, SecurityException {
		Method method = clazz.getDeclaredMethod(methodIdentifier, Scanner.class);
		method.setAccessible(true);
		
		return method;
	}
	
	private void mockUserInput(String data) {
		ByteArrayInputStream systemIn = new ByteArrayInputStream(data.getBytes());
		System.setIn(systemIn);
	}
}
