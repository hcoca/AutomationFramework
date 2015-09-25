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
public class editLocation extends TestBaseSetup{
	private Properties settings = ReadPropertyValues
			.getPropertyFile("./Config/settings.properties");
	private LoginPage loginPage;
	private HomePage homePage;
	private LocationsPage locationsPage;
	private IssuesPage issuesPage;
	private FormLocationPage formLocationPage;
	String userName = settings.getProperty("username");
	String password = settings.getProperty("password");
	String name = "LocationToEdit";
	String displayName = "LocToEdit";
	String confRooms = "x0";
	String newName = "LocationRenamed";
	String newDisplayName = "LocRenamed";
	String description = "This location was edited";
	@BeforeMethod
	public void Preconditions()
	{
		System.out.println("Before Test - Edit Location");
		try {			
			LocationsRequests.postLocation(name, displayName);
			LogManager.info("Executing before test method of editLocation, creating location");
	
		} 
		catch (UnsupportedOperationException | IOException e) {
			LogManager.info("Failed to execute before test method of editLocation, location was not created");
			e.printStackTrace();
		}
	}
	@Test
	  public void testEditLocation() throws Exception {
		  
		  System.out.println("Edit Location");
		  LogManager.info("Executing editLocation Test Case");
		  loginPage = new LoginPage(driver);
		  homePage = loginPage.SignIn(userName, password);
		  locationsPage	= homePage.SelectLocationsOption();
		  formLocationPage = locationsPage.clickonLocation(displayName);		  
		  homePage = formLocationPage.fillFormAndSave(newName, newDisplayName, description);
		  issuesPage = homePage.SelectIssuesOption();
		  locationsPage = homePage.SelectLocationsOption()
				  				  .verifyChangesWereMade(newName, newDisplayName, confRooms);
		  homePage.SignOut();	  	  		
	  } 
	@AfterMethod
	public void Postconditions()
	{
		System.out.println("After Test - Edit Location");
		String id = "";
		try {			
			id = LocationsRequests.getLocationId(newName);
			LocationsRequests.deleteLocation(id);
			LogManager.info("Executing after test method of editLocation, removing location created");
	
		} 
		catch (UnsupportedOperationException | IOException e) {
			LogManager.info("Failed to execute after test method of editLocation, location created was not removed");
			e.printStackTrace();
		}
	}
}
