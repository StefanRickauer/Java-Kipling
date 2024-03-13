package com.rickauer.kipling.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
		BaseConfigFileConfiguration configuration = new BaseConfigFileConfiguration(configurationFilePath, headerType,
				jdkPath, jarPath, exePath);

		ConfigFileCreatorLogger.info("Executed createConfigurationFile().");

		return configuration;
	}

	; // TODO: Refactor all request...Path()- Methods

	private static String requestConfigurationFilePath(Scanner scanner) {

		try {
			String configFilePath;

			System.out.println("Please provide the path and name for the configuration file.");
			configFilePath = scanner.next();

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
		} catch (Exception e) {
			throw new RuntimeException("requestConfigurationFilePath(): " + e.getMessage());
		}
	}

	private static String requestHeaderType(Scanner scanner) {

		try {
			String input;

			System.out
					.println("What kind of application do you want to build? \nType: g for GUI\nType: c for console\n");
			input = scanner.next();

			return switch (input) {
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
			String path;

			System.out.println("Please provide the path to the JDK.");

			path = scanner.next();

			if (!Files.exists(Paths.get(path))) {
				ConfigFileCreatorLogger.fatal("Error: File '" + path + "' does not exist.");
				System.err.println("Error: File '" + path + "' does not exist.");
				throw new RuntimeException("Error: File '" + path + "' does not exist.");
			}
			return path;
		} catch (Exception e) {
			throw new RuntimeException("requestJDKPath(): " + e.getMessage());
		}
	}

	private static String requestJARPath(Scanner scanner) {

		try {
			String jarPath;

			System.out.println("Please provide the path to the JAR file (runnable JAR!).");

			jarPath = scanner.next();

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
		} catch (Exception e) {
			throw new RuntimeException("requestJARPath(): " + e.getMessage());
		}
	}

	private static String requestEXEPath(Scanner scanner) {

		try {
			String exePath;

			System.out.println("Please provide the path to the EXE file.");

			exePath = scanner.next();

			if (!exePath.endsWith(".exe")) {
				ConfigFileCreatorLogger.fatal("Error: File '" + exePath + "' must be an EXE file.");
				System.err.println("Error: File '" + exePath + "' must be an EXE file.");
				throw new RuntimeException("Error: File '" + exePath + "' must be an EXE file.");
			}

			return exePath;
		} catch (Exception e) {
			throw new RuntimeException("requestEXEPath(): " + e.getMessage());
		}
	}

	;// TODO: Write unit tests for new methods
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
