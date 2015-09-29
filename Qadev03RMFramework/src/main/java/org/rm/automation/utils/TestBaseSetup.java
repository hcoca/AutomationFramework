package org.rm.automation.utils;

import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.IReporter;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.annotations.Listeners;
import org.testng.xml.XmlSuite;

/**
 * The TestBaseSetup class is the parent class of every test case in this framework.
 * Implements ISuiteListener and IReporter, both from the TestNG library, and because of that,
 * it needs to overwrite the onStart, onFinish and generateReport methods.
 * 
 * This class also uses the @Listener notation calling to itself, so that it can be used in the POM listeners list 
 * of the maven-surefire-plugin each time that it runs.
 */
@Listeners({org.rm.automation.utils.TestBaseSetup.class})
public class TestBaseSetup implements ISuiteListener, IReporter{
	
	protected static WebDriver driver = null;
	private Properties settings = ReadPropertyValues
			.getPropertyFile("./Config/settings.properties");
	private Properties settingsPom = ReadPropertyValues
			.getPropertyFile("./target/classes/settings.properties");
	private String browser = settings.getProperty("browser");
	private String browserCmd = settingsPom.getProperty("browserCmd");
	
	/* 
	 * Each time an ISuite is triggered a driver is created so that all test cases are managed
	 * under this one, it means that they will run under the same browser.   
	 */
	@Override
	public void onStart(ISuite arg0) {
		try {
			if(browserCmd.isEmpty())
			{
				driver = BrowserManager.getDriver(browser);
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				driver.manage().window().maximize();
				LogManager.info("Browser: " + browser + " was opened");
				LogManager.info("Suite name: " + arg0.getName() + " starting");
			}
			else
			{
				driver = BrowserManager.getDriver(browserCmd);
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				driver.manage().window().maximize();
				LogManager.info("Browser: " + browser + " was opened");
				LogManager.info("Suite name: " + arg0.getName() + " starting");
			}
		} 
		catch (Exception e) {
			System.out.println("Error....." + e.getStackTrace());
		}
    }
	
	/* 
	 * The method generates a report for all the ISuites after their execution.
	 * 
	 * Update: the driver.quit() method has been moved here so that in this way it will be triggered
	 * just after the overridden method generateReport is called (generateReport always will be triggered 
	 * after a list of suites)
	 */
	@Override
    public void generateReport(List<XmlSuite> arg0, List<ISuite> arg1, String arg2) {
		driver.quit();
    	LogManager.info("Browser: " + browser + " was closed");
    	for(ISuite suite : arg1){
    		LogManager.info("Test " + suite.getName() + " report location: " + suite.getOutputDirectory());
    	}
    }
	
	/* 
	 * The point of where the driver is closed.
	 */
	@Override
	public void onFinish(ISuite suite) { 
//		driver.quit(); 
	}
}
