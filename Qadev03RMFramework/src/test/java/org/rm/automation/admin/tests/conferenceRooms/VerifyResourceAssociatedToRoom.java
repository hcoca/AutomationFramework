package org.rm.automation.admin.tests.conferenceRooms;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import org.json.simple.JSONObject;
import org.rm.automation.admin.pageobjects.HomePage;
import org.rm.automation.admin.pageobjects.LoginPage;
import org.rm.automation.admin.pageobjects.conferenceRooms.ConferenceRoomsPage;
import org.rm.automation.admin.pageobjects.conferenceRooms.ResourceAssociationPage;
import org.rm.automation.admin.pageobjects.conferenceRooms.RoomInfoPage;
import org.rm.automation.base.TestBaseSetup;
import org.rm.automation.utils.ReadPropertyValues;
import org.rm.automation.utils.StringGenerator;
import org.rm.automation.utils.api.ConferenceRoomsRequests;
import org.rm.automation.utils.api.ResourcesRequests;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class VerifyResourceAssociatedToRoom extends TestBaseSetup{
	private Properties settings = ReadPropertyValues
			.getPropertyFile("./Config/settings.properties");
	private String userName = settings.getProperty("username");
	private String password = settings.getProperty("password");
	
	private LoginPage loginPage;
	private HomePage homePage;
	private ConferenceRoomsPage conferenceRoom;
	private RoomInfoPage roomInfo;
	private ResourceAssociationPage resourceAssociation;
	
	private String roomId;
	private String roomName;
	private String resourceAssociatedToRoom;
	private String resourceId;
	
	private boolean actualJSONResult;
	private boolean actualResult;
	
	String name = StringGenerator.getString();
	String customName = StringGenerator.getString();
	String description = StringGenerator.getString();
	String icon = "fa-gift";

	@BeforeTest
	public void setUp() throws UnsupportedOperationException, IOException{
		JSONObject room = ConferenceRoomsRequests.getRooms().get(0);
		roomId = room.get("_id").toString();
		roomName = room.get("displayName").toString();
		
		ResourcesRequests.postResource(name, customName, icon, description);
		resourceId = ResourcesRequests.getResourceId("gift");
	}
	
	@Test
	public void verifyResourceAssociatedToRoom() throws UnsupportedOperationException, IOException{
		loginPage = new LoginPage(driver);
		homePage = loginPage.SignIn(userName, password);
		conferenceRoom = homePage.SelectRoomsOption();
		roomInfo = conferenceRoom.doubleClickConferenceRoom(roomName);
		resourceAssociation = roomInfo.clickResourceAssociationBtn();
		resourceAssociation = resourceAssociation.associateResource("gift");
		conferenceRoom = resourceAssociation.clickSaveButton();
		conferenceRoom = conferenceRoom.clickOnResource("gift");
		
		actualJSONResult = ConferenceRoomsRequests.getResourceIdAssociatedToRoom(roomId).contains(resourceId);
		AssertJUnit.assertTrue(actualJSONResult);
		
		actualResult = conferenceRoom.isAssociatedToResource("gift", roomName);
		AssertJUnit.assertTrue(actualResult);
	}
	
	@AfterTest
	public void tearDown(){
		ResourcesRequests.deleteResource(resourceId);
	}
}
