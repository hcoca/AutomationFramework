package org.rm.automation.tablet.tests.homepage;

import java.util.Properties;

import org.rm.automation.base.TestBaseSetup;
import org.rm.automation.tablet.pageobjects.LoginPage;
import org.rm.automation.tablet.pageobjects.homepage.HomePage;
import org.rm.automation.tablet.pageobjects.meetings.MeetingsPage;
import org.rm.automation.utils.ReadPropertyValues;
import org.testng.annotations.Test;

public class test extends TestBaseSetup{
	private Properties settings = ReadPropertyValues
			.getPropertyFile("./Config/settings.properties");
	private String userName = settings.getProperty("username");
	private String password = settings.getProperty("password");
	
	private LoginPage loginPage;
	private HomePage homePage;
	private MeetingsPage meetingsPage;
	
	@Test
	public void test() throws InterruptedException{
		
		LoginPage loginPage = new LoginPage(driver);
		homePage = loginPage.access("http://172.20.208.142:4040/", "roompro\\room", "Control123!", "b21");
		meetingsPage = homePage.selectSchedulePage();
				
		Thread.sleep(10000);
	}
}
