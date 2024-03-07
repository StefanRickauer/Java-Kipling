package com.rickauer.kipling;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;
import java.io.File;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class Launch4jAdapterTest {

	static Launch4jAdapter adapter, invalidAdapter;
	static File destinaitonFile;
	static String configFileLocation = "C:\\Users\\noNameForM3\\Documents\\Tests\\kiplingTESTgui\\config.xml";
	
	@BeforeAll
	private static void initializeTestData() {
		
		adapter = new Launch4jAdapter("C:\\Program Files (x86)\\Launch4j\\launch4jc.exe");
		invalidAdapter = new Launch4jAdapter("invalid path");
		destinaitonFile = new File("C:\\Users\\noNameForM3\\Documents\\Tests\\kiplingTESTgui\\testGUI.exe");
		
		// Make sure files from previous test runs will be deleted before new test run
		if (destinaitonFile.exists())
			destinaitonFile.delete();
	}
	
	@Test
	void createExecutableTest() {
		
		adapter.createExecutable(configFileLocation);
		assertTrue(destinaitonFile.exists());
	}
	
	@Test
	void createExecutableRaiseIOExceptionTest() {

		Exception runtimeException = assertThrows(RuntimeException.class, () -> invalidAdapter.createExecutable(configFileLocation));
		assertEquals("Error executing 'invalid path' '" + configFileLocation + "'.", runtimeException.getMessage());
	}
}
