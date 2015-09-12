package org.rm.automation.base;

import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.rm.automation.utils.BrowserManager;
import org.rm.automation.utils.LogManager;
import org.rm.automation.utils.ReadPropertyValues;
import org.testng.IReporter;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.annotations.Listeners;
import org.testng.xml.XmlSuite;

@Listeners({org.rm.automation.base.TestBaseSetup.class})
public class TestBaseSetup implements ISuiteListener,IReporter{
	
	protected static WebDriver driver = null;
	private Properties settings = ReadPropertyValues
			.getPropertyFile("./Config/settings.properties");
	private String browser = settings.getProperty("browser");
	
	@Override
	public void onStart(ISuite arg0) {
		try {
			driver = BrowserManager.getDriver(browser);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			LogManager.info("Browser <" + browser + "> was opened");		
		} 
		catch (Exception e) {
			System.out.println("Error....." + e.getStackTrace());
		}
    }
	
	@Override
    public void generateReport(List<XmlSuite> arg0, List<ISuite> arg1, String arg2) {           
    	driver.quit();
    	LogManager.info("Browser <" + browser + "> was closed");
    }
	
	@Override
	public void onFinish(ISuite suite) {}
}
