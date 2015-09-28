package org.rm.automation.tablet.tests.search;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Random;

import org.json.simple.JSONObject;
import org.rm.automation.tablet.pageobjects.LoginPage;
import org.rm.automation.tablet.pageobjects.homepage.HomePage;
import org.rm.automation.tablet.pageobjects.search.SearchPage;
import org.rm.automation.utils.LogManager;
import org.rm.automation.utils.ReadPropertyValues;
import org.rm.automation.utils.StringGenerator;
import org.rm.automation.utils.TestBaseSetup;
import org.rm.automation.utils.api.ConferenceRoomsRequests;
import org.rm.automation.utils.api.LocationsRequests;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class VerifyLocationIsClear extends TestBaseSetup {
	Properties settings = ReadPropertyValues
			.getPropertyFile("./Config/settings.properties");
	private String username = settings.getProperty("username");
	private String password = settings.getProperty("password");
	private String url = "http://" + settings.getProperty("server")+":"+
							settings.getProperty("port");
	private String roomName;
	private String locationName = StringGenerator.getString();
	private String locationId;
	private int position;
	private Random random = new Random();
	
	private LoginPage loginPage;
	private HomePage homePage;
	private SearchPage searchPage;

	private String messageFormat = "Expected <%s> but found <%s>";
	private String messageError;
	private String locationExpected = "";
	private String locationActual;
	
	@BeforeMethod
	public void Preconditions() throws UnsupportedOperationException, IOException
	{
		LogManager.info("VerifyLocationIsClear: Executing Precondition, "
				+ "assigning a resource to a conference room");
		
		ArrayList<JSONObject> list = ConferenceRoomsRequests.getRooms();
		position = random.nextInt(list.size());
		
		roomName = list.get(position).get("customDisplayName").toString();
		
		LocationsRequests.postLocation(locationName, locationName);
		locationId = LocationsRequests.getLocationId(locationName);
	}
	
	@Test
	public void testVerifyLocationIsClear()
	{
		LogManager.info("VerifyLocationIsClear: Executing Test Case");

		loginPage = new LoginPage(driver);
		homePage = loginPage.access(url, username, password, roomName);
		searchPage = homePage.selectSearchPage()
				.enableAdvancedSearch()
				.setLocation(locationName)
				.clickClearButton();
		
		locationActual = searchPage.getLocation();
		messageError = String.format(messageFormat, locationExpected, locationActual);
		Assert.assertEquals(locationActual, locationExpected, messageError);
	}
	
	@AfterMethod
	public void Postconditions() throws UnsupportedOperationException, IOException
	{
		LogManager.info("VerifyLocationIsClear: Executing Postcondition, "
				+ "removing the location assigned");
		LocationsRequests.deleteLocation(locationId);
	}
}
