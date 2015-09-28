package org.rm.automation.admin.tests.conferenceRooms;


import java.util.Properties;

import org.json.simple.JSONObject;
import org.rm.automation.admin.pageobjects.HomePage;
import org.rm.automation.admin.pageobjects.LoginPage;
import org.rm.automation.admin.pageobjects.conferenceRooms.ConferenceRoomsPage;
import org.rm.automation.admin.pageobjects.conferenceRooms.RoomInfoPage;
import org.rm.automation.utils.LogManager;
import org.rm.automation.utils.ReadPropertyValues;
import org.rm.automation.utils.TestBaseSetup;
import org.rm.automation.utils.api.ConferenceRoomsRequests;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


/**
 * @author Pedro David Fuentes Antezana.
 * 
 * This test case is to verify that the room status can be set to enabled
 * state using the GUI. 
 */
public class VerifyRoomStateEnabled extends TestBaseSetup{
	private Properties settings = ReadPropertyValues
			.getPropertyFile("./Config/settings.properties");
	private String userName = settings.getProperty("username");
	private String password = settings.getProperty("password");
	
	private LoginPage loginPage;
	private HomePage homePage;
	private ConferenceRoomsPage conferenceRoom;
	private RoomInfoPage roomInfo;

	private String roomId;
	private String roomEnabled;
	private String roomName;

	private boolean actualJSONResult;
	private boolean actualResult;
	
 	@BeforeClass
 	public void setup(){
 		JSONObject room = ConferenceRoomsRequests.getRooms().get(0);
 		LogManager.info("VerifyRoomStateEnabled: Executing Precondition, getting a room");
		roomId = room.get("_id").toString();
		roomEnabled = room.get("enabled").toString();
		roomName = room.get("displayName").toString();
		if(roomEnabled.equals("true")){
			ConferenceRoomsRequests.setValue(roomId, "enabled", "false");
		}
 	}
	
	@Test
	public void verifyRoomStateEnabled(){
		LogManager.info("VerifyRoomStateEnabled: Executing Test Case");
		
		loginPage = new LoginPage(driver);
		homePage = loginPage.SignIn(userName, password);
		conferenceRoom = homePage.SelectRoomsOption();
		roomInfo = conferenceRoom.doubleClickConferenceRoom(roomName);
		roomInfo = roomInfo.clickPowerOffBtn();
		conferenceRoom = roomInfo.clickSaveBtn();

		actualJSONResult = (boolean)ConferenceRoomsRequests.getRoom(roomId).get("enabled");
		AssertJUnit.assertTrue(actualJSONResult);
		
		actualResult = conferenceRoom.isEnabledRoom(roomName);
		AssertJUnit.assertTrue(actualResult);
	}
	
	@AfterClass
	public void tearDown(){
		ConferenceRoomsRequests.setValue(roomId, "enabled", roomEnabled);
		LogManager.info("VerifyRoomStateEnabled: Executing Postcondition, updating room state to its original value");
	}
}
