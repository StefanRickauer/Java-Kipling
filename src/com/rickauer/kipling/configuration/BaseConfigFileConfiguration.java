package com.rickauer.kipling.configuration;

public final class BaseConfigFileConfiguration implements ConfigFileConfiguration {

	private final String configurationFilePath;	
	private final String headerType;
	private final String jdkPath;
	private final String jarPath;
	private final String exePath; 
	
	public BaseConfigFileConfiguration(final String configurationPath, final String headerType, final String jdkPath, final String jarPath, final String exePath) {
		this.configurationFilePath = configurationPath;
		this.headerType = headerType;
		this.jdkPath = jdkPath;
		this.jarPath = jarPath;
		this.exePath = exePath;
	}
	
	@Override
	public String getConfigurationFilePath() {
		return configurationFilePath;
	}
	
	@Override
	public String getHeaderType() {
		return headerType;
	}

	@Override
	public String getJARPath() {
		return jarPath;
	}

	@Override
	public String getExecutablePath() {
		return exePath;
	}

	@Override
	public String getJDKPath() {
		return jdkPath;
	}
}
