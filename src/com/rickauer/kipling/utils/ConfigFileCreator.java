package com.rickauer.kipling.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.rickauer.kipling.configuration.BaseConfigFileConfiguration;

public final class ConfigFileCreator {

	private static Logger ConfigFileCreatorLogger = LogManager.getLogger(ConfigFileCreator.class.getName());

	private ConfigFileCreator() {
		throw new UnsupportedOperationException("The utility class '" + ConfigFileCreator.class.getCanonicalName()
				+ "' is not supposed to be instantiated");
	}

	// https://stackoverflow.com/questions/13042008/java-util-nosuchelementexception-scanner-reading-user-input
	// Use next() when using Scanner multiple times:
	// https://stackoverflow.com/questions/26779393/java-using-scanner-multiple-timesS

	public static BaseConfigFileConfiguration retrieveConfiguration() {

		ConfigFileCreatorLogger.info("Executing createConfigurationFile() ...");

		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);

		String configurationFilePath = requestConfigurationFilePath(scanner);
		String headerType = requestHeaderType(scanner);
		String jdkPath = retrieveJDKPath(scanner);
		String jarPath = requestJARPath(scanner);
		String exePath = requestEXEPath(scanner);

		scanner.close();
		BaseConfigFileConfiguration configuration = new BaseConfigFileConfiguration(configurationFilePath, headerType, jdkPath, jarPath, exePath);

		ConfigFileCreatorLogger.info("Executed createConfigurationFile().");

		return configuration;
	}

	private static String requestConfigurationFilePath(Scanner scanner) {

		try {
			String configFilePath = requestInput(scanner, "Please provide the path and name for the configuration file. The path entered will be created and must not be an existing file.");

			if (!configFilePath.endsWith(".xml")) {
				processWrongFileEnding("xml", configFilePath);
			}

			if (Files.exists(Paths.get(configFilePath))) {
				processFileConflict(true, configFilePath);
			}

			return configFilePath;
		} catch (Exception e) {
			throw new RuntimeException("requestConfigurationFilePath(): " + e.getMessage());
		}
	}

	private static String requestHeaderType(Scanner scanner) {

		try {
			String headerType = requestInput(scanner, "What kind of application do you want to build? \nType: g for GUI\nType: c for console\n");

			return switch (headerType) {
				case "g" -> "gui";
				case "c" -> "console";
				default  -> "console";
			};

		} catch (Exception e) {
			throw new RuntimeException("requestHeaderType(): Error requesting header type.", e);
		}
	}

	private static String retrieveJDKPath(Scanner scanner) {
		return (System.getenv("java_home") == null) ? requestJDKPath(scanner) : "%java_home%";
	}

	private static String requestJDKPath(Scanner scanner) {

		try {
			String jdkPath = requestInput(scanner, "Please provide the path to the JDK.");

			if (!Files.exists(Paths.get(jdkPath))) {
				processFileConflict(false, jdkPath);
			}
			return jdkPath;
		} catch (Exception e) {
			throw new RuntimeException("requestJDKPath(): " + e.getMessage());
		}
	}

	private static String requestJARPath(Scanner scanner) {

		try {
			String jarPath = requestInput(scanner, "Please provide the path to the JAR file (runnable JAR!). The path entered must be an existing file.");

			if (!jarPath.endsWith(".jar")) {
				processWrongFileEnding("jar", jarPath);
			}

			if (!Files.exists(Paths.get(jarPath))) {
				processFileConflict(false, jarPath);
			}
			return jarPath;
		} catch (Exception e) {
			throw new RuntimeException("requestJARPath(): " + e.getMessage());
		}
	}

	private static String requestEXEPath(Scanner scanner) {

		try {
			String exePath = requestInput(scanner, "Please provide the path to the EXE file. The path entered will be created and must not be an existing file.");

			if (!exePath.endsWith(".exe")) {
				processWrongFileEnding("exe", exePath);
			}

			return exePath;
		} catch (Exception e) {
			throw new RuntimeException("requestEXEPath(): " + e.getMessage());
		}
	}
	
	private static String requestInput(Scanner scanner, String prompt) {
		System.out.println(prompt);
		return scanner.next();
	}
	
	private static void processWrongFileEnding(String expectedFileEnding, String path) {
		ConfigFileCreatorLogger.fatal("File type error: '" + path + "' must be of type '" + expectedFileEnding + "'.");
		System.err.println("File type error: '" + path + "' must be of type '" + expectedFileEnding + "'.");
		throw new RuntimeException("File type error: '" + path + "' must be of type '" + expectedFileEnding + "'.");
	}
	
	private static void processFileConflict(boolean alreadyExists, String path) {
		String existencePromt = alreadyExists ? "' already exists." : "' does not exist.";
		
		ConfigFileCreatorLogger.fatal("Error: File '" + path + existencePromt);
		System.err.println("Error: File '" + path + existencePromt);
		throw new RuntimeException("Error: File '" + path + existencePromt);
	}

	public static void saveConfigurationFile(BaseConfigFileConfiguration configuration) {

		ConfigFileCreatorLogger.info("Saving configuration file to '" + configuration.getConfigurationFilePath() + "' ...");
		createNewConfigFile(configuration);
		String fileContent = createConfigFileContent(configuration);

		try (FileWriter writer = new FileWriter(configuration.getConfigurationFilePath())) {
			writer.write(fileContent);
		} catch (Exception e) {
			throw new RuntimeException("saveConfigurationFile(): Error saving file.");
		}
		ConfigFileCreatorLogger.info("Saved configuration file to '" + configuration.getConfigurationFilePath() + "'.");
	}

	private static void createNewConfigFile(BaseConfigFileConfiguration configuration) {
		
		try {
			File configFile = new File(configuration.getConfigurationFilePath());
			if (!configFile.createNewFile()) {
				ConfigFileCreatorLogger.fatal("saveConfigurationFile(): Error creating file.");
				throw new RuntimeException("saveConfigurationFile(): Error creating file.");
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	private static String createConfigFileContent(BaseConfigFileConfiguration configuration) {
		StringBuilder xmlFile = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\"?><launch4jConfig><dontWrapJar>false</dontWrapJar><headerType>");
		xmlFile.append(configuration.getHeaderType());
		xmlFile.append("</headerType><jar>");
		xmlFile.append(configuration.getJARPath());
		xmlFile.append("</jar><outfile>");
		xmlFile.append(configuration.getExecutablePath());
		xmlFile.append("</outfile><errTitle></errTitle><cmdLine></cmdLine><chdir>.</chdir><priority>normal</priority><downloadUrl></downloadUrl><supportUrl></supportUrl>");
		xmlFile.append("<stayAlive>false</stayAlive><restartOnCrash>false</restartOnCrash><manifest></manifest><icon></icon><jre><path>");
		xmlFile.append(configuration.getJDKPath());
		xmlFile.append("</path><requiresJdk>false</requiresJdk><requires64Bit>false</requires64Bit><minVersion></minVersion><maxVersion></maxVersion></jre></launch4jConfig>");

		return xmlFile.toString();
	}
}
