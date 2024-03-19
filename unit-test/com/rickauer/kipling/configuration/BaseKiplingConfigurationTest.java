package com.rickauer.kipling.configuration;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;

public final class BaseKiplingConfigurationTest {

	@Test
	void processCommandLineArgumentsTest() {		// Test will fail until config file checker is implemented 
		
		String configFilePath = System.getProperty("user.dir") + "\\test-data\\gui" + "\\config.xml";
		String[] mockArgs = new String[] {"--lnch", "launch4jc.exe", "--conf", configFilePath};	
		BaseKiplingConfiguration baseKiplingConfiguration = BaseKiplingConfiguration.processCommandLineArguments(mockArgs);
		
		assertEquals(mockArgs[1], baseKiplingConfiguration.getLaunch4jcPath());
		assertEquals(mockArgs[3], baseKiplingConfiguration.getConfigurationFilePath());
	}
	
	@Test
	void processCommandLineArgumentsRaiseExceptionTest1() {
		
		String[] emptyArgArray = new String[0];
		
		Exception illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> BaseKiplingConfiguration.processCommandLineArguments(emptyArgArray));
		assertEquals("No arguments detected.", illegalArgumentException.getMessage());
	}
	
	@Test
	void processCommandLineArgumentsRaiseExceptionTest2() {
		
		String[] invalidArgumentArray = new String[] {"--invalid"};
		
		Exception illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> BaseKiplingConfiguration.processCommandLineArguments(invalidArgumentArray));
		assertEquals("Invalid argument: --invalid", illegalArgumentException.getMessage());
	}
	
	@Test
	void displayUsageMessageTest() {
		
		String expectedOutput = """
				
				Usage: Kipling <option> <argument>
				The following options are possible: 
					--lnch <argument> 
						Argument has to be the path to launch4jc.exe
					--conf <argument>
						Argument has to be the path to the config (.xml) file
						
				Enter interactive mode (create configuration file):
					--ppt
						You will be prompted to provide the information necessary to create a config.xml file from scratch
				""";

		PrintStream standardSystemOut = System.out;
		ByteArrayOutputStream redirectedSystemOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(redirectedSystemOut));
        
        BaseKiplingConfiguration.displayUsageMessage();
        
        String acutalOutput = redirectedSystemOut.toString();
		assertEquals(expectedOutput.trim(), acutalOutput.trim());		// trim(): Otherwise comparison will fail due to additional line (which might be caused by redirecting output)
		
		// Stop redirecting output 
		System.setOut(standardSystemOut);
	}
}
