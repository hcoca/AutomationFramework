package org.rm.automation.tablet.tests.homepage;

import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.rm.automation.tablet.pageobjects.LoginPage;
import org.rm.automation.tablet.pageobjects.homepage.HomePage;
import org.rm.automation.tablet.pageobjects.homepage.NextHomePanel;
import org.rm.automation.tablet.preconditions.homepage.PostContidionHomePageTC;
import org.rm.automation.tablet.preconditions.homepage.PreConditionHomePageTC;
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
public class VerifytheStarTimeNextPanel extends TestBaseSetup {

	private LoginPage login;
	private HomePage homepage;
	private NextHomePanel nextHomePage;
	private String roomName;

	private String meetingTitle = "meetingTitle";
	private String starTimeSpect = RoomManagerTime.addminutesCurrentTime(15);
	private String startTime = RoomManagerTime.addMinutesToCurrentTime(15);
	private String endTime = RoomManagerTime.addMinutesToCurrentTime(16);
	private String meetingId;
	

	@BeforeTest
	public void beforeclass() throws UnsupportedOperationException, IOException{
		roomName = PreConditionHomePageTC.getRoomName();
		meetingId = PreConditionHomePageTC.createCurrentMeeting(meetingTitle, startTime, endTime);
	}
	
	@Test
	public void verifytheStarTimeNextPanel(){
		login = new LoginPage(driver);
 		homepage = login.access(roomName);
 		nextHomePage = new NextHomePanel(homepage.getDriver());
 		String actual = nextHomePage.getTimeNextStar();

		Assert.assertEquals(actual, starTimeSpect);

	}
	
 	@AfterClass
 	public void tearDown(){
 		PostContidionHomePageTC.deleteMeeting(meetingId, roomName);
 	}
}