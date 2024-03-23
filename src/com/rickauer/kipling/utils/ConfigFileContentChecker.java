package com.rickauer.kipling.utils;

import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class ConfigFileContentChecker {
	
	private static Logger ConfigFileContentCheckerLogger = LogManager.getLogger(ConfigFileContentChecker.class.getName());
	
	private ConfigFileContentChecker() {
		throw new UnsupportedOperationException("The utility class '" + ConfigFileContentChecker.class.getCanonicalName()
				+ "' is not supposed to be instantiated");
	}
	
	public static void checkHeaderTypeValidity(String headerType) {
		
		if ( !(headerType.equals("gui") || headerType.equals("console")) ) {
			ConfigFileContentCheckerLogger.fatal("Error: Header type must be 'gui' or 'console'.");
			System.err.println("Error: Header type must be 'gui' or 'console'.");
			throw new RuntimeException("Error: Header type must be 'gui' or 'console'.");
		}
		
	}
	
	public static void checkConfigurationFileValidity(String configFilePath) {
		
		ConfigFileContentCheckerLogger.info("Checking configuration file validity ...");
		
		if (!configFilePath.endsWith(".xml")) {
			processWrongFileEnding("xml", configFilePath);
		}

		if (Files.exists(Paths.get(configFilePath))) {
			processFileConflict(true, configFilePath);
		}
		
		ConfigFileContentCheckerLogger.info("Checked configuration file validity.");
	}
	
	public static void checkJDKPathValidity(String jdkPath) {
		
		ConfigFileContentCheckerLogger.info("Checking jdk path validity ...");
		
		if (jdkPath.equalsIgnoreCase("%java_home%")) {	
			return;	
		}
		
		if (!Files.exists(Paths.get(jdkPath))) {
			processFileConflict(false, jdkPath);
		}
		
		ConfigFileContentCheckerLogger.info("Checked jdk path validity.");
	}
	
	public static void checkJARPathValidity(String jarPath) {
		
		ConfigFileContentCheckerLogger.info("Checking jar path validity ...");
		
		if (!jarPath.endsWith(".jar")) {
			processWrongFileEnding("jar", jarPath);
		}

		if (!Files.exists(Paths.get(jarPath))) {
			processFileConflict(false, jarPath);
		}
		
		ConfigFileContentCheckerLogger.info("Checked jar path validity.");
	}
	
	public static void checkEXEPathValidity(String exePath) {
		
		ConfigFileContentCheckerLogger.info("Checking exe path validity ...");
		
		if (!exePath.endsWith(".exe")) {
			processWrongFileEnding("exe", exePath);
		}
		
		ConfigFileContentCheckerLogger.info("Checked exe path validity.");
	}
	
	private static void processWrongFileEnding(String expectedFileEnding, String path) {
		ConfigFileContentCheckerLogger.fatal("File type error: '" + path + "' must be of type '" + expectedFileEnding + "'.");
		System.err.println("File type error: '" + path + "' must be of type '" + expectedFileEnding + "'.");
		throw new RuntimeException("File type error: '" + path + "' must be of type '" + expectedFileEnding + "'.");
	}
	
	private static void processFileConflict(boolean alreadyExists, String path) {
		String existencePromt = alreadyExists ? "' already exists." : "' does not exist.";
		
		ConfigFileContentCheckerLogger.fatal("Error: File '" + path + existencePromt);
		System.err.println("Error: File '" + path + existencePromt);
		throw new RuntimeException("Error: File '" + path + existencePromt);
	}
}
