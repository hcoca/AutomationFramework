package org.rm.automation.tablet.tests.search;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

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

public class VerifyAdvancedSearch extends TestBaseSetup {
	Properties settings = ReadPropertyValues
			.getPropertyFile("./Config/settings.properties");
	private String username = settings.getProperty("username");
	private String password = settings.getProperty("password");
	private String url = "http://" + settings.getProperty("server")+":"+
							settings.getProperty("port");
	private String roomName;
	
	private LoginPage loginPage;
	private HomePage homePage;
	private SearchPage searchPage;
	
	private String messageFormat = "Expected <%s> but found <%s>";
	private String messageError;
	private String labelExpected = "Advanced";
	private String labelActual;
	
	@BeforeMethod
	public void Preconditions() throws UnsupportedOperationException, IOException
	{
		LogManager.info("VerifyAdvancedSearch: Executing Precondition, getting all the rooms");
		ArrayList<JSONObject> list = ConferenceRoomsRequests.getRooms();
		
		roomName = list.get(0).get("customDisplayName").toString();
	}
	
	@Test
	public void testVerifyAdvancedSearch()
	{
		LogManager.info("VerifyAdvancedSearch: Executing Test Case");

		loginPage = new LoginPage(driver);
		homePage = loginPage.access(url, username, password, roomName);
		searchPage = homePage.selectSearchPage()
		.enableAdvancedSearch();
		
		labelActual = searchPage.getLabelAdvancedSearh();
		messageError = String.format(messageFormat, labelExpected, labelActual);
		Assert.assertEquals(labelActual, labelExpected, messageError);
	}
}
