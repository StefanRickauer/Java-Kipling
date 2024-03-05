package com.rickauer.kipling;

public final class BaseConfigFileConfiguration implements ConfigFileConfiguration {
	; // Logger nicht vergessen
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
