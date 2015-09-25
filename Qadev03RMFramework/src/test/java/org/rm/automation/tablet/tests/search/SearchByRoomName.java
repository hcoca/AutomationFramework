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
import org.rm.automation.utils.TestBaseSetup;
import org.rm.automation.utils.api.ConferenceRoomsRequests;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SearchByRoomName extends TestBaseSetup {
	Properties settings = ReadPropertyValues
			.getPropertyFile("./Config/settings.properties");
	private String username = settings.getProperty("username");
	private String password = settings.getProperty("password");
	private String url = "http://" + settings.getProperty("server")+":"+
							settings.getProperty("port");
	
	private String roomName;
	private Random random = new Random();
	private int position;
	
	private LoginPage loginPage;
	private HomePage homePage;
	private SearchPage searchPage;
	
	private String messageFormat = "Expected <%s> but found <%s>";
	private String messageError;
	private String roomNameExpected;
	private String roomNameActual;
	
	@BeforeMethod
	public void Preconditions() throws UnsupportedOperationException, IOException
	{
		LogManager.info("SearchByRoomName: Executing Precondition, getting all rooms");
		
		ArrayList<JSONObject> list = ConferenceRoomsRequests.getRooms();
		position = random.nextInt(list.size());
		roomName = list.get(position).get("customDisplayName").toString();
		roomNameExpected = roomName;
	}
	
	@Test
	public void testSearchMeetingByRoom()
	{
		LogManager.info("SearchByRoomName: Executing Test Case");

		loginPage = new LoginPage(driver);
		homePage = loginPage.access(url, username, password, roomName);
		searchPage = homePage.selectSearchPage()
		.enableAdvancedSearch()
		.setRoomName(roomName);
		
		roomNameActual = searchPage.getSearchRoomName();
		messageError = String.format(messageFormat, roomNameExpected, roomNameActual);
		Assert.assertEquals(roomNameActual, roomNameExpected, messageError);
	}
}
