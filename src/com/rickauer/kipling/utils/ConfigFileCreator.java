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
		throw new UnsupportedOperationException("The utility class '" + ConfigFileCreator.class.getCanonicalName()
				+ "' is not supposed to be instantiated");
	}

	; // TODO: Troubleshoot "NoSuchElementException": https://stackoverflow.com/questions/13042008/java-util-nosuchelementexception-scanner-reading-user-input
	public static BaseConfigFileConfiguration createConfigurationFile() {

		ConfigFileCreatorLogger.info("Executing createConfigurationFile() ...");

		String configurationFilePath = requestConfigurationFilePath();
		String headerType = requestHeaderType();
		String jdkPath = retrieveJDKPath();
		String jarPath = requestJARPath();
		String exePath = requestEXEPath();
		BaseConfigFileConfiguration configuration = new BaseConfigFileConfiguration(configurationFilePath, headerType,
				jdkPath, jarPath, exePath);

		ConfigFileCreatorLogger.info("Executed createConfigurationFile().");

		return configuration;
	}

	; // TODO: Refactor all request...Path()- Methods
	private static String requestConfigurationFilePath() {

		try (Scanner scanner = new Scanner(System.in)) {
			String configFilePath;

			System.out.println("Please provide the path and name for the configuration file.");
			configFilePath = scanner.nextLine();

			if (!configFilePath.endsWith(".xml")) {
				ConfigFileCreatorLogger.fatal("Error: File '" + configFilePath + "' must be an XML file.");
				System.err.println("Error: File '" + configFilePath + "' must be an XML file.");
				throw new RuntimeException("Error: File '" + configFilePath + "' must be an XML file.");
			}

			if (Files.exists(Paths.get(configFilePath))) {
				ConfigFileCreatorLogger.fatal("Error: File '" + configFilePath + "' already exists.");
				System.err.println("Error: File '" + configFilePath + "' already exists.");
				throw new RuntimeException("Error: File '" + configFilePath + "' already exists.");
			}

			return configFilePath;
		}
	}

	private static String requestHeaderType() {

		try (Scanner scanner = new Scanner(System.in)) {
			String input;

			System.out.println("What kind of application do you want to build? \nType: g for GUI\nType: c for console\n");
			input = scanner.next();

			return switch (input) {
				case "g" -> "gui";
				case "c" -> "console";
				default  -> "console";
			};

		}
	}

	private static String retrieveJDKPath() {
		return (System.getenv("java_home") == null) ? requestJDKPath() : "%java_home%";
	}

	private static String requestJDKPath() {

		try (Scanner scanner = new Scanner(System.in)) {
			String path;

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

	private static String requestJARPath() {

		try (Scanner scanner = new Scanner(System.in)) {
			String jarPath;

			System.out.println("Please provide the path to the JAR file (runnable JAR!).");

			jarPath = scanner.nextLine();

			if (!jarPath.endsWith(".jar")) {
				ConfigFileCreatorLogger.fatal("Error: File '" + jarPath + "' must be a JAR file.");
				System.err.println("Error: File '" + jarPath + "' must be a JAR file.");
				throw new RuntimeException("Error: File '" + jarPath + "' must be a JAR file.");
			}
			
			if (!Files.exists(Paths.get(jarPath))) {
				ConfigFileCreatorLogger.fatal("Error: File '" + jarPath + "' does not exist.");
				System.err.println("Error: File '" + jarPath + "' does not exist.");
				throw new RuntimeException("Error: File '" + jarPath + "' does not exist.");
			}
			return jarPath;
		}
	}
	
	private static String requestEXEPath() {

		try (Scanner scanner = new Scanner(System.in)) {
			String exePath;

			System.out.println("Please provide the path to the EXE file.");

			exePath = scanner.nextLine();

			if (!exePath.endsWith(".exe")) {
				ConfigFileCreatorLogger.fatal("Error: File '" + exePath + "' must be an EXE file.");
				System.err.println("Error: File '" + exePath + "' must be an EXE file.");
				throw new RuntimeException("Error: File '" + exePath + "' must be an EXE file.");
			}
			
			return exePath;
		}
	}
}
