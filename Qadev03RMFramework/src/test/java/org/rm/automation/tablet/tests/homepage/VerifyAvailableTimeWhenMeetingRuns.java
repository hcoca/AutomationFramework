package org.rm.automation.tablet.tests.homepage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.rm.automation.tablet.pageobjects.LoginPage;
import org.rm.automation.tablet.pageobjects.homepage.AvailablePanel;
import org.rm.automation.tablet.pageobjects.homepage.HomePage;
import org.rm.automation.tablet.preconditions.homepage.PreConditionHomePageTC;
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
		roomName = PreConditionHomePageTC.GetRoomName();
		meetingId = PreConditionHomePageTC.CreateCurrentMeeting();
		meetingEndTime = MeetingManager.getMeetingEndTimeFormated();
 	}
 	
 	@Test
 	public void verifyAvailableTimeWhenMeetingRuns(){
 		String errorMessage = " The time expected is different that we exepected.";
 		login = new LoginPage(driver);
 		homePage = login.access(roomName);
 		availablePanel = new AvailablePanel(homePage.getDriver());
 		availablePanel.waitForMainBusyPanel();
 		
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