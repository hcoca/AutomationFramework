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
import org.rm.automation.utils.api.ResourcesRequests;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SearchByResource extends TestBaseSetup {
	Properties settings = ReadPropertyValues
			.getPropertyFile("./Config/settings.properties");
	private String username = settings.getProperty("username");
	private String password = settings.getProperty("password");
	private String url = "http://" + settings.getProperty("server")+":"+
							settings.getProperty("port");
	private String roomName;
	private String roomId;
	private String resourceName = StringGenerator.getString();
	private String description = StringGenerator.getString();
	private String icon = "fa fa-gift";
	private String resourceId;
	private String quantity;
	private int position;
	private Random random = new Random();
	
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
		LogManager.info("SearchByResource: Executing Precondition, "
				+ "assigning a resource to a conference room");
		
		ArrayList<JSONObject> list = ConferenceRoomsRequests.getRooms();
		position = random.nextInt(list.size());
		
		roomName = list.get(position).get("customDisplayName").toString();
		roomId = list.get(position).get("_id").toString();
		
		ResourcesRequests.postResource(resourceName, resourceName, icon, description);
		resourceId = ResourcesRequests.getResourceId(resourceName);
		quantity = String.valueOf(random.nextInt(20));
		
		ConferenceRoomsRequests.setResourceInRoom(roomId, resourceId, quantity);
		roomNameExpected = roomName;
	}
	
	@Test
	public void testSearchByResource()
	{
		LogManager.info("SearchByResource: Executing Test Case");

		loginPage = new LoginPage(driver);
		homePage = loginPage.access(url, username, password, roomName);
		searchPage = homePage.selectSearchPage()
		.selectResource(resourceName);
		
		roomNameActual = searchPage.getSearchRoomName();
		messageError = String.format(messageFormat, roomNameExpected, roomNameActual);
		Assert.assertEquals(roomNameActual, roomNameExpected, messageError);
	}
	
	@AfterMethod
	public void Postconditions()
	{
		LogManager.info("SearchByResource: Executing Postcondition, "
				+ "removing the resource created");
		ResourcesRequests.deleteResource(resourceId);
	}
}
