package org.rm.automation.tablet.tests.commonPages;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import org.json.simple.JSONObject;
import org.rm.automation.utils.TestBaseSetup;
import org.rm.automation.tablet.pageobjects.LoginPage;
import org.rm.automation.tablet.pageobjects.homepage.HomePage;
import org.rm.automation.tablet.pageobjects.meetings.MeetingsPage;
import org.rm.automation.tablet.pageobjects.search.SearchPage;
import org.rm.automation.utils.LogManager;
import org.rm.automation.utils.ReadPropertyValues;
import org.rm.automation.utils.api.ConferenceRoomsRequests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import junit.framework.Assert;

public class VerifyIfSearchButtonRedirectsToSearchPage extends TestBaseSetup{
		private Properties settings = ReadPropertyValues
				.getPropertyFile("./Config/settings.properties");
		private String username = settings.getProperty("username");	
		private String password = settings.getProperty("password");
		private String server = settings.getProperty("server");
		private String port = settings.getProperty("port");
		private String url = "http://"+server+":"+port;
		private String roomName;
		
		@BeforeClass
	 	public void setup() throws UnsupportedOperationException, IOException{
			ArrayList<JSONObject> allRooms = ConferenceRoomsRequests.getRooms();
			roomName = allRooms.get(0).get("displayName").toString();
	 	}
		
		@Test
		public void verifyIfTheIconRedirectsToSchedulePage(){
			LogManager.info("VerifyIfSearchButtonRedirectsToSearchPage: Executing test case verifyIfTheIconRedirectsToSchedulePage");
			String errorMessage = "The Schedule button is not redirecting to the Schedule page";
			LoginPage login = new LoginPage(driver);
			HomePage home = login.access(url, username, password, roomName);
			MeetingsPage schedule = home.selectSchedulePage();		
			SearchPage search = schedule.goSearchPage();
			
			Assert.assertTrue(errorMessage, search.checkIfIconRedirectsToSearchPage());
		}
}
