package com.rickauer.kipling;

public final class BaseKiplingConfiguration implements KiplingConfiguration {

	private String lauch4jcPath;
	private String configurationFilePath;
	
	public static final BaseKiplingConfiguration processCommandLineArguments(String[] args) {
		
		// process command line args (i.e. evaluate parameters)
		
		return new BaseKiplingConfiguration(args[0], args[1]);		// dummy implementation (enable me to run unit tests)
	}
	
	private BaseKiplingConfiguration(String launch4jcPath, String configurationFilePath) {
		this.lauch4jcPath = launch4jcPath;
		this.configurationFilePath = configurationFilePath;
	}
	
	@Override
	public String getLaunch4jcPath() {
		return lauch4jcPath;
	}

	@Override
	public String getConfigurationFilePath() {
		return configurationFilePath;
	}

	@Override
	public String getJARPath() {
		throw new UnsupportedOperationException("Not implemented yet.");
	}

	@Override
	public String getExecutablePath() {
		throw new UnsupportedOperationException("Not implemented yet.");
	}

}
