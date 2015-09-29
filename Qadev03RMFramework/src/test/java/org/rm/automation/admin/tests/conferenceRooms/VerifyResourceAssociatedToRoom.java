package org.rm.automation.admin.tests.conferenceRooms;

import java.util.Properties;

import org.rm.automation.admin.pageobjects.HomePage;
import org.rm.automation.admin.pageobjects.LoginPage;
import org.rm.automation.admin.pageobjects.conferenceRooms.ConferenceRoomsPage;
import org.rm.automation.admin.pageobjects.conferenceRooms.ResourceAssociationPage;
import org.rm.automation.admin.pageobjects.conferenceRooms.RoomInfoPage;
import org.rm.automation.tablet.conditions.conferenceRooms.PostConditionConferenceRooms;
import org.rm.automation.tablet.conditions.conferenceRooms.PreConditionConferenceRooms;
import org.rm.automation.utils.LogManager;
import org.rm.automation.utils.ReadPropertyValues;
import org.rm.automation.utils.StringGenerator;
import org.rm.automation.utils.TestBaseSetup;
import org.rm.automation.utils.api.ConferenceRoomsRequests;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
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
	
	private String commonName = StringGenerator.getString();
	private String resourceName = commonName;
	private String resourceCustomName = commonName;
	private String resourceDescription = StringGenerator.getString();
	private String resourceIcon = "fa fa-gift";
	
	private String roomId;
	private String roomName;
	private String resourceId;
	
	private boolean actualJSONResult;
	private boolean actualResult;

	@BeforeClass
	public void setUp(){
		LogManager.info("VerifyResourceAssociatedToRoom: Executing Precondition, getting a room");
		roomId = PreConditionConferenceRooms.getRoomId();
		roomName = PreConditionConferenceRooms.getRoomName();

		LogManager.info("VerifyResourceAssociatedToRoom: Executing Precondition, creating a resource");
		resourceId = PreConditionConferenceRooms.postResource(resourceName, resourceCustomName, resourceIcon, resourceDescription);
	}
	
	@Test
	public void verifyResourceAssociatedToRoom(){
		
		loginPage = new LoginPage(driver);
		homePage = loginPage.SignIn(userName, password);
		conferenceRoom = homePage.SelectRoomsOption();
		roomInfo = conferenceRoom.doubleClickConferenceRoom(roomName);
		resourceAssociation = roomInfo.clickResourceAssociationBtn();
		resourceAssociation = resourceAssociation.associateResource(resourceName);
		conferenceRoom = resourceAssociation.clickSaveButton();
		conferenceRoom = conferenceRoom.clickOnResource(resourceName);
		
		actualJSONResult = ConferenceRoomsRequests.getResourceIdAssociatedToRoom(roomId).contains(resourceId);
		AssertJUnit.assertTrue(actualJSONResult);
		
		actualResult = conferenceRoom.isAssociatedToResource(resourceName, roomName);
		AssertJUnit.assertTrue(actualResult);
	}
	
	@AfterClass
	public void tearDown(){
		LogManager.info("VerifyResourceAssociatedToRoom: Executing Postcondition, removing resource");
		PostConditionConferenceRooms.deleteResource(resourceId);
	}
}
