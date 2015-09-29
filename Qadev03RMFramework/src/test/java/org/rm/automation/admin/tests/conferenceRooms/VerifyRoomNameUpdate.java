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
import org.testng.AssertJUnit;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


/**
 * @author Pedro David Fuentes Antezana.
 * 
 * This test case is to verify that the room name selected through GUI 
 * can be modified. 
 */
public class VerifyRoomNameUpdate extends TestBaseSetup{
	private Properties settings = ReadPropertyValues
			.getPropertyFile("./Config/settings.properties");
	private String userName = settings.getProperty("username");
	private String password = settings.getProperty("password");
	
	private LoginPage loginPage;
	private HomePage homePage;
	private ConferenceRoomsPage conferenceRoom;
	private RoomInfoPage roomInfo;

	private String roomId;
	private String roomName;
	private String updatedRoomName = "UPDATED";
	
	private boolean actualResult;
	
 	@BeforeClass
 	public void setup(){
 		LogManager.info("VerifyRoomNameUpdate: Executing Precondition, getting a room");
		roomId = PreConditionConferenceRooms.getRoomId();
		roomName = PreConditionConferenceRooms.getRoomName();
 	}
	
	@Test
	public void verifyRoomNameUpdate(){
		LogManager.info("VerifyRoomNameUpdate: Executing Test Case");
		
		loginPage = new LoginPage(driver);
		homePage = loginPage.SignIn(userName, password);
		conferenceRoom = homePage.SelectRoomsOption();
		roomInfo = conferenceRoom.doubleClickConferenceRoom(roomName);
		roomInfo = roomInfo.setDisplayName(updatedRoomName);
		conferenceRoom = roomInfo.clickSaveBtn();
		
		actualResult = conferenceRoom.isValidRoom(updatedRoomName);
		
		AssertJUnit.assertTrue(actualResult);
	}
	
	@AfterClass
	public void tearDown(){
		LogManager.info("VerifyRoomNameUpdate: Executing Postcondition, updating room name to its original value");
		PostConditionConferenceRooms.setConferenceRoomName(roomId, roomName);
	}
}