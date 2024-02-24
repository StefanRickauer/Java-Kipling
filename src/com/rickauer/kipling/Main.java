package com.rickauer.kipling;

import org.apache.logging.log4j.*;

public class Main {
	
	private static Logger mainLogger = LogManager.getLogger(Main.class.getName());
	
	public static void main(String[] args) {
		try {
			mainLogger.info("Starting Kipling...");
			// To stuff here
			mainLogger.info("Finished Kipling.");
		} catch (Exception e) {
			mainLogger.error("Could not finish task.", e);
		}
	}
}
