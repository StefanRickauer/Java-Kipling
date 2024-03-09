package com.rickauer.kipling;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class BaseConfigFileConfiguration implements ConfigFileConfiguration {

	private static Logger baseConfigFileConfigurationLogger = LogManager.getLogger(BaseConfigFileConfiguration.class.getName());
	
	private String headerType;
	private String jdkPath;
	
	public BaseConfigFileConfiguration(final String headerType, final String jdkPath) {
		this.headerType = headerType;
		this.jdkPath = jdkPath;
	}
	
	public static void createConfigurationFile() {
		
		baseConfigFileConfigurationLogger.info("Creating configuration file ...");
		// Do stuff here
		baseConfigFileConfigurationLogger.info("Created configuration file.");
		
		throw new UnsupportedOperationException("Not implemented yet");
	}
	; // Builder ?
	
	@Override
	public String getHeaderType() {
		return headerType;
	}

	@Override
	public String getJARPath() {
		throw new UnsupportedOperationException("Not implemented yet");
	}

	@Override
	public String getExecutablePath() {
		throw new UnsupportedOperationException("Not implemented yet");
	}

	@Override
	public String getJDKPath() {
		return jdkPath;
	}
}
