package com.rickauer.kipling.utils;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.rickauer.kipling.configuration.BaseConfigFileConfiguration;

public class ConfigFileCreatorIntegrationTest {
	
	public static void main(String[] args) {
		
		BaseConfigFileConfiguration configuration = ConfigFileCreator.retrieveConfiguration();
		System.out.println("\nSummary:");
		System.out.println("Application type: 		'" + configuration.getHeaderType() + "'");
		System.out.println("Configuration file path: 	'" + configuration.getConfigurationFilePath() + "'");
		System.out.println("Executable file path: 		'" + configuration.getExecutablePath() + "'");
		System.out.println("JDK path: 			'" + configuration.getJDKPath() + "'");
		System.out.println("JAR path: 			'" + configuration.getJARPath() + "'");
		
		ConfigFileCreator.saveConfigurationFile(configuration);
		
		if (Files.exists(Paths.get(configuration.getConfigurationFilePath()))) {
			System.out.println("\nFile saved successfully. Deleting file now.");
			File file = new File(configuration.getConfigurationFilePath());
			file.delete();
			
			if (!Files.exists(Paths.get(configuration.getConfigurationFilePath()))) {
				System.out.println("File deleted successfully.");
			}
		}
	}
}
