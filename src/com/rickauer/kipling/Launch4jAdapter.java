package com.rickauer.kipling;

import java.io.IOException;

public class Launch4jAdapter {
	
	private final String launch4jcInstallLocation;
	
	public Launch4jAdapter(String launch4jcInstallLocation) {
		this.launch4jcInstallLocation = launch4jcInstallLocation;
	}
	
	public void createExe(String configFileLocation) {
		
		try {
			ProcessBuilder builder = new ProcessBuilder();
			builder.command(launch4jcInstallLocation, configFileLocation);
			builder.start();
			
			
		} catch (IOException e) {
			throw new RuntimeException("Error executing '" + launch4jcInstallLocation + " " + configFileLocation + "'.", e);
		}
	}
}
