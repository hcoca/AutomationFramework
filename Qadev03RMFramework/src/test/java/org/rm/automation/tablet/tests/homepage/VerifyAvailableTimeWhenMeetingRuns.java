package org.rm.automation.tablet.tests.homepage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.rm.automation.tablet.pageobjects.LoginPage;
import org.rm.automation.tablet.pageobjects.homepage.AvailablePanel;
import org.rm.automation.tablet.pageobjects.homepage.HomePage;
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
 * This test case is to verify that after a meeting has been created, the "starting available time" 
 * displayed is correct.
 * 
 * Description: The "starting available time" should be the end time of an already running meeting.  
 */
public class VerifyAvailableTimeWhenMeetingRuns extends TestBaseSetup {
	// Page objects for this test
	private LoginPage login;
	private HomePage homePage;
	private AvailablePanel availablePanel;
	
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
	private String meetingEndTime;
	
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
			meetingEndTime = MeetingManager.getMeetingEndTimeFormated();
			LogManager.info("VerifyAvailableTimeWhenMeetingRuns: Executing Precondition, creating a meeting");
		} catch (ParseException e) {
			LogManager.error("VerifyAvailableTimeWhenMeetingRuns: ParseException - " + e.toString());
			e.printStackTrace();
		}
 	}
 	
 	@Test
 	public void verifyAvailableTimeWhenMeetingRuns(){
 		LogManager.info("VerifyAvailableTimeWhenMeetingRuns: Executing Test Case");
 		String errorMessage = " The time expected is different that we exepected.";
 		login = new LoginPage(driver);
 		homePage = login.access(serviceURL, userName, userPw, roomName);
 		availablePanel = new AvailablePanel(homePage.getDriver());
 		availablePanel.waitForMainBusyPanel(); // Check if it can goes in the constructor
 		
 		expectedResult = meetingEndTime;
 		actualResult = availablePanel.getStartAvailableTimeLabelText();
 		

		Assert.assertEquals(actualResult, expectedResult, errorMessage);

 	}
 	
 	@AfterClass
 	public void tearDown(){
 		try {
			MeetingsRequests.deleteMeeting(meetingId, roomName);
			LogManager.info("VerifyAvailableTimeWhenMeetingRuns: Executing Postcondition, removing meeting");
		} catch (UnsupportedOperationException e) {
			LogManager.error("VerifyAvailableTimeWhenMeetingRuns: UnsupportedOperationException - " + e.toString());
			e.printStackTrace();
		} catch (IOException e) {
			LogManager.error("VerifyAvailableTimeWhenMeetingRuns: IOException - " + e.toString());
			e.printStackTrace();
		} catch (ParseException e) {
			LogManager.error("VerifyAvailableTimeWhenMeetingRuns: ParseException - " + e.toString());
			e.printStackTrace();
		}
 	}
}