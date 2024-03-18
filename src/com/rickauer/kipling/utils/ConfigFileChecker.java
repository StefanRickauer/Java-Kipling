package com.rickauer.kipling.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public final class ConfigFileChecker {
	
	private ConfigFileChecker() {
		throw new UnsupportedOperationException("The utility class '" + ConfigFileChecker.class.getCanonicalName()
				+ "' is not supposed to be instantiated");
	}
	
	public static boolean configurationFileIsValid(String path) {
		
		if (!pathExists(path)) {
			return false;
		}
		
		Path filePath = Path.of(path);
		String fileContent = getFileContent(filePath);
		
		// TODO: Use regex to extract information such as jar file path 
		
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
	
}
