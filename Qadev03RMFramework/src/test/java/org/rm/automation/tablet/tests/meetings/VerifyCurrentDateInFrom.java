package org.rm.automation.tablet.tests.meetings;

import org.rm.automation.tablet.pageobjects.LoginPage;
import org.rm.automation.tablet.pageobjects.homepage.HomePage;
import org.rm.automation.tablet.pageobjects.meetings.*;
import org.rm.automation.tablet.preconditions.meetings.PreConditionMeetings;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.rm.automation.utils.TestBaseSetup;
import org.testng.Assert;
import org.testng.annotations.*;


public class VerifyCurrentDateInFrom extends TestBaseSetup {
	private LoginPage loginPage;
	private HomePage homePage;
	private MeetingsPage meetingsPage;
	private String roomName;
	
	private Date myDate = new Date();
	private String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(myDate);
	
	@BeforeMethod
 	public void setup() throws UnsupportedOperationException, IOException{
		roomName = PreConditionMeetings.getRoomName();
	}
	@Test
	public void testVerifyCurrentDateInFromField(){
		String message = "Verifying value in From field is the current date";
		
		loginPage = new LoginPage(driver);
 		homePage = loginPage.access(roomName);
 		meetingsPage = homePage.selectSchedulePage();
 		Assert.assertEquals(meetingsPage.verifyCurrentDateInFromField(), currentDate, message);
	}
}

