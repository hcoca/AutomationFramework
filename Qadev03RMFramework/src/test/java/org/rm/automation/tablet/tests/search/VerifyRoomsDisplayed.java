package org.rm.automation.tablet.tests.search;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.json.simple.JSONObject;
import org.openqa.selenium.WebElement;
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

public class VerifyRoomsDisplayed extends TestBaseSetup {
	Properties settings = ReadPropertyValues
			.getPropertyFile("./Config/settings.properties");
	private String username = settings.getProperty("username");
	private String password = settings.getProperty("password");
	private String url = "http://" + settings.getProperty("server") + ":" +
							settings.getProperty("port");
	private String roomName;
	
	private LoginPage loginPage;
	private HomePage homePage;
	private SearchPage searchPage;
	
	private String messageFormat = "Expected <%s> but found <%s>";
	private String messageError;
	private List<String> roomListExpected;//login
	private List<WebElement> roomListActual;//search
	
	@BeforeMethod
	public void Preconditions() throws UnsupportedOperationException, IOException
	{
		LogManager.info("VerifyRoomsDisplayed: Executing Precondition, getting all the rooms");
		ArrayList<JSONObject> list = ConferenceRoomsRequests.getRooms();
		roomName = list.get(0).get("customDisplayName").toString();
	}

	@Test
	public void testVerifyRoomsDisplayed()
	{
		LogManager.info("VerifyRoomsDisplayed: Executing Test Case");

		loginPage = new LoginPage(driver);
		homePage = loginPage.access(url, username, password, roomName);
		searchPage = homePage.selectSearchPage();
		
		roomListExpected = searchPage.getRoomListLogin();
		roomListActual = searchPage.getRoomListSearch();
		
		messageError = String.format(messageFormat, roomListExpected.size(), roomListActual.size());
		Assert.assertEquals(roomListActual.size(), roomListExpected.size(), messageError);
		
		String roomNameExpected, roomNameActual;
		for(int i = 0; i < roomListActual.size(); i++)
		{
			roomNameExpected = roomListExpected.get(i);
			roomNameActual = roomListActual.get(i).getText();
			messageError = String.format(messageFormat, roomNameExpected, roomNameActual);
			
			Assert.assertEquals(roomNameActual, roomNameExpected, messageError);
		}
	}
}
