package org.rm.automation.tablet.tests.meetings;
import org.rm.automation.tablet.conditions.meetings.PreConditionMeetings;
import org.rm.automation.tablet.pageobjects.LoginPage;
import org.rm.automation.tablet.pageobjects.homepage.HomePage;
import org.rm.automation.tablet.pageobjects.meetings.*;
import org.rm.automation.utils.RoomManagerTime;


import org.rm.automation.utils.TestBaseSetup;
import org.testng.Assert;
import org.testng.annotations.*;
public class VerifyFromLoweThanTo extends TestBaseSetup {
	private LoginPage loginPage;
	private HomePage homePage;
	private MeetingsPage meetingsPage;
	private String roomName;
	private String organizer = "Administrator";
	private String subject = "New Meeting";
	private String startTime = RoomManagerTime.addMinutesToDate(10);
	private String endTime = RoomManagerTime.addMinutesToDate(5);
	@BeforeMethod
 	public void setup(){
		roomName = PreConditionMeetings.getRoomName();				
 	}
	@Test
	public void testVerifyFromLoweThanTo(){
		String message = "Verifying an error message is displayed when a meeting is created with From Time higher than To Time value";
		loginPage = new LoginPage(driver);
 		homePage = loginPage.access(roomName);
 		meetingsPage = homePage.selectSchedulePage();
 		meetingsPage.setOrganizer(organizer)
 					.setSubject(subject)
 					.setDates(startTime, endTime)
					.confirmMeeting();
 		Assert.assertTrue(meetingsPage.verifyErrorFromHigherThanToMessage(), message);

	}
}
