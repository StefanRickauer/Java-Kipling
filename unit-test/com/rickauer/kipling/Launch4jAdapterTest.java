package com.rickauer.kipling;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;
import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class Launch4jAdapterTest {

	private static Logger launch4jAdapterTestlogger = LogManager.getLogger(Launch4jAdapterTest.class.getName());
	static Launch4jAdapter adapter, invalidAdapter;
	static File destinaitonFile;
	static String configFileLocation = "C:\\Users\\noNameForM3\\Documents\\Tests\\kiplingTESTgui\\config.xml";
	
	@BeforeAll
	private static void initializeTestData() {
		
		launch4jAdapterTestlogger.info("Initializing test data...");
		
		adapter = new Launch4jAdapter("C:\\Program Files (x86)\\Launch4j\\launch4jc.exe");
		invalidAdapter = new Launch4jAdapter("invalid path");
		destinaitonFile = new File("C:\\Users\\noNameForM3\\Documents\\Tests\\kiplingTESTgui\\testGUI.exe");
		
		// Make sure files from previous test runs will be deleted before new test run
		if (destinaitonFile.exists())
			destinaitonFile.delete();
		
		launch4jAdapterTestlogger.info("Initialized test data.");
	}
	
	@Test
	void createExecutableTest() {
		
		launch4jAdapterTestlogger.info("Executing createExecutableTest()...");

		adapter.createExecutable(configFileLocation);
		assertTrue(destinaitonFile.exists());
		
		launch4jAdapterTestlogger.info("Executed createExecutableTest().");
	}
	
	@Test
	void createExecutableRaiseIOExceptionTest() {
		launch4jAdapterTestlogger.info("Executing createExecutableRaiseIOExceptionTest()...");

		assertThrows(RuntimeException.class, () -> invalidAdapter.createExecutable(configFileLocation));
		Exception ioe = assertThrows(RuntimeException.class, () -> invalidAdapter.createExecutable(configFileLocation));
		assertEquals("Error executing 'invalid path' '" + configFileLocation + "'.", ioe.getMessage());
		
		launch4jAdapterTestlogger.info("Executed createExecutableRaiseIOExceptionTest().");
	}
}
