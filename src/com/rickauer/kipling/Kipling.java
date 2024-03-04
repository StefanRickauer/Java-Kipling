package com.rickauer.kipling;

import org.apache.logging.log4j.*;

public class Kipling {
	
	private static final String PROGRAM = "Kipling";
	private static final String VERSION = "0.4";
	private static final String PROGRAM_AND_VERSION = PROGRAM + " [Version " + VERSION + "]";
	
	private static Logger kiplingLogger = LogManager.getLogger(Kipling.class.getName());
	
	public static void main(String[] args) {
		
		try {
			System.out.println(PROGRAM_AND_VERSION);
			kiplingLogger.info("Starting...");
			kiplingLogger.info("Calling Launch4J...");
			
			BaseKiplingConfiguration configuration = BaseKiplingConfiguration.processCommandLineArguments(args);
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
