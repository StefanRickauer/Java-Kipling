package com.rickauer.kipling;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BaseKiplingConfigurationTest {

	@Test
	void processCommandLineArgumentsTest() {
		
		String[] mockArgs = new String[] {"--lnch", "launch4jc.exe", "--conf", "config.xml"};	
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
}
