package org.rm.automation.admin.tests.locations;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.rm.automation.utils.ReadPropertyValues;
import org.rm.automation.base.MyWebDriver;
import org.rm.automation.base.TestBaseSetup;
import org.rm.automation.admin.pageobjects.LoginPage;
import org.rm.automation.admin.pageobjects.HomePage;
import org.rm.automation.admin.pageobjects.locations.LocationsPage;
import org.rm.automation.admin.pageobjects.locations.FormLocationPage;
import org.rm.automation.admin.pageobjects.locations.IssuesPage;
import org.rm.automation.admin.pageobjects.locations.FormAssociateRoomPage;

public class createAssociatedLocation extends TestBaseSetup {
	private Properties settings = ReadPropertyValues
			.getPropertyFile("./Config/settings.properties");
	public static MyWebDriver myWebDriver;
	private WebDriver driver;
	private LoginPage loginPage;
	private HomePage homePage;
	private LocationsPage locationsPage;
	private IssuesPage issuesPage;
	private FormLocationPage formLocationPage;
	private FormAssociateRoomPage formAssociateRoomPage;

  @BeforeClass
  public void setUp() throws Exception {
	  driver=myWebDriver.myDriver;
  }

  @Test
  public void testCreateAssociatedLocation() throws Exception {
	  
	  String userName = settings.getProperty("username");
	  String password = settings.getProperty("password");
	  String name = "Location 2";
	  String displayName = "Loc2";
	  String confRooms = "x1";
	  String roomName = "B201"; 
	  
	  System.out.println("Create Location with a room associated functionality details...");
	  loginPage = new LoginPage(driver);
	  homePage = loginPage.SignIn(userName, password);
	  locationsPage = homePage.SelectLocationsOption();
	  formLocationPage = locationsPage.clickonAddButton();
	  formAssociateRoomPage = formLocationPage.gotoLocationsAssociations();
	  formLocationPage = formAssociateRoomPage.associateConferenceRoom(roomName);
	  homePage = formLocationPage.fillFormAndSave(name, displayName);
	  issuesPage = homePage.SelectIssuesOption();
	  locationsPage = homePage.SelectLocationsOption();
      homePage = locationsPage.verifyLocationsWasCreated(name, displayName, confRooms);
	  homePage.SignOut();	 	  
  } 
}
