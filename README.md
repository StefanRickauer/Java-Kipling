Kipling is a console application to build executables. It will use the command line interface of 'Launch4J' within an Adapter class.

In case you download this project, you'll need:
  - Launch4J Version 3.50    (https://sourceforge.net/projects/launch4j/files/launch4j-3/3.50/)
  - Log4J Version 2.23.0     (https://logging.apache.org/log4j/2.x/download.html)

Also add the following files to the classpath:
  - log4j-api-2.23.0.jar
  - log4j-core-2.23.0.jar

Finally, you need to add the 'config' folder to the classpath.


Important:

Since the test data is tailored for my system, the system test will fail after downloading the project. This is due to the 
contents of config.xml within the test-data folder. 

SOLUTION: Run the test suite twice. 
The test suite contains tests that will create new config files tailored for the respective system. You need to run the suite twice
because of the order the tests are being executed (at least on my system).
Thereafter, the system test should run as expected.