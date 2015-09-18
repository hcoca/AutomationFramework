package org.rm.automation.admin.tests.locations;

import java.io.IOException;
import java.util.Properties;
import org.rm.automation.utils.LogManager;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.rm.automation.base.TestBaseSetup;
import org.rm.automation.utils.ReadPropertyValues;
import org.rm.automation.utils.api.ConferenceRoomsRequests;
import org.rm.automation.utils.api.LocationsRequests;
import org.rm.automation.admin.pageobjects.HomePage;
import org.rm.automation.admin.pageobjects.LoginPage;
import org.rm.automation.admin.pageobjects.locations.FormAssociateRoomPage;
import org.rm.automation.admin.pageobjects.locations.FormLocationPage;
import org.rm.automation.admin.pageobjects.locations.IssuesPage;
import org.rm.automation.admin.pageobjects.locations.LocationsPage;
public class associateRoomWithLocation extends TestBaseSetup {
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
	String name = "New Location";
	String displayName = "NewLoc";
	String confRooms = "x1"; 
	@BeforeMethod
	public void Preconditions()
	{
		System.out.println("Before Test - Associate Room With Location");
		try {			
			LocationsRequests.postLocation(name, displayName);
			LogManager.info("Executing before test method of associateRoomWithLocation, creating location");
	
		} 
		catch (UnsupportedOperationException | IOException e) {
			LogManager.info("Failed to execute before test method of associateRoomWithLocation, location was not created");
			e.printStackTrace();
		}
	}
	@Test
	  public void testAssociateRoomWithLocation() throws Exception {
		  String roomName = ConferenceRoomsRequests.getRooms().get(0).get("customDisplayName").toString();
		  System.out.println("Associate Room With Location");
		  LogManager.info("Executing associateRoomWithLocation Test Case");
		  loginPage = new LoginPage(driver);
		  homePage = loginPage.SignIn(userName, password);
		  locationsPage	= homePage.SelectLocationsOption();
		  formLocationPage = locationsPage.clickonLocation(displayName);
		  formAssociateRoomPage = formLocationPage.gotoLocationsAssociations();
		  formLocationPage = formAssociateRoomPage.associateConferenceRoom(roomName);
		  homePage = formLocationPage.fillFormAndSave();
		  issuesPage = homePage.SelectIssuesOption();
		  locationsPage = homePage.SelectLocationsOption();
		  homePage = locationsPage.verifyLocationsWasEdited(confRooms);
		  homePage.SignOut();	  	  		
	  } 
	 @AfterMethod
		public void Postconditions()
		{
			System.out.println("After Test - Associate Room With Location");
			String id = "";
			try {
				id = LocationsRequests.getLocationId(name);
				LocationsRequests.deleteLocation(id);
				LogManager.info("Executing after test method of associateRoomWithLocation, removing location created");
		
			} 
			catch (UnsupportedOperationException | IOException e) {
				LogManager.info("Failed to execute after test method of associateRoomWithLocation, location created was not removed");
				e.printStackTrace();
			}
		}

}
