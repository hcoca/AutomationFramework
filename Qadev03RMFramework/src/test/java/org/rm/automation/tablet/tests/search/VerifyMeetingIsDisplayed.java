package org.rm.automation.tablet.tests.search;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Random;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.rm.automation.tablet.pageobjects.LoginPage;
import org.rm.automation.tablet.pageobjects.homepage.HomePage;
import org.rm.automation.tablet.pageobjects.search.SearchPage;
import org.rm.automation.utils.LogManager;
import org.rm.automation.utils.ReadPropertyValues;
import org.rm.automation.utils.RoomManagerTime;
import org.rm.automation.utils.StringGenerator;
import org.rm.automation.utils.TestBaseSetup;
import org.rm.automation.utils.api.ConferenceRoomsRequests;
import org.rm.automation.utils.api.MeetingsRequests;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class VerifyMeetingIsDisplayed extends TestBaseSetup{
	Properties settings = ReadPropertyValues
			.getPropertyFile("./Config/settings.properties");
	private String username = settings.getProperty("username");
	private String password = settings.getProperty("password");
	private String url = "http://" + settings.getProperty("server")+":"+
							settings.getProperty("port");
	private String roomName;
	private String meetingName = StringGenerator.getString();
	private String startTime = RoomManagerTime.substractMinutesToCurrentTime(1);
	private String endTime = RoomManagerTime.addMinutesToCurrentTime(3);
	private int position;
	private Random random = new Random();
	
	private LoginPage loginPage;
	private HomePage homePage;
	private SearchPage searchPage;
	
	private String messageFormat = "Expected <%s> but found <%s>";
	private String messageError;
	private String meetingExpected;
	private String meetingActual;
	
	@BeforeMethod
	public void Preconditions() throws UnsupportedOperationException, IOException, ParseException
	{
		LogManager.info("VerifyMeetingIsDisplayed: Executing Precondition, "
				+ "creating a meeting");
		
		ArrayList<JSONObject> list = ConferenceRoomsRequests.getRooms();
		position = random.nextInt(list.size());
		
		roomName = list.get(position).get("customDisplayName").toString();
		
		MeetingsRequests.postMeeting(roomName, meetingName, startTime, endTime);
		meetingExpected = meetingName;
	}
	
	@Test
	public void testVerifyMeetingIsDisplayed()
	{
		LogManager.info("VerifyMeetingIsDisplayed: Executing Test Case");

		loginPage = new LoginPage(driver);
		homePage = loginPage.access(url, username, password, roomName);
		searchPage = homePage.selectSearchPage();
		
		meetingActual = searchPage.getMeetingTitle();
		messageError = String.format(messageFormat, meetingExpected, meetingActual);
		Assert.assertEquals(meetingActual, meetingExpected, messageError);
	}
	
	@AfterMethod
	public void Postconditions() throws ParseException, UnsupportedOperationException, IOException
	{
		LogManager.info("VerifyMeetingIsDisplayed: Executing Postcondition, "
				+ "removing meeting");
		String id = MeetingsRequests.getMeetingId(meetingName, roomName);
		MeetingsRequests.deleteMeeting(id, roomName);
	}
}
