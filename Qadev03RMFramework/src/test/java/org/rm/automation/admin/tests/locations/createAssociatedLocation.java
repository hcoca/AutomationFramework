package org.rm.automation.admin.tests.locations;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.rm.automation.base.MyWebDriver;
import org.rm.automation.base.TestBaseSetup;
import org.rm.automation.admin.pageobjects.LoginPage;

public class createAssociatedLocation extends TestBaseSetup {
	public static MyWebDriver myWebDriver;
	private WebDriver driver;
	private LoginPage loginPage;

  @BeforeClass
  public void setUp() throws Exception {
	  driver=myWebDriver.myDriver;
  }

  @Test
  public void testCreateAssociatedLocation() throws Exception {
	  String userName = "qadev03\\userRM01";
	  String password = "Control123!";
	  String name = "Location 2";
	  String displayName = "Loc2";
	  String confRooms = "x1";
	  String roomName = "B201"; 
	  
	  System.out.println("Create Location with a room associated functionality details...");
	  loginPage = new LoginPage(driver)
		  		.SignIn(userName, password)
		  		.SelectLocationsOption()
		  		.clickonAddButton()
		  		.gotoLocationsAssociations()
		  		.associateConferenceRoom(roomName)
		  		.fillFormAndSave(name, displayName)
		  		.SelectIssuesOption()
		  		.SelectLocationsOption()
		  		.verifyLocationsWasCreated(name, displayName, confRooms)
		  		.SignOut();	
	  
	  
	  /*loginPage = new LoginPage(driver);
	  homePage = loginPage.SignIn(userName, password);
	  locationsPage = homePage.clickonLocationsLink();
	  formLocationPage = locationsPage.clickonAddButton();
	  formAssociateRoomPage = formLocationPage.gotoLocationsAssociations();
	  formLocationPage = formAssociateRoomPage.associateConferenceRoom(roomName);
	  locationsPage = formLocationPage.fillFormAndSave(name, displayName);
	  issuesPage = homePage.clickonIssuesLink();
	  locationsPage = homePage.clickonLocationsLink();
	  Assert.assertTrue(locationsPage.verifyLocationName(name), "Location Name doesn't match");
	  Assert.assertTrue(locationsPage.verifyLocationDisplayName(displayName), "Location Display Name doesn't match");
	  Assert.assertTrue(locationsPage.verifyLocationConfRooms(confRooms), "Location COnference Rooms doesn't match");
	  loginPage = homePage.clickonSignOut();   */
  } 
}
