package org.rm.automation.admin.tests.conferenceRooms;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import org.json.simple.JSONObject;
import org.rm.automation.admin.pageobjects.HomePage;
import org.rm.automation.admin.pageobjects.LoginPage;
import org.rm.automation.admin.pageobjects.conferenceRooms.ConferenceRoomsPage;
import org.rm.automation.admin.pageobjects.conferenceRooms.RoomInfoPage;
import org.rm.automation.base.TestBaseSetup;
import org.rm.automation.utils.ReadPropertyValues;
import org.rm.automation.utils.api.ConferenceRoomsRequests;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
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
	
 	@BeforeTest
 	public void setup() throws UnsupportedOperationException, IOException{
		ArrayList<JSONObject> allRooms = ConferenceRoomsRequests.getRooms();
		roomId = allRooms.get(0).get("_id").toString();
		roomCode = allRooms.get(0).get("code").toString();
		roomName = allRooms.get(0).get("displayName").toString();
 	}
	
	@Test(priority = 2)
	public void verifyRoomCodeUpdate() throws UnsupportedOperationException, IOException{
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
	public void tearDown() throws UnsupportedOperationException, IOException{
		ConferenceRoomsRequests.setValue(roomId, "code", roomCode);
	}
}