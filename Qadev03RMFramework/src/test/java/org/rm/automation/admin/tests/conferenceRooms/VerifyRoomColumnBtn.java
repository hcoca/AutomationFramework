package org.rm.automation.admin.tests.conferenceRooms;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;

import java.util.Properties;

import org.openqa.selenium.WebElement;
import org.rm.automation.admin.pageobjects.HomePage;
import org.rm.automation.admin.pageobjects.LoginPage;
import org.rm.automation.admin.pageobjects.conferenceRooms.ConferenceRoomsPage;
import org.rm.automation.utils.ReadPropertyValues;
import org.rm.automation.utils.TestBaseSetup;


public class VerifyRoomColumnBtn extends TestBaseSetup{
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
	
	@Test
	public void verifyRoomColumnBtn(){
		objLogin = new LoginPage(driver);
		objLogin.SignIn(userName, password);
		objHomePage = new HomePage(driver);
		objHomePage.SelectRoomsOption();
		objConferenceRooms = new ConferenceRoomsPage(driver);

		WebElement roomColumnBtn = objConferenceRooms.getRoomColumnBtn();
		AssertJUnit.assertTrue(roomColumnBtn.isDisplayed());
	}
	
//	@AfterTest
//    public void afterTest() {
//        driver.quit();          
//    }
}
