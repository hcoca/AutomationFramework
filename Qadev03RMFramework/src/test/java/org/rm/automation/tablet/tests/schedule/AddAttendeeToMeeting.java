package org.rm.automation.tablet.tests.schedule;

import java.io.IOException;
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



/**
 * @author ManuelVasquez
 *
 * This Test case is to verify than an attendee can be added to an already
 * created meeting 
 */
public class AddAttendeeToMeeting extends TestBaseSetup{

	//Test Case properties
	private Properties settings = ReadPropertyValues
			.getPropertyFile("./Config/settings.properties");
	private String password = settings.getProperty("password");
	private String meetingTitle = "Adding an attendee";
	private String roomName;
	private String meetingId;
	private String attendee = "elmonito@roompro.com";
	private String errorMessage = "The attendee with the email "+attendee+" was not added correctly";
	
	//Page objects
	private LoginPage login;
	private HomePage home;
	private MeetingsPage meetings;
		
	@BeforeClass
 	public void setup() throws UnsupportedOperationException, IOException{
		roomName = PreConditionMeetings.getRoomName();
		meetingId = PreConditionMeetings.CreateMeetingInAfternoon(roomName, meetingTitle);		
 	}
	
	@Test
	public void addAttendee(){		
		
		login = new LoginPage(driver);
		home = login.access(roomName);
		meetings = home.selectSchedulePage();
		
		meetings.selectMeeting();
		meetings.setAtendees(attendee);
		meetings.confirmMeeting();		
		meetings.confirmUser(password);
		meetings.saveMeeting();		
		
		Assert.assertTrue(errorMessage, meetings.isAttendeeAdded(attendee));
	}
	
	@AfterMethod
	public void DeleteMeeting(){
		PostContidionHomePageTC.deleteMeeting(meetingId, roomName);		
	}
} 