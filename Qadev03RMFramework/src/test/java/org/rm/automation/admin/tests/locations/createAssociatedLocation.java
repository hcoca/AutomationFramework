package org.rm.automation.admin.tests.locations;

import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.rm.automation.utils.LogManager;
import org.rm.automation.utils.ReadPropertyValues;
import org.rm.automation.utils.api.LocationsRequests;
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
	private LoginPage loginPage;
	private HomePage homePage;
	private LocationsPage locationsPage;
	private IssuesPage issuesPage;
	private FormLocationPage formLocationPage;
	private FormAssociateRoomPage formAssociateRoomPage;
	String userName = settings.getProperty("username");
	String password = settings.getProperty("password");
	String name = "Location 2";
	String displayName = "Loc2";
	String confRooms = "x1";
	String roomName = "B201"; 

  @Test
  public void testCreateAssociatedLocation() throws Exception {
	  
	  String userName = settings.getProperty("username");
	  String password = settings.getProperty("password");
	  String name = "Location 2";
	  String displayName = "Loc2";
	  String confRooms = "x1";
	  String roomName = "B201"; 
	  
	  System.out.println("Create Location with a room associated");
	  LogManager.info("Executing createAssociatedLocation Test Case");
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
  @AfterTest
	public void Postconditions()
	{
		System.out.println("After Test - Create Basic Location");
		String id = "";
		try {
			id = LocationsRequests.getLocationId(name);
			LocationsRequests.deleteLocation(id);
			LogManager.info("Executing after test method of createAssociatedLocation, removing location created");
	
		} 
		catch (UnsupportedOperationException | IOException e) {
			LogManager.info("Failed to execute after test method of createAssociatedLocation, location created was not removed");
			e.printStackTrace();
		}
	}
}
