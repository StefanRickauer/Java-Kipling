package com.rickauer.kipling;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class BaseConfigFileConfiguration implements ConfigFileConfiguration {

	private static Logger baseConfigFileConfigurationLogger = LogManager.getLogger(BaseConfigFileConfiguration.class.getName());
	
	public static void saveConfigurationFile() {
		throw new UnsupportedOperationException("Not implemented yet");
	}
	; // Builder ?
	
	@Override
	public String getHeaderType() {
		throw new UnsupportedOperationException("Not implemented yet");
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
		throw new UnsupportedOperationException("Not implemented yet");
	}
}
