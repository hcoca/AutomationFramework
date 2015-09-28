package org.rm.automation.tablet.tests.meetings;

import org.rm.automation.tablet.pageobjects.LoginPage;
import org.rm.automation.tablet.pageobjects.homepage.HomePage;
import org.rm.automation.tablet.pageobjects.homepage.SchedulePanel;
import org.rm.automation.tablet.pageobjects.meetings.MeetingsPage;
import org.rm.automation.tablet.pageobjects.meetings.TimeLinePanel;
import org.rm.automation.tablet.preconditions.homepage.PostContidionHomePageTC;
import org.rm.automation.tablet.preconditions.meetings.PreConditionMeetings;
import org.rm.automation.utils.TestBaseSetup;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * @author Pedro David Fuentes Antezana
 * 
 * This test case is to verify the Subject of a meeting that has already been updated by
 * dragging its icon image in the time line panel of the schedule page.   
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
		meetingId = PreConditionMeetings.CreateMeetingInAfternoon(roomName, meetingTitle);
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