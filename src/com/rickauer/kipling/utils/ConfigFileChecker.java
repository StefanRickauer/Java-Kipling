package com.rickauer.kipling.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class ConfigFileChecker {
	
	private final static String HEADER_REGEX = "<headerType>(.+?)</headerType>";
	private final static String JDK_REGEX = "<path>(.+?)</path>";
	private final static String JAR_REGEX = "<jar>(.+?)</jar>";
	private final static String EXE_REGEX = "<outfile>(.+?)</outfile>";
	
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
		
		String headerType = extractContent(HEADER_REGEX, content);
		String jdkPath = extractContent(JDK_REGEX, content);
		String jarPath = extractContent(JAR_REGEX, content);
		String exePath = extractContent(EXE_REGEX, content);
		
		ConfigFileContentChecker.checkHeaderTypeValidity(headerType);
		ConfigFileContentChecker.checkJDKPathValidity(jdkPath);
		ConfigFileContentChecker.checkJARPathValidity(jarPath);
		ConfigFileContentChecker.checkEXEPathValidity(exePath);
		
		return true;
	}
	
	private static String extractContent(final String regex, final String content) {
		Pattern pattern = Pattern.compile(regex, Pattern.DOTALL);
		Matcher matcher = pattern.matcher(content);
		matcher.find();
		
		return matcher.group(1);
	}
}
