package com.rickauer.kipling;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class BaseConfigFileConfigurationTest {

	static BaseConfigFileConfiguration baseConfigFileConfiguration;
	
	@BeforeAll
	static void initializeTestData() {
		baseConfigFileConfiguration = new BaseConfigFileConfiguration("C:\\tmp\\test.xml", "gui", "C:\\Program Files\\Java\\jdk-17", "C:\\tmp\\sortbyvlaue.jar", "C:\\tmp\\myProgram.exe");
	}
	
	@Test
	public void getConfigurationFilePathTest() {
		assertEquals("C:\\tmp\\test.xml", baseConfigFileConfiguration.getConfigurationFilePath());
	}
	
	@Test
	void getHeaderTypeTest() {
		assertEquals("gui", baseConfigFileConfiguration.getHeaderType());
	}
	; // Uncomment unit tests as soon as the respective methods have been implemented
	@Test
	public void getJARPathTest() {
		assertEquals("C:\\tmp\\sortbyvlaue.jar", baseConfigFileConfiguration.getJARPath());
	}

	@Test
	public void getExecutablePathTest() {
		assertEquals("C:\\tmp\\myProgram.exe", baseConfigFileConfiguration.getExecutablePath());
	}
	
	@Test
	void getJDKPathTest() {
		assertEquals("C:\\Program Files\\Java\\jdk-17", baseConfigFileConfiguration.getJDKPath());
	}
	
//	@Test
//	void saveConfigurationFileTest() {
//		baseConfigFileConfiguration.saveConfigurationFile();
//	}
}
