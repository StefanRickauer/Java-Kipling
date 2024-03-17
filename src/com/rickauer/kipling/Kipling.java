package com.rickauer.kipling;

import org.apache.logging.log4j.*;

public final class Kipling {
	
	private static final String PROGRAM = "Kipling";
	private static final String VERSION = "1.0";
	private static final String PROGRAM_AND_VERSION = PROGRAM + " [Version " + VERSION + "]";
	
	private static Logger kiplingLogger = LogManager.getLogger(Kipling.class.getName());
	
	public static void main(String[] args) {
		
		try {
			System.out.println(PROGRAM_AND_VERSION);
			kiplingLogger.info("Starting...");
			
			kiplingLogger.info("Processing command line arguments ...");
			BaseKiplingConfiguration configuration = BaseKiplingConfiguration.processCommandLineArguments(args);
			kiplingLogger.info("Processed command line arguments.");
			
			kiplingLogger.info("Calling Launch4J...");
			Launch4jAdapter adapter = new Launch4jAdapter(configuration.getLaunch4jcPath());
			adapter.createExecutable(configuration.getConfigurationFilePath());
			kiplingLogger.info("Called Launch4J.");

			kiplingLogger.info("Finished.");
			System.out.println("Executable successfully created.");
		} catch (Exception e) {
			BaseKiplingConfiguration.displayUsageMessage();
			kiplingLogger.error("Could not finish task.", e);
		}
	}
}
