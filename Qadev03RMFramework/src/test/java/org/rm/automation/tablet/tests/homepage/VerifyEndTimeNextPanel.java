package org.rm.automation.tablet.tests.homepage;


import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.rm.automation.tablet.pageobjects.LoginPage;
import org.rm.automation.tablet.pageobjects.homepage.HomePage;
import org.rm.automation.tablet.pageobjects.homepage.NextHomePage;
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
 *VerifytheStarTimeNextPanel
 */
public class VerifyEndTimeNextPanel extends TestBaseSetup {

	private LoginPage login;
	private HomePage homepage;
	private NextHomePage nextHomePage;
	private String roomName;

	private String meetingTitle = "meetingTitle";
	private String endTimeSpect = RoomManagerTime.addminutesCurrentTime(17);
	private String startTime = RoomManagerTime.addMinutesToCurrentTime(15);
	private String endTime = RoomManagerTime.addMinutesToCurrentTime(17);
	private String meetingId;
	

	@BeforeTest
	public void beforeclass() throws UnsupportedOperationException, IOException{
		ArrayList<JSONObject> allRooms = ConferenceRoomsRequests.getRooms();
		roomName = allRooms.get(0).get("displayName").toString();
		
		try {
			MeetingsRequests.postMeeting(roomName, meetingTitle, startTime, endTime);
			meetingId = MeetingsRequests.getMeetingId(meetingTitle, roomName);
			LogManager.info("VerifyEndTimeNextPanel: Executing Precondition, creating a meeting");
		} catch (ParseException e) {
			LogManager.error("VerifyEndTimeNextPanel: ParseException - " + e.toString());
			e.printStackTrace();
		}
	}
	
	@Test
	public void test(){
		login = new LoginPage(driver);
 		homepage = login.access(roomName);
 		nextHomePage = new NextHomePage(homepage.getDriver());
 		String actual = nextHomePage.getTimeNextEnd();

		Assert.assertEquals(actual, endTimeSpect);

	}
	
 	@AfterClass
 	public void tearDown(){
 		try {
			MeetingsRequests.deleteMeeting(meetingId, roomName);
			LogManager.info("VerifyEndTimeNextPanel: Executing Postcondition, removing meeting");
		} catch (UnsupportedOperationException e) {
			LogManager.error("VerifyEndTimeNextPanel: UnsupportedOperationException - " + e.toString());
			e.printStackTrace();
		} catch (IOException e) {
			LogManager.error("VerifyEndTimeNextPanel: IOException - " + e.toString());
			e.printStackTrace();
		} catch (ParseException e) {
			LogManager.error("VerifyEndTimeNextPanel: ParseException - " + e.toString());
			e.printStackTrace();
		}
 	}
}