package org.rm.automation.tablet.tests.schedule;

import java.util.Properties;

import org.rm.automation.tablet.conditions.homepage.PostContidionHomePageTC;
import org.rm.automation.tablet.conditions.meetings.PreConditionMeetings;
import org.rm.automation.tablet.pageobjects.LoginPage;
import org.rm.automation.tablet.pageobjects.homepage.HomePage;
import org.rm.automation.tablet.pageobjects.meetings.MeetingsPage;
import org.rm.automation.utils.ReadPropertyValues;
import org.rm.automation.utils.TestBaseSetup;
import org.testng.annotations.*;

import junit.framework.Assert;

public class RemoveAttendeeFromMeeting extends TestBaseSetup{

	//Test case properties
	private Properties settings = ReadPropertyValues
			.getPropertyFile("./Config/settings.properties");
	private String password = settings.getProperty("password");
	private String meetingTitle = "Removing attendee";
	private String meetingId;
	private String roomName;
	private String[] attendees = {"user001@atxrm.com"};
	private String errorMessage = "The attendee "+attendees[0]+" has not been removed";
	
	//Page objects
	private LoginPage login;
	private HomePage home;
	private MeetingsPage meetings;
	
	@BeforeClass
 	public void setup(){
		roomName = PreConditionMeetings.getRoomName();
		meetingId = PreConditionMeetings.CreateMeetingInAfternoon(roomName, meetingTitle, attendees);		
 	} 
	
	@Test
	public void removeAttendee(){			
		login = new LoginPage(driver);
		home = login.access(roomName);
		meetings = home.selectSchedulePage();
		
		meetings.selectMeeting();
		meetings.removeAttendee();
		meetings.confirmMeeting();
		meetings.confirmUser(password);
		meetings.saveMeeting();
		
		Assert.assertTrue(errorMessage,meetings.attendeeRemoved().isEmpty());;
	}
	
	@AfterMethod
	public void DeleteMeeting(){
		PostContidionHomePageTC.deleteMeeting(meetingId, roomName);		
	}
}
