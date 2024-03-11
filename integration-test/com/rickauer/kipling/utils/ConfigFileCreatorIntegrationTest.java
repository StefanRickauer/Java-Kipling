package com.rickauer.kipling.utils;

import com.rickauer.kipling.BaseConfigFileConfiguration;

public class ConfigFileCreatorIntegrationTest {
	
	public static void main(String[] args) {
		
		BaseConfigFileConfiguration configuration = ConfigFileCreator.createConfigurationFile();
		System.out.println("Application type: '" + configuration.getHeaderType() + "'");
		System.out.println("Configuration file path: '" + configuration.getConfigurationFilePath() + "'");
		System.out.println("Executable file path: '" + configuration.getExecutablePath() + "'");
		System.out.println("JDK path: '" + configuration.getJDKPath() + "'");
		System.out.println("JAR path: '" + configuration.getJARPath() + "'");
	}
}
