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

/**
 * @author luiscachi
 *VerifytheStarTimeNextPanel
 */
public class VerifytheStarTimeNextPanel extends TestBaseSetup {

	private LoginPage login;
	private HomePage homepage;
	private NextHomePage nextHomePage;
	private String roomName;

	private String meetingTitle = "meetingTitle";
	private String starTimeSpect = RoomManagerTime.addminutesCurrentTime(15);
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
			LogManager.info("VerifytheStarTimeNextPanel: Executing Precondition, creating a meeting");
		} catch (ParseException e) {
			LogManager.error("VerifytheStarTimeNextPanel: ParseException - " + e.toString());
			e.printStackTrace();
		}
	}
	
	@Test
	public void test(){
		login = new LoginPage(driver);
 		homepage = login.access(roomName);
 		nextHomePage = new NextHomePage(homepage.getDriver());
 		String actual = nextHomePage.getTimeNextStar();
 		System.out.println("actual===?"+actual);
 		System.out.println(starTimeSpect);
 		try {
			Assert.assertEquals(actual, starTimeSpect);
		} catch (Throwable t) {
			LogManager.error("VerifytheStarTimeNextPanel assert is fail: "+t.toString());
		}
	}
	
 	@AfterClass
 	public void tearDown(){
 		try {
			MeetingsRequests.deleteMeeting(meetingId, roomName);
			LogManager.info("VerifytheStarTimeNextPanel: Executing Postcondition, removing meeting");
		} catch (UnsupportedOperationException e) {
			LogManager.error("VerifytheStarTimeNextPanel: UnsupportedOperationException - " + e.toString());
			e.printStackTrace();
		} catch (IOException e) {
			LogManager.error("VerifytheStarTimeNextPanel: IOException - " + e.toString());
			e.printStackTrace();
		} catch (ParseException e) {
			LogManager.error("VerifytheStarTimeNextPanel: ParseException - " + e.toString());
			e.printStackTrace();
		}
 	}
}