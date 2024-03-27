package com.rickauer.kipling;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;

import com.rickauer.kipling.configuration.BaseKiplingConfiguration;

class KiplingTest {

	@Test
	void callKiplingWihoutArgs() {
		
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
