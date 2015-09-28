package org.rm.automation.tablet.tests.meetings;
import org.rm.automation.tablet.pageobjects.LoginPage;
import org.rm.automation.tablet.pageobjects.homepage.HomePage;
import org.rm.automation.tablet.pageobjects.meetings.*;
import org.rm.automation.tablet.preconditions.homepage.PostContidionHomePageTC;
import org.rm.automation.tablet.preconditions.meetings.PreConditionMeetings;
import org.rm.automation.utils.ReadPropertyValues;

import java.util.Properties;

import org.rm.automation.utils.TestBaseSetup;
import org.testng.Assert;
import org.testng.annotations.*;

public class VerifySubjectCanBeUpdated extends TestBaseSetup {
	private LoginPage loginPage;
	private HomePage homePage;
	private MeetingsPage meetingsPage;
	private String roomName;
	private Properties settings = ReadPropertyValues
			.getPropertyFile("./Config/settings.properties");
	private String password  = settings.getProperty("passwordES");
	String subject = "New Meeting";
	String newSubject = "Meeting Updated";
	private String meetingId;
	
	@BeforeMethod
 	public void setup(){
		roomName = PreConditionMeetings.getRoomName();
		meetingId = PreConditionMeetings.CreateMeetingInAfternoon(roomName, subject);
				
 	}
	@Test
	public void testVerifySubjectCanBeUpdated(){
		String message = "Verifying Subject of a meeting can be updated";
		
		loginPage = new LoginPage(driver);
 		homePage = loginPage.access(roomName);
 		meetingsPage = homePage.selectSchedulePage();
 		meetingsPage.selectMeeting(subject)
 					.setSubject(newSubject)
 					.updateMeeting()
 					.confirmUser(password)
 					.saveMeeting();
 		Assert.assertTrue(meetingsPage.verifyMeetingWasUpdated(), message);

	}
	@AfterMethod 
	public void deleteMeeting(){
		PostContidionHomePageTC.deleteMeeting(meetingId, roomName);
	}

}
