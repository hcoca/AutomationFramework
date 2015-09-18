package org.rm.automation.admin.tests.locations;
import java.io.IOException;
import java.util.Properties;
import org.rm.automation.utils.LogManager;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.rm.automation.base.TestBaseSetup;
import org.rm.automation.utils.ReadPropertyValues;
import org.rm.automation.utils.api.ConferenceRoomsRequests;
import org.rm.automation.utils.api.LocationsRequests;
import org.rm.automation.admin.pageobjects.HomePage;
import org.rm.automation.admin.pageobjects.LoginPage;
import org.rm.automation.admin.pageobjects.locations.RemoveLocationsPage;
import org.rm.automation.admin.pageobjects.locations.IssuesPage;
import org.rm.automation.admin.pageobjects.locations.LocationsPage;
public class removeLocationAssociated extends TestBaseSetup{
	private Properties settings = ReadPropertyValues
			.getPropertyFile("./Config/settings.properties");
	private LoginPage loginPage;
	private HomePage homePage;
	private LocationsPage locationsPage;
	private IssuesPage issuesPage;
	private RemoveLocationsPage removeLocationsPage;
	String userName = settings.getProperty("username");
	String password = settings.getProperty("password");
	String name = "LocationAssociatedToRemove";
	String displayName = "LocAssociatedToRemove";
	String expectedTotal = "Total Items: 0";
	String roomName;
	@BeforeMethod
	public void Preconditions()
	{
		System.out.println("Before Test - Edit Location");
		try {	
			LogManager.info("Executing before test method of removeLocationAssociated, creating location with a room associated");
			LocationsRequests.postLocation(name, displayName);
			String locationId = LocationsRequests.getLocationId(name);
			roomName = ConferenceRoomsRequests.getRooms().get(0).get("customDisplayName").toString();
			  
			String roomId = ConferenceRoomsRequests.getRooms().get(0).get("_id").toString();
			ConferenceRoomsRequests.setValue(roomId, "locationId", locationId);
			  
	
		} 
		catch (UnsupportedOperationException | IOException e) {
			LogManager.info("Failed to execute before test method of removeLocationAssociated, location was not created");
			e.printStackTrace();
		}
	}
	@Test
	  public void testRemoveLocation() throws Exception {
		  
		  System.out.println("Remove Location");
		  LogManager.info("Executing removeLocationAssociated Test Case");
		  loginPage = new LoginPage(driver);
		  homePage = loginPage.SignIn(userName, password);
		  locationsPage	= homePage.SelectLocationsOption();
		  removeLocationsPage = locationsPage.removeLocation(displayName);	
		  homePage = removeLocationsPage.confirmRemoveLocation(roomName)
				  						.verifyLocationWasDeleted(name);
		  issuesPage = homePage.SelectIssuesOption();
		  locationsPage = homePage.SelectLocationsOption();
		  homePage.SignOut();	 	  		
	  } 
}
