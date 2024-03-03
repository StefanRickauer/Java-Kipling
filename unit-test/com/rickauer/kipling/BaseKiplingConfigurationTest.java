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
	
	@Test
	void processCommandLineArgumentsRaiseExceptionTest1() {
		
		System.out.println("BaseKiplingConfigurationTest: Executing processCommandLineArgumentsRaiseExceptionTest1() ...");
		
		String[] emptyArgArray = new String[0];
		
		Exception illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> BaseKiplingConfiguration.processCommandLineArguments(emptyArgArray));
		assertEquals("No arguments detected.", illegalArgumentException.getMessage());
		
		System.out.println("BaseKiplingConfigurationTest: Executed processCommandLineArgumentsRaiseExceptionTest1().");
	}
	
	@Test
	void processCommandLineArgumentsRaiseExceptionTest2() {
		
		System.out.println("BaseKiplingConfigurationTest: Executing processCommandLineArgumentsRaiseExceptionTest2() ...");
		
		String[] invalidArgumentArray = new String[] {"--invalid"};
		
		Exception illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> BaseKiplingConfiguration.processCommandLineArguments(invalidArgumentArray));
		assertEquals("Invalid argument: --invalid", illegalArgumentException.getMessage());
		
		System.out.println("BaseKiplingConfigurationTest: Executed processCommandLineArgumentsRaiseExceptionTest2().");
	}
}
