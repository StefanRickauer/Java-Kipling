package com.rickauer.kipling.utils;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public final class ConfigFileContentCheckerTest {
	
	static Class<ConfigFileContentChecker> clazz;
	
	@BeforeAll
	static void initializeTestData() {
		clazz = ConfigFileContentChecker.class;
	}
	
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
		String configFilePath = System.getProperty("user.dir") + "\\test-data\\gui" + "\\config.docx";
		Exception exception = assertThrows(RuntimeException.class, () -> ConfigFileContentChecker.checkConfigurationFileValidity(configFilePath));
		assertEquals("File type error: '" + configFilePath + "' must be of type 'xml'.", exception.getMessage());
	}
	
	@Test
	void checkConfigurationFileValidityInalidInputTestTwo() {
		String configFilePath = System.getProperty("user.dir") + "\\test-data\\gui" + "\\config.xml";
		Exception exception = assertThrows(RuntimeException.class, () -> ConfigFileContentChecker.checkConfigurationFileValidity(configFilePath));
		assertEquals("Error: File '" + configFilePath + "' already exists.", exception.getMessage());
	}
	
	@Test
	void checkJDKPathValidityValidInputTestOne() {
		String jdkPathOne 	= "%java_home%";
		String jdkPathTwo 	= "%JAVA_HOME%";
		String jdkPathThree = "%jAvA_hOMe%";
		String jdkPathFour 	= "%jaVa_HOMe%";
		
		assertDoesNotThrow(() -> ConfigFileContentChecker.checkJDKPathValidity(jdkPathOne));
		assertDoesNotThrow(() -> ConfigFileContentChecker.checkJDKPathValidity(jdkPathTwo));
		assertDoesNotThrow(() -> ConfigFileContentChecker.checkJDKPathValidity(jdkPathThree));
		assertDoesNotThrow(() -> ConfigFileContentChecker.checkJDKPathValidity(jdkPathFour));
	}
	
	@Test
	void checkJDKPathValidityValidInputTestTwo() {
		String[] jdkPaths = System.getenv("java_home").split(";");
		
		for (String path : jdkPaths) {
			assertDoesNotThrow(() -> ConfigFileContentChecker.checkJDKPathValidity(path));
		}
	}
	
	@Test
	void checkJDKPathValidityInvalidInputTest() {
		String invalidJDKPath = "F:\\invalid\\path.hi";
		Exception exception = assertThrows(RuntimeException.class, ()-> ConfigFileContentChecker.checkJDKPathValidity(invalidJDKPath));
		assertEquals("Error: File '" + invalidJDKPath + "' does not exist.", exception.getMessage());
	}
	
	@Test
	void checkJARPathValidityValidInputTest() {
		String jarPath = System.getProperty("user.dir") + "\\test-data\\gui" + "\\guiTest.jar";
		assertDoesNotThrow(() -> ConfigFileContentChecker.checkJARPathValidity(jarPath));
	}
	
	@Test
	void checkJARPathValidityInvalidInputTestOne() {
		String invalidJARPath = "hi.exe";
		Exception exception = assertThrows(RuntimeException.class, () -> ConfigFileContentChecker.checkJARPathValidity(invalidJARPath));
		assertEquals("File type error: '" + invalidJARPath + "' must be of type 'jar'.", exception.getMessage());
	}
	
	@Test
	void checkJARPathValidityInvalidInputTestTwo() {
		String invalidJARPath = "hi.jar";
		Exception exception = assertThrows(RuntimeException.class, () -> ConfigFileContentChecker.checkJARPathValidity(invalidJARPath));
		assertEquals("Error: File '" + invalidJARPath + "' does not exist.", exception.getMessage());
	}
	
	@Test
	void checkEXEPathValidityValidInputTest() {
		String exePath = "kipling.exe";
		assertDoesNotThrow(() -> ConfigFileContentChecker.checkEXEPathValidity(exePath));
	}
	
	@Test
	void checkEXEPathValidityInvalidInputTest() {
		String invalidEXEPath = "invalid.zip";
		Exception exception = assertThrows(RuntimeException.class, () -> ConfigFileContentChecker.checkEXEPathValidity(invalidEXEPath));
		assertEquals("File type error: '" + invalidEXEPath + "' must be of type 'exe'.", exception.getMessage());
	}
	
	@Test
	void processWrongFileEndingTest() {
		String expectedFileEnding = "jar";
		String path = "C:\\some\\path.exe";
		
		Exception exception = assertThrows(InvocationTargetException.class, () -> getProcessFileConflict("processWrongFileEnding", expectedFileEnding, path).invoke(null, expectedFileEnding, path));
		assertEquals(RuntimeException.class, exception.getCause().getClass());
		assertEquals("File type error: '" + path + "' must be of type '" + expectedFileEnding + "'.", exception.getCause().getMessage());
	}
	
	@Test
	void processFileConflictTest() {
		boolean exists = true;
		String path = "C:\\tmp";
		
		Exception exception = assertThrows(InvocationTargetException.class, () -> getProcessFileConflict("processFileConflict", path).invoke(null, exists, path));
		assertEquals(RuntimeException.class, exception.getCause().getClass());
		assertEquals("Error: File '" + path + "' already exists.", exception.getCause().getMessage());
	}
	
	private Method getProcessFileConflict(String methodIdentifier, Object firstParam, Object secondParam) throws NoSuchMethodException, SecurityException {
		Method method = clazz.getDeclaredMethod(methodIdentifier, firstParam.getClass(), secondParam.getClass());
		method.setAccessible(true);
		
		return method;
	}
	
	private Method getProcessFileConflict(String methodIdentifier, Object param) throws NoSuchMethodException, SecurityException {
		Method method = clazz.getDeclaredMethod(methodIdentifier, boolean.class, param.getClass());
		method.setAccessible(true);
		
		return method;
	}
}
