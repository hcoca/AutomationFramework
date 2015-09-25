package org.rm.automation.tablet.tests.homepage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.rm.automation.tablet.pageobjects.LoginPage;
import org.rm.automation.tablet.pageobjects.homepage.HomePage;
import org.rm.automation.tablet.pageobjects.homepage.NowPanel;
import org.rm.automation.tablet.preconditions.homepage.PostContidionHomePageTC;
import org.rm.automation.tablet.preconditions.homepage.PreConditionHomePageTC;
import org.rm.automation.utils.LogManager;
import org.rm.automation.utils.MeetingManager;
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
 * This test case is to verify that after a meeting has been created, the meeting remaining time 
 * is the same as the meeting remaining time displayed in the "now panel" of the tablet home page.  
 */
public class VerifyTimeRemainingOnRunningMeeting extends TestBaseSetup {
	
	// Page objects for this test
	private LoginPage login;
	private HomePage homePage;
	private NowPanel nowPanel;

	// Room properties
	private String roomName;
	
	// Meeting properties
	private String meetingTitle = "meetingTitle";
	private String startTime = RoomManagerTime.substractMinutesToCurrentTime(1);
	private String endTime = RoomManagerTime.addMinutesToCurrentTime(3);
	private String meetingId;
	private String meetingRemainingTime;
	
	// Results
	private String expectedResult;
	private String actualResult;
	
 	@BeforeClass
 	public void setup(){
 		roomName = PreConditionHomePageTC.getRoomName();
 		meetingId = PreConditionHomePageTC.createCurrentMeeting(meetingTitle, startTime, endTime);
 		meetingRemainingTime = MeetingManager.getRemainingTimeFormated();
 	}
 	
 	@Test
 	public void verifyTimeRemainingOnRunningMeeting(){
 		
 		login = new LoginPage(driver);
 		homePage = login.access(roomName);
 		nowPanel = new NowPanel(homePage.getDriver());
 		nowPanel.waitForMainPanel(); // Check if it can goes in the constructor
 		
 		expectedResult = meetingRemainingTime;
 		actualResult = nowPanel.getTimeRemainingLabel();
 		
		Assert.assertEquals(actualResult, expectedResult);

 	}
 	
 	@AfterClass
 	public void tearDown(){
 		PostContidionHomePageTC.deleteMeeting(meetingId, roomName);
 	}
}