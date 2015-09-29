package org.rm.automation.tablet.tests.meetings;
import org.json.simple.parser.ParseException;
import org.rm.automation.tablet.conditions.homepage.PostContidionHomePageTC;
import org.rm.automation.tablet.conditions.meetings.PreConditionMeetings;
import org.rm.automation.tablet.pageobjects.LoginPage;
import org.rm.automation.tablet.pageobjects.homepage.HomePage;
import org.rm.automation.tablet.pageobjects.meetings.*;
import org.rm.automation.utils.ReadPropertyValues;
import org.rm.automation.utils.RoomManagerTime;
import org.rm.automation.utils.api.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;


import org.rm.automation.utils.TestBaseSetup;
import org.testng.Assert;
import org.testng.annotations.*;
public class VerifyStartTimeCanBeUpdated extends TestBaseSetup{
	private LoginPage loginPage;
	private HomePage homePage;
	private MeetingsPage meetingsPage;
	private String roomName;
	private Properties settings = ReadPropertyValues
			.getPropertyFile("./Config/settings.properties");
	private String password  = settings.getProperty("passwordES");
	private String subject = "New Meeting";
	private String endTime = RoomManagerTime.addMinutesToCurrentTime(20);
	private String meetingId;
	private String newStartTime;
	private Date date;
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@BeforeMethod
 	public void setup() throws ParseException, java.text.ParseException{
		roomName = PreConditionMeetings.getRoomName();
		meetingId = PreConditionMeetings.CreateMeetingInAfternoon(roomName, subject);
		endTime = MeetingsRequests.getMeeting(subject, roomName).get("start").toString();
		endTime = endTime.replace("T", " ").replace(".000Z", "");		
		date = formatter.parse(endTime);
		newStartTime = RoomManagerTime.addMinutesToDate(date, 15);		
 	}
	@Test
	public void testVerifyStartTimeCanBeUpdated(){
		String message = "Verifying End Time value of a meeting can be updated";
		loginPage = new LoginPage(driver);
 		homePage = loginPage.access(roomName);
 		meetingsPage = homePage.selectSchedulePage();
 		meetingsPage.selectMeeting(subject)
 					.setStartTime(newStartTime)
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
