package org.rm.automation.tablet.tests.meetings;
import org.rm.automation.tablet.pageobjects.LoginPage;
import org.rm.automation.tablet.pageobjects.homepage.HomePage;
import org.rm.automation.tablet.pageobjects.meetings.*;
import org.rm.automation.utils.LogManager;
import org.rm.automation.utils.ReadPropertyValues;
import org.rm.automation.utils.RoomManagerTime;
import org.rm.automation.utils.api.*;


import java.util.ArrayList;
import java.util.Properties;

import org.json.simple.JSONObject;
import org.rm.automation.utils.TestBaseSetup;
import org.testng.Assert;
import org.testng.annotations.*;
public class VerifyFromLoweThanTo extends TestBaseSetup {
	private LoginPage loginPage;
	private HomePage homePage;
	private MeetingsPage meetingsPage;
	private String serviceURL;
	private String roomName;
	private Properties settings = ReadPropertyValues
			.getPropertyFile("./Config/settings.properties");
	private String userName = settings.getProperty("username");
	private String password  = settings.getProperty("passwordES");
	private String server = settings.getProperty("server");
	private String port = settings.getProperty("port");
	String subject = "New Meeting";
	
	private String startTime = RoomManagerTime.addMinutesToDate(10);
	private String endTime = RoomManagerTime.addMinutesToDate(5);
	@BeforeMethod
 	public void setup(){
		try{
			ArrayList<JSONObject> allRooms = ConferenceRoomsRequests.getRooms();
			roomName = allRooms.get(0).get("displayName").toString();
			serviceURL = "http://" + server + ":" + port + "/";
			MeetingsRequests.postMeeting(roomName, subject, startTime, endTime);
		}
		catch(Exception e){
			e.printStackTrace();
		}		
 	}
	@Test
	public void testVerifyFromLoweThanTo(){
		String organizer = "Administrator";
		String subject = "PageObjects Meeting";
		LogManager.info("Executing: Verify From time is lowe than To time test case");
		loginPage = new LoginPage(driver);
 		homePage = loginPage.access(serviceURL, userName, password, roomName);
 		meetingsPage = homePage.selectSchedulePage();
 		meetingsPage.setOrganizer(organizer)
 					.setSubject(subject)
 					.setDates(startTime, endTime)
					.confirmMeeting();
 		Assert.assertTrue(meetingsPage.verifyErrorFromHigherThanToMessage());

	}
}
