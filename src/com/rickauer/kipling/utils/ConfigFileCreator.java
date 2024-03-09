package com.rickauer.kipling.utils;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.rickauer.kipling.BaseConfigFileConfiguration;

public class ConfigFileCreator {
	
	private static Logger ConfigFileCreatorLogger = LogManager.getLogger(ConfigFileCreator.class.getName());
	
	private ConfigFileCreator() {
		throw new UnsupportedOperationException("The utility class '" + ConfigFileCreator.class.getCanonicalName() + "' is not supposed to be instantiated");
	}
	
	public static BaseConfigFileConfiguration createConfigurationFile() {
		
		ConfigFileCreatorLogger.info("Executing createConfigurationFile() ...");
		
		String headerType = requestHeaderType();
		String jdkPath = retrieveJDKPath();
		BaseConfigFileConfiguration configuration = new BaseConfigFileConfiguration(headerType, jdkPath);
		
		ConfigFileCreatorLogger.info("Executed createConfigurationFile().");
		
		return configuration;
	}
	
	private static String requestHeaderType() {
		
		try (Scanner scanner = new Scanner(System.in)) {
			char input;
			
			System.out.println("What kind of application do you want to build? \nType: g for GUI\nType: c for console\n");
			input = scanner.next().charAt(0);
			
			return switch (input) {
				case 'g' -> "gui";
				case 'c' -> "console";
				default  -> "console";
			};
			
		}
	}
	
	private static String retrieveJDKPath() {
		return (System.getenv("java_home") == null) ? requestJDKPath() : "%java_home%";
	}
	
	private static String requestJDKPath() {
		
		try (Scanner scanner = new Scanner(System.in)) {
			String path = "";
			
			System.out.println("Please provide the path to the JDK.");
			
			path = scanner.nextLine();
			
			if (!Files.exists(Paths.get(path))) {
				ConfigFileCreatorLogger.fatal("Error: File '" + path + "' does not exist.");
				System.err.println("Error: File '" + path + "' does not exist.");
				throw new RuntimeException("Error: File '" + path + "' does not exist.");
			}
			return path;
		}
	}
}
