package org.rm.automation.tablet.tests.homepage;

import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.rm.automation.base.TestBaseSetup;
import org.rm.automation.tablet.pageobjects.LoginPage;
import org.rm.automation.tablet.pageobjects.homepage.HomePage;
import org.rm.automation.tablet.pageobjects.homepage.NextHomePage;
import org.rm.automation.utils.LogManager;
import org.rm.automation.utils.RoomManagerTime;
import org.rm.automation.utils.api.ConferenceRoomsRequests;
import org.rm.automation.utils.api.MeetingsRequests;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class VerifyTheOrganizerisDisplay extends TestBaseSetup  {
	

	private LoginPage login;
	private HomePage homepage;
	private NextHomePage nextHomePage;
	private String roomName;
	private String organizer;

	private String meetingTitle = "meetingTitle";
	private String startTime = RoomManagerTime.addMinutesToCurrentTime(15);
	private String endTime = RoomManagerTime.addMinutesToCurrentTime(16);
	private String meetingId;
	
	
	

	@BeforeTest
	public void beforeclass() throws UnsupportedOperationException, IOException{
		ArrayList<JSONObject> allRooms = ConferenceRoomsRequests.getRooms();
		roomName = allRooms.get(0).get("displayName").toString();
		
		try {
			MeetingsRequests.postMeeting(roomName, meetingTitle, startTime, endTime);
			meetingId = MeetingsRequests.getMeetingId(meetingTitle, roomName);
			LogManager.info("VerifyTitleOnRunningMeeting: Executing Precondition, creating a meeting");
		} catch (ParseException e) {
			LogManager.error("VerifyTitleOnRunningMeeting: ParseException - " + e.toString());
			e.printStackTrace();
		}
	}
	
	@Test
	public void test(){
		login = new LoginPage(driver);
		organizer = login.getUserLoginName();
 		homepage = login.access(roomName);
 		nextHomePage = new NextHomePage(homepage.getDriver());
 		String actual = nextHomePage.getOrganizer();
 		try {
			Assert.assertEquals(actual, organizer);
		} catch (Throwable t) {
			LogManager.error("verifyNextTitleWhenThereIsnotMeting assert is fail: "+t.toString());
		}
	}
	
 	@AfterClass
 	public void tearDown(){
 		try {
			MeetingsRequests.deleteMeeting(meetingId, roomName);
			LogManager.info("VerifyOrganizerOnRunningMeeting: Executing Postcondition, removing meeting");
		} catch (UnsupportedOperationException e) {
			LogManager.error("VerifyOrganizerOnRunningMeeting: UnsupportedOperationException - " + e.toString());
			e.printStackTrace();
		} catch (IOException e) {
			LogManager.error("VerifyOrganizerOnRunningMeeting: IOException - " + e.toString());
			e.printStackTrace();
		} catch (ParseException e) {
			LogManager.error("VerifyOrganizerOnRunningMeeting: ParseException - " + e.toString());
			e.printStackTrace();
		}
 	}
}
