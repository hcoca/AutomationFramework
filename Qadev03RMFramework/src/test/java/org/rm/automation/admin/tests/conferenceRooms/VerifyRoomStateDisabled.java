package org.rm.automation.admin.tests.conferenceRooms;

import java.io.IOException;
import java.util.ArrayList;
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
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


/**
 * @author Pedro David Fuentes Antezana.
 * 
 * This test case is to verify that the room status can be set to disabled
 * state using the GUI. 
 */
public class VerifyRoomStateDisabled extends TestBaseSetup{
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
	
 	@BeforeTest
 	public void setup(){
 		JSONObject room = ConferenceRoomsRequests.getRooms().get(0);
 		LogManager.info("VerifyRoomStateDisabled: Executing Precondition, getting a room");
		roomId = room.get("_id").toString();
		roomEnabled = room.get("enabled").toString();
		roomName = room.get("displayName").toString();
		if(roomEnabled.equals("false")){
			ConferenceRoomsRequests.setValue(roomId, "enabled", "true");
		}
 	}
	
	@Test(priority = 2)
	public void verifyRoomStateDisabled(){
		LogManager.info("VerifyRoomStateDisabled: Executing Test Case");
		
		loginPage = new LoginPage(driver);
		homePage = loginPage.SignIn(userName, password);
		conferenceRoom = homePage.SelectRoomsOption();
		roomInfo = conferenceRoom.doubleClickConferenceRoom(roomName);
		roomInfo = roomInfo.clickPowerOnBtn();
		conferenceRoom = roomInfo.clickSaveBtn();
		
		actualJSONResult = (boolean)ConferenceRoomsRequests.getRoom(roomId).get("enabled");
		AssertJUnit.assertFalse(actualJSONResult);
		
		actualResult = conferenceRoom.isEnabledRoom(roomName);
		AssertJUnit.assertFalse(actualResult);
	}
	
	@AfterTest
	public void tearDown(){
		ConferenceRoomsRequests.setValue(roomId, "enabled", roomEnabled);
		LogManager.info("VerifyRoomStateDisabled: Executing Postcondition, updating room state to its original value");
	}
}
