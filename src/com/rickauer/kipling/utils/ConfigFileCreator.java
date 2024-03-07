package com.rickauer.kipling.utils;

import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConfigFileCreator {
	
	private static Logger ConfigFileCreatorLogger = LogManager.getLogger(ConfigFileCreator.class.getName());
	
	private ConfigFileCreator() {
		throw new UnsupportedOperationException("The utility class '" + ConfigFileCreator.class.getCanonicalName() + "' is not supposed to be instantiated");
	}
	
	; // change return type to BaseConfigFileConfiguration
	public static String createConfigurationFile() {
		
		ConfigFileCreatorLogger.info("Executing createConfigurationFile() ...");
		
		String headerType = requestHeaderType();
		
		ConfigFileCreatorLogger.info("Executed createConfigurationFile().");
		
		return headerType;
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
}
