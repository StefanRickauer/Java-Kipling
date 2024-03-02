package com.rickauer.kipling;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BaseKiplingConfigurationTest {

	@Test
	void processCommandLineArgumentsTest() {
		
		System.out.println("BaseKiplingConfigurationTest: Executing processCommandLineArgumentsTest() ...");
		
		String[] mockArgs = new String[] {"--l", "launch4jc.exe", "--c", "config.xml"};
		
		BaseKiplingConfiguration baseKiplingConfiguration = BaseKiplingConfiguration.processCommandLineArguments(mockArgs);
		
		assertEquals(mockArgs[1], baseKiplingConfiguration.getLaunch4jcPath());
		assertEquals(mockArgs[3], baseKiplingConfiguration.getConfigurationFilePath());
		
		System.out.println("BaseKiplingConfigurationTest: Executed processCommandLineArgumentsTest().");
	}
}
