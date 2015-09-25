package org.rm.automation.tablet.tests.homepage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import org.json.simple.JSONObject;
import org.rm.automation.tablet.pageobjects.LoginPage;
import org.rm.automation.tablet.pageobjects.homepage.EndOfDayPanel;
import org.rm.automation.tablet.pageobjects.homepage.HomePage;
import org.rm.automation.tablet.pageobjects.meetings.MeetingsPage;
import org.rm.automation.utils.LogManager;
import org.rm.automation.utils.ReadPropertyValues;
import org.rm.automation.utils.TestBaseSetup;
import org.rm.automation.utils.api.ConferenceRoomsRequests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * @author Pedro David Fuentes Antezana
 * 
 * This test case is to verify that after clicking on the End of day panel, it redirects to the
 * Schedule page  
 */
public class VerifyEndOfDayPanelAccessToMeetings extends TestBaseSetup {
	// Page objects for this test
	private LoginPage login;
	private HomePage homePage;
	private EndOfDayPanel endOfDayPanel;
	private MeetingsPage meetingsPage;
	
	// Custom user environment settings
		private Properties settings = ReadPropertyValues
				.getPropertyFile("./Config/settings.properties");
		private String userName = settings.getProperty("username");
		private String userPw = settings.getProperty("passwordES");
		private String server = settings.getProperty("server");
		private String port = settings.getProperty("port");
	
	// Tablet properties
	private String serviceURL;
	
	// Room properties
	private String roomName;
	
	// Results
	private String expectedResult;
	private String actualResult;
	
 	@BeforeClass
 	public void setup() throws UnsupportedOperationException, IOException{
		ArrayList<JSONObject> allRooms = ConferenceRoomsRequests.getRooms();
		roomName = allRooms.get(0).get("displayName").toString();
		serviceURL = "http://" + server + ":" + port + "/";
		LogManager.info("VerifyEndOfDayPanelAccessToMeetings: Executing Precondition, getting a default room");
 	}
 	
 	@Test
 	public void verifyAvailablePanelAccesToMeetings(){
 		LogManager.info("VerifyEndOfDayPanelAccessToMeetings: Executing Test Case");
 		
 		login = new LoginPage(driver);
 		homePage = login.access(serviceURL, userName, userPw, roomName);
 		endOfDayPanel = new EndOfDayPanel(homePage.getDriver());
 		meetingsPage = endOfDayPanel.clickOnMainPanel();
 		
 		expectedResult = "Schedule";
 		actualResult = meetingsPage.getScheduleLabelText();
 		
 		try {
 			Assert.assertEquals(actualResult, expectedResult);
		} catch (Throwable t) {
			LogManager.error("VerifyEndOfDayPanelAccessToMeetings: The assertion has failed - " + t.toString());
		}
 	}
}