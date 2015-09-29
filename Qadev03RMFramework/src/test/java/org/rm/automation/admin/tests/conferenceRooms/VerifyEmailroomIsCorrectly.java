package org.rm.automation.admin.tests.conferenceRooms;

import java.io.IOException;
import java.util.Properties;

import org.rm.automation.admin.pageobjects.HomePage;
import org.rm.automation.admin.pageobjects.LoginPage;
import org.rm.automation.admin.pageobjects.conferenceRooms.ConferenceRoomsPage;
import org.rm.automation.admin.pageobjects.conferenceRooms.OutOfOrderPlanningPage;
import org.rm.automation.admin.pageobjects.conferenceRooms.ResourceAssociationPage;
import org.rm.automation.admin.pageobjects.conferenceRooms.RoomInfoPage;
import org.rm.automation.tablet.conditions.conferenceRooms.PreConditionConferenceRooms;
import org.rm.automation.utils.LogManager;
import org.rm.automation.utils.ReadPropertyValues;
import org.rm.automation.utils.TestBaseSetup;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import junit.framework.Assert;

public class VerifyEmailroomIsCorrectly extends TestBaseSetup{
	private LoginPage login;
	private HomePage homePage;
	private ConferenceRoomsPage conferenceRoom;
	private RoomInfoPage roomInfo;
	private ResourceAssociationPage resourseasso;
	private OutOfOrderPlanningPage ooopp;

	
	// room properties 
	private String roomName;
	private String emailroom;
	
	// login properties
	private Properties settings = ReadPropertyValues
			.getPropertyFile("./Config/settings.properties");
	private String userName = settings.getProperty("username");
	private String password = settings.getProperty("password");
	
	
 	@BeforeClass
 	public void setup() throws UnsupportedOperationException, IOException{
		roomName = PreConditionConferenceRooms.getRoomName();
		emailroom = PreConditionConferenceRooms.getEmailAddress();
 	}
 	
 	@Test
 	public void testEmail(){
		login = new LoginPage(driver);
		homePage = login.SignIn(userName, password);
		conferenceRoom = homePage.SelectRoomsOption();

		roomInfo = conferenceRoom.doubleClickConferenceRoom(roomName);
		String roominfoEmail = roomInfo.getemailroom();
		try{
			Assert.assertEquals(emailroom, roominfoEmail);
		}catch(Throwable t) {
			LogManager.error("::error Assert::VerifyEmail"+t.toString());
		}
		
		resourseasso = roomInfo.clickResourceAssociationBtn();
		String resourceAEmail = resourseasso.getemailroom();
		try{
			Assert.assertEquals(emailroom, resourceAEmail);
		}catch(Throwable t) {
			LogManager.error("::error Assert::VerifyEmail"+t.toString());
		}
		
		ooopp = resourseasso.clickOutOfOrderPlanningBtn();
		String actual = ooopp.getemailroom();
		try{
			Assert.assertEquals(emailroom, actual);
		}catch(Throwable t) {
			LogManager.error("::error Assert::VerifyEmail"+t.toString());
		}
 	}
}
