package org.rm.automation.tablet.tests.meetings;

import org.rm.automation.tablet.pageobjects.LoginPage;
import org.rm.automation.tablet.pageobjects.homepage.HomePage;
import org.rm.automation.tablet.pageobjects.meetings.*;
import org.rm.automation.utils.ReadPropertyValues;
import org.rm.automation.utils.api.ConferenceRoomsRequests;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import org.json.simple.JSONObject;
import org.rm.automation.utils.TestBaseSetup;
import org.testng.Assert;
import org.testng.annotations.*;


public class VerifyCurrentDateInFrom extends TestBaseSetup {
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
	private Date myDate = new Date();
	private String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(myDate);
	
	@BeforeMethod
 	public void setup() throws UnsupportedOperationException, IOException{
		ArrayList<JSONObject> allRooms = ConferenceRoomsRequests.getRooms();
		roomName = allRooms.get(0).get("displayName").toString();
		serviceURL = "http://" + server + ":" + port + "/";
 	}
	@Test
	public void testVerifyCurrentDateInFromField(){
		String message = "Verifying value in From field is the current date";
		
		loginPage = new LoginPage(driver);
 		homePage = loginPage.access(serviceURL, userName, password, roomName);
 		meetingsPage = homePage.selectSchedulePage();
 		Assert.assertEquals(meetingsPage.verifyCurrentDateInFromField(), currentDate, message);
	}
}

