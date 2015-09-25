package org.rm.automation.tablet.tests.homepage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.rm.automation.tablet.pageobjects.LoginPage;
import org.rm.automation.tablet.pageobjects.homepage.HomePage;
import org.rm.automation.tablet.pageobjects.homepage.NowPanel;
import org.rm.automation.utils.LogManager;
import org.rm.automation.utils.MeetingManager;
import org.rm.automation.utils.ReadPropertyValues;
import org.rm.automation.utils.TestBaseSetup;
import org.rm.automation.utils.api.ConferenceRoomsRequests;
import org.rm.automation.utils.api.MeetingsRequests;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * @author Pedro David Fuentes Antezana
 * 
 * This test case is to verify that after a meeting has been created, the meeting remaining time 
 * is the same as the meeting remaining time displayed in the "now panel" of the tablet home page.  
 */
public class VerifyTimeRemainingOnRunningMeeting extends TestBaseSetup {
	// Page objects for this test
	private LoginPage login;
	private HomePage homePage;
	private NowPanel nowPanel;
	
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
	
	// Meeting properties
	private String meetingTitle = "meetingTitle";
	private int behindMinute = 1; // 1 minute before current time
	private int aheadMinute = 3; // 3 minutes ahead current time
	private String meetingId;
	private String meetingRemainingTime;
	
	// Results
	private String expectedResult;
	private String actualResult;
	
 	@BeforeClass
 	public void setup() throws UnsupportedOperationException, IOException{
		ArrayList<JSONObject> allRooms = ConferenceRoomsRequests.getRooms();
		roomName = allRooms.get(0).get("displayName").toString();
		serviceURL = "http://" + server + ":" + port + "/";
		
		try {
			MeetingManager.createRunninMeeting(roomName, meetingTitle, behindMinute, aheadMinute);
			meetingId = MeetingsRequests.getMeetingId(meetingTitle, roomName);
			meetingRemainingTime = MeetingManager.getRemainingTimeFormated();
			LogManager.info("VerifyTimeRemainingOnRunningMeeting: Executing Precondition, creating a meeting");
		} catch (ParseException e) {
			LogManager.error("VerifyTimeRemainingOnRunningMeeting: ParseException - " + e.toString());
			e.printStackTrace();
		}
 	}
 	
 	@Test
 	public void verifyTimeRemainingOnRunningMeeting(){
 		LogManager.info("VerifyTimeRemainingOnRunningMeeting: Executing Test Case");
 		
 		login = new LoginPage(driver);
 		homePage = login.access(serviceURL, userName, userPw, roomName);
 		nowPanel = new NowPanel(homePage.getDriver());
 		nowPanel.waitForMainPanel(); // Check if it can goes in the constructor
 		
 		expectedResult = meetingRemainingTime;
 		actualResult = nowPanel.getTimeRemainingLabel();
 		

		Assert.assertEquals(actualResult, expectedResult);

 	}
 	
 	@AfterClass
 	public void tearDown(){
 		try {
			MeetingsRequests.deleteMeeting(meetingId, roomName);
			LogManager.info("VerifyTimeRemainingOnRunningMeeting: Executing Postcondition, removing meeting");
		} catch (UnsupportedOperationException e) {
			LogManager.error("VerifyTimeRemainingOnRunningMeeting: UnsupportedOperationException - " + e.toString());
			e.printStackTrace();
		} catch (IOException e) {
			LogManager.error("VerifyTimeRemainingOnRunningMeeting: IOException - " + e.toString());
			e.printStackTrace();
		} catch (ParseException e) {
			LogManager.error("VerifyTimeRemainingOnRunningMeeting: ParseException - " + e.toString());
			e.printStackTrace();
		}
 	}
}