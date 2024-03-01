package com.rickauer.kipling;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BaseKiplingConfigurationTest {

	@Test
	void processCommandLineArgumentsTest() {
		
		System.out.println("BaseKiplingConfigurationTest: Executing processCommandLineArgumentsTest() ...");
		
		String[] mockArgs = new String[] {"launch4jc.exe", "config.xml"};
		
		BaseKiplingConfiguration baseKiplingConfiguration = BaseKiplingConfiguration.processCommandLineArguments(mockArgs);
		
		assertEquals(mockArgs[0], baseKiplingConfiguration.getLaunch4jcPath());
		assertEquals(mockArgs[1], baseKiplingConfiguration.getConfigurationFilePath());
		
		System.out.println("BaseKiplingConfigurationTest: Executed processCommandLineArgumentsTest().");
	}
}
