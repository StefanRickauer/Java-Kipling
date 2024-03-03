package com.rickauer.kipling;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class BaseKiplingConfiguration implements KiplingConfiguration {

	private static Logger baseConfigLogger = LogManager.getLogger(BaseKiplingConfiguration.class.getName());
	private String lauch4jcPath;
	private String configurationFilePath;
	
	public static final BaseKiplingConfiguration processCommandLineArguments(String[] args) {
		
		baseConfigLogger.info("Executing processCommandLineArguments() ...");
		
		if (args.length == 0) {
			baseConfigLogger.fatal("No arguments detected.");
			System.err.println("Error: No arguments detected.");
			throw new IllegalArgumentException("No arguments detected.");
		}
		
		String launch4jc = "", configuration = "";
		
		for (int i = 0; i < args.length; i++) {
			switch(args[i]) {
				case "--l" -> {
					i++;
					launch4jc = args[i];
				}
				case "--c" -> {
					i++;
					configuration = args[i];
				}
				default -> {
					System.err.println("Invalid argument: " + args[i]);
					baseConfigLogger.fatal("Invalid argument: " + args[i]);
					throw new IllegalArgumentException("Invalid argument: " + args[i]);
				}
			}
		}
		
		baseConfigLogger.info("Executed processCommandLineArguments().");
		
		return new BaseKiplingConfiguration(launch4jc, configuration);		
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
