package com.rickauer.kipling;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

public final class KiplingSystemTest {
	
	public static void main(String[] args) {
		
		testKiplingWithJarAndConfiguration();
		
	}
	
	static void testKiplingWithJarAndConfiguration() {
		
		String configFilePath = System.getProperty("user.dir") + "\\test-data\\gui\\config.xml";
		String exeFilePath = System.getProperty("user.dir") + "\\test-data\\gui\\guiTest.exe";
		
		if (Files.exists(Paths.get(exeFilePath))) {
			File file = new File(exeFilePath);
			file.delete();
		}
		
		String[] commandLineArguments = { 
				"--lnch", 
				"C:\\Program Files (x86)\\Launch4j\\launch4jc.exe", 
				"--conf", 
				configFilePath
				};
		
		Kipling.main(commandLineArguments);
		
		if (Files.exists(Paths.get(exeFilePath))) {
			System.out.println("Successfully created .exe file. Trying to run exe.");
			
			try {
				ProcessBuilder builder = new ProcessBuilder();
				builder.command(exeFilePath);
				
				@SuppressWarnings("unused")
				Process process = builder.start();
			} catch (Exception e) {
				System.err.println("Error running .exe file.");
				e.printStackTrace();
			}
			
		} else {
			System.err.println("Error: Could not create .exe file.");
		}
	}
}
