package org.rm.automation.admin.tests.locations;

import java.io.IOException;
import java.util.Properties;
import org.rm.automation.utils.LogManager;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.rm.automation.utils.ReadPropertyValues;
import org.rm.automation.utils.TestBaseSetup;
import org.rm.automation.utils.api.LocationsRequests;
import org.rm.automation.admin.pageobjects.HomePage;
import org.rm.automation.admin.pageobjects.LoginPage;
import org.rm.automation.admin.pageobjects.locations.RemoveLocationsPage;
import org.rm.automation.admin.pageobjects.locations.IssuesPage;
import org.rm.automation.admin.pageobjects.locations.LocationsPage;

public class removeLocation extends TestBaseSetup {
	private Properties settings = ReadPropertyValues
			.getPropertyFile("./Config/settings.properties");
	private LoginPage loginPage;
	private HomePage homePage;
	private LocationsPage locationsPage;
	private IssuesPage issuesPage;
	private RemoveLocationsPage removeLocationsPage;
	String userName = settings.getProperty("username");
	String password = settings.getProperty("password");
	String name = "LocationToRemove";
	String displayName = "LocToRemove";
	String expectedTotal = "Total Items: 0";
	@BeforeMethod
	public void Preconditions()
	{
		System.out.println("Before Test - Remove Location");
		try {			
			LocationsRequests.postLocation(name, displayName);
			LogManager.info("Executing before test method of removeLocation, creating location");
	
		} 
		catch (UnsupportedOperationException | IOException e) {
			LogManager.info("Failed to execute before test method of removeLocation, location was not created");
			e.printStackTrace();
		}
	}
	@Test
	  public void testRemoveLocation() throws Exception {
		  
		  System.out.println("Remove Location");
		  LogManager.info("Executing removeLocation Test Case");
		  loginPage = new LoginPage(driver);
		  homePage = loginPage.SignIn(userName, password);
		  locationsPage	= homePage.SelectLocationsOption();
		  removeLocationsPage = locationsPage.removeLocation(displayName);	
		  homePage = removeLocationsPage.confirmRemoveLocation()
				  						.verifyLocationWasDeleted(name);
		  issuesPage = homePage.SelectIssuesOption();
		  locationsPage = homePage.SelectLocationsOption();
		  homePage.SignOut();	 	  		
	  } 

}
