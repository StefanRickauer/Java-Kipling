package com.rickauer.kipling;

import org.apache.logging.log4j.*;

public class Main {
	
	private static final String PROGRAM = "Kipling";
	private static final String VERSION = "0.05";
	private static final String PROGRAM_AND_VERSION = PROGRAM + " [Version " + VERSION + "]";
	
	private static Logger mainLogger = LogManager.getLogger(Main.class.getName());
	
	
	public static void main(String[] args) {
		try {
			mainLogger.info(PROGRAM_AND_VERSION);
			mainLogger.info("Starting...");
			mainLogger.info("Calling Launch4J...");
			// Do stuff here
			mainLogger.info("Called Launch4J.");
			mainLogger.info("Finished.");
		} catch (Exception e) {
			mainLogger.error("Could not finish task.", e);
		}
	}
}
