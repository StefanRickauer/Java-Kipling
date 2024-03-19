package com.rickauer.kipling.configuration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.rickauer.kipling.utils.ConfigFileChecker;
import com.rickauer.kipling.utils.ConfigFileCreator;

public final class BaseKiplingConfiguration implements KiplingConfiguration {

	private static Logger baseConfigLogger = LogManager.getLogger(BaseKiplingConfiguration.class.getName());
	private final String lauch4jcPath;
	private final String configurationFilePath;
	
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
				case "--lnch" -> {
					i++;
					launch4jc = args[i];
				}
				case "--conf" -> {
					i++;
					if (!ConfigFileChecker.isConfigurationFileValid(args[i])) {
						processInvalidInput("Invalid configuration file: ", args[i]);
					}
					configuration = args[i];
				}
				case "--ppt" -> {
					BaseConfigFileConfiguration config = ConfigFileCreator.retrieveConfiguration();
					ConfigFileCreator.saveConfigurationFile(config);
					configuration = config.getConfigurationFilePath();
				}
				default -> {
					processInvalidInput("Invalid argument: ", args[i]);
				}
			}
		}
		
		baseConfigLogger.info("Executed processCommandLineArguments().");
		
		return new BaseKiplingConfiguration(launch4jc, configuration);		
	}
	
	private static void processInvalidInput(String message, String argument) {
		System.err.println(message + argument);
		baseConfigLogger.fatal(message + argument);
		throw new IllegalArgumentException(message + argument);
	}
	
	public static void displayUsageMessage() {
		System.out.println("""
				
				Usage: Kipling <option> <argument>
				The following options are possible: 
					--lnch <argument> 
						Argument has to be the path to launch4jc.exe
					--conf <argument>
						Argument has to be the path to the config (.xml) file
						
				Enter interactive mode (create configuration file):
					--ppt
						You will be prompted to provide the information necessary to create a config.xml file from scratch
				""");
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
}
