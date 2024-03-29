package com.rickauer.kipling.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConfigFileContentExtractor {
	
	private static Logger configFileContentExtractorLogger = LogManager.getLogger(ConfigFileContentExtractor.class.getName());
	
	private ConfigFileContentExtractor() {
		throw new UnsupportedOperationException("The utility class '" + ConfigFileContentChecker.class.getCanonicalName()
				+ "' is not supposed to be instantiated");
	}
	
	public static String getExePath(final String path) {
		
		/*
		 * There is no logic that checks the validity of the file path because getExePath() will only be called to display the
		 * file path. The existence of the exe file path is being checked earlier by ConfigFileContentChecker.checkEXEPathValidity().
		 * If the file path wasn't valid this method wouldn't be executed.
		 */
		
		configFileContentExtractorLogger.info("Exctracting exe file path ...");
		
		Path filePath = Path.of(path);
		String fileContent;
		
		try {
			fileContent = Files.readString(filePath);
		} catch (IOException e) {
			throw new RuntimeException("Error reading file: " + filePath.toString());
		}
		
		Pattern pattern = Pattern.compile("<outfile>(.+?)</outfile>", Pattern.DOTALL);
		Matcher matcher = pattern.matcher(fileContent);
		matcher.find();
		
		configFileContentExtractorLogger.info("Exctracted exe file path.");
		
		return matcher.group(1);
	}
}
