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
import org.rm.automation.utils.ReadPropertyValues;
import org.rm.automation.utils.RoomManagerTime;
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
 * This test case is to verify that after a meeting has been created, the meeting title is the same as
 * the title displayed in the "now panel" of the tablet home page.  
 */
public class VerifyTitleOnRunningMeeting extends TestBaseSetup {
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
	private String startTime = RoomManagerTime.substractMinutesToCurrentTime(1);
	private String endTime = RoomManagerTime.addMinutesToCurrentTime(3);
	private String meetingId;
	
	// Results
	private String expectedResult = meetingTitle;
	private String actualResult;
	
 	@BeforeClass
 	public void setup() throws UnsupportedOperationException, IOException{
		ArrayList<JSONObject> allRooms = ConferenceRoomsRequests.getRooms();
		roomName = allRooms.get(0).get("displayName").toString();
		serviceURL = "http://" + server + ":" + port + "/";
		
		try {
			MeetingsRequests.postMeeting(roomName, meetingTitle, startTime, endTime);
			meetingId = MeetingsRequests.getMeetingId(meetingTitle, roomName);
			LogManager.info("VerifyTitleOnRunningMeeting: Executing Precondition, creating a meeting");
		} catch (ParseException e) {
			LogManager.error("VerifyTitleOnRunningMeeting: ParseException - " + e.toString());
			e.printStackTrace();
		}
 	}
 	
 	@Test
 	public void verifyTitleOnRunningMeeting(){
 		LogManager.info("VerifyTitleOnRunningMeeting: Executing Test Case");
 		
 		login = new LoginPage(driver);
 		homePage = login.access(serviceURL, userName, userPw, roomName);
 		nowPanel = new NowPanel(homePage.getDriver());
 		nowPanel.waitForMainPanel(); // Check if it can goes in the constructor
 		actualResult = nowPanel.getTitleLabelText();
 		
 		try {
 			Assert.assertEquals(actualResult, expectedResult);
		} catch (Throwable t) {
			LogManager.error("VerifyTitleOnRunningMeeting: The assertion has failed - " + t.toString());
		}
 	}
 	
 	@AfterClass
 	public void tearDown(){
 		try {
			MeetingsRequests.deleteMeeting(meetingId, roomName);
			LogManager.info("VerifyTitleOnRunningMeeting: Executing Postcondition, removing meeting");
		} catch (UnsupportedOperationException e) {
			LogManager.error("VerifyTitleOnRunningMeeting: UnsupportedOperationException - " + e.toString());
			e.printStackTrace();
		} catch (IOException e) {
			LogManager.error("VerifyTitleOnRunningMeeting: IOException - " + e.toString());
			e.printStackTrace();
		} catch (ParseException e) {
			LogManager.error("VerifyTitleOnRunningMeeting: ParseException - " + e.toString());
			e.printStackTrace();
		}
 	}
}