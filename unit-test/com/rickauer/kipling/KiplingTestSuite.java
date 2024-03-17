package com.rickauer.kipling;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

import com.rickauer.kipling.configuration.BaseConfigFileConfigurationTest;
import com.rickauer.kipling.configuration.BaseKiplingConfigurationTest;
import com.rickauer.kipling.utils.*;

@Suite
@SelectClasses({Launch4jAdapterTest.class, BaseKiplingConfigurationTest.class, BaseConfigFileConfigurationTest.class, ConfigFileCreatorTest.class})
public class KiplingTestSuite {
	// No implementation needed. 
}
