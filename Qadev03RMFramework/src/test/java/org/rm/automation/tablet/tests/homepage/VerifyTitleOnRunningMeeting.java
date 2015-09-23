package org.rm.automation.tablet.tests.homepage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.rm.automation.base.TestBaseSetup;
import org.rm.automation.tablet.pageobjects.LoginPage;
import org.rm.automation.tablet.pageobjects.homepage.HomePage;
import org.rm.automation.tablet.pageobjects.homepage.NowPanel;
import org.rm.automation.utils.LogManager;
import org.rm.automation.utils.ReadPropertyValues;
import org.rm.automation.utils.RoomManagerTime;
import org.rm.automation.utils.api.ConferenceRoomsRequests;
import org.rm.automation.utils.api.MeetingsRequests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class VerifyTitleOnRunningMeeting extends TestBaseSetup {
	// Page object.
	private LoginPage login;
	private HomePage homePage;
	private NowPanel nowPanel;
	
	// tablet properties
	private String serviceURL;
	
	// room properties 
	private String roomName;
	
	// meeting properties
	private String meetingTitle = "meetingTitle";
	private String startTime = RoomManagerTime.substractMinutes(1);
	private String endTime = RoomManagerTime.addMinutes(3);
	
	// login properties
	private Properties settings = ReadPropertyValues
			.getPropertyFile("./Config/settings.properties");
	private String userName = settings.getProperty("username");
	private String userPw = settings.getProperty("passwordES");
	private String server = settings.getProperty("server");
	private String port = settings.getProperty("port");
	
	// results
	private String expectedResult = meetingTitle;
	private String actualResult;
	
 	@BeforeClass
 	public void setup() throws UnsupportedOperationException, IOException{
		ArrayList<JSONObject> allRooms = ConferenceRoomsRequests.getRooms();
		roomName = allRooms.get(0).get("displayName").toString();
		serviceURL = "http://" + server + ":" + port + "/";
		
		try {
			MeetingsRequests.postMeeting(roomName, meetingTitle, startTime, endTime);
		} catch (ParseException e) {
			LogManager.error(" COMMING SOON! ");
		}
 	}
 	@Test
 	public void verifyTitleOnRunningMeeting(){
 		login = new LoginPage(driver);
 		homePage = login.access(serviceURL, userName, userPw, roomName);
 		nowPanel = new NowPanel(homePage.getDriver());
 		nowPanel.waitForMainPanel(); // Check if it can goes in the constructor
 		actualResult = nowPanel.getTitleLabelText();
 		
 		try {
 			Assert.assertEquals(actualResult, expectedResult);
		} catch (Throwable t) {
			LogManager.error(" COMMING SOON! ");
		}
 	}
}