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
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class VerifyAllFieldsAreClear extends TestBaseSetup{
	Properties settings = ReadPropertyValues
			.getPropertyFile("./Config/settings.properties");
	private String username = settings.getProperty("username");
	private String password = settings.getProperty("password");
	private String url = "http://" + settings.getProperty("server")+":"+
							settings.getProperty("port");
	private String roomNameRandom = StringGenerator.getString();
	private String roomName;
	private String locationName = "<All>";
	private Random random = new Random();
	private String capacity =String.valueOf(random.nextInt(50));
	private int position;
	
	private LoginPage loginPage;
	private HomePage homePage;
	private SearchPage searchPage;
	
	@BeforeMethod
	public void Preconditions() throws UnsupportedOperationException, IOException
	{
		LogManager.info("VerifyClearButton: Executing Precondition, "
				+ "getting all rooms");
		ArrayList<JSONObject> list = ConferenceRoomsRequests.getRooms();
		position = random.nextInt(list.size());
		
		roomName = list.get(position).get("customDisplayName").toString();
	}
	
	@Test
	public void testVerifyAllFieldsAreClear()
	{
		LogManager.info("VerifyClearButton: Executing Test Case");

		loginPage = new LoginPage(driver);
		homePage = loginPage.access(url, username, password, roomName);
		searchPage = homePage.selectSearchPage()
		.enableAdvancedSearch()
		.setRoomName(roomNameRandom)
		.setLocation(locationName)
		.setCapacity(capacity)
		.clickClearButton()
		.verifyFieldsAreEmpty();
	}
}
