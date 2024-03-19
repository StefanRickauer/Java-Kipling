package com.rickauer.kipling.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class ConfigFileChecker {
	
	private ConfigFileChecker() {
		throw new UnsupportedOperationException("The utility class '" + ConfigFileChecker.class.getCanonicalName()
				+ "' is not supposed to be instantiated");
	}
	
	public static boolean isConfigurationFileValid(String path) {
		
		if (!pathExists(path)) {
			return false;
		}
		
		Path filePath = Path.of(path);
		String fileContent = getFileContent(filePath);
		
		return isConfigurationFileContentValid(fileContent);
	}
	
	private static boolean pathExists(String path) {
		return Files.exists(Paths.get(path));
	}
	
	private static String getFileContent(Path filePath) {
		
		try {
			return Files.readString(filePath);
		} catch (IOException e) {
			throw new RuntimeException("Error reading file: " + filePath.toString());
		}
	}
	
	private static boolean isConfigurationFileContentValid(String content) {
		
		// TODO: Use regex to extract information such as jar file path
		
		String headerType = extractHeaderType(content);
		String jdkPath = extractJDKPath(content);
		String jarPath = extractJARPath(content);
		String exePath = extractEXEPath(content);
		
		return false;
	}
	
	; // Strategy pattern?
	private static String extractHeaderType(String content) {
		Pattern pattern = Pattern.compile("<headerType>(.+?)</headerType>", Pattern.DOTALL);
		Matcher matcher = pattern.matcher(content);
		matcher.find();
		
		return matcher.group(1);
	}
	
	private static String extractJDKPath(String content) {
		throw new UnsupportedOperationException("Not implemented yet");
	}
	
	private static String extractJARPath(String content) {
		throw new UnsupportedOperationException("Not implemented yet");
	}
	
	private static String extractEXEPath(String content) {
		throw new UnsupportedOperationException("Not implemented yet");
	}
}
