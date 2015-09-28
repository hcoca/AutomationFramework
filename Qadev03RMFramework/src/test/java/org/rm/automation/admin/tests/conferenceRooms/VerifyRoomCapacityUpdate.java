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
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


/**
 * @author Pedro David Fuentes Antezana.
 * 
 * This test case is to verify that the room capacity selected through GUI 
 * can be modified. 
 */
public class VerifyRoomCapacityUpdate extends TestBaseSetup{
	private Properties settings = ReadPropertyValues
			.getPropertyFile("./Config/settings.properties");
	private String userName = settings.getProperty("username");
	private String password = settings.getProperty("password");
	
	private LoginPage loginPage;
	private HomePage homePage;
	private ConferenceRoomsPage conferenceRoom;
	private RoomInfoPage roomInfo;

	private String roomId;
	private String roomCapacity;
	private String roomName;
	private String updatedCapacity = "571";
	
	private String expectedResult;
	private String actualJSONResult;
	private boolean actualResult;
	
 	@BeforeTest
 	public void setup(){
		JSONObject room = ConferenceRoomsRequests.getRooms().get(0);
		LogManager.info("VerifyRoomCapacityUpdate: Executing Precondition, getting a room");
		roomId = room.get("_id").toString();
		// This if-else should be re factorized.
		if(room.get("capacity") != null){
			roomCapacity = room.get("capacity").toString();
		}else{
			roomCapacity = null;
		}
		roomName = room.get("displayName").toString();
 	}
	
	@Test(priority = 2)
	public void verifyRoomCapacityUpdate(){
		LogManager.info("VerifyRoomCapacityUpdate: Executing Test Case");
		
		loginPage = new LoginPage(driver);
		homePage = loginPage.SignIn(userName, password);
		conferenceRoom = homePage.SelectRoomsOption();
		roomInfo = conferenceRoom.doubleClickConferenceRoom(roomName);
		roomInfo = roomInfo.setCapacity(updatedCapacity);
		conferenceRoom = roomInfo.clickSaveBtn();
		
		expectedResult = updatedCapacity;
		actualJSONResult = ConferenceRoomsRequests.getRoom(roomId).get("capacity").toString();
		AssertJUnit.assertEquals(expectedResult, actualJSONResult);
		
		actualResult = conferenceRoom.isCapacityUpdated(updatedCapacity, roomName);
		AssertJUnit.assertTrue(actualResult);
	}
	
	@AfterTest
	public void tearDown(){
		ConferenceRoomsRequests.setValue(roomId, "capacity", roomCapacity);
		LogManager.info("VerifyRoomCapacityUpdate: Executing Postcondition, updating capacity to its original value");
	}
}