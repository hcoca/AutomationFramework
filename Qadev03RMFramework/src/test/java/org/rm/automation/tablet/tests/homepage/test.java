package org.rm.automation.tablet.tests.homepage;

import java.util.Properties;

import org.rm.automation.tablet.pageobjects.LoginPage;
import org.rm.automation.tablet.pageobjects.homepage.HomePage;
import org.rm.automation.tablet.pageobjects.meetings.MeetingsPage;
import org.rm.automation.utils.ReadPropertyValues;
import org.rm.automation.utils.TestBaseSetup;
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
		homePage = loginPage.access("http://localhost:4040/", "571Network\\Administrator", "Pilot571david77", "room571");
		meetingsPage = homePage.selectSchedulePage();
				
		Thread.sleep(10000);
	}
}
