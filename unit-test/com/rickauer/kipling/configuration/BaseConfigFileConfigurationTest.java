package com.rickauer.kipling.configuration;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public final class BaseConfigFileConfigurationTest {

	static BaseConfigFileConfiguration baseConfigFileConfiguration;
	static String workingDirectory, testDirectory, configFile, jarFile, exeFile, configFilePath, jarFilePath, exeFilePath;

	@BeforeAll
	static void initializeTestData() {
		workingDirectory = System.getProperty("user.dir");
		testDirectory = "\\test-data\\gui";
		configFile = "\\config.xml";
		jarFile = "\\guiTest.jar";
		exeFile = "\\guiTest.exe";
		
		configFilePath = workingDirectory + testDirectory + configFile;
		jarFilePath = workingDirectory + testDirectory + jarFile;
		exeFilePath = workingDirectory + testDirectory + exeFile;
		
		baseConfigFileConfiguration = new BaseConfigFileConfiguration(configFilePath, "gui", "C:\\Program Files\\Java\\jdk-17", jarFilePath, exeFilePath);
	}
	
	@Test
	public void getConfigurationFilePathTest() {
		assertEquals(configFilePath, baseConfigFileConfiguration.getConfigurationFilePath());
	}
	
	@Test
	void getHeaderTypeTest() {
		assertEquals("gui", baseConfigFileConfiguration.getHeaderType());
	}
	
	@Test
	public void getJARPathTest() {
		assertEquals(jarFilePath, baseConfigFileConfiguration.getJARPath());
	}

	@Test
	public void getExecutablePathTest() {
		assertEquals(exeFilePath, baseConfigFileConfiguration.getExecutablePath());
	}
	
	@Test
	void getJDKPathTest() {
		assertEquals("C:\\Program Files\\Java\\jdk-17", baseConfigFileConfiguration.getJDKPath());
	}
}
