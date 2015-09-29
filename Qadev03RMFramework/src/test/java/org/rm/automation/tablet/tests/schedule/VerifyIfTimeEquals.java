package org.rm.automation.tablet.tests.schedule;

import org.rm.automation.utils.TestBaseSetup;
import org.rm.automation.tablet.pageobjects.LoginPage;
import org.rm.automation.tablet.pageobjects.homepage.HomePage;
import org.rm.automation.tablet.pageobjects.meetings.MeetingsPage;
import org.rm.automation.tablet.preconditions.meetings.PreConditionMeetings;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import junit.framework.Assert;

public class VerifyIfTimeEquals extends TestBaseSetup{

	private String roomName;
	private String errorMessage = "The current time doesnt match with the RoomManager time";
	
	private LoginPage login;
	private HomePage home;
	private MeetingsPage meetings;
	
	@BeforeClass
 	public void setup(){
		roomName = PreConditionMeetings.getRoomName();
 	} 	
	
	@Test
	public void verifyIfTheCurrentTimeEquals(){
		
		login = new LoginPage(driver);
		home = login.access(roomName);
		meetings = home.selectSchedulePage();
		
		Assert.assertTrue(errorMessage, meetings.checkTime());
	}
}
