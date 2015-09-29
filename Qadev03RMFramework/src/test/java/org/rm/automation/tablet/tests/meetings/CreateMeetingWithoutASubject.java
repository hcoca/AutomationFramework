package org.rm.automation.tablet.tests.meetings;

import org.rm.automation.tablet.conditions.meetings.PreConditionMeetings;
import org.rm.automation.tablet.pageobjects.LoginPage;
import org.rm.automation.tablet.pageobjects.homepage.HomePage;
import org.rm.automation.tablet.pageobjects.meetings.*;

import java.io.IOException;

import org.rm.automation.utils.TestBaseSetup;
import org.testng.Assert;
import org.testng.annotations.*;


public class CreateMeetingWithoutASubject extends TestBaseSetup {
	private LoginPage loginPage;
	private HomePage homePage;
	private MeetingsPage meetingsPage;
	private String roomName;
	@BeforeMethod
 	public void setup() throws UnsupportedOperationException, IOException{
		roomName = PreConditionMeetings.getRoomName();
 	}
	@Test
	public void createMeetingWithoutASubject(){
		String organizer = "Administrator";
		String message = "Verifying an error mesage is displayed when a meeting is created without a subject";
		
		loginPage = new LoginPage(driver);
 		homePage = loginPage.access(roomName);
 		meetingsPage = homePage.selectSchedulePage();
 		meetingsPage.setOrganizer(organizer)
					.confirmMeeting();
 		Assert.assertTrue(meetingsPage.verifyErrorSubjectMessage(), message);
	}
}
