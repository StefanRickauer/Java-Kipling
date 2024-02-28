package com.rickauer.kipling;

import org.apache.logging.log4j.*;

public class Kipling {
	
	private static final String PROGRAM = "Kipling";
	private static final String VERSION = "0.1";
	private static final String PROGRAM_AND_VERSION = PROGRAM + " [Version " + VERSION + "]";
	
	private static Logger kiplingLogger = LogManager.getLogger(Kipling.class.getName());
	
	
	public static void main(String[] args) {
		
		try {
			System.out.println(PROGRAM_AND_VERSION);
			kiplingLogger.info("Starting...");
			kiplingLogger.info("Calling Launch4J...");
			// Do stuff here
			kiplingLogger.info("Called Launch4J.");
			kiplingLogger.info("Finished.");
		} catch (Exception e) {
			kiplingLogger.error("Could not finish task.", e);
		}
	}
}
