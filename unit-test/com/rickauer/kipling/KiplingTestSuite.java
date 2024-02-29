package com.rickauer.kipling;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({Launch4jAdapterTest.class, BaseKiplingConfigurationTest.class})
public class KiplingTestSuite {
	// No implementation needed. 
}
