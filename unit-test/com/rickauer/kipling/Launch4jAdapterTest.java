package com.rickauer.kipling;

import static org.junit.jupiter.api.Assertions.*;
import java.io.File;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class Launch4jAdapterTest {

	static Launch4jAdapter adapter;
	static File createdExecutable;
	
	@BeforeAll
	private static void initializeTestData() {
		adapter = new Launch4jAdapter("C:\\Program Files (x86)\\Launch4j\\launch4jc.exe");
		createdExecutable = new File("C:\\Users\\noNameForM3\\Documents\\Tests\\kiplingTESTgui\\testGUI.exe");
		
		// Make sure files from previous test runs will be deleted before new test run
		if (createdExecutable.exists())
			createdExecutable.delete();
	}
	@Test
	void createExeTest() {
		adapter.createExe("C:\\Users\\noNameForM3\\Documents\\Tests\\kiplingTESTgui\\config.xml");
		
		// Necessary, otherwise test will fail because assertion will start before .exe was created
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
		assertTrue(createdExecutable.exists());
	}

}
