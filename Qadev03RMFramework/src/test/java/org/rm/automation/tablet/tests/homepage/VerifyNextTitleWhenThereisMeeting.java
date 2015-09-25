package org.rm.automation.tablet.tests.homepage;

import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.rm.automation.tablet.pageobjects.LoginPage;
import org.rm.automation.tablet.pageobjects.homepage.HomePage;
import org.rm.automation.tablet.pageobjects.homepage.NextHomePanel;
import org.rm.automation.utils.LogManager;
import org.rm.automation.utils.RoomManagerTime;
import org.rm.automation.utils.TestBaseSetup;
import org.rm.automation.utils.api.ConferenceRoomsRequests;
import org.rm.automation.utils.api.MeetingsRequests;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * @author luiscachi
 *VerifyNextTitleWhenThereisMeeting
 */
public class VerifyNextTitleWhenThereisMeeting extends TestBaseSetup {

	private LoginPage login;
	private HomePage homepage;
	private NextHomePanel nextHomePage;
	private String roomName;

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
			LogManager.info("VerifyNextTitleWhenThereisMeeting: Executing Precondition, creating a meeting");
		} catch (ParseException e) {
			LogManager.error("VerifyNextTitleWhenThereisMeeting: ParseException - " + e.toString());
			e.printStackTrace();
		}
	}
	
	@Test
	public void test(){
		login = new LoginPage(driver);
 		homepage = login.access(roomName);
 		nextHomePage = new NextHomePanel(homepage.getDriver());
 		String actual = nextHomePage.getTitleNext();
 		try {
			Assert.assertEquals(actual, meetingTitle);
		} catch (Throwable t) {
			LogManager.error("VerifyNextTitleWhenThereisMeeting assert is fail: "+t.toString());
		}
	}
	
 	@AfterClass
 	public void tearDown(){
 		try {
			MeetingsRequests.deleteMeeting(meetingId, roomName);
			LogManager.info("VerifyNextTitleWhenThereisMeeting: Executing Postcondition, removing meeting");
		} catch (UnsupportedOperationException e) {
			LogManager.error("VerifyNextTitleWhenThereisMeeting: UnsupportedOperationException - " + e.toString());
			e.printStackTrace();
		} catch (IOException e) {
			LogManager.error("VerifyNextTitleWhenThereisMeeting: IOException - " + e.toString());
			e.printStackTrace();
		} catch (ParseException e) {
			LogManager.error("VerifyNextTitleWhenThereisMeeting: ParseException - " + e.toString());
			e.printStackTrace();
		}
 	}
}