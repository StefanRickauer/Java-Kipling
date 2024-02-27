package com.rickauer.kipling;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Launch4jAdapter {
	
	private static Logger launch4jAdapterLogger = LogManager.getLogger(Launch4jAdapter.class.getName());
	private final String launch4jcInstallLocation;
	
	public Launch4jAdapter(String launch4jcInstallLocation) {
		this.launch4jcInstallLocation = launch4jcInstallLocation;
	}
	
	public void createExecutable(String configFileLocation) {
		
		launch4jAdapterLogger.info("Executing createExecutable()...");
		
		try {
			ProcessBuilder builder = new ProcessBuilder();
			builder.command(launch4jcInstallLocation, configFileLocation);
			Process process = builder.start();
			process.waitFor();
			
		} catch (IOException e) {
			launch4jAdapterLogger.error("createExecutable(String configFileLocation): I/O operation failed.", e);
			throw new RuntimeException("Error executing '" + launch4jcInstallLocation + "' '" + configFileLocation + "'.", e);
		} catch (InterruptedException e) {
			launch4jAdapterLogger.error("waitFor(): Thread was interrupted by another thread.", e);
			throw new RuntimeException("Error executing Process.waitFor()", e);
		}
		
		launch4jAdapterLogger.info("Executed createExecutable().");
	}
}
