package org.rm.automation.admin.tests.conferenceRooms;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.rm.automation.admin.pageobjects.HomePage;
import org.rm.automation.admin.pageobjects.LoginPage;
import org.rm.automation.admin.pageobjects.conferenceRooms.ConferenceRoomsPage;
import org.rm.automation.base.TestBaseSetup;
import org.rm.automation.utils.ReadPropertyValues;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class VerifyTotalConferenceRooms extends TestBaseSetup{
	private Properties settings = ReadPropertyValues
			.getPropertyFile("./Config/settings.properties");
	String userName = settings.getProperty("username");
	String password = settings.getProperty("password");
	
//	WebDriver driver;
	LoginPage objLogin;
	HomePage objHomePage;
	ConferenceRoomsPage objConferenceRooms;
	
//	@BeforeTest
//	public void setup(){
//		driver = new FirefoxDriver();
//		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//		driver.get("http://localhost:4040//admin");
////		driver.manage().window().maximize();
//	}
	
//	@BeforeClass
//	public void setUp() {
//		driver=getDriver();
////		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//	}
	
	@Test(priority = 2)
	public void verifyTotalConferenceRooms(){
		objLogin = new LoginPage(driver);
		objLogin.SignIn(userName, password);
		objHomePage = new HomePage(driver);
		objHomePage.SelectRoomsOption();
		objConferenceRooms = new ConferenceRoomsPage(driver);
		
		String actualResult = objConferenceRooms.getTotalItemsLabelValue();
		String expectedResult = "Total Items: 3";
		
		AssertJUnit.assertEquals(actualResult, expectedResult);
	}
	
//	@AfterTest
//    public void afterTest() {
//        driver.quit();          
//    }
}
