package org.rm.automation.admin.tests.locations;

import java.io.IOException;
import java.util.Properties;
import org.rm.automation.utils.LogManager;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.rm.automation.utils.ReadPropertyValues;
import org.rm.automation.utils.TestBaseSetup;
import org.rm.automation.utils.api.LocationsRequests;
import org.rm.automation.admin.pageobjects.HomePage;
import org.rm.automation.admin.pageobjects.LoginPage;
import org.rm.automation.admin.pageobjects.locations.FormLocationPage;
import org.rm.automation.admin.pageobjects.locations.IssuesPage;
import org.rm.automation.admin.pageobjects.locations.LocationsPage;

public class createChildLocation extends TestBaseSetup{
	private Properties settings = ReadPropertyValues
			.getPropertyFile("./Config/settings.properties");
	private LoginPage loginPage;
	private HomePage homePage;
	private LocationsPage locationsPage;
	private IssuesPage issuesPage;
	private FormLocationPage formLocationPage;
	String userName = settings.getProperty("username");
	String password = settings.getProperty("password");
	String nameParent = "Location Parent";
	String displayNameParent = "LocParent";
	String nameChild = "Location Child";
	String displayNameChild = "LocChild";	
	String confRoomsChild = "x0";
	 @BeforeMethod
		public void Preconditions()
		{
			System.out.println("Before Test - Create Child Location");
			try {			
				LocationsRequests.postLocation(nameParent, displayNameParent);
				LogManager.info("Executing before test method of createChildLocation, creating parent location");
		
			} 
			catch (UnsupportedOperationException | IOException e) {
				LogManager.info("Failed to execute before test method of createChildLocation, parent location was not created");
				e.printStackTrace();
			}
		}
	 @Test
	  public void testCreateChildLocation() throws Exception {
		  
		  System.out.println("Create Basic Location");
		  LogManager.info("Executing createBasicLocation Test Case");
		  loginPage = new LoginPage(driver);
		  homePage = loginPage.SignIn(userName, password);
		  locationsPage	= homePage.SelectLocationsOption();
		  formLocationPage = locationsPage.clickonAddButton();		  
		  homePage = formLocationPage
				  		.setParentLocation(displayNameParent)
				  		.fillFormAndSave(nameChild, displayNameChild);
		  issuesPage = homePage.SelectIssuesOption();
		  locationsPage = homePage.SelectLocationsOption();
		  homePage = locationsPage.verifyChangesWereMade(nameChild, displayNameChild, confRoomsChild)
				  					.clickonLocation(displayNameChild)
				  					.verifyCorrectParent(nameParent);
		  homePage.SignOut();	  	  		
	  } 
	  @AfterMethod
		public void Postconditions()
		{
			System.out.println("After Test - Create Child Location");
			String idParent = "";
			String idChild = "";
			try {			
				idParent = LocationsRequests.getLocationId(nameParent);
				LocationsRequests.deleteLocation(idParent);
				idChild = LocationsRequests.getLocationId(nameChild);
				LocationsRequests.deleteLocation(idChild);
				LogManager.info("Executing after test method of createChildLocation, removing locations created");
		
			} 
			catch (UnsupportedOperationException | IOException e) {
				LogManager.info("Failed to execute after test method of createChildLocation, locations created were not removed");
				e.printStackTrace();
			}
		}

}
