package org.rm.automation.tablet.tests.homepage;


import java.io.IOException;

import org.rm.automation.tablet.pageobjects.LoginPage;
import org.rm.automation.tablet.pageobjects.homepage.HomePage;
import org.rm.automation.tablet.pageobjects.homepage.NextHomePanel;
import org.rm.automation.tablet.preconditions.homepage.PostContidionHomePageTC;
import org.rm.automation.tablet.preconditions.homepage.PreConditionHomePageTC;
import org.rm.automation.utils.RoomManagerTime;
import org.rm.automation.utils.TestBaseSetup;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * @author luiscachi
 * VerifytheStarTimeNextPanel
 */
public class VerifyEndTimeNextPanel extends TestBaseSetup {

	private LoginPage login;
	private HomePage homepage;
	private NextHomePanel nextHomePage;
	private String roomName;

	private String meetingTitle = "meetingTitle";
	private String endTimeSpect = RoomManagerTime.addminutesCurrentTime(17);
	private String startTime = RoomManagerTime.addMinutesToCurrentTime(15);
	private String endTime = RoomManagerTime.addMinutesToCurrentTime(17);
	private String meetingId;
	

	@BeforeClass
	public void beforeclass() throws UnsupportedOperationException, IOException{
		roomName = PreConditionHomePageTC.getRoomName();
		meetingId = PreConditionHomePageTC.setupMeeting(roomName,meetingTitle, startTime, endTime);
			
	}
	
	@Test
	public void test(){
		login = new LoginPage(driver);
 		homepage = login.access(roomName);
 		nextHomePage = new NextHomePanel(homepage.getDriver());
 		String actual = nextHomePage.getTimeNextEnd();

		Assert.assertEquals(actual, endTimeSpect);

	}
	
 	@AfterClass
 	public void tearDown(){
 		PostContidionHomePageTC.deleteMeeting(meetingId, roomName);
 	}
}