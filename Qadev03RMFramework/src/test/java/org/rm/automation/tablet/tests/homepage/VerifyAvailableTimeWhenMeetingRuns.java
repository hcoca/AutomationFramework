package org.rm.automation.tablet.tests.homepage;


import org.rm.automation.tablet.conditions.homepage.PostContidionHomePageTC;
import org.rm.automation.tablet.conditions.homepage.PreConditionHomePageTC;
import org.rm.automation.tablet.pageobjects.LoginPage;
import org.rm.automation.tablet.pageobjects.homepage.AvailablePanel;
import org.rm.automation.tablet.pageobjects.homepage.HomePage;
import org.rm.automation.utils.MeetingManager;
import org.rm.automation.utils.TestBaseSetup;
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
	
	// Error message.
	private String errorMessage = " The time expected is different that we exepected.";
	
 	@BeforeClass
 	public void setup(){
		roomName = PreConditionHomePageTC.getRoomName();
		meetingId = PreConditionHomePageTC.createCurrentMeeting(roomName, meetingTitle, behindMinute, aheadMinute);
		meetingEndTime = MeetingManager.getMeetingEndTimeFormated();
 	}
 	
 	@Test
 	public void verifyAvailableTimeWhenMeetingRuns(){
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
 		PostContidionHomePageTC.deleteMeeting(meetingId, roomName);
 	}
}