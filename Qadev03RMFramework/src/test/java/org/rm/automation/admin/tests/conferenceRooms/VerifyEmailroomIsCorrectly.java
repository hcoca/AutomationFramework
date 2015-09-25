package org.rm.automation.admin.tests.conferenceRooms;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import org.bouncycastle.asn1.cmp.OOBCertHash;
import org.json.simple.JSONObject;
import org.rm.automation.admin.pageobjects.HomePage;
import org.rm.automation.admin.pageobjects.LoginPage;
import org.rm.automation.admin.pageobjects.conferenceRooms.ConferenceRoomsPage;
import org.rm.automation.admin.pageobjects.conferenceRooms.OutOfOrderPlanningPage;
import org.rm.automation.admin.pageobjects.conferenceRooms.ResourceAssociationPage;
import org.rm.automation.admin.pageobjects.conferenceRooms.RoomInfoPage;
import org.rm.automation.utils.LogManager;
import org.rm.automation.utils.ReadPropertyValues;
import org.rm.automation.utils.TestBaseSetup;
import org.rm.automation.utils.api.ConferenceRoomsRequests;
import org.seleniumhq.jetty7.util.log.Log;
import org.testng.annotations.BeforeTest;
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
	
	
 	@BeforeTest
 	public void setup() throws UnsupportedOperationException, IOException{
		ArrayList<JSONObject> allRooms = ConferenceRoomsRequests.getRooms();
		roomName = allRooms.get(0).get("displayName").toString();
		emailroom = allRooms.get(0).get("emailAddress").toString();
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
