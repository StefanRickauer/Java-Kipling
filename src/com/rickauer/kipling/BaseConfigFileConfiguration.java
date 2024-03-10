package com.rickauer.kipling;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class BaseConfigFileConfiguration implements ConfigFileConfiguration {

	private static Logger baseConfigFileConfigurationLogger = LogManager.getLogger(BaseConfigFileConfiguration.class.getName());
	
	private String configurationFilePath;	; // get configuration file path or hard code it at first
	private String headerType;
	private String jdkPath;
	
	public BaseConfigFileConfiguration(final String configurationPath, final String headerType, final String jdkPath) {
		this.configurationFilePath = configurationPath;
		this.headerType = headerType;
		this.jdkPath = jdkPath;
	}
	
	public void saveConfigurationFile() {
		
		baseConfigFileConfigurationLogger.info("Saving configuration file to '" + configurationFilePath + "' ...");
		// Do stuff here
		baseConfigFileConfigurationLogger.info("Saved configuration file '" + configurationFilePath + "'.");
		
		throw new UnsupportedOperationException("Not implemented yet");
	}
	; // Builder ?
	
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
