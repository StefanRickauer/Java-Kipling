package com.rickauer.kipling;

import org.apache.logging.log4j.*;

import com.rickauer.kipling.configuration.BaseKiplingConfiguration;
import com.rickauer.kipling.utils.ConfigFileContentExtractor;
import com.rickauer.kipling.utils.Launch4jAdapter;

public final class Kipling {
	
	private static final String PROGRAM = "Kipling";
	private static final String VERSION = "1.0";
	private static final String PROGRAM_AND_VERSION = PROGRAM + " [Version " + VERSION + "]";
	
	private static Logger kiplingLogger = LogManager.getLogger(Kipling.class.getName());
	
	public static void main(String[] args) {
		
		try {
			System.out.println(PROGRAM_AND_VERSION);
			kiplingLogger.info(PROGRAM_AND_VERSION + " starting ...");
			
			kiplingLogger.info("Processing command line arguments ...");
			BaseKiplingConfiguration configuration = BaseKiplingConfiguration.processCommandLineArguments(args);
			kiplingLogger.info("Processed command line arguments.");
			
			kiplingLogger.info("Calling Launch4J...");
			Launch4jAdapter adapter = new Launch4jAdapter(configuration.getLaunch4jcPath());
			adapter.createExecutable(configuration.getConfigurationFilePath());
			kiplingLogger.info("Called Launch4J.");

			kiplingLogger.info(PROGRAM_AND_VERSION + " finished.");
			System.out.println("Executable successfully created and saved to: '" + ConfigFileContentExtractor.getExePath(configuration.getConfigurationFilePath()) + "'.");
		} catch (Exception e) {
			BaseKiplingConfiguration.displayUsageMessage();
			kiplingLogger.error("Error: Could not finish task.", e);
		}
	}
}