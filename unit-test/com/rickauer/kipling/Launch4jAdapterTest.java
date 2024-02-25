package com.rickauer.kipling;

import static org.junit.jupiter.api.Assertions.*;
import java.io.File;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class Launch4jAdapterTest {

	static Launch4jAdapter adapter;
	static File destinaitonFile;
	
	@BeforeAll
	private static void initializeTestData() {
		adapter = new Launch4jAdapter("C:\\Program Files (x86)\\Launch4j\\launch4jc.exe");
		destinaitonFile = new File("C:\\Users\\noNameForM3\\Documents\\Tests\\kiplingTESTgui\\testGUI.exe");
		
		// Make sure files from previous test runs will be deleted before new test run
		if (destinaitonFile.exists())
			destinaitonFile.delete();
	}
	
	@Test
	void createExecutableTest() {
		adapter.createExecutable("C:\\Users\\noNameForM3\\Documents\\Tests\\kiplingTESTgui\\config.xml");
		
		// Necessary, otherwise test will fail because assertion will start before .exe was created
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
		assertTrue(destinaitonFile.exists());
	}

}
