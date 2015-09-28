package org.rm.automation.tablet.tests.meetings;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.rm.automation.tablet.pageobjects.LoginPage;
import org.rm.automation.tablet.pageobjects.homepage.AvailablePanel;
import org.rm.automation.tablet.pageobjects.homepage.HomePage;
import org.rm.automation.tablet.pageobjects.homepage.SchedulePanel;
import org.rm.automation.tablet.pageobjects.meetings.MeetingsPage;
import org.rm.automation.tablet.pageobjects.meetings.TimeLinePanel;
import org.rm.automation.tablet.preconditions.homepage.PostContidionHomePageTC;
import org.rm.automation.tablet.preconditions.homepage.PreConditionHomePageTC;
import org.rm.automation.tablet.preconditions.meetings.PreConditionMeetings;
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
 */
public class VerifySubjectAfterDragMeetingIconImage extends TestBaseSetup {
	// Page objects for this test
	private LoginPage login;
	private HomePage homePage;
	private SchedulePanel schedulePanel;
	private MeetingsPage meetingsPage;
	private TimeLinePanel timeLinePanel;
	
	// Room properties
	private String roomName;
	private String userPassword;
	
	// Meeting properties
	private String meetingTitle = "meetingTitle";
	private String meetingId;
	
	// Results
	private String expectedResult;
	private String actualResult;
	
	// Error message.
	private String errorMessage = " The Subject is different than we exepected.";
	
 	@BeforeClass
 	public void setup(){
		roomName = PreConditionMeetings.getRoomName();
		meetingId = PreConditionMeetings.createPastMeeting(roomName, meetingTitle);
		userPassword = PreConditionMeetings.getUserPassword();
 	}
 	
 	@Test
 	public void verifySubjectAfterDragMeetingIconImage(){
 		login = new LoginPage(driver);
 		homePage = login.access(roomName);
 		schedulePanel = new SchedulePanel(homePage.getDriver());
 		meetingsPage = schedulePanel.clickOnMainPanel();
 		timeLinePanel = new TimeLinePanel(meetingsPage.getDriver());
 		timeLinePanel.dragMeetingIconImage(meetingTitle);
 		
 		expectedResult = timeLinePanel.getSubjectText();
 		
		timeLinePanel.confirmMeeting();
		timeLinePanel.confirmUser(userPassword);
		timeLinePanel.saveMeeting();
 		
		actualResult = timeLinePanel.getSubjectText();

		Assert.assertEquals(actualResult, expectedResult, errorMessage);
 	}
 	
 	@AfterClass
 	public void tearDown(){
 		PostContidionHomePageTC.deleteMeeting(meetingId, roomName);
 	}
}