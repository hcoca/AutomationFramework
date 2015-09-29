package org.rm.automation.admin.tests.conferenceRooms;


import java.util.Properties;

import org.rm.automation.admin.conditions.conferenceRooms.PostConditionConferenceRooms;
import org.rm.automation.admin.conditions.conferenceRooms.PreConditionConferenceRooms;
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
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


/**
 * @author Pedro David Fuentes Antezana.
 * 
 * This test case is to verify that the room code selected through GUI 
 * can be modified. 
 */
public class VerifyRoomCodeUpdate extends TestBaseSetup{
	private Properties settings = ReadPropertyValues
			.getPropertyFile("./Config/settings.properties");
	private String userName = settings.getProperty("username");
	private String password = settings.getProperty("password");
	
	private LoginPage loginPage;
	private HomePage homePage;
	private ConferenceRoomsPage conferenceRoom;
	private RoomInfoPage roomInfo;

	private String roomId;
	private String roomCode;
	private String roomName;
	private String updatedCode = "UPDATED_571_CODE";
	
	private String expectedResult;
	private String actualJSONResult;
	private boolean actualResult;
	
 	@BeforeClass
 	public void setup(){
 		LogManager.info("VerifyRoomCodeUpdate: Executing Precondition, getting a room");
		roomId = PreConditionConferenceRooms.getRoomId();
		roomCode = PreConditionConferenceRooms.getRoomCode();
		roomName = PreConditionConferenceRooms.getRoomName();
 	}
	
	@Test
	public void verifyRoomCodeUpdate(){
		LogManager.info("VerifyRoomCodeUpdate: Executing Test Case");
		
		loginPage = new LoginPage(driver);
		homePage = loginPage.SignIn(userName, password);
		conferenceRoom = homePage.SelectRoomsOption();
		roomInfo = conferenceRoom.doubleClickConferenceRoom(roomName);
		roomInfo = roomInfo.setCode(updatedCode);
		conferenceRoom = roomInfo.clickSaveBtn();
		
		expectedResult = updatedCode;
		actualJSONResult = ConferenceRoomsRequests.getRoom(roomId).get("code").toString();
		AssertJUnit.assertEquals(expectedResult, actualJSONResult);
		
		actualResult = conferenceRoom.isCodeUpdated(updatedCode, roomName);
		AssertJUnit.assertTrue(actualResult);
	}
	
	@AfterTest
	public void tearDown(){
		LogManager.info("VerifyRoomCodeUpdate: Executing Postcondition, updating code to its original value");
		PostConditionConferenceRooms.setConferenceRoomCode(roomId, "code", roomCode);
	}
}