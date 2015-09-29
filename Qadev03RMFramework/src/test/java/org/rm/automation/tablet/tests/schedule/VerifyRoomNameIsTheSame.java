package org.rm.automation.tablet.tests.schedule;

import org.rm.automation.utils.TestBaseSetup;
import org.rm.automation.tablet.conditions.meetings.PreConditionMeetings;
import org.rm.automation.tablet.pageobjects.LoginPage;
import org.rm.automation.tablet.pageobjects.homepage.HomePage;
import org.rm.automation.tablet.pageobjects.meetings.MeetingsPage;
import org.testng.annotations.*;

import junit.framework.Assert;

public class VerifyRoomNameIsTheSame extends TestBaseSetup{

	private String roomName;
	String errorMessage = "The current room is not the room "+roomName;
	
	private LoginPage login;
	private HomePage home;
	private MeetingsPage meetings;
	
	@BeforeClass
 	public void setup(){
		roomName = PreConditionMeetings.getRoomName();
 	}
	
	@Test
	public void verifyIfTheNameOfTheRoomIsTheSame(){
		
		login = new LoginPage(driver);
		home = login.access(roomName);
		meetings = home.selectSchedulePage();
		
		Assert.assertTrue(errorMessage,meetings.isTheRightRoom(roomName));;
	}
}
