package com.rickauer.kipling.utils;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.rickauer.kipling.BaseConfigFileConfiguration;

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
		
			String configFilePath = (String) getStaticMethodByName("requestConfigurationFilePath", scanner).invoke(null, scanner);
			scanner.close();
			assertEquals("C:\\some\\path\\someFile.xml", configFilePath);
		}
	}
	
	@Test
	void requestConfigurationFilePathInvalidInputTestOne() throws IllegalArgumentException, SecurityException {
		mockUserInput("C:\\some\\path\\someFile.docx");
		
		try (Scanner scanner = new Scanner(System.in)) {
			Exception exception = assertThrows(InvocationTargetException.class, () -> getStaticMethodByName("requestConfigurationFilePath", scanner).invoke(null, scanner));
			assertEquals(RuntimeException.class, exception.getCause().getClass());
			assertEquals("requestConfigurationFilePath(): File type error: 'C:\\some\\path\\someFile.docx' must be of type 'xml'.", exception.getCause().getMessage());
		}
		
	}
	
	@Test
	void requestConfigurationFilePathInvalidInputTestTwo() throws IllegalArgumentException, SecurityException {
		
		String configFilePath = createConfigurationFileContents()[0];
		mockUserInput(configFilePath);
		
		try (Scanner scanner = new Scanner(System.in)) {
			Exception exception = assertThrows(InvocationTargetException.class, () -> getStaticMethodByName("requestConfigurationFilePath", scanner).invoke(null, scanner));
			assertEquals(RuntimeException.class, exception.getCause().getClass());
			assertEquals("requestConfigurationFilePath(): Error: File '" + configFilePath + "' already exists.", exception.getCause().getMessage());
		} 
		
	}
	
	@Test
	void requestHeaderTypeGuiTest() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {

		mockUserInput("g");
		
		try (Scanner scanner = new Scanner(System.in)) {
			String headerType = (String) getStaticMethodByName("requestHeaderType", scanner).invoke(null, scanner);
			assertEquals("gui", headerType);
		}
	}
	
	@Test
	void requestHeaderTypeConsoleTest() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		mockUserInput("c");
		
		try (Scanner scanner = new Scanner(System.in)) {
			String headerType = (String) getStaticMethodByName("requestHeaderType", scanner).invoke(null, scanner);
			assertEquals("console", headerType);
		}
		
	}
	
	@Test
	void requestHeaderTypeDefaultTest() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		mockUserInput("something else");
		
		try (Scanner scanner = new Scanner(System.in)) {
			String headerType = (String) getStaticMethodByName("requestHeaderType", scanner).invoke(null, scanner);
			assertEquals("console", headerType);
		}
	}
	
	@Test
	void requestHeaderTypeInvalidInputTest() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		mockUserInput("1234");
		
		try (Scanner scanner = new Scanner(System.in)) {
			String headerType = (String) getStaticMethodByName("requestHeaderType", scanner).invoke(null, scanner);
			assertEquals("console", headerType);
		}
	}	
	
	@Test
	void retrieveJDKPathTest() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		try (Scanner scanner = new Scanner(System.in)) {
			String jdkPath = (String) getStaticMethodByName("retrieveJDKPath", scanner).invoke(null, scanner);
			assertTrue(jdkPath.equals("%java_home%"));
		}
	}
	
	@Test
	void requestJDKPathValidInputTest() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		mockUserInput("C:\\tmp"); // Assert with space in path failed.
		
		try (Scanner scanner = new Scanner(System.in)) {
			String jdkPath = (String) getStaticMethodByName("requestJDKPath", scanner).invoke(null, scanner);
			assertTrue(jdkPath.equals("C:\\tmp"));
		}
	}
	
	@Test
	void requestJDKPathInvalidInputTest() throws IllegalArgumentException, SecurityException {
		mockUserInput("A:\\Path\\does\\not/exist");
		
		// The "RuntimeException" will be wrapped with the "InvocationTargetException" 
		// https://www.baeldung.com/java-lang-reflect-invocationtargetexception
		
		try (Scanner scanner = new Scanner(System.in)) {
			Exception exception = assertThrows(InvocationTargetException.class, () -> getStaticMethodByName("requestJDKPath", scanner).invoke(null, scanner));
			assertEquals(RuntimeException.class, exception.getCause().getClass());
			assertEquals("requestJDKPath(): Error: File 'A:\\Path\\does\\not/exist' does not exist.", exception.getCause().getMessage());
		}
	}
	
	@Test
	void requestJARPathTest() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		
		String jarFilePath = createConfigurationFileContents()[3];
		mockUserInput(jarFilePath);
		
		try (Scanner scanner = new Scanner(System.in)) {
			String jarPath = (String) getStaticMethodByName("requestJARPath", scanner).invoke(null, scanner);
			assertEquals(jarFilePath, jarPath);
		}
	}
	
	@Test
	void requestJARPathInvalidFileTest() {
		mockUserInput("C:\\tmp\\test.xml");
		
		try (Scanner scanner = new Scanner(System.in)) {
			Exception exception = assertThrows(InvocationTargetException.class, () -> getStaticMethodByName("requestJARPath", scanner).invoke(null, scanner));
			assertEquals(RuntimeException.class, exception.getCause().getClass());
			assertEquals("requestJARPath(): File type error: 'C:\\tmp\\test.xml' must be of type 'jar'.", exception.getCause().getMessage());
		}
	}
	
	@Test
	void requestJARPathInvalidPathTest() {
		mockUserInput("C:\\invalid\\path\\test.jar");
		
		try (Scanner scanner = new Scanner(System.in)) {
			Exception exception = assertThrows(InvocationTargetException.class, () -> getStaticMethodByName("requestJARPath", scanner).invoke(null, scanner));
			assertEquals(RuntimeException.class, exception.getCause().getClass());
			assertEquals("requestJARPath(): Error: File 'C:\\invalid\\path\\test.jar' does not exist.", exception.getCause().getMessage());
		}
	}
	
	@Test
	void requestEXEPathTest() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		
		String exeFilePath = createConfigurationFileContents()[4];
		mockUserInput(exeFilePath);
		
		try (Scanner scanner = new Scanner(System.in)) {
			String jarPath = (String) getStaticMethodByName("requestEXEPath", scanner).invoke(null, scanner);
			assertEquals(exeFilePath, jarPath);
		}
	}
	
	@Test
	void requestEXEPathInvalidFileTest() {
		mockUserInput("C:\\tmp\\test.xml");
		
		try (Scanner scanner = new Scanner(System.in)) {
			Exception exception = assertThrows(InvocationTargetException.class, () -> getStaticMethodByName("requestEXEPath", scanner).invoke(null, scanner));
			assertEquals(RuntimeException.class, exception.getCause().getClass());
			assertEquals("requestEXEPath(): File type error: 'C:\\tmp\\test.xml' must be of type 'exe'.", exception.getCause().getMessage());
		}
	}
	
	@Test
	void saveConfigurationFileFileCreatedTest() {
		
		String[] configFileContent = createConfigurationFileContents();
		
		if (Files.exists(Paths.get(configFileContent[0]))) {
			File file = new File(configFileContent[0]);
			file.delete();
		}
		
		BaseConfigFileConfiguration testData = createBaseConfigFileConfigutration(configFileContent);
		
		ConfigFileCreator.saveConfigurationFile(testData);
		
		assertTrue(Files.exists(Paths.get(configFileContent[0])));
	}
	
	@Test
	void createNewConfigFileFileContentTest() throws IllegalArgumentException, SecurityException, IOException {
		
		String[] configFileContent = createConfigurationFileContents();
		
		if (Files.exists(Paths.get(configFileContent[0]))) {
			File file = new File(configFileContent[0]);
			file.delete();
		}
		
		BaseConfigFileConfiguration testData = createBaseConfigFileConfigutration(configFileContent);
		
		ConfigFileCreator.saveConfigurationFile(testData);
		
		Path path = Paths.get(configFileContent[0]);
		String fileContent = Files.readAllLines(path).get(0);
		
		StringBuilder xmlFile = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\"?><launch4jConfig><dontWrapJar>false</dontWrapJar><headerType>");
		xmlFile.append(configFileContent[1]);
		xmlFile.append("</headerType><jar>");
		xmlFile.append(configFileContent[3]);
		xmlFile.append("</jar><outfile>");
		xmlFile.append(configFileContent[4]);
		xmlFile.append("</outfile><errTitle></errTitle><cmdLine></cmdLine><chdir>.</chdir><priority>normal</priority><downloadUrl></downloadUrl><supportUrl></supportUrl>");
		xmlFile.append("<stayAlive>false</stayAlive><restartOnCrash>false</restartOnCrash><manifest></manifest><icon></icon><jre><path>");
		xmlFile.append(configFileContent[2]);
		xmlFile.append("</path><requiresJdk>false</requiresJdk><requires64Bit>false</requires64Bit><minVersion></minVersion><maxVersion></maxVersion></jre></launch4jConfig>");
		
		String expectedContent = xmlFile.toString();
		
		assertEquals(expectedContent, fileContent);
	}
	
	private Method getStaticMethodByName(String methodIdentifier, Object object) throws NoSuchMethodException, SecurityException {
		Method method = clazz.getDeclaredMethod(methodIdentifier, object.getClass());
		method.setAccessible(true);
		
		return method;
	}
	
	private void mockUserInput(String data) {
		ByteArrayInputStream systemIn = new ByteArrayInputStream(data.getBytes());
		System.setIn(systemIn);
	}
	
	private String[] createConfigurationFileContents() {
		String workingDirectory = System.getProperty("user.dir");
		String testDirectory = "\\test-data\\gui";
		String configFile = "\\config.xml";
		String jarFile = "\\guiTest.jar";
		String exeFile = "\\guiTest.exe";
		
		String configFilePath = workingDirectory + testDirectory + configFile;
		String jarFilePath = workingDirectory + testDirectory + jarFile;
		String exeFilePath = workingDirectory + testDirectory + exeFile;
		
		String[] configurationFileContents = { configFilePath, "gui", "%java_home%", jarFilePath, exeFilePath };
		
		return configurationFileContents;
	}
	
	private BaseConfigFileConfiguration createBaseConfigFileConfigutration(String[] configFileContent) {
		
		return new BaseConfigFileConfiguration(
				configFileContent[0],
				configFileContent[1],
				configFileContent[2],
				configFileContent[3],
				configFileContent[4]	);
	}
}
