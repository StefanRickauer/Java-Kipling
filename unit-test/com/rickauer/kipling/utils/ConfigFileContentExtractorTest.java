package com.rickauer.kipling.utils;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public final class ConfigFileContentExtractorTest {

	@Test
	void getExePathValidInputTest() {
		
		String validFilePath = System.getProperty("user.dir") + "\\test-data\\gui\\config.xml";
		String expectedExeFilePath = System.getProperty("user.dir") + "\\test-data\\gui\\guiTest.exe";
		
		assertEquals(expectedExeFilePath, ConfigFileContentExtractor.getExePath(validFilePath));
	}
}
