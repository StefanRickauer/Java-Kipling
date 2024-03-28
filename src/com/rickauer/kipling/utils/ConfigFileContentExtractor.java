package com.rickauer.kipling.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConfigFileContentExtractor {
	
	private ConfigFileContentExtractor() {
		throw new UnsupportedOperationException("The utility class '" + ConfigFileContentChecker.class.getCanonicalName()
				+ "' is not supposed to be instantiated");
	}
	
	public static String getExePath(final String path) {
		
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
		
		return matcher.group(1);
	}
}
