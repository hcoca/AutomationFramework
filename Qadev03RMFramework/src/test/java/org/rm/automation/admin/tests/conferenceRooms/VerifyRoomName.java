package org.rm.automation.admin.tests.conferenceRooms;

import java.util.Properties;

import org.json.simple.JSONObject;
import org.rm.automation.admin.pageobjects.HomePage;
import org.rm.automation.admin.pageobjects.LoginPage;
import org.rm.automation.admin.pageobjects.conferenceRooms.ConferenceRoomsPage;
import org.rm.automation.utils.LogManager;
import org.rm.automation.utils.ReadPropertyValues;
import org.rm.automation.utils.TestBaseSetup;
import org.rm.automation.utils.api.ConferenceRoomsRequests;
import org.testng.AssertJUnit;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


/**
 * @author Pedro David Fuentes Antezana.
 * 
 * This test case is to verify that the room selected through GUI exists and 
 * is the same as the one retrieved by API.	
 */
public class VerifyRoomName extends TestBaseSetup{
	private Properties settings = ReadPropertyValues
			.getPropertyFile("./Config/settings.properties");
	private String userName = settings.getProperty("username");
	private String password = settings.getProperty("password");
	
	private LoginPage loginPage;
	private HomePage homePage;
	private ConferenceRoomsPage conferenceRoom;

	private String roomName;
	
	private boolean actualResult;
	
 	@BeforeTest
 	public void setup(){
 		JSONObject room = ConferenceRoomsRequests.getRooms().get(0);
 		LogManager.info("VerifyRoomName: Executing Precondition, getting a room");
		roomName =  room.get("displayName").toString();
 	}
	
	@Test(priority = 2)
	public void verifyRoomName(){
		LogManager.info("VerifyRoomName: Executing Test Case");
		
		loginPage = new LoginPage(driver);
		homePage = loginPage.SignIn(userName, password);
		conferenceRoom = homePage.SelectRoomsOption();
		
		actualResult = conferenceRoom.isValidRoom(roomName);
		
		AssertJUnit.assertTrue(actualResult);
	}
}
