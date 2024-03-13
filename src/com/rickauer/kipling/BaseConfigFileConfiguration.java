package com.rickauer.kipling;

public final class BaseConfigFileConfiguration implements ConfigFileConfiguration {

	private String configurationFilePath;	
	private String headerType;
	private String jdkPath;
	private String jarPath;
	private String exePath; 
	
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
