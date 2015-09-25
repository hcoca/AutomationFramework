package org.rm.automation.tablet.tests.meetings;

import org.rm.automation.tablet.pageobjects.LoginPage;
import org.rm.automation.tablet.pageobjects.homepage.HomePage;
import org.rm.automation.tablet.pageobjects.meetings.*;
import org.rm.automation.utils.LogManager;
import org.rm.automation.utils.ReadPropertyValues;
import org.rm.automation.utils.api.ConferenceRoomsRequests;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import org.json.simple.JSONObject;
import org.rm.automation.utils.TestBaseSetup;
import org.testng.Assert;
import org.testng.annotations.*;


public class CreateMeetingWithoutASubject extends TestBaseSetup {
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
	@BeforeMethod
 	public void setup() throws UnsupportedOperationException, IOException{
		ArrayList<JSONObject> allRooms = ConferenceRoomsRequests.getRooms();
		roomName = allRooms.get(0).get("displayName").toString();
		serviceURL = "http://" + server + ":" + port + "/";
 	}
	@Test
	public void createMeetingWithoutASubject(){
		LogManager.info("Executing: Create a new meeting without a subject test case");
		String organizer = "Administrator";
		String atendees = "elver@atxrm.com";
		
		loginPage = new LoginPage(driver);
 		homePage = loginPage.access(serviceURL, userName, password, roomName);
 		meetingsPage = homePage.selectSchedulePage();
 		meetingsPage.setOrganizer(organizer)
					.setAtendees(atendees)
					.confirmMeeting();
 		Assert.assertTrue(meetingsPage.verifyErrorSubjectMessage());
	}
}
