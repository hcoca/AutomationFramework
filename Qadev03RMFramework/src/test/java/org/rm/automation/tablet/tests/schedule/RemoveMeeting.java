package org.rm.automation.tablet.tests.schedule;

import java.util.Properties;

import org.rm.automation.tablet.conditions.meetings.PreConditionMeetings;
import org.rm.automation.tablet.pageobjects.LoginPage;
import org.rm.automation.tablet.pageobjects.homepage.HomePage;
import org.rm.automation.tablet.pageobjects.meetings.MeetingsPage;
import org.rm.automation.utils.ReadPropertyValues;
import org.rm.automation.utils.TestBaseSetup;
import org.testng.annotations.*;

import junit.framework.Assert;

public class RemoveMeeting extends TestBaseSetup{

	private Properties settings = ReadPropertyValues
			.getPropertyFile("./Config/settings.properties");
	private String password = settings.getProperty("password");
	private String meetingTitle = "Remove meeting";
	private String meetingRemoved = "Meeting successfully removed";
	private String roomName;
	private String errorMessage = "The meeting has not been removed";
	
	private LoginPage login;
	private HomePage home;
	private MeetingsPage meetings;
	
	@BeforeClass
 	public void setup(){
		roomName = PreConditionMeetings.getRoomName();
		PreConditionMeetings.CreateMeetingInAfternoon(roomName, meetingTitle);		
 	} 	
	
	@Test
	public void removeMeeting(){
				
		login = new LoginPage(driver);
		home = login.access(roomName);
		meetings = home.selectSchedulePage();
		
		//Meetings page functions
		meetings.selectMeeting();
		
		//A little workaround because there's an API bug		
		meetings.updateMeeting();
		meetings.confirmUser(password);
		meetings.saveMeeting();
		home = meetings.goHomePage();
		//A little workaround because there's an API bug
		
		meetings = home.selectSchedulePage();
		meetings.selectMeeting();
		meetings.deleteMeeting();
		meetings.confirmUser(password);
		meetings.saveDeleteMeeting();	
		
		Assert.assertTrue(errorMessage, meetingRemoved.equals(meetings.meetingAdvices()));
		
	}
	
	
}
